package com.mobiquity.service.impl;

import com.mobiquity.domain.Item;
import com.mobiquity.domain.Parcel;
import com.mobiquity.exception.APIException;
import com.mobiquity.service.ParcelDataParserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ParcelDataParserServiceImpl implements ParcelDataParserService {

    private static final String COLON = ":";
    private static final String COMMA = ",";
    private static final String WHITE_SPACE = " ";
    private static final String PARENTHESES_LEFT = "(";
    private static final String PARENTHESES_RIGHT = ")";
    private static final String CURRENCY = "€";

    public Pair<Parcel, Set<Item>> parseData(String line) throws APIException {
        Parcel parcel = parseParcelData(line);
        Set<Item> items = parseItemsData(line);

        return Pair.of(parcel, items);
    }

    private Set<Item> parseItemsData(String line) throws APIException {
        String[] separatedItemDetails = StringUtils.split(StringUtils.substringAfter(line, COLON), WHITE_SPACE);

        Set<Item> items = Arrays.stream(separatedItemDetails)
                .map(string -> StringUtils.substringBetween(string, PARENTHESES_LEFT, PARENTHESES_RIGHT))
                .map(this::parseItemDetails)
                .collect(Collectors.toSet());

        validateItemDetails(items);

        return items;
    }
    private Item parseItemDetails(String itemDetails) {
        String[] itemDetail = StringUtils.split(itemDetails, COMMA);
        int index = Integer.parseInt(itemDetail[0]);
        double weight = Double.parseDouble(itemDetail[1]);
        double cost = Double.parseDouble(StringUtils.remove(itemDetail[2], CURRENCY));

        return new Item(index,weight,cost);
    }

    private void validateItemDetails(Set<Item> items) throws APIException {
        for (Item item : items) {
            if (item.getWeight() > 100)
                throw new APIException("Max weight and cost of an item is ≤ 100");
            if (item.getCost() > 100)
                throw new APIException("Max weight and cost of an item is ≤ 100");
        }
    }

    private Parcel parseParcelData(String line) throws APIException {
        double packageSize = Double.parseDouble(StringUtils.substringBefore(line, COLON).trim());
        Parcel parcel = new Parcel(packageSize);
        validateParcelDetails(parcel);

        return parcel;
    }

    private void validateParcelDetails(Parcel parcel) throws APIException {
        if(parcel.getWeightLimit() > 100)
            throw new APIException("Max weight that a package can take is ≤ 100");
    }
}
