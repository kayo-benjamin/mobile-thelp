package com.example.mobilethelp.model;

public class ChatSession {
    private Long id;
    // Outros campos que a API possa retornar, como nome do atendente, etc.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}