package com.counters.model.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



@Transactional
public abstract class AbstractEntityDAO {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getCurrentSession(){return this.sessionFactory.getCurrentSession();}
}
