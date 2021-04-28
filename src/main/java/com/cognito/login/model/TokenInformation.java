package com.cognito.login.model;

public class TokenInformation {
    
    private String token;
    private String token_type;
    private Integer expiration;
    private String error_message;
    
    public TokenInformation() {
    }
    
    public TokenInformation(String token, String token_type, Integer expiration) {
        this.token = token;
        this.token_type = token_type;
        this.expiration = expiration;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getToken_type() {
        return token_type;
    }
    
    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
    
    public Integer getExpiration() {
        return expiration;
    }
    
    public void setExpiration(Integer expiration) {
        this.expiration = expiration;
    }
    
    public String getError_message() {
        return error_message;
    }
    
    public void setError_message(String error_message) {
        this.error_message = error_message;
    }
    
    @Override
    public String toString() {
        return "TokenInformation{" +
                "token='" + token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", expiration=" + expiration +
                ", error_message='" + error_message + '\'' +
                '}';
    }
}