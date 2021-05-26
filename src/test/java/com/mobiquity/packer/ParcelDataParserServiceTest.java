package com.mobiquity.packer;

import com.mobiquity.domain.Item;
import com.mobiquity.domain.Parcel;
import com.mobiquity.exception.APIException;
import com.mobiquity.service.ParcelDataParserService;
import com.mobiquity.service.impl.ParcelDataParserServiceImpl;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ParcelDataParserServiceTest {

    private final static String LINE_OF_PARCEL_DETAILS_WITH_VALID_DATA = "56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)";
    private final static String LINE_OF_PARCEL_DETAILS_WITH_INVALID_PARCEL_WEIGHT_LIMIT = "101 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)";
    private final static String LINE_OF_PARCEL_DETAILS_WITH_INVALID_ITEM_WEIGHT = "56 : (1,190.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)";
    private final static String LINE_OF_PARCEL_DETAILS_WITH_INVALID_ITEM_COST = "56 : (1,90.72,€113) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)";

    private final ParcelDataParserService parcelDataParserService;

    public ParcelDataParserServiceTest() {
        this.parcelDataParserService =  new ParcelDataParserServiceImpl();
    }

    @Test
    @DisplayName("TARGET: parse one line of parcel details and its potential items " +
            "WHEN: parcel details and its items do not violate any constraint " +
            "EXPECTATION: data is parsed successfully and corresponding objects are created")
    public void test1() throws APIException {
       Pair<Parcel, Set<Item>> pair = parcelDataParserService.parseData(LINE_OF_PARCEL_DETAILS_WITH_VALID_DATA);

       assertNotNull(pair);
       assertNotNull(pair.getLeft());
       assertNotNull(pair.getRight());
       assertEquals(56, pair.getLeft().getWeightLimit());
       assertEquals(9, pair.getRight().size());
    }


    @Test
    @DisplayName("TARGET: parse one line of parcel details and its potential items " +
            "WHEN: parcel detail violate constraints " +
            "EXPECTATION: APIException is thrown")
    public void test2() throws APIException {
        assertThrows(APIException.class, () -> parcelDataParserService.parseData(LINE_OF_PARCEL_DETAILS_WITH_INVALID_PARCEL_WEIGHT_LIMIT));
    }

    @Test
    @DisplayName("TARGET: parse one line of parcel details and its potential items " +
            "WHEN: item weight violate constraints " +
            "EXPECTATION: APIException is thrown")
    public void test3() throws APIException {
        assertThrows(APIException.class, () -> parcelDataParserService.parseData(LINE_OF_PARCEL_DETAILS_WITH_INVALID_ITEM_WEIGHT));
    }

    @Test
    @DisplayName("TARGET: parse one line of parcel details and its potential items " +
            "WHEN: item weight violate constraints " +
            "EXPECTATION: APIException is thrown")
    public void test4() throws APIException {
        assertThrows(APIException.class, () -> parcelDataParserService.parseData(LINE_OF_PARCEL_DETAILS_WITH_INVALID_ITEM_COST));
    }
}
