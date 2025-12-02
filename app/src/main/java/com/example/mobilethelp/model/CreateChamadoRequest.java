package com.example.mobilethelp.model;

public class CreateChamadoRequest {
    private String titulo;
    private String descricao;

    public CreateChamadoRequest(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }
    // Getters e Setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}