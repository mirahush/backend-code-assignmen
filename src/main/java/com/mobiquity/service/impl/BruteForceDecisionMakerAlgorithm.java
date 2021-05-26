package com.mobiquity.service.impl;

import com.google.common.collect.Sets;
import com.mobiquity.domain.Item;
import com.mobiquity.domain.Parcel;
import com.mobiquity.service.DecisionMakerAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.stream.Collectors;

/**
 *     This is the second implementation in which I have used the brute force
 *     approach.
 *     The FIRST step was to remove items which their weight is larger than the parcel
 *     maximum weight to remove infeasible options.
 *
 *     The SECOND step was to create all subsets of items which can be included in a
 *     parcel.
 *
 *     The THIRD step was to iterate over them and keep the record of best weight and
 *     best cost that was found so far.
 *
 *     It was important to keep record of both weight and cost as in scenario in which
 *     costs were the same, parcel with lower weight was preferable
 */
public class BruteForceDecisionMakerAlgorithm implements DecisionMakerAlgorithm {

    private final Logger logger = LoggerFactory.getLogger(BruteForceDecisionMakerAlgorithm.class);

    @Override
    public Parcel pickOptimumItems(Parcel parcel, Set<Item> items) {
        items = items.stream().filter( item -> parcel.getWeightLimit() >= item.getWeight()).collect(Collectors.toSet());

        double bestCost = 0.0;
        double bestWeight = 0.0;
        Set<Set<Item>> powerSet = Sets.powerSet(items);

        for (Set<Item> setOfItems : powerSet){
            if(calculateTotalWeightOfItems(setOfItems) < parcel.getWeightLimit()){
                if(calculateTotalCostOfItems(setOfItems) > bestCost){
                    bestCost = calculateTotalCostOfItems(setOfItems);
                    bestWeight = calculateTotalWeightOfItems(setOfItems);
                    parcel.setItems(setOfItems);
                }
                else if (calculateTotalCostOfItems(setOfItems) == bestCost){
                    bestCost = calculateTotalCostOfItems(setOfItems);
                    if(calculateTotalWeightOfItems(setOfItems) < bestWeight) {
                        bestWeight = calculateTotalWeightOfItems(setOfItems);
                        parcel.setItems(setOfItems);
                    }
                }
            }
        }

        return parcel;
    }

    private static Double calculateTotalWeightOfItems(Set<Item> items){
        return items.stream().map(Item::getWeight).reduce(Double::sum).orElse(0.0);
    }

    private static Double calculateTotalCostOfItems(Set<Item> items){
        return items.stream().map(Item::getCost).reduce(Double::sum).orElse(0.0);
    }
}
