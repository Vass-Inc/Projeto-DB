package com.projetobd.models;

public class Dominio {
    private int idDominio;
    private String dominio;

    public Dominio(int idDominio, String dominio) {
        this.idDominio = idDominio;
        this.dominio = dominio;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public int getIdDominio() {
        return idDominio;
    }

    public void setIdDominio(int idDominio) {
        this.idDominio = idDominio;
    }
}
