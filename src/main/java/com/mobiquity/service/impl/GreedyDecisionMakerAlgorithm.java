package com.mobiquity.service.impl;

import com.mobiquity.domain.Item;
import com.mobiquity.domain.Parcel;
import com.mobiquity.service.DecisionMakerAlgorithm;

import java.util.HashSet;
import java.util.Set;

/**
 *      The first solution that I developed was using greedy algorithm
 *      and I tried to sort the items based on their ratio of cost to weight.
 *      Yet this algorithm did not produced the optimum value for the 3rd test
 *      case.
 */

public class GreedyDecisionMakerAlgorithm implements DecisionMakerAlgorithm {

    @Override
    public Parcel pickOptimumItems(Parcel parcel, Set<Item> items) {
        Set<Item> pickedItems = new HashSet<>();

        items.stream()
                .sorted((item1,item2)-> Double.compare(
                        item2.getCost()/item2.getWeight(),
                        item1.getCost()/item1.getWeight()))
                .filter(item -> item.getWeight() <= parcel.getWeightLimit())
                .forEach(item -> {
                            Double currentWeight = pickedItems.stream().map(Item::getWeight).reduce(Double::sum).orElse(0.0);
                            if( currentWeight + item.getWeight() <= parcel.getWeightLimit() ){
                                pickedItems.add(item);
                            }
                        }
                );
        return parcel;
    }
}
