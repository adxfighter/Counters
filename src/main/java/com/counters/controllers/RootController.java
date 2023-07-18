package com.counters.controllers;

import com.counters.model.Address;
import com.counters.model.Counter;
import com.counters.model.DAO.config.SessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.ParseException;

@Controller
public class RootController {

    @Autowired
    SessionFactoryBuilder sessionFactoryBuilder;

    @Autowired
    Address address;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("address", address.getAddress());
        model.addAttribute("addresses", address.getAddresses().keySet());
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String indexPost(ModelMap model, HttpServletRequest request) throws SQLException, ParseException {
        String addressStr = request.getParameter("selectAddress");
        if (addressStr != null) {
            address.setAddress(addressStr);
            String db = (String) address.getAddresses().get(addressStr);
            if (db != null) {
                sessionFactoryBuilder.setSessionFactory(db);
            }
        }
        model.addAttribute("address", address.getAddress());
        model.addAttribute("addresses", address.getAddresses().keySet());
        return "index";
    }

    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    public ModelAndView calendar() throws SQLException {

        ModelAndView model = new ModelAndView("calendar", "command", new Counter());

        return model;
    }
}
