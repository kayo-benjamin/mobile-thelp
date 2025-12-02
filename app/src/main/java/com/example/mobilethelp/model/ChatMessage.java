package com.example.mobilethelp.model;

public class ChatMessage {
    private String autor;
    private String mensagem;
    private String dataHora;

    public ChatMessage(String autor, String mensagem, String dataHora) {
        this.autor = autor;
        this.mensagem = mensagem;
        this.dataHora = dataHora;
    }
    
    // Getters e Setters
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public String getDataHora() { return dataHora; }
    public void setDataHora(String dataHora) { this.dataHora = dataHora; }
}