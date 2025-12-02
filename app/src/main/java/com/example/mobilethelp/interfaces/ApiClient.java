package com.example.mobilethelp.interfaces;

import android.content.Context;

import com.example.mobilethelp.util.SessionManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "http://192.168.48.207:8080/api/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context) {
        // Interceptor para logs
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Interceptor para adicionar o token de autenticação
        AuthInterceptor authInterceptor = new AuthInterceptor(context);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    // Classe interna para o interceptor de autenticação
    private static class AuthInterceptor implements Interceptor {
        private final SessionManager sessionManager;

        public AuthInterceptor(Context context) {
            this.sessionManager = new SessionManager(context);
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            String token = sessionManager.fetchAuthToken();

            if (token != null) {
                Request.Builder builder = originalRequest.newBuilder()
                        .header("Authorization", "Bearer " + token);
                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }

            return chain.proceed(originalRequest);
        }
    }
}