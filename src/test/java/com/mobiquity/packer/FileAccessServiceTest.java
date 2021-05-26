package com.mobiquity.packer;

import com.mobiquity.service.FileAccessService;
import com.mobiquity.service.impl.FileAccessServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileAccessServiceTest {

    private static String CORRECT_FILE_NAME = "example_input";
    private static String WRONG_FILE_NAME = "resources/example_input";

    private final FileAccessService fileAccessService;

    FileAccessServiceTest(){
        this.fileAccessService = new FileAccessServiceImpl();
    }

    @Test
    @DisplayName("TARGET: read file " +
            "WHEN: file does exists in resources " +
            "EXPECTATION: buffered reader is not null")
    public void test1(){
        BufferedReader bufferedReader = fileAccessService.readFile(CORRECT_FILE_NAME);

        assertNotNull(bufferedReader);
    }

    @Test
    @DisplayName("TARGET: read file " +
            "WHEN: file does NOT exists in resources" +
            "EXPECTATION: exception is thrown")
    public void test2(){
        assertThrows(NullPointerException.class, () -> fileAccessService.readFile(WRONG_FILE_NAME));
    }
}
