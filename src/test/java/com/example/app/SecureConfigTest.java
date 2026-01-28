package com.example.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;

/**
 * Unit tests for SecureConfig class
 * Improves code coverage
 */
public class SecureConfigTest {
    
    @Before
    public void setUp() {
        // Set environment variables for testing
        System.setProperty("API_KEY", "test-api-key-12345");
        System.setProperty("DB_PASSWORD", "test-db-password");
    }
    
    @After
    public void tearDown() {
        // Clean up
        System.clearProperty("API_KEY");
        System.clearProperty("DB_PASSWORD");
    }
    
    @Test
    public void testSecureQueryGeneration() {
        // Mock environment for test
        SecureConfig config = new SecureConfig() {
            @Override
            public String getApiKey() {
                return "test-key";
            }
            
            @Override
            public String getDatabasePassword() {
                return "test-pass";
            }
        };
        
        String query = config.buildSecureQuery("123");
        
        // Verify parameterized query is used
        assertTrue("Query should use parameterized placeholder", 
                   query.contains("?"));
        assertFalse("Query should not contain direct user input", 
                    query.contains("123"));
    }
    
    @Test
    public void testSecureQueryDoesNotContainUserId() {
        SecureConfig config = new SecureConfig() {
            @Override
            public String getApiKey() {
                return "test-key";
            }
            
            @Override
            public String getDatabasePassword() {
                return "test-pass";
            }
        };
        
        String maliciousInput = "'; DROP TABLE users; --";
        String query = config.buildSecureQuery(maliciousInput);
        
        // Verify SQL injection is prevented
        assertFalse("Query should not contain malicious input", 
                    query.contains(maliciousInput));
    }
    
    @Test
    public void testQueryStructure() {
        SecureConfig config = new SecureConfig() {
            @Override
            public String getApiKey() {
                return "test-key";
            }
            
            @Override
            public String getDatabasePassword() {
                return "test-pass";
            }
        };
        
        String query = config.buildSecureQuery("user123");
        
        // Verify correct query structure
        assertEquals("SELECT * FROM users WHERE id = ?", query);
    }
}
