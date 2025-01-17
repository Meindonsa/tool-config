package com.meindonsa.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.meindonsa.config.utils.Functions;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Functions.class)
class ToolConfigApplicationTests {

    final String text = "Hello";

    @Test
    void contextLoads() {}

    @Test
    public void upperCase() {
        final String result = "Hello";
        assertEquals(result, Functions.toCapitalize(text));
    }

    @Test
    void isInvalidString() {
        assertTrue(Functions.isInvalidString(""));
        assertTrue(Functions.isInvalidString(null));
    }

    @Test
    void isValidString() {
        assertFalse(Functions.isInvalidString(text));
    }
}
