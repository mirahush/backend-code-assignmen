package com.mobiquity.service;

import com.mobiquity.domain.Item;
import com.mobiquity.domain.Parcel;

import java.util.List;

public interface DecisionMakerAlgorithm {

    String pickOptimumItems(Parcel parcel, List<Item> items);
}
