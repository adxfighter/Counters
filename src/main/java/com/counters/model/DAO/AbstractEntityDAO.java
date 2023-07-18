package com.counters.model.DAO;

import com.counters.model.DAO.config.SessionFactoryBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public abstract class AbstractEntityDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private SessionFactoryBuilder sessionFactoryBuilder;

    protected Session getCurrentSession() {
        sessionFactory = sessionFactoryBuilder.getSessionFactory();
        return this.sessionFactory.getCurrentSession();
    }
}
