package com.counters.model.BO;

import com.counters.model.Counter;
import com.counters.model.DAO.CounterDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by adementiev on 29.04.14.
 */
@Component
public class CounterBOImpl implements CounterBO {

    @Autowired
    CounterDAO counterDAO;


    public void addCounter(Counter counter) throws SQLException {

        counterDAO.addCounter(counter);

    }

    public List<Counter> getAllCounters() throws SQLException {

        return counterDAO.getAllCounters();

    }

    public void setCounterDAO(CounterDAO counterDAO) {
        this.counterDAO = counterDAO;
    }

    public Counter getCounterByName(String counterName) throws SQLException {

        return counterDAO.getCounterByName(counterName);

    }

    public Counter getCounterById(Integer id) throws SQLException {

        return counterDAO.getCounterById(id);

    }

    public void deleteCounter(Counter counter) throws SQLException {

        counterDAO.deleteCounter(counter);
    }

    public List<Counter> getListOfCounters() throws SQLException {
        return counterDAO.getListOfCounters();
    }
}

