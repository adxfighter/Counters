package com.counters.model.BO;

import com.counters.model.Counter;
import com.counters.model.Pokazanie;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by adementiev on 29.04.14.
 */
@Component
public interface PokazanieBO {

    void addPokazanie(Pokazanie pokazanie) throws SQLException;
    void deletePokazanie(Pokazanie pokazanie) throws SQLException;
    void updatePokazanie(Pokazanie pokazanie) throws SQLException;
    List<Pokazanie> getPokazaniaByCounter(Counter counter) throws SQLException;
    Pokazanie getLastPokazanieByCounter(Counter counter) throws SQLException;
    Pokazanie getFirstPokazanie() throws SQLException;
    Pokazanie getPokazanieById(Integer id) throws SQLException;
    List<Pokazanie> getPokazaniaByDate(java.sql.Date date) throws SQLException;
}
