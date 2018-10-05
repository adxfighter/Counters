package com.counters.model.DAO;

import com.counters.model.Counter;
import com.counters.model.Pokazanie;
import org.hibernate.Query;
import org.hibernate.Session;
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
public class PokazanieDAOImpl extends AbstractEntityDAO implements PokazanieDAO {

    private static Logger logger = Logger.getLogger(PokazanieDAOImpl.class.getName());

    public void   addPokazanie(Pokazanie pokazanie) throws SQLException {

        Session session = null;
        try {
            session = getCurrentSession();
            session.save(pokazanie);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception during inserting: ", e);
        }
    }

    public void deletePokazanie(Pokazanie pokazanie) throws SQLException {


        Session session = null;
        try {
            session = getCurrentSession();
            session.delete(pokazanie);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception during inserting: ", e);
        }
    }

    public void updatePokazanie(Pokazanie pokazanie) throws SQLException {

        Session session;
        try {
            session = getCurrentSession();
            //update
            session.update(pokazanie);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception during inserting: ", e);
        }
    }

    public Pokazanie getPokazanieById(Integer id) throws SQLException {
        Session session;
        Pokazanie pokazanie = null;
        try {
            session = getCurrentSession();
            Query query = session.createQuery(
                    "SELECT p FROM Pokazanie p WHERE p.id = :id order by p.date desc"
            )
                    .setInteger("id", id);

            pokazanie = (Pokazanie) query.list().get(0);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception during finding by id: ", e);
        }
        return pokazanie;
    }

    @Transactional
    public List<Pokazanie> getPokazaniaByCounter(Counter counter) throws SQLException {
        Session session;
        List pokazania = new ArrayList<Pokazanie>();
            session = getCurrentSession();
            Integer counterId = counter.getId();
            Query query = session.createQuery(
                    "SELECT p FROM Pokazanie p WHERE p.counter.id = :counterId order by p.date desc"
            )
                    .setInteger("counterId", counterId);
            pokazania = (List<Pokazanie>) query.list();

            return pokazania;
    }

    public List<Pokazanie> getPokazaniaByDate(java.sql.Date date) throws SQLException {
        Session session;
        List pokazania = new ArrayList<Pokazanie>();
            session = getCurrentSession();
            Query query = session.createQuery(
                    "SELECT p FROM Pokazanie p WHERE p.date = :date order by p.counter.id asc"
            )
                    .setDate("date", date);
            pokazania = (List<Pokazanie>) query.list();

            return pokazania;
    }

    public Pokazanie getLastPokazanieByCounter(Counter counter) throws SQLException {
        Session session;
        Pokazanie pokazanie;
            session = getCurrentSession();
            Integer counterId = counter.getId();
            Query query = session.createQuery(
                    "SELECT p FROM Pokazanie p WHERE p.counter.id = :counterId order by p.date desc"
            )
                    .setInteger("counterId", counterId);
            try {
               pokazanie = (Pokazanie) query.list().get(0);

            }
            catch (IndexOutOfBoundsException exception) {
                pokazanie = null;
            }

            return pokazanie;
    }

    public Pokazanie getFirstPokazanie() throws SQLException {
        Session session;
        Pokazanie pokazanie;
            session = getCurrentSession();
            Query query = session.createQuery(
                    "SELECT p FROM Pokazanie p order by p.date, p.counter.id asc"
            );

            try {
                pokazanie = (Pokazanie) query.list().get(0);

            }
            catch (IndexOutOfBoundsException exception) {
                pokazanie = null;
            }

            return pokazanie;

    }

}
