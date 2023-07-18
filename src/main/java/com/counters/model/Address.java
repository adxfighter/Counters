package com.counters.model;

import com.counters.model.DAO.config.Db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adementiev on 26.10.14.
 */

@Component
public class Address {

    private final Map<String, String> addresses = new HashMap();

    @Autowired
    private String address;

    @Value("${db1.address}")
    private String address1;

    @Value("${db2.address}")
    private String address2;

    public Map getAddresses() {
        if (addresses.size() == 0) {
            addresses.put(address1, Db.DB1.getName());
            addresses.put(address2, Db.DB2.getName());
        }
        return addresses;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
