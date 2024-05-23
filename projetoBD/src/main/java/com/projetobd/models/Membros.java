package com.projetobd.models;

public class Membros {
    private int IDmembros;
    private int numFuncionario;
    private String nome;
    private String orcid;
    private String funcao;

    public Membros(int numFuncionario, String nome, String orcid, String funcao) {
        this.numFuncionario = numFuncionario;
        this.nome = nome;
        this.orcid = orcid;
        this.funcao = funcao;
    }

    public int getIDmembros() {
        return IDmembros;
    }

    public void setIDmembros(int IDmembros) {
        this.IDmembros = IDmembros;
    }

    public int getNumFuncionario() {
        return numFuncionario;
    }

    public void setNumFuncionario(int numFuncionario) {
        this.numFuncionario = numFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOrcid() {
        return orcid;
    }

    public void setOrcid(String orcid) {
        this.orcid = orcid;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
