package com.example.app;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SampleTest {

    @Test
    public void testAddition() {
        // Simple test to check addition
        assertEquals(2 + 2, 4);
    }

    @Test
    public void failingTest() {
        // Optional: use to test CI failure
        // assertEquals(2 + 2, 5);
    }
}

