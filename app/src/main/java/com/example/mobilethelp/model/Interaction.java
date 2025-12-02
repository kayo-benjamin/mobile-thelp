package com.example.mobilethelp.model;

public class Interaction {
    private String author;
    private String message;
    private String timestamp;

    public Interaction(String author, String message, String timestamp) {
        this.author = author;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}