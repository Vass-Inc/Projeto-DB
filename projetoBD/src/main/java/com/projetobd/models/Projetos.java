package com.projetobd.models;

import java.sql.Date;

public class Projetos {
    private int idProjeto;
    private String nomeCurto;
    private String titulo;
    private String palavraChave;
    private Date dataInicio;
    private Date dataFim;

    public Projetos(int idProjeto, String nomeCurto, String titulo, String palavraChave, Date dataInicio, Date dataFim) {
        this.idProjeto = idProjeto;
        this.nomeCurto = nomeCurto;
        this.titulo = titulo;
        this.palavraChave = palavraChave;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getNomeCurto() {
        return nomeCurto;
    }

    public void setNomeCurto(String nomeCurto) {
        this.nomeCurto = nomeCurto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPalavraChave() {
        return palavraChave;
    }

    public void setPalavraChave(String palavraChave) {
        this.palavraChave = palavraChave;
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
