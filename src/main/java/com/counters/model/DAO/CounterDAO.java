package com.counters.model.DAO;

import com.counters.model.Counter;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by adementiev on 29.04.14.
 */
@Component
public interface CounterDAO {

    void addCounter(Counter counter) throws SQLException;

    List<Counter> getAllCounters() throws SQLException;

    Counter getCounterByName(String counterName) throws SQLException;

    Counter getCounterById(Integer id) throws SQLException;

    void deleteCounter(Counter counter) throws SQLException;
    List<Counter> getListOfCounters() throws SQLException;



}

