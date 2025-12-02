package com.example.mobilethelp.model;

import java.util.List;

public class Chamado {
    private Long id;
    private String titulo;
    private String descricao;
    private String status;
    private String dataAbertura;
    private List<Interacao> interacoes;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(String dataAbertura) { this.dataAbertura = dataAbertura; }
    public List<Interacao> getInteracoes() { return interacoes; }
    public void setInteracoes(List<Interacao> interacoes) { this.interacoes = interacoes; }
}