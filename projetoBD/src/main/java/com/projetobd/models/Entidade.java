package com.projetobd.models;

public class Entidade {
    private final int id;
    private final String nome;
    private final String email;
    private final String telefone;
    private final String designacao;
    private final String morada;
    private final String nomePais;

    public Entidade(int id, String nome, String email, String telefone, String designacao, String morada, String nomePais) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.designacao = designacao;
        this.morada = morada;
        this.nomePais = nomePais;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getDesignacao() {
        return designacao;
    }

    public String getMorada() {
        return morada;
    }

    public String getNomePais() {
        return nomePais;
    }
}

