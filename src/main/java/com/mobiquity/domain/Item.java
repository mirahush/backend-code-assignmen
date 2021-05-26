package com.mobiquity.domain;

/**
 * This class
 */
public class Item {
    Integer index;
    Double weight;
    Double cost;

    public Item() {
    }

    public Item(Integer index, Double weight, Double cost) {
        this.index = index;
        this.weight = weight;
        this.cost = cost;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
