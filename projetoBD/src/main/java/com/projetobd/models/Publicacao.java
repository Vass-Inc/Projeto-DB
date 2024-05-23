package com.projetobd.models;

public class Publicacao {
    private int IDpublicacao;
    private String tipo;
    private String valor;

    public Publicacao(String tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public int getIDpublicacao() {
        return IDpublicacao;
    }

    public void setIDpublicacao(int IDpublicacao) {
        this.IDpublicacao = IDpublicacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
