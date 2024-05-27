package com.projetobd.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Projetos {
    private int idProjeto;
    private String nome;
    private String titulo;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    private List<Estado> estados;
    private List<Keyword> keywords;

    public Projetos(String nome, Date dataInicio, String descricao, String titulo, List<Estado> estados, List<Keyword> keywords) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.descricao = descricao;
        this.titulo = titulo;
        this.estados = estados;
        this.keywords = keywords;
    }

    public void addEstado(Estado estado) {
        this.estados.add(estado);
    }

    public void addKeywords(Keyword keyword){
        this.keywords.add(keyword);
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
}
