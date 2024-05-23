package com.projetobd.models;

public class FuncaoMembro {
    private int idProjeto;
    private int idMembro;

    public FuncaoMembro(int idProjeto, int idMembro) {
        this.idProjeto = idProjeto;
        this.idMembro = idMembro;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public int getIdMembro() {
        return idMembro;
    }

    public void setIdMembro(int idMembro) {
        this.idMembro = idMembro;
    }
}
