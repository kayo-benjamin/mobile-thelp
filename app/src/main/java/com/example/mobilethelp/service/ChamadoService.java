package com.example.mobilethelp.service;

import com.example.mobilethelp.model.AddInteracaoRequest;
import com.example.mobilethelp.model.Chamado;
import com.example.mobilethelp.model.CreateChamadoRequest;
import com.example.mobilethelp.model.Interacao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ChamadoService {

    // CREATE (Endpoint corrigido para corresponder ao backend)
    @POST("chamados")
    Call<Chamado> createChamado(@Body CreateChamadoRequest createChamadoRequest);

    // READ (Listar todos)
    @GET("chamados")
    Call<List<Chamado>> getChamados();

    // READ (Pegar um específico)
    @GET("chamados/{id}")
    Call<Chamado> getChamadoById(@Path("id") Long id);

    // UPDATE (Adicionar interação) - Verifique se este endpoint está correto no seu backend
    @POST("chamados/{id}/interacoes")
    Call<Interacao> addInteracao(@Path("id") Long id, @Body AddInteracaoRequest addInteracaoRequest);

    // UPDATE (Fechar chamado) - Verifique se este endpoint está correto no seu backend
    @PUT("chamados/{id}/fechar")
    Call<Chamado> fecharChamado(@Path("id") Long id);
}