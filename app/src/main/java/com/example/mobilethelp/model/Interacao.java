package com.example.mobilethelp.model;

import com.google.gson.annotations.SerializedName;

public class Interacao {

    @SerializedName("id_interacao")
    private Integer id;

    @SerializedName("id_chamado")
    private Integer idChamado;

    @SerializedName("id_usuario")
    private Integer idUsuario;

    @SerializedName("intMensagem")
    private String mensagem;

    @SerializedName("intUrlAnexo")
    private String urlAnexo;

    @SerializedName("intCriadoEm")
    private String dataHora;

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getIdChamado() { return idChamado; }
    public void setIdChamado(Integer idChamado) { this.idChamado = idChamado; }
    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public String getUrlAnexo() { return urlAnexo; }
    public void setUrlAnexo(String urlAnexo) { this.urlAnexo = urlAnexo; }
    public String getDataHora() { return dataHora; }
    public void setDataHora(String dataHora) { this.dataHora = dataHora; }
}