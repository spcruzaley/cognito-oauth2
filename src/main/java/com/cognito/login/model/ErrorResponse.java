package com.cognito.login.model;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    private List<String> messages;

    public ErrorResponse() {
        this.messages = new ArrayList<>();
    }

    public ErrorResponse(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }
}
