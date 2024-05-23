package com.projetobd.models;

public class Programa {
    private int idPrograma;
    private String nomePrograma;

    public Programa(int idPrograma, String nomePrograma) {
        this.idPrograma = idPrograma;
        this.nomePrograma = nomePrograma;
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getNomePrograma() {
        return nomePrograma;
    }

    public void setNomePrograma(String nomePrograma) {
        this.nomePrograma = nomePrograma;
    }
}
