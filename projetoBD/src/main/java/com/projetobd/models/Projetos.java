package com.projetobd.models;

import java.time.LocalDate;

public class Projetos {
    private int idProjeto;
    private String nomeCurto;
    private String titulo;
    private String palavraChave;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public Projetos(int idProjeto, String nomeCurto, String titulo, String palavraChave, LocalDate dataInicio, LocalDate dataFim) {
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}
