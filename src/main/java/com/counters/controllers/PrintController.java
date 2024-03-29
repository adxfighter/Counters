package com.counters.controllers;

import com.counters.model.*;
import com.counters.model.BO.CounterBO;
import com.counters.model.BO.PokazanieBO;
import com.counters.model.BO.PriceBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
public class PrintController {

    private static Logger logger = Logger.getLogger(PrintController.class.getName());

    @Autowired
    PokazanieBO pokazanieBO;

    @Autowired
    PriceBO priceBO;

    @Autowired
    CounterBO counterBO;

    @Autowired
    Address address;

    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public ModelAndView printPokazania() throws SQLException {

        ModelAndView model = new ModelAndView("print");
        model.addObject("isCorrect", isLastPokazaniaListCorrect());
        model.addObject("pokazania", getLastPokazaniaList());
        model.addObject("address", address.getAddress());

        return model;

    }

    @RequestMapping(value = "/printDelta", method = RequestMethod.GET)
    public ModelAndView printDelta() throws SQLException {

        ModelAndView model = new ModelAndView("printDelta");
        model.addObject("isCorrect", isLastPokazaniaListCorrect());
        model.addObject("pokazaniaPair", getPokazaniaPairList());
        model.addObject("address", address.getAddress());

        return model;

    }

    @RequestMapping(value = "/printAndPrices", method = RequestMethod.GET)
    public ModelAndView printPokazaniaAndPrices() throws SQLException {

        ModelAndView model = new ModelAndView("printAndPrices");
        model.addObject("isCorrect", isLastPokazaniaListCorrect());
        model.addObject("pokazaniaAndPrices", getLastUsedDeltaList());
        model.addObject("address", address.getAddress());

        return model;

    }

    private List<Pokazanie> getLastPokazaniaList() throws SQLException {
        List<Counter> countersList = counterBO.getListOfCounters();

        List<Pokazanie> pokazanieList = countersList.stream().
                map(this::getLastPokazanieByCounter).
                collect(Collectors.toList());

        return pokazanieList;
    }

    private List<PokazaniePair> getPokazaniaPairList() throws SQLException {
        List<Counter> countersList = counterBO.getListOfCounters();

        List<PokazaniePair> pokazaniePairList = countersList.stream().
                map(this::getPokazaniaByCounter).
                map(list -> new PokazaniePair(list.get(1), list.get(0))).
                collect(Collectors.toList());

        return pokazaniePairList;
    }

    private List<Pokazanie> getPokazaniaByCounter(Counter counter) {
        try {
            return pokazanieBO.getPokazaniaByCounter(counter);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

    private Pokazanie getLastPokazanieByCounter(Counter counter) {
        try {
            return pokazanieBO.getLastPokazanieByCounter(counter);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

    private boolean isLastPokazaniaListCorrect() throws SQLException {
        boolean isCorrect = true;

        List<Counter> countersList = counterBO.getListOfCounters();

        if (countersList.size() > 1) {
            Date pokazanie1Date = pokazanieBO.getLastPokazanieByCounter(countersList.get(0)).getDate();

            isCorrect = countersList.stream().
                    map(this::getLastPokazanieByCounter).
                    map(pokazanie -> pokazanie.getDate()).
                    allMatch(pokazanie1Date::equals);
        }

        return isCorrect;
    }

    private List<UsedDelta> getLastUsedDeltaList() throws SQLException {

        List<Counter> countersList = counterBO.getListOfCounters();
        List<UsedDelta> usedDeltaList = countersList.stream()
                .map(this::getUsedDeltaByCounter)
                .collect(Collectors.toList());

        return usedDeltaList;
    }

    private UsedDelta getUsedDeltaByCounter(Counter counter) {
        try {
            Pokazanie pokazanie = pokazanieBO.getLastPokazanieByCounter(counter);
            Price price = priceBO.getLastPriceByCounter(counter);
            UsedDelta usedDelta = new UsedDelta();
            usedDelta.setPeriod(counter.getCounterName());
            usedDelta.setPrice(price.getPrice());
            usedDelta.setData(pokazanie.getData());

            return usedDelta;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }
}
