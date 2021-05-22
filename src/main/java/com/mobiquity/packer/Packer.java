package com.mobiquity.packer;

import com.mobiquity.domain.Item;
import com.mobiquity.domain.Parcel;
import com.mobiquity.exception.APIException;
import com.mobiquity.service.DecisionMakerAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class Packer {

    private static DecisionMakerAlgorithm decisionMakerAlgorithm;

    private Packer(DecisionMakerAlgorithm decisionMakerAlgorithm) {
        Packer.decisionMakerAlgorithm = decisionMakerAlgorithm;
    }

    public static String pack(String filePath) throws APIException {
        StringBuilder result = new StringBuilder();
        new BufferedReader(
                new InputStreamReader(
                        requireNonNull(Packer.class.getClassLoader().getResourceAsStream(filePath))))
                .lines()
                .forEach(line -> {
                    Pair<Integer,List<Item>> pair = processLineOfDate(line);
                    result.append(decisionMakerAlgorithm.pickOptimumItems(new Parcel(pair.getLeft()), pair.getRight()));
                });

        return result.toString();
    }

    private static Pair<Integer, List<Item>> processLineOfDate(String line){
        Integer packageSize = Integer.valueOf(StringUtils.substringBefore(line, ":").trim());
        String[] partitionedItemDetails = StringUtils.split(StringUtils.substringAfter(line, ":")," ");
        List<Item> itemDetails = Arrays.stream(partitionedItemDetails)
                .map(s -> StringUtils.substringBetween(s, "(", ")"))
                .map(s -> {
                    String[] itemDetail = StringUtils.split(s,",");
                    return new Item(
                            Integer.valueOf(itemDetail[0]),
                            Double.valueOf(itemDetail[1]),
                            Double.valueOf(StringUtils.remove(itemDetail[2],"€")));
                })
                .collect(Collectors.toList());

        return Pair.of(packageSize, itemDetails);
    }
}
