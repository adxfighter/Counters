package com.counters.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by root on 02.08.14.
 */

@Entity
@Table(name = "Pokazania")
public class Pokazanie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="counter_id", nullable=false)
    private Counter counter;

    @Column
    private Double data;

    @Column
    private Date date;

    @Column
    private Boolean isPaid = false;

    public Pokazanie() {
    }

    public Pokazanie(Integer id, Counter counter, Double data, Date date) {
        this.id = id;
        this.counter = counter;
        this.data = data;
        this.date = date;
    }

    public Pokazanie(Counter counter, Double data, Date date) {
        this.counter = counter;
        this.data = data;
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

    public Double getData() {
        return data;
    }

    public void setData(Double data) {
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }
}
