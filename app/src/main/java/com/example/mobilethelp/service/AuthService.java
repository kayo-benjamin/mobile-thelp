package com.example.mobilethelp.service;

import com.example.mobilethelp.model.LoginRequest;
import com.example.mobilethelp.model.LoginResponse;
import com.example.mobilethelp.model.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("auth/register")
    Call<Void> register(@Body RegisterRequest registerRequest); // Usamos Void quando não esperamos um corpo na resposta
}