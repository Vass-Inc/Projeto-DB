package com.projetobd.controllers;

import com.projetobd.Database;
import com.projetobd.Main;
import com.projetobd.models.Entidade;
import com.projetobd.models.Pais;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddProjeto {

    @FXML
    public TextField txtNomeCurto, txtTitulo, txtPalavrasChave, txtDominioCientifico, txtAreaCientifica, txtComp;
    @FXML
    public TextArea txtDescricao;
    @FXML
    public TextField txtNomeEntidade, txtEmailEntidade, txtEstado, txtPrograma, txtTelefoneEntidade, txtDesignacaoEntidade, txtMoradaEntidade, txtTipoPub;
    @FXML
    public TextField txtPub, txtDepartamento, txtDataInicio, txtDataFim, txtPaisEntidade;
    @FXML
    private MenuButton menuButtonEntidade;

    public void initialize() {
        loadEntidades();
    }

    public void chooseEntidade(ActionEvent actionEvent) {
    }

    public void adicionar(ActionEvent actionEvent) {
    }

    public void paraTras(ActionEvent actionEvent) {
    }

    public void voltar(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("projetos.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void loadEntidades() {
        try {
            // Connect to your database
            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT E.ID_entidade, E.nome, E.email, E.telefone, E.designacao, E.morada, p.nomePais " +
                    "FROM Entidade E, Pais p " +
                    "WHERE E.ID_pais = p.ID_pais");

            while (rs.next()) {
                MenuItem menuItem = new MenuItem(rs.getString("nome"));
                Entidade entidade = new Entidade(
                        rs.getInt("ID_entidade"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("designacao"),
                        rs.getString("morada"),
                        rs.getString("nomePais")
                );
                menuItem.setUserData(entidade);
                menuItem.setOnAction(event -> selectEntidade(entidade));
                menuButtonEntidade.getItems().add(menuItem);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void selectEntidade(Entidade entidade) {
        txtNomeEntidade.setText(entidade.getNome());
        txtEmailEntidade.setText(entidade.getEmail());
        txtTelefoneEntidade.setText(entidade.getTelefone());
        txtDesignacaoEntidade.setText(entidade.getDesignacao());
        txtMoradaEntidade.setText(entidade.getMorada());
        txtPaisEntidade.setText(entidade.getNomePais());
    }
}
