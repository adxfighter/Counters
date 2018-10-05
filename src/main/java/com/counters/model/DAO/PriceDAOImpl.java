package com.counters.model.DAO;

import com.counters.model.Counter;
import com.counters.model.Price;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by adementiev on 29.04.14.
 */

@Component
public class PriceDAOImpl extends AbstractEntityDAO implements PriceDAO {

    private static Logger logger = Logger.getLogger(PriceDAOImpl.class.getName());


    public void addPrice(Price price) throws SQLException {


        Session session = null;
        try {
            session = getCurrentSession();
            //persistent
            session.save(price);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception during inserting: ", e);
        }
    }

    public void deletePrice(Price price) throws SQLException {

        Session session;
        try {
            session = getCurrentSession();
            session.delete(price);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception during inserting: ", e);
        }
    }

    public Price getPriceById(Integer id) throws SQLException {
        Session session;
        Price price= null;
        try {
            session = getCurrentSession();
            price = (Price) session.load(Price.class, id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception during finding by id: ", e);
        }
        return price;
    }

    public List<Price> getPriceByCounter(Counter counter) throws SQLException {
        Session session;
        List prices = new ArrayList<Price>();
            session = getCurrentSession();
            Integer counterId = counter.getId();
            Query query = session.createQuery(
                    "SELECT p FROM Price p WHERE p.counter.id = :counterId order by p.date desc"
            )
                    .setInteger("counterId", counterId);
            prices = (List<Price>) query.list();

            return prices;
    }

    public Price getLastPriceByCounter(Counter counter) throws SQLException {
        Session session;
        Price price;
            session = getCurrentSession();
            Integer counterId = counter.getId();
            Query query = session.createQuery(
                    "SELECT p FROM Price p WHERE p.counter.id = :counterId order by p.date desc"
            )
                    .setInteger("counterId", counterId);
            try {
               price = (Price) query.list().get(0);

            }
            catch (IndexOutOfBoundsException exception) {
                price = null;
            }

            return price;
    }

    public Price getPriceByCounterAndDate (Counter counter, Date date) throws SQLException {
        Session session;
        Price price;
            session = getCurrentSession();
            Integer counterId = counter.getId();
            Query query = session.createQuery(
                    "SELECT p FROM Price p WHERE p.counter.id = :counterId and p.date<= :date  order by p.date desc"
            )
                    .setInteger("counterId", counterId);
            query.setDate("date", date);
            try {
                price = (Price) query.list().get(0);

            }
            catch (IndexOutOfBoundsException exception) {
                price = null;
            }

            return price;
    }

}
