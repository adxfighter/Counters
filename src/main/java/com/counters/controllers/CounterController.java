package com.counters.controllers;

import com.counters.model.BO.CounterBO;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
public class CounterController {

    @Autowired
    CounterBO counterBO;

    Counter counter;

    @RequestMapping(value = "/addCounter", method = RequestMethod.POST)
    public String testAdd(@ModelAttribute("counter") Counter counter, ModelMap model, HttpServletRequest request) throws SQLException {

        model.addAttribute("counter_name", counter.getCounterName());

        counterBO.addCounter(new Counter(counter.getCounterName()));

        return "addCounter";
    }

    @RequestMapping(value = "/updateCounter", method = RequestMethod.POST)
    public ModelAndView updateCounter(HttpServletRequest request) throws SQLException {

        String counterNameOld = request.getParameter("selectCounter");

        counter = counterBO.getCounterByName(counterNameOld);

        ModelAndView model = new ModelAndView("updateCounter", "command", new Counter());
        model.addObject("counterNameOld", counterNameOld);

        return model;
    }

    @RequestMapping(value = "/counterIndex", method = RequestMethod.GET)
    public ModelAndView counterIndex() {
        ModelAndView model = new ModelAndView("counterIndex");

        return model;
    }

    @RequestMapping(value = "/updateCounterInfo", method = RequestMethod.POST)
    public ModelAndView updateCounterInfo(HttpServletRequest request) throws SQLException {

        String counterName = request.getParameter("counterName");

        counter.setCounterName(counterName);
        counterBO.updateCounter(counter);

        ModelAndView model = new ModelAndView("updateCounterInfo");
        model.addObject("counterName", counterName);

        return model;

    }


    @RequestMapping(value = "/counter", method = RequestMethod.GET)
    public ModelAndView addCounter() throws SQLException {

        List<Counter> countersList = counterBO.getAllCounters();

        List<String> listCountersNames = countersList.stream().map(Counter::getCounterName)
                .collect(Collectors.toList());

        ModelAndView model = new ModelAndView("counter", "command", new Counter());
        model.addObject("listCountersNames", listCountersNames);

        return model;

    }

    @RequestMapping(value = "/counterList", method = RequestMethod.GET)
    public ModelAndView chooseCounterToGetPriceList() throws SQLException {

        List<String> listCountersNames = ControllersUtils.getAllCountersNames(counterBO);

        ModelAndView model = new ModelAndView("counterList", "command", new Counter());
        model.addObject("listCountersNames", listCountersNames);

        return model;

    }

}
