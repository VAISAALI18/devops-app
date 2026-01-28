package com.example.app;

/**
 * Secure configuration class demonstrating best practices
 * Uses environment variables instead of hardcoded credentials
 */
public class SecureConfig {
    
    // Retrieve from environment variables - SECURE
    private final String apiKey;
    private final String databasePassword;
    
    public SecureConfig() {
        // Read from environment variables
        this.apiKey = System.getenv("API_KEY");
        this.databasePassword = System.getenv("DB_PASSWORD");
        
        // Validate that credentials are provided
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException("API_KEY environment variable not set");
        }
        if (databasePassword == null || databasePassword.isEmpty()) {
            throw new IllegalStateException("DB_PASSWORD environment variable not set");
        }
    }
    
    public String getApiKey() {
        return apiKey;
    }
    
    public String getDatabasePassword() {
        return databasePassword;
    }
    
    /**
     * Secure authentication using parameterized queries
     * Prevents SQL injection
     */
    public String buildSecureQuery(String userId) {
        // Use parameterized query placeholder instead of string concatenation
        return "SELECT * FROM users WHERE id = ?";
    }
}
