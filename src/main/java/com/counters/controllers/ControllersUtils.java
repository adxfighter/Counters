package com.counters.controllers;

import com.counters.model.BO.CounterBO;
import com.counters.model.BO.PokazanieBO;
import com.counters.model.Counter;
import com.counters.model.Pokazanie;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 * Created by root on 14.07.14.
 */
public class ControllersUtils {


    static List<String> getAllCountersNames(CounterBO counterBO) throws SQLException {
        List<Counter> countersList = counterBO.getAllCounters();

        List<String> listCountersNames = countersList.stream().map(Counter::getCounterName)
                .collect(Collectors.toList());

        return listCountersNames;
    }

    static Pokazanie getLastPokazanieByCounter(Counter counter, PokazanieBO pokazanieBO) throws SQLException {
        return pokazanieBO.getLastPokazanieByCounter(counter);
    }
}
