package com.counters.model.BO;

import com.counters.model.Counter;
import com.counters.model.Price;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by adementiev on 29.04.14.
 */
@Component
public interface PriceBO {

    void addPrice(Price price) throws SQLException;
    void deletePrice(Price price) throws SQLException;
    List<Price> getPriceByCounter(Counter counter) throws SQLException;
    Price getLastPriceByCounter(Counter counter) throws SQLException;
    Price getPriceById(Integer id) throws SQLException;
    Price getPriceByCounterAndDate(Counter counter, Date date)  throws SQLException;


}
