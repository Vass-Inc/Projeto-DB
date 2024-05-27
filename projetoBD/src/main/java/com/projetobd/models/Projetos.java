package com.projetobd.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;
import java.time.LocalDate;

import javafx.beans.property.*;

public class Projetos {
    private final IntegerProperty idProjeto;
    private final StringProperty nomeCurto;
    private final StringProperty titulo;
    private final StringProperty palavraChave;
    private final ObjectProperty<LocalDate> dataInicio;
    private final StringProperty estado;

    public Projetos(int idProjeto, String nomeCurto, String titulo, String palavraChave, LocalDate dataInicio, String estado) {
        this.idProjeto = new SimpleIntegerProperty(idProjeto);
        this.nomeCurto = new SimpleStringProperty(nomeCurto);
        this.titulo = new SimpleStringProperty(titulo);
        this.palavraChave = new SimpleStringProperty(palavraChave);
        this.dataInicio = new SimpleObjectProperty<>(dataInicio);
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

    public LocalDate getDataInicio() {
        return dataInicio.get();
    }

    public ObjectProperty<LocalDate> dataInicioProperty() {
        return dataInicio;
    }

    public String getEstado() {
        return estado.get();
    }

    public StringProperty estadoProperty() {
        return estado;
    }
}
