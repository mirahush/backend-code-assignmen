package com.mobiquity.service;

import java.io.BufferedReader;

public interface FileAccessService {

    BufferedReader readFile(String filePath);
}
