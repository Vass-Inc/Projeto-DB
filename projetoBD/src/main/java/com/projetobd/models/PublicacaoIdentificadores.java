package com.projetobd.models;

public class PublicacaoIdentificadores {
    private int idProjeto;
    private int idIdentificadores;

    public PublicacaoIdentificadores(int idProjeto, int idIdentificadores) {
        this.idProjeto = idProjeto;
        this.idIdentificadores = idIdentificadores;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public int getIdIdentificadores() {
        return idIdentificadores;
    }

    public void setIdIdentificadores(int idIdentificadores) {
        this.idIdentificadores = idIdentificadores;
    }
}

