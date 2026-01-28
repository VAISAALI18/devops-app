package com.example.app;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Enhanced unit test class for basic App functionality
 */
public class AppTest {
    
    @Test
    public void testAppHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull("app should have a greeting", classUnderTest.getGreeting());
    }
    
    @Test
    public void testGreetingContent() {
        App classUnderTest = new App();
        String greeting = classUnderTest.getGreeting();
        assertTrue("Greeting should contain 'Hello'", 
                   greeting.contains("Hello"));
    }
    
    @Test
    public void testGreetingNotEmpty() {
        App classUnderTest = new App();
        String greeting = classUnderTest.getGreeting();
        assertFalse("Greeting should not be empty", 
                    greeting.isEmpty());
    }
    
    @Test
    public void testGreetingLength() {
        App classUnderTest = new App();
        String greeting = classUnderTest.getGreeting();
        assertTrue("Greeting should have reasonable length", 
                   greeting.length() > 5);
    }
    
    @Test
    public void testMultipleInstances() {
        App app1 = new App();
        App app2 = new App();
        
        assertEquals("Multiple instances should return same greeting", 
                     app1.getGreeting(), app2.getGreeting());
    }
}
