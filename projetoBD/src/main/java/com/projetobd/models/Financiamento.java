package com.projetobd.models;

public class Financiamento {
    private int idFinanciamento;
    private String tipo;

    public Financiamento(int idFinanciamento, String tipo) {
        this.idFinanciamento = idFinanciamento;
        this.tipo = tipo;
    }

    public int getIdFinanciamento() {
        return idFinanciamento;
    }

    public void setIdFinanciamento(int idFinanciamento) {
        this.idFinanciamento = idFinanciamento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
