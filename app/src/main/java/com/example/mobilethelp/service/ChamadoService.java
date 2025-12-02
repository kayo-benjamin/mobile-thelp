package com.example.mobilethelp.service;

import com.example.mobilethelp.interfaces.ApiClient;

public class ChamadoService extends ApiClient {
    private String baseUrl = "";

    public ChamadoService() {
        super();
        this.baseUrl = BASE_URL + "chamados";
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public static ChamadoService getInstance() {
        return new ChamadoService();
    }

    public void listarChamados() {
        // Implementação para listar chamados
    }

    public void criarChamado(String titulo, String descricao) {
        // Implementação para criar um novo chamado
    }

}
