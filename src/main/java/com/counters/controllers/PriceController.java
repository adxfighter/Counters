package com.counters.controllers;

import com.counters.model.BO.CounterBO;
import com.counters.model.BO.PokazanieBO;
import com.counters.model.BO.PriceBO;
import com.counters.model.Counter;
import com.counters.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.counters.controllers.ControllersUtils.getAllCountersNames;

@Controller
public class PriceController {

    private static Logger logger = Logger.getLogger(PriceController.class.getName());

    @Autowired
    PriceBO priceBO;

    @Autowired
    CounterBO counterBO;

    @RequestMapping(value = "/priceIndex", method = RequestMethod.GET)
    public ModelAndView priceIndex() {
        ModelAndView model = new ModelAndView("priceIndex");

        return model;
    }


    @RequestMapping(value = "/price", method = RequestMethod.GET)
    public ModelAndView enterPrice() throws SQLException {

        List<String> listCountersNames = getAllCountersNames(counterBO);

        ModelAndView model = new ModelAndView("price", "command", new Price());
        model.addObject("listCountersNames", listCountersNames);

        return model;

    }

    @RequestMapping(value = "/addPrice", method = RequestMethod.POST)
    public String addPrice(@ModelAttribute("data") Price price, ModelMap model, HttpServletRequest request) throws SQLException, ParseException {


        String counterName = request.getParameter("selectCounter");
        Counter counter = counterBO.getCounterByName(counterName);
        model.addAttribute("counter_name", counter.getCounterName());
        model.addAttribute("data", price.getPrice());

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
        priceBO.addPrice(new Price(counter, price.getPrice(), sqlDate));

        return "addPrice";
    }

    @RequestMapping(value = "/priceList", method = RequestMethod.GET)
    public ModelAndView chooseCounterToGetPriceList() throws SQLException {

        List<String> listCountersNames = ControllersUtils.getAllCountersNames(counterBO);

        ModelAndView model = new ModelAndView("priceList", "command", new Counter());
        model.addObject("listCountersNames", listCountersNames);

        return model;

    }

    @RequestMapping(value = "/deletePrice", method = RequestMethod.POST)
    public ModelAndView deletePrice(HttpServletRequest request) throws SQLException {

        String counterName = request.getParameter("selectCounter");
        Counter counter;
        counter = counterBO.getCounterByName(counterName);

        ModelAndView model = new ModelAndView("deletePrice");
        model.addObject("counter_name", counterName);


        List<Price> priceList = new ArrayList<Price>();
        priceList = priceBO.getPriceByCounter(counter);

        model.addObject("priceList", priceList);
        return model;

    }

    @RequestMapping(value = "/deletePriceInfo", method = RequestMethod.POST)
    public ModelAndView deletePriceInfo(HttpServletRequest request) throws SQLException {

        String[] selectedItems = request.getParameterValues("selectedItems");

        selectedItems = Arrays.stream(selectedItems).map((str) -> Integer.parseInt(str))
                .map(this::getPriceById)
                .peek(this::deletePrice)
                .map(this::itemDescription)
                .toArray(String[]::new);

        ModelAndView model = new ModelAndView("deletePriceInfo");

        model.addObject("selectedItems", selectedItems);

        return model;

    }

    private Price getPriceById(Integer id){
        try {
            return priceBO.getPriceById(id);
        } catch (SQLException e) {
            logger.log(Level.SEVERE,e.getMessage(),e);
            return null;
        }

    }

    private void deletePrice(Price price){
        try {
            priceBO.deletePrice(price);
        } catch (SQLException e) {
            logger.log(Level.SEVERE,e.getMessage(),e);
        }

    }

    private String itemDescription(Price price){
        return price.getPrice() + " от " + price.getDate().toString() + " счетик: " + price.getCounter().getCounterName();
    }

}
