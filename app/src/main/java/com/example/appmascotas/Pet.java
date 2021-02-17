package com.example.appmascotas;

import java.io.Serializable;

public class Pet implements Serializable {
    private String name;
    private int Foto;
    private String like;

    public Pet(String name, int foto, String like) {
        this.name = name;
        Foto = foto;
        this.like = like;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFoto() {
        return Foto;
    }

    public void setFoto(int foto) {
        Foto = foto;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }
}
