package com.mobiquity.domain;

import java.util.HashSet;
import java.util.Set;

public class Parcel {
    Double weightLimit;
    Set<Item> items;

    public Parcel(Double weightLimit) {
        this.weightLimit = weightLimit;
        this.items = new HashSet<>();
    }

    public Double getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(Double weightLimit) {
        this.weightLimit = weightLimit;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
