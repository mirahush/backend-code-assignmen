package com.mobiquity.domain;

public class Parcel {
    Integer weightLimit;
/*
    List<Item> items;
*/

    public Parcel() {
    }

    public Parcel(Integer weightLimit) {
        this.weightLimit = weightLimit;
    }

    public Integer getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(Integer weightLimit) {
        this.weightLimit = weightLimit;
    }


}
