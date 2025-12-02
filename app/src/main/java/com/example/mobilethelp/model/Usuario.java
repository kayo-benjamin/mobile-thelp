package com.example.mobilethelp.model;

import com.google.gson.annotations.SerializedName;

public class Usuario {
    @SerializedName("id_usuario")
    private Integer id;

    public Usuario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}