package com.example.mobilethelp.model;

public class SendMessageRequest {
    private String mensagem;

    public SendMessageRequest(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}