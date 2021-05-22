package com.mobiquity.packer;

import com.mobiquity.domain.Item;
import com.mobiquity.exception.APIException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Packer {

  private Packer() {
  }

  public static String pack(String filePath) throws APIException {
    try {
      Files.lines(Paths.get(filePath), Charset.defaultCharset())
              .forEach(line -> {
                Pair<Integer,List<Item>> pair = processLineOfDate(line);
              });
    } catch (IOException e) {
      e.printStackTrace();
    }


    return null;
  }

  private static Pair<Integer, List<Item>> processLineOfDate(String line){
    Integer packageSize = Integer.valueOf(StringUtils.substringBefore(line, ":"));
    String[] partitionedItemDetails = StringUtils.split(StringUtils.substringAfter(line, ":")," ");
    List<Item> itemDetails = Arrays.stream(partitionedItemDetails)
            .map(s -> StringUtils.substringBetween(StringUtils.substringAfter(s, ":"), "(", ")"))
            .map(s -> {
              String[] itemDetail = StringUtils.split(s,",");
              return new Item(
                      Integer.valueOf(itemDetail[0]),
                      Double.valueOf(itemDetail[1]),
                      Double.valueOf(StringUtils.remove(itemDetail[2],"$")));
            })
            .collect(Collectors.toList());

    return Pair.of(packageSize, itemDetails);
  }

  private  String processLineOfDate2(String line){
    return null;
  }

}
