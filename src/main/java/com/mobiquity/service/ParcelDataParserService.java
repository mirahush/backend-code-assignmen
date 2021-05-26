package com.mobiquity.service;

import com.mobiquity.domain.Item;
import com.mobiquity.domain.Parcel;
import com.mobiquity.exception.APIException;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Set;

public interface ParcelDataParserService {

    Pair<Parcel, Set<Item>> parseData(String line) throws APIException;
}
