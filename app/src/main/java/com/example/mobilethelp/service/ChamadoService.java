package com.example.mobilethelp.service;

import com.example.mobilethelp.model.AddInteracaoRequest;
import com.example.mobilethelp.model.Chamado;
import com.example.mobilethelp.model.Interacao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ChamadoService {

    @POST("api/chamados")
    Call<Chamado> createChamado(@Body Chamado chamado);

    @GET("api/chamados")
    Call<List<Chamado>> getChamados();

    // Corrigido para Integer
    @GET("chamados/{id}")
    Call<Chamado> getChamadoById(@Path("id") Integer id);

    // Corrigido para Integer
    @POST("chamados/{id}/interacoes")
    Call<Interacao> addInteracao(@Path("id") Integer id, @Body AddInteracaoRequest addInteracaoRequest);

    // Corrigido para Integer
    @PUT("chamados/{id}/fechar")
    Call<Chamado> fecharChamado(@Path("id") Integer id);
}