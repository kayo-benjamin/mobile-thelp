package com.example.mobilethelp.service;

import com.example.mobilethelp.interfaces.ApiClient;

public class ChatService extends ApiClient {
    private String baseUrl = "";

    public ChatService(){
        super();
        this.baseUrl = BASE_URL + "chat";
    }
    public String getBaseUrl(){
        return this.baseUrl;
    }
    public static ChatService getInstance(){
        return new ChatService();
    }
    public void sendMessage(String message){

    }
    public void receiveMessage(String message){

    }
}
