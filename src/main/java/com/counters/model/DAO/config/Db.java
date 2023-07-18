package com.counters.model.DAO.config;

public enum Db {

    DB1("db1"),
    DB2("db2");

    private String name;

    Db(String db) {
        this.name = db;
    }

    public String getName() {
        return name;
    }
}
