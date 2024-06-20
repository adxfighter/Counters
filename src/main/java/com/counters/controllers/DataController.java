package com.counters.controllers;

import com.counters.model.BO.CounterBO;
import com.counters.model.BO.PokazanieBO;
import com.counters.model.BO.PriceBO;
import com.counters.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.counters.controllers.ControllersUtils.getAllCountersNames;

@Controller
public class DataController {
    private static Logger logger = Logger.getLogger(CounterController.class.getName());

    @Autowired
    PokazanieBO pokazanieBO;

    @Autowired
    PriceBO priceBO;

    @Autowired
    CounterBO counterBO;

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public ModelAndView enterData() throws SQLException {

        List<String> listCountersNames = getAllCountersNames(counterBO);

        ModelAndView model = new ModelAndView("data", "command", new Pokazanie());
        model.addObject("listCountersNames", listCountersNames);

        List<Counter> counterList = counterBO.getListOfCounters();

        Map<String, Double> pokazanieMap = getLastPokazaniaMap(counterList);

        model.addObject("lastPokazaniaMap", pokazanieMap);

        return model;

    }

    private Map<String, Double> getLastPokazaniaMap(List<Counter> counterList) {
        Map<String, Double> pokazanieMap = new HashMap<>();

        counterList.stream().forEach(counter -> {
            try {
                pokazanieMap.put(counter.getCounterName(), ControllersUtils.getLastPokazanieByCounter(counter, pokazanieBO).getData());
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "ERROR: ", e);
            }
        });
        return pokazanieMap;
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView chooseCounterToGetListPokizaniaToDelete() throws SQLException {

        List<String> listCountersNames = getAllCountersNames(counterBO);

        ModelAndView model = new ModelAndView("delete", "command", new Counter());
        model.addObject("listCountersNames", listCountersNames);

        return model;

    }

    @RequestMapping(value = "/used", method = RequestMethod.GET)
    public ModelAndView chooseCounterToGetListUsed() throws SQLException {

        List<String> listCountersNames = getAllCountersNames(counterBO);

        ModelAndView model = new ModelAndView("used", "command", new Counter());
        model.addObject("listCountersNames", listCountersNames);

        return model;

    }

    @RequestMapping(value = "/addData", method = RequestMethod.POST)
    public String addData(@ModelAttribute("data") Pokazanie pokazanie, ModelMap model, HttpServletRequest request) throws SQLException, ParseException {

        String counterName = request.getParameter("selectCounter");
        Counter counter = counterBO.getCounterByName(counterName);
        model.addAttribute("counter_name", counter.getCounterName());
        model.addAttribute("data", pokazanie.getData());

        String stringDate = request.getParameter("date1");
        Date simpleDate;

        if (stringDate == null || stringDate.equals("")) {
            Calendar calendar = new GregorianCalendar();
            simpleDate = calendar.getTime();
            stringDate = simpleDate.toString();
        } else {
            DateFormat formatter = new SimpleDateFormat("d-M-yyyy");
            simpleDate = formatter.parse(stringDate);
        }

        java.sql.Date sqlDate = new java.sql.Date(simpleDate.getTime());

        model.addAttribute("date", stringDate);
        pokazanieBO.addPokazanie(new Pokazanie(counter, pokazanie.getData(), sqlDate));

        return "addData";
    }

    @RequestMapping(value = "/deleteData", method = RequestMethod.POST)
    public ModelAndView deletePokazania(HttpServletRequest request) throws SQLException {

        String counterName = request.getParameter("selectCounter");
        Counter counter = counterBO.getCounterByName(counterName);

        ModelAndView model = new ModelAndView("deleteData");
        model.addObject("counter_name", counterName);

        List<Pokazanie> pokazaniaList = pokazanieBO.getPokazaniaByCounter(counter);

        model.addObject("pokazaniaList", pokazaniaList);
        return model;

    }

    //TODO: Java8
    @RequestMapping(value = "/usedData", method = RequestMethod.GET)
    public ModelAndView seeUsed(HttpServletRequest request) throws SQLException {

        ModelAndView model = new ModelAndView("usedData");
        String counterName = request.getParameter("selectCounter");
        Counter counter = null;
        counter = counterBO.getCounterByName(counterName);

        model.addObject("counter_name", counterName);

        List<Pokazanie> pokazaniaList = pokazanieBO.getPokazaniaByCounter(counter);

        Price price;
        Double potracheno;
        Double delta;
        String period;
        Double sum;
        List<UsedDelta> usedDeltaList = new ArrayList<UsedDelta>();
        for (int i = 0; i < pokazaniaList.size() - 1; i++) {
            delta = pokazaniaList.get(i).getData() - pokazaniaList.get(i + 1).getData();
            if (delta < 0) {
                potracheno = pokazaniaList.get(i).getData();
            } else {
                potracheno = delta;
            }
            price = priceBO.getPriceByCounterAndDate(counter, pokazaniaList.get(i).getDate());
            sum = new BigDecimal(price.getPrice() * potracheno).setScale(1, RoundingMode.UP).doubleValue();
            period = pokazaniaList.get(i + 1).getDate().toString() + " - " + pokazaniaList.get(i).getDate().toString();
            usedDeltaList.add(new UsedDelta(pokazaniaList.get(i).getData(), price.getPrice(), sum, period, potracheno, pokazaniaList.get(i).getIsPaid()));
        }

        model.addObject("usedDeltaList", usedDeltaList);
        return model;
    }

    //TODO: Java8
    @RequestMapping(value = "/sum", method = RequestMethod.GET)
    public ModelAndView seeSum(HttpServletRequest request) throws SQLException {
        ModelAndView model = new ModelAndView("sum");
        Counter firstCounter = pokazanieBO.getFirstPokazanie().getCounter();
        List<Pokazanie> pokazaniaList = new ArrayList<Pokazanie>();
        pokazaniaList = pokazanieBO.getPokazaniaByCounter(firstCounter);
        Price price = null;
        Double potracheno;
        Double delta;
        String period;
        Double sum;
        Date date1, date2;
        java.sql.Date sqlDate;
        List<UsedDelta> usedDeltaList = new ArrayList<UsedDelta>();

        for (int i = 0; i < pokazaniaList.size() - 1; i++) {
            date1 = pokazaniaList.get(i).getDate();
            sqlDate = new java.sql.Date(date1.getTime());
            List<Pokazanie> pokazaniaListForDate1 = pokazanieBO.getPokazaniaByDate(sqlDate);
            date2 = pokazaniaList.get(i + 1).getDate();
            sqlDate = new java.sql.Date(date2.getTime());
            List<Pokazanie> pokazaniaListForDate2 = pokazanieBO.getPokazaniaByDate(sqlDate);
            sum = 0.0;

            for (int j = 0; j < pokazaniaListForDate1.size(); j++) {
                if (pokazaniaListForDate2.size() >= j + 1) {
                    if (pokazaniaListForDate2.get(j) != null) {
                        delta = pokazaniaListForDate1.get(j).getData() - pokazaniaListForDate2.get(j).getData();
                        if (delta < 0) {
                            potracheno = pokazaniaListForDate1.get(j).getData();
                        } else {
                            potracheno = delta;
                        }
                    } else {
                        potracheno = pokazaniaListForDate1.get(j).getData();
                    }
                } else {
                    potracheno = pokazaniaListForDate1.get(j).getData();
                }
                price = priceBO.getPriceByCounterAndDate(pokazaniaListForDate1.get(j).getCounter(), pokazaniaListForDate1.get(j).getDate());
                sum += new BigDecimal(price.getPrice() * potracheno).setScale(2, RoundingMode.UP).doubleValue();

            }

            sum = new BigDecimal(sum).setScale(1, RoundingMode.UP).doubleValue();
            period = date2.toString() + " - " + date1.toString();
            usedDeltaList.add(new UsedDelta(0.0, 0.0, sum, period, 0.0, false));
        }

        model.addObject("usedDeltaList", usedDeltaList);
        return model;
    }

    @RequestMapping(value = "/insertAvgData", method = RequestMethod.GET)
    public ModelAndView insertAvgData(HttpServletRequest request) throws SQLException {

        ModelAndView model = new ModelAndView("insertAvgData", "command", new Months());

        return model;
    }

    @RequestMapping(value = "/insertAvgDataInfo", method = RequestMethod.POST)
    public ModelAndView insertAvgDataInfo(HttpServletRequest request) throws SQLException {
        ModelAndView model = new ModelAndView("insertAvgDataInfo");

        String intervalString = request.getParameter("interval");

        List<Counter> counters = counterBO.getListOfCounters();
        List<Pokazanie> pokazania;
        List<Pokazanie> avgPokazania = new ArrayList<>();
        Pokazanie pokazanie;
        Double avgData;
        Integer avgDataInt;

        Calendar calendar = new GregorianCalendar();
        Date simpleDate = calendar.getTime();
        java.sql.Date sqlDate = new java.sql.Date(simpleDate.getTime());

        LocalDate dateFrom;
        LocalDate dateTo;
        int daysToNow = 0;
        int daysInterval = 0;

        Integer interval = Integer.parseInt(intervalString);

        for (int i = 0; i < counters.size(); i++) {
            pokazania = pokazanieBO.getPokazaniaByCounter(counters.get(i));

            if (i == 0) {
                dateFrom = pokazania.get(interval - 1).getDate().toLocalDate();
                dateTo = pokazania.get(0).getDate().toLocalDate();
                daysInterval = (int) Duration.between(dateFrom.atStartOfDay(), dateTo.atStartOfDay()).toDays();
                daysToNow = (int) Duration.between(dateTo.atStartOfDay(), sqlDate.toLocalDate().atStartOfDay()).toDays();
            }

            avgData = pokazania.get(0).getData() + (pokazania.get(0).getData() - pokazania.get(interval - 1).getData()) / daysInterval * daysToNow;
            avgDataInt = avgData.intValue();
            pokazanie = new Pokazanie(counters.get(i), avgDataInt.doubleValue(), sqlDate);
            pokazanieBO.addPokazanie(pokazanie);
            avgPokazania.add(pokazanie);
        }

        model.addObject("selectedItems",
                avgPokazania.stream().map(this::itemDescription).
                        toArray(String[]::new));

        return model;
    }

    @RequestMapping(value = "/deleteDataInfo", method = RequestMethod.POST)
    public ModelAndView deletePokazaniaInfo(HttpServletRequest request) throws SQLException {

        String[] selectedItems = request.getParameterValues("selectedItems");

        selectedItems = Arrays.stream(selectedItems).map((str) -> Integer.parseInt(str))
                .map(this::getPokazanieById)
                .peek(this::deletePokazanie)
                .map(this::itemDescription)
                .toArray(String[]::new);

        ModelAndView model = new ModelAndView("deleteDataInfo");

        model.addObject("selectedItems", selectedItems);

        return model;

    }

    private Pokazanie getPokazanieById(Integer id) {
        try {
            return pokazanieBO.getPokazanieById(id);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }

    }

    private void deletePokazanie(Pokazanie pokazanie) {
        try {
            pokazanieBO.deletePokazanie(pokazanie);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private String itemDescription(Pokazanie pokazanie) {
        return pokazanie.getData() + " от " + pokazanie.getDate().toString() + " счетик: " + pokazanie.getCounter().getCounterName();
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView chooseCounterToGetListPokizaniaToUpdate() throws SQLException {

        List<String> listCountersNames = getAllCountersNames(counterBO);

        ModelAndView model = new ModelAndView("update", "command", new Counter());
        model.addObject("listCountersNames", listCountersNames);

        return model;

    }

    @RequestMapping(value = "/updateData", method = RequestMethod.POST)
    public ModelAndView updatePokazania(HttpServletRequest request) throws SQLException {

        String counterName = request.getParameter("selectCounter");
        Counter counter = counterBO.getCounterByName(counterName);

        ModelAndView model = new ModelAndView("updateData");
        model.addObject("counter_name", counterName);

        List<Pokazanie> pokazaniaList = pokazanieBO.getPokazaniaByCounter(counter);

        model.addObject("pokazaniaList", pokazaniaList);
        return model;

    }

    @RequestMapping(value = "/updateDataInfo", method = RequestMethod.POST)
    public ModelAndView updatePokazaniaInfo(HttpServletRequest request) throws SQLException {

        String[] selectedItems = request.getParameterValues("selectedItems");

        selectedItems = Arrays.stream(selectedItems).map((str) -> Integer.parseInt(str))
                .map(this::getPokazanieById)
                .peek(this::updatePokazanie)
                .map(this::itemDescription)
                .toArray(String[]::new);

        ModelAndView model = new ModelAndView("updateDataInfo");

        model.addObject("selectedItems", selectedItems);

        return model;
    }

    private void updatePokazanie(Pokazanie pokazanie) {
        try {
            pokazanie.setIsPaid(!pokazanie.getIsPaid());
            pokazanieBO.updatePokazanie(pokazanie);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

}
