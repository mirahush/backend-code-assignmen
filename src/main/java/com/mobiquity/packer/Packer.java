package com.mobiquity.packer;

import com.mobiquity.domain.Item;
import com.mobiquity.domain.Parcel;
import com.mobiquity.exception.APIException;
import com.mobiquity.service.DecisionMakerAlgorithm;
import com.mobiquity.service.FileAccessService;
import com.mobiquity.service.ParcelDataParserService;
import com.mobiquity.service.impl.BruteForceDecisionMakerAlgorithm;
import com.mobiquity.service.impl.FileAccessServiceImpl;
import com.mobiquity.service.impl.ParcelDataParserServiceImpl;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Packer {

    private static DecisionMakerAlgorithm decisionMakerAlgorithm = new BruteForceDecisionMakerAlgorithm();
    private static FileAccessService fileAccessService = new FileAccessServiceImpl();
    private static ParcelDataParserService parcelDataParserService = new ParcelDataParserServiceImpl();

    public static String pack(String filePath) throws APIException {
        StringBuilder result = new StringBuilder();

        List<String> lines =
        fileAccessService.readFile(filePath)
                .lines().collect(Collectors.toList());
        for(String line : lines) {
            Pair<Parcel, Set<Item>> pair = parcelDataParserService.parseData(line);
            Parcel parcel = decisionMakerAlgorithm.pickOptimumItems(pair.getLeft(), pair.getRight());
            result.append(parcel.getItems().stream().map(Item::getIndex).collect(Collectors.toList())).append("\n");
        }

        return result.toString();
    }
}
