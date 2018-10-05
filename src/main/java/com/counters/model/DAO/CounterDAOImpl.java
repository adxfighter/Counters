package com.counters.model.DAO;

import com.counters.model.Counter;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by adementiev on 29.04.14.
 */
@Component
public class CounterDAOImpl extends AbstractEntityDAO implements CounterDAO {

    private static Logger logger = Logger.getLogger(CounterDAOImpl.class.getName());

    public void addCounter(Counter counter) {
        Session session;
        try {
            session = getCurrentSession();
            session.save(counter);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception during inserting: ", e);
        }

    }

    public List<Counter> getAllCounters() throws SQLException {
        Session session;
        List counters = new ArrayList<Counter>();
        try {
            session = getCurrentSession();
            counters = session.createCriteria(Counter.class).list();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception during getting all: ", e);
        }
        return counters;
    }

    public Counter getCounterByName(String counterName) throws SQLException {
        Session session;
            session = getCurrentSession();
            Query query = session.createQuery(
                    "SELECT c FROM Counter c WHERE c.counterName = :counterName"
            )
                    .setString("counterName", counterName);
            Counter counter = (Counter) query.list().get(0);
            return counter;
    }

    public List<Counter> getListOfCounters() throws SQLException {
        Session session;
            session = getCurrentSession();
            Query query = session.createQuery(
                    "SELECT c FROM Counter c ORDER BY c.counterName"
            );
            List<Counter> counters = new ArrayList<Counter>();
            counters = (List<Counter>) query.list();
            return counters;
    }

    public Counter getCounterById(Integer id) throws SQLException {
        Session session;
        Counter counter = null;
        try {
            session = getCurrentSession();
            counter = (Counter) session.load(Counter.class, id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception during finding by id: ", e);
        }
        return counter;
    }

    public void deleteCounter(Counter counter) throws SQLException {
        Session session;
        try {
            session = getCurrentSession();
            session.delete(counter);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception during deleting: ", e);
        }
    }

}
