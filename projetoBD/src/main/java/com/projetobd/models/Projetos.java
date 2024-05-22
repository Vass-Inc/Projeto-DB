package com.projetobd.models;

import javax.sound.midi.spi.MidiDeviceProvider;

public class Projetos {
    private String nome;
    private String titulo;
    private String descricao;
    private String dataInicio;
    private String dataFim;

    public Projetos(String nome, String titulo, String descricao, String dataInicio, String dataFim){
        this.nome = nome;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
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

    public String getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }
    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }


}
