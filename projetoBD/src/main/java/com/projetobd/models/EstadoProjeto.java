package com.projetobd.models;

public class EstadoProjeto {
    private int idTipoEstado;
    private int idProjeto;

    public EstadoProjeto(int idTipoEstado, int idProjeto) {
        this.idTipoEstado = idTipoEstado;
        this.idProjeto = idProjeto;
    }

    public int getIdTipoEstado() {
        return idTipoEstado;
    }

    public void setIdTipoEstado(int idTipoEstado) {
        this.idTipoEstado = idTipoEstado;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }
}
