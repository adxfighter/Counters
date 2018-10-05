package com.counters.model;

/**
 * Created by adementiev on 26.10.14.
 */
public class UsedDelta {
    private Double data;
    private String period;
    private Double sum;
    private Double price;
    private Double count;
    private Boolean isPaid;

    public UsedDelta(){

    }

    public UsedDelta(Double data, Double price, Double sum, String period, Double count, Boolean isPaid){
        this.data = data;
        this.period = period;
        this.sum = sum;
        this.price = price;
        this.count = count;
        this.isPaid = isPaid;
    }

    public Double getData() {
        return data;
    }

    public void setData(Double data) {
        this.data = data;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }
}
