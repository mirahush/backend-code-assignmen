package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.Objects;

public class PackerTest {


    @Test
    public void test() throws APIException {
        URL url = getClass().getClassLoader().getResource("example_input");
        Packer.pack(Objects.requireNonNull(url).getPath());
    }
}
