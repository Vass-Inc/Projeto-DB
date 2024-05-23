package com.projetobd.models;

public class AreaProjeto {
    private int idProjeto;
    private int idAreaCientifica;

    public AreaProjeto(int idProjeto, int idAreaCientifica) {
        this.idProjeto = idProjeto;
        this.idAreaCientifica = idAreaCientifica;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public int getIdAreaCientifica() {
        return idAreaCientifica;
    }

    public void setIdAreaCientifica(int idAreaCientifica) {
        this.idAreaCientifica = idAreaCientifica;
    }
}
