package com.example.mobilethelp.model;

import com.google.gson.annotations.SerializedName;

public class Organizacao {
    @SerializedName("id_organizacao")
    private Integer id;

    public Organizacao(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}