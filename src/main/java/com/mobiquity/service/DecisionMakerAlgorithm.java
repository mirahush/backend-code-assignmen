package com.mobiquity.service;

import com.mobiquity.domain.Item;
import com.mobiquity.domain.Parcel;

import java.util.Set;

public interface DecisionMakerAlgorithm {

    Parcel pickOptimumItems(Parcel parcel, Set<Item> items);
}
