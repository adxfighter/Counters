package com.counters.model.BO;

import com.counters.model.Counter;
import com.counters.model.Pokazanie;
import com.counters.model.DAO.PokazanieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by adementiev on 29.04.14.
 */

@Component
public class PokazanieBOImpl implements PokazanieBO {

    @Autowired
    PokazanieDAO pokazanieDAO;


    public void setPokazanieDAO(PokazanieDAO pokazanieDAO) {
        this.pokazanieDAO = pokazanieDAO;
    }

    public void addPokazanie(Pokazanie pokazanie) throws SQLException {

        pokazanieDAO.addPokazanie(pokazanie);

    }

    public Pokazanie getPokazanieById(Integer id) throws SQLException {
        return pokazanieDAO.getPokazanieById(id);
    }

    public void deletePokazanie(Pokazanie pokazanie) throws SQLException {
        pokazanieDAO.deletePokazanie(pokazanie);
    }

    public void updatePokazanie(Pokazanie pokazanie) throws SQLException { pokazanieDAO.updatePokazanie(pokazanie); }

    public List<Pokazanie> getPokazaniaByCounter(Counter counter) throws SQLException {
        return pokazanieDAO.getPokazaniaByCounter(counter);
    }

    public List<Pokazanie> getPokazaniaByDate (java.sql.Date date) throws SQLException {
        return pokazanieDAO.getPokazaniaByDate (date);
    }
    public Pokazanie getLastPokazanieByCounter(Counter counter) throws SQLException {
        return pokazanieDAO.getLastPokazanieByCounter(counter);
    }

    public Pokazanie getFirstPokazanie() throws SQLException {
        return pokazanieDAO.getFirstPokazanie();
    }

}
