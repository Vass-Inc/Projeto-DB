package com.projetobd.controllers;

import com.projetobd.Database;
import com.projetobd.models.Projetos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private TableView<Projetos> tableView;
    @FXML
    private TableColumn<Projetos, Integer> colunaIdProjeto;
    @FXML
    private TableColumn<Projetos, String> colunaNomeCurto;
    @FXML
    private TableColumn<Projetos, String> colunaTitulo;
    @FXML
    private TableColumn<Projetos, String> colunaDescricao;
    @FXML
    private TableColumn<Projetos, String> colunaPalavraChave;
    @FXML
    private TableColumn<Projetos, java.sql.Date> colunaDataInicio;
    @FXML
    private TableColumn<Projetos, java.sql.Date> colunaDataFim;

    private ObservableList<Projetos> projetoList = FXCollections.observableArrayList();

    private void initialize() {
        colunaIdProjeto.setCellValueFactory(new PropertyValueFactory<>("idProjeto"));
        colunaNomeCurto.setCellValueFactory(new PropertyValueFactory<>("nomeCurto"));
        colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaPalavraChave.setCellValueFactory(new PropertyValueFactory<>("palavraChave"));
        colunaDataInicio.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        colunaDataFim.setCellValueFactory(new PropertyValueFactory<>("dataFim"));

        loadDataBase();

        tableView.setRowFactory(tv -> {
            TableRow<Projetos> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Projetos projeto = row.getItem();
                    openDetalhesProjeto(projeto);
                }
            });
            return row;
        });
    }

    private void openDetalhesProjeto(Projetos projeto) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("detalhesProjeto.fxml"));
            Parent root = loader.load();

            DetalhesProjetoController detalhesProjetoController = loader.getController();

            detalhesProjetoController.initData(projeto);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Detalhes do Projeto");

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void loadDataBase() {
        projetoList.setAll(Database.getAllProjetos());
        tableView.setItems(projetoList);
    }

    public void handleAdd(ActionEvent actionEvent) {
    }

    public void handleEdit(ActionEvent actionEvent) {
    }

    public void handleDelete(ActionEvent actionEvent) {

    }
}
