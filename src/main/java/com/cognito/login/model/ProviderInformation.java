package com.cognito.login.model;

public class ProviderInformation {
    private String userId;
    private String providerName;
    private ProviderType providerType;
    private String issuer;

    public ProviderInformation() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public ProviderType getProviderType() {
        return providerType;
    }

    public void setProviderType(ProviderType providerType) {
        this.providerType = providerType;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @Override
    public String toString() {
        return "ProviderInformation{" +
                "userId='" + userId + '\'' +
                ", providerName='" + providerName + '\'' +
                ", providerType=" + providerType +
                ", issuer='" + issuer + '\'' +
                '}';
    }
}
