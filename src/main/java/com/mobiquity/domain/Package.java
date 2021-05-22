package com.mobiquity.domain;

import java.util.List;

public class Package {
    Integer weightLimit;
/*
    List<Item> items;
*/

    public Package() {
    }

    public Package(Integer weightLimit) {
        this.weightLimit = weightLimit;
    }

    public Integer getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(Integer weightLimit) {
        this.weightLimit = weightLimit;
    }


}
