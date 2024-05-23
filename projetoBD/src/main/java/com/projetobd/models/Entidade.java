package com.projetobd.models;

public class Entidade {
    private int idEntidade;
    private String nome;
    private String email;
    private String telefone;
    private String designacao;
    private String morada;
    private int idProjeto;
    private int idPais;

    public Entidade(int idEntidade, String nome, String email, String telefone, String designacao, String morada, int idProjeto, int idPais) {
        this.idEntidade = idEntidade;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.designacao = designacao;
        this.morada = morada;
        this.idProjeto = idProjeto;
        this.idPais = idPais;
    }

    public int getIdEntidade() {
        return idEntidade;
    }

    public void setIdEntidade(int idEntidade) {
        this.idEntidade = idEntidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }
}

