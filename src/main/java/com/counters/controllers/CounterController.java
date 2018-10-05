package com.counters.controllers;

import com.counters.model.BO.CounterBO;
import com.counters.model.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
public class CounterController {

    @Autowired
    CounterBO counterBO;

    @RequestMapping(value = "/addCounter", method = RequestMethod.POST)
    public String testAdd(@ModelAttribute("counter") Counter counter, ModelMap model, HttpServletRequest request) throws SQLException {

        model.addAttribute("counter_name", counter.getCounterName());

        counterBO.addCounter(new Counter(counter.getCounterName()));

        return "addCounter";
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

}
