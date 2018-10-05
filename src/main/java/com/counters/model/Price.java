package com.counters.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by root on 02.08.14.
 */

@Entity
@Table(name = "Prices")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER,
            targetEntity = Counter.class)
    private Counter counter;

    @Column
    private Double price;

    @Column
    private Date date;

    public Price(){}
    public Price(Integer id, Counter counter, Double price, Date date) {
        this.id = id;
        this.counter = counter;
        this.price = price;
        this.date = date;
    }
    public Price(Counter counter, Double price, Date date) {
        this.counter = counter;
        this.price = price;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Counter getCounter() {
        return counter;
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
