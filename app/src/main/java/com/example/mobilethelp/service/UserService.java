package com.example.mobilethelp.service;

import com.example.mobilethelp.interfaces.ApiClient;

public class UserService extends ApiClient {

    private String baseUrl = "";
    public UserService(){
        super();
        this.baseUrl = BASE_URL + "auth";
    }

    public String getBaseUrl(){
        return this.baseUrl;
    }

    public static UserService getInstance(){
        return new UserService();
    }

    public void login(String username, String password){

    }

    public void register(String username, String password){

    }

}
