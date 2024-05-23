package com.projetobd.models;

public class FinancaProjeto {
    private int idProjeto;
    private int idPrograma;
    private int idTipoFinanciamento;

    public FinancaProjeto(int idProjeto, int idPrograma, int idTipoFinanciamento) {
        this.idProjeto = idProjeto;
        this.idPrograma = idPrograma;
        this.idTipoFinanciamento = idTipoFinanciamento;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    public int getIdTipoFinanciamento() {
        return idTipoFinanciamento;
    }

    public void setIdTipoFinanciamento(int idTipoFinanciamento) {
        this.idTipoFinanciamento = idTipoFinanciamento;
    }
}

