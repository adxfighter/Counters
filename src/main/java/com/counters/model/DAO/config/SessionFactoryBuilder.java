package com.counters.model.DAO.config;

import com.counters.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Component;

@Component
public class SessionFactoryBuilder {

    @Autowired
    private HibernateTransactionManager transactionManager;

    @Autowired
    private SessionFactory sessionFactory;

    @Value("${db1.datasource.url}")
    private String url1;

    @Value("${db2.datasource.url}")
    private String url2;

    @Value("${db1.datasource.username}")
    private String username1;

    @Value("${db2.datasource.username}")
    private String username2;

    @Value("${db1.datasource.password}")
    private String password1;

    @Value("${db2.datasource.password}")
    private String password2;


    private SessionFactory sessionFactory1;
    private SessionFactory sessionFactory2;

    public void setSessionFactory(String db) {
        if (Db.DB1.getName().equals(db)) {
            if (sessionFactory1 == null) {
                sessionFactory1 = getSessionFactory(url1, username1, password1);
            }
            this.sessionFactory = sessionFactory1;
        } else {

            if (sessionFactory2 == null) {
                sessionFactory2 = getSessionFactory(url2, username2, password2);
            }
            this.sessionFactory = sessionFactory2;
        }
        this.transactionManager.setSessionFactory(sessionFactory);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private SessionFactory getSessionFactory(String url, String username, String password) {
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");

        config.setProperty("hibernate.connection.url", url);
        config.setProperty("hibernate.connection.username", username);
        config.setProperty("hibernate.connection.password", password);

        config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        config.setProperty("hibernate.hbm2ddl.auto", "validate");
        config.setProperty("hibernate.show_sql", "false");
        config.setProperty("hibernate.current_session_context_class", "org.springframework.orm.hibernate4.SpringSessionContext");
        config.addAnnotatedClass(Counter.class);
        config.addAnnotatedClass(Pokazanie.class);
        config.addAnnotatedClass(PokazaniePair.class);
        config.addAnnotatedClass(Price.class);
        config.addAnnotatedClass(UsedDelta.class);
        StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
        return config.buildSessionFactory(standardServiceRegistryBuilder.build());
    }
}
