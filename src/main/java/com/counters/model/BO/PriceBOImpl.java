package com.counters.model.BO;

import com.counters.model.Counter;
import com.counters.model.Price;
import com.counters.model.DAO.PriceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by adementiev on 29.04.14.
 */

@Component
public class PriceBOImpl implements PriceBO {

    @Autowired
    PriceDAO priceDAO;


    public void setPriceDAO(PriceDAO priceDAO) {
        this.priceDAO = priceDAO;
    }

    public void addPrice(Price price) throws SQLException {

        priceDAO.addPrice(price);

    }

    public Price getPriceById(Integer id) throws SQLException {
        return priceDAO.getPriceById(id);
    }

    public void deletePrice(Price price) throws SQLException {
        priceDAO.deletePrice(price);
    }

    public List<Price> getPriceByCounter(Counter counter) throws SQLException {

        return priceDAO.getPriceByCounter(counter);

    }
    public Price getLastPriceByCounter(Counter counter) throws SQLException {
        return priceDAO.getLastPriceByCounter(counter);
    }

    public Price getPriceByCounterAndDate (Counter counter, Date date)  throws SQLException {
        return priceDAO.getPriceByCounterAndDate (counter, date);
    }


}
