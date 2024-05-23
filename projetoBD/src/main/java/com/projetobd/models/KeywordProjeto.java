package com.projetobd.models;

public class KeywordProjeto {
    private int idProjeto;
    private int idKeyword;

    public KeywordProjeto(int idProjeto, int idKeyword) {
        this.idProjeto = idProjeto;
        this.idKeyword = idKeyword;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public int getIdKeyword() {
        return idKeyword;
    }

    public void setIdKeyword(int idKeyword) {
        this.idKeyword = idKeyword;
    }
}

