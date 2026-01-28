package com.example.app;

public class VulnerableCode {
    
    // Hardcoded credentials - Security vulnerability
    private static final String PASSWORD = "admin123";
    private static final String API_KEY = "sk-1234567890abcdef";
    
    public boolean authenticate(String inputPassword) {
        // Weak comparison
        if (inputPassword.equals(PASSWORD)) {
            return true;
        }
        return false;
    }
    
    public String getApiKey() {
        return API_KEY;
    }
    
    // SQL Injection vulnerability
    public String getUserData(String userId) {
        String query = "SELECT * FROM users WHERE id = '" + userId + "'";
        return query;
    }
}
