package com.example.mobilethelp.service;

import com.example.mobilethelp.model.ChatMessage;
import com.example.mobilethelp.model.ChatSession;
import com.example.mobilethelp.model.SendMessageRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ChatService {

    /**
     * Inicia uma nova sessão de chat.
     * O backend encontra um atendente e cria a sessão, retornando os detalhes.
     * Endpoint corrigido de "chat/start" para "chat".
     */
    @POST("chat")
    Call<ChatSession> startChat();

    /**
     * Busca todas as mensagens de uma sessão de chat específica.
     * Endpoint corrigido de "chat/{sessionId}/messages" para "chat/{id}".
     */
    @GET("chat/{id}")
    Call<List<ChatMessage>> getChatMessages(@Path("id") Long sessionId);

    /**
     * Envia uma nova mensagem para uma sessão de chat específica.
     * Endpoint corrigido de "chat/{sessionId}/messages" para "chat/{id}".
     */
    @POST("chat/{id}")
    Call<ChatMessage> sendMessage(@Path("id") Long sessionId, @Body SendMessageRequest message);
}