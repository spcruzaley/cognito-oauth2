package com.cognito.login.model;

public class UserInfo {

    private String email;
    private String username;
    private String rol;
    private String imagePath;
    private ProviderInformation providerInformation;

    public UserInfo() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public ProviderInformation getProviderInformation() {
        return providerInformation;
    }

    public void setProviderInformation(ProviderInformation providerInformation) {
        this.providerInformation = providerInformation;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
