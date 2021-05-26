package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PackerTest {


    @Test
    public void test() throws APIException {
        String result = Packer.pack("example_input");

        assertNotNull(result);
        assertEquals(4, StringUtils.countMatches(result, "\n"));
        assertTrue(StringUtils.isNotBlank(result));
    }
}
