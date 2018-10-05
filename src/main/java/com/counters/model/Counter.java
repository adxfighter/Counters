package com.counters.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 02.08.14.
 */
@Entity
@Table(name = "Counters")
public class Counter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "counter_name")
    private String counterName;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy="counter")
    private List<Pokazanie> pokazania = new ArrayList<Pokazanie>();

    public Counter(){}
    public Counter(String counterName){
        this.counterName = counterName;
    }
    public Counter(Integer id, String counterName){
        this.id = id;
        this.counterName = counterName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public List getPokazania() {
        return pokazania;
    }

    public void setPokazania(List pokazania) {
        this.pokazania = pokazania;
    }
}
