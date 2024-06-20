package com.counters.model;

import org.springframework.context.annotation.Bean;


public class Months {
    Integer months;

    public Integer getMonths() {
        return months;
    }

    @Bean
    public void setMonths(Integer months) {
        this.months = months;
    }
}
