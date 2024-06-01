package com.projetobd.controllers;

import com.projetobd.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddProjeto {

    public TextField txtNomeCurto, txtTitulo, txtPalavrasChave, txtDominioCientifico, txtAreaCientifica, txtComp;
    public TextArea txtDescricao;
    public TextField txtNomeEnt, txtEmail, txtEstado, txtPrograma, txtTelefone, txtDesignacao, txtMorada, txtTipoPub;
    public TextField txtPub, txtDepartamento, txtDataInicio, txtDataFim;


    public void chooseEntidade(ActionEvent actionEvent) {
    }

    public void adicionar(ActionEvent actionEvent) {
    }

    public void paraTras(ActionEvent actionEvent) {
    }

    public void voltar(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
