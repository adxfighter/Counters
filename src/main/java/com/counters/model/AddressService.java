package com.counters.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Bean
    @ConditionalOnProperty(name = "spring.profiles.active", havingValue = "db1")
    public String getAddress1(@Value("${db1.address}") String address) {
        return address;
    }

    @Bean
    @ConditionalOnProperty(name = "spring.profiles.active", havingValue = "db2")
    public String getAddress2(@Value("${db2.address}") String address) {
        return address;
    }
}
