package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PackerTest {


    @Test
    public void test() throws APIException {
        String s = Packer.pack("example_input");
        Assertions.assertNotNull(s);
    }
}
