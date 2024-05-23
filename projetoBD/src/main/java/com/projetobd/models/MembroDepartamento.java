package com.projetobd.models;

public class MembroDepartamento {
    private int idDepartamento;
    private int idMembro;

    public MembroDepartamento(int idDepartamento, int idMembro) {
        this.idDepartamento = idDepartamento;
        this.idMembro = idMembro;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getIdMembro() {
        return idMembro;
    }

    public void setIdMembro(int idMembro) {
        this.idMembro = idMembro;
    }
}

