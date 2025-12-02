package com.example.mobilethelp.model;

public class AddInteracaoRequest {
    private String mensagem;

    public AddInteracaoRequest(String mensagem) {
        this.mensagem = mensagem;
    }
    // Getters e Setters
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
}