package com.counters.model;

public class PokazaniePair {
    Pokazanie former;
    Pokazanie last;
    Double delta;


    public PokazaniePair(Pokazanie former, Pokazanie last) {
        this.former = former;
        this.last = last;
        delta = last.getData() - former.getData();
    }

    public Pokazanie getFormer() {
        return former;
    }

    public Pokazanie getLast() {
        return last;
    }

    public Double getDelta() {
        return delta;
    }
}
