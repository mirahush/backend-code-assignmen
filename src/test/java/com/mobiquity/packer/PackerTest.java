package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PackerTest {


    @Test
    public void test() throws APIException {
        String result = Packer.pack("example_input");

        assertNotNull(result);
        assertTrue(StringUtils.isNotBlank(result));
    }
}
