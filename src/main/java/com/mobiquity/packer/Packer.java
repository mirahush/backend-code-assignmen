package com.mobiquity.packer;

import com.mobiquity.domain.Item;
import com.mobiquity.domain.Parcel;
import com.mobiquity.exception.APIException;
import com.mobiquity.service.DecisionMakerAlgorithm;
import com.mobiquity.service.FileAccessService;
import com.mobiquity.service.impl.BruteForceDecisionMakerAlgorithm;
import com.mobiquity.service.impl.FileAccessServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class Packer {

    private static DecisionMakerAlgorithm decisionMakerAlgorithm = new BruteForceDecisionMakerAlgorithm();
    private static FileAccessService fileAccessService = new FileAccessServiceImpl();

    private Packer() {
    }

    public static String pack(String filePath) throws APIException {
        StringBuilder result = new StringBuilder();
        new BufferedReader(
                new InputStreamReader(
                        requireNonNull(Packer.class.getClassLoader().getResourceAsStream(filePath))))
                .lines()
                .forEach(line -> {
                    Pair<Double,Set<Item>> pair = processLineOfDate(line);
                    Parcel parcel = decisionMakerAlgorithm.pickOptimumItems(new Parcel(pair.getLeft()), pair.getRight());
                    result.append(parcel.getItems().stream().map(Item::getIndex).collect(Collectors.toList()));
                });
        System.out.println(result);
        return result.toString();
    }

    private static Pair<Double, Set<Item>> processLineOfDate(String line){
        Double packageSize = Double.valueOf(StringUtils.substringBefore(line, ":").trim());
        String[] partitionedItemDetails = StringUtils.split(StringUtils.substringAfter(line, ":")," ");
        Set<Item> itemDetails = Arrays.stream(partitionedItemDetails)
                .map(s -> StringUtils.substringBetween(s, "(", ")"))
                .map(s -> {
                    String[] itemDetail = StringUtils.split(s,",");
                    return new Item(
                            Integer.valueOf(itemDetail[0]),
                            Double.valueOf(itemDetail[1]),
                            Double.valueOf(StringUtils.remove(itemDetail[2],"€")));
                })
                .collect(Collectors.toSet());

        return Pair.of(packageSize, itemDetails);
    }
}
