package com.mobiquity.packer;

import com.mobiquity.domain.Item;
import com.mobiquity.domain.Parcel;
import com.mobiquity.exception.APIException;
import com.mobiquity.service.ParcelDataParserService;
import com.mobiquity.service.impl.ParcelDataParserServiceImpl;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class ParcelDataParserServiceTest {

    private final static String VALID_LINE_OF_PARCEL_DETAILS = "56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)";
    private final static String INVALID_LINE_OF_PARCEL_DETAILS = "101 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)";

    private final ParcelDataParserService parcelDataParserService;

    public ParcelDataParserServiceTest() {
        this.parcelDataParserService =  new ParcelDataParserServiceImpl();
    }

    @Test
    public void x() throws APIException {
       Pair<Parcel, Set<Item>> pair = parcelDataParserService.parseData(VALID_LINE_OF_PARCEL_DETAILS);
    }
}
