package com.projetobd.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Projetos {
    private final IntegerProperty idProjeto;
    private final StringProperty nomeCurto;
    private final StringProperty titulo;
    private final StringProperty palavraChave;
    private final SimpleStringProperty dataInicio;
    private final StringProperty estado;

    public Projetos(int idProjeto, String nomeCurto, String titulo, String palavraChave, String dataInicio, String estado) {
        this.idProjeto = new SimpleIntegerProperty(idProjeto);
        this.nomeCurto = new SimpleStringProperty(nomeCurto);
        this.titulo = new SimpleStringProperty(titulo);
        this.palavraChave = new SimpleStringProperty(palavraChave);
        this.dataInicio = new SimpleStringProperty(dataInicio);
        this.estado = new SimpleStringProperty(estado);
    }

    public int getIdProjeto() {
        return idProjeto.get();
    }

    public IntegerProperty idProjetoProperty() {
        return idProjeto;
    }

    public String getNomeCurto() {
        return nomeCurto.get();
    }

    public StringProperty nomeCurtoProperty() {
        return nomeCurto;
    }

    public String getTitulo() {
        return titulo.get();
    }

    public StringProperty tituloProperty() {
        return titulo;
    }

    public String getPalavraChave() {
        return palavraChave.get();
    }

    public StringProperty palavraChaveProperty() {
        return palavraChave;
    }

    public String getDataInicio() {
        return dataInicio.get();
    }

    public SimpleStringProperty dataInicioProperty() {
        return dataInicio;
    }

    public String getEstado() {
        return estado.get();
    }

    public StringProperty estadoProperty() {
        return estado;
    }
}
