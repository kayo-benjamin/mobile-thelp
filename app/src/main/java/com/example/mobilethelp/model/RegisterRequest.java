package com.example.mobilethelp.model;

public class RegisterRequest {
    private String email;
    private String nome;
    private String confirmarSenha;
    private String senha;

    public RegisterRequest(String email, String nome, String confirmarSenha, String senha) {
        this.email = email;
        this.nome = nome;
        this.confirmarSenha = confirmarSenha;
        this.senha = senha;
    }

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}