package com.counters.model.DAO.config;

import org.hibernate.SessionFactory;
import org.hsqldb.jdbc.JDBCDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by adementiev on 01/03/18.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.counters.model.DAO")
public class DaoConfiguration {

    @Autowired
    Environment environment;

    @Bean
    @Primary
    @Profile("embedded")
    public DataSource dataEmbeddedSource() {
        JDBCDataSource dataSource = new JDBCDataSource();
        // Will save data between executions in files in working directory.
        dataSource.setDatabase("jdbc:hsqldb:file:./hsqldb");
        return dataSource;
    }

    @Bean
    @Primary
    @Profile("db1")
    @ConfigurationProperties(prefix = "db1.datasource")
    public DataSource dataMysql1Source() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    @Profile("db2")
    @ConfigurationProperties(prefix = "db2.datasource")
    public DataSource dataMysql2Source() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

    @Bean
    @Autowired
    public LocalSessionFactoryBean sessionMysqlFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        props.setProperty("hibernate.hbm2ddl.auto", "validate");
        props.setProperty("hibernate.show_sql", "false");
        props.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        sessionFactoryBean.setHibernateProperties(props);
        sessionFactoryBean.setPackagesToScan("com.counters.model");
        return sessionFactoryBean;
    }
}