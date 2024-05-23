package com.projetobd.models;

public class DominioProjeto {
    private int idDominio;
    private int idProjeto;

    public DominioProjeto(int idDominio, int idProjeto) {
        this.idDominio = idDominio;
        this.idProjeto = idProjeto;
    }

    public int getIdDominio() {
        return idDominio;
    }

    public void setIdDominio(int idDominio) {
        this.idDominio = idDominio;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }
}

