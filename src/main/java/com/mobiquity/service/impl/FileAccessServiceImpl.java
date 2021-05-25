package com.mobiquity.service.impl;

import com.mobiquity.packer.Packer;
import com.mobiquity.service.FileAccessService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

public class FileAccessServiceImpl implements FileAccessService {

    @Override
    public BufferedReader readFile(String filePath){
        return new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(Packer.class.getClassLoader().getResourceAsStream(filePath))));
    }
}
