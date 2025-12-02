package com.example.mobilethelp.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Chamado {

    @SerializedName("id_chamado")
    private Integer id;

    @SerializedName("chaTitulo")
    private String titulo;

    @SerializedName("chaDescricao")
    private String descricao;

    @SerializedName("idOrganizacao")
    private Organizacao idOrganizacao; // Corrigido para objeto

    @SerializedName("idUsuarioAbertura")
    private Usuario idUsuarioAbertura; // Corrigido para objeto

    @SerializedName("idUsuarioAtribuido")
    private Usuario idUsuarioAtribuido; // Corrigido para objeto

    @SerializedName("chaStatus")
    private String status;

    @SerializedName("chaPrioridade")
    private String prioridade;

    @SerializedName("chaCriadoEm")
    private String dataAbertura;

    private transient List<Interacao> interacoes;

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Organizacao getIdOrganizacao() { return idOrganizacao; }
    public void setIdOrganizacao(Organizacao idOrganizacao) { this.idOrganizacao = idOrganizacao; }
    public Usuario getIdUsuarioAbertura() { return idUsuarioAbertura; }
    public void setIdUsuarioAbertura(Usuario idUsuarioAbertura) { this.idUsuarioAbertura = idUsuarioAbertura; }
    public Usuario getIdUsuarioAtribuido() { return idUsuarioAtribuido; }
    public void setIdUsuarioAtribuido(Usuario idUsuarioAtribuido) { this.idUsuarioAtribuido = idUsuarioAtribuido; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getPrioridade() { return prioridade; }
    public void setPrioridade(String prioridade) { this.prioridade = prioridade; }
    public String getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(String dataAbertura) { this.dataAbertura = dataAbertura; }
    public List<Interacao> getInteracoes() { return interacoes; }
    public void setInteracoes(List<Interacao> interacoes) { this.interacoes = interacoes; }
}