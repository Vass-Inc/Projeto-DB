package com.projetobd.controllers;

import com.projetobd.Database;
import com.projetobd.Main;
import com.projetobd.models.Entidade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EntidadeController {

    @FXML
    private TableView<Entidade> tableView;
    @FXML
    private TableColumn<Entidade, Integer> colunaIdEntidade;
    @FXML
    private TableColumn<Entidade, String> colunaNome;
    @FXML
    private TableColumn<Entidade, String> colunaEmail;
    @FXML
    private TableColumn<Entidade, String> colunaTelefone;
    @FXML
    private TableColumn<Entidade, String> colunaDesinacao;
    @FXML
    private TableColumn<Entidade, String> colunaMorada;
    @FXML
    private TableColumn<Entidade, String> colunaPais;

    private final ObservableList<Entidade> entidades = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        configurarColunas();
        carregarDados();
    }

    private void configurarColunas() {
        this.colunaIdEntidade.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        this.colunaDesinacao.setCellValueFactory(new PropertyValueFactory<>("designacao"));
        this.colunaMorada.setCellValueFactory(new PropertyValueFactory<>("morada"));
        this.colunaPais.setCellValueFactory(new PropertyValueFactory<>("nomePais"));
    }

    private void carregarDados() {
        try {
            Connection connection = Database.getConnection();
            String query = "SELECT e.ID_entidade, e.nome, e.email, e.telefone, e.designacao, e.morada, p.nomePais FROM Entidade e, Pais p WHERE e.ID_pais = p.ID_pais";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                entidades.add(new Entidade(
                        rs.getInt("ID_entidade"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("designacao"),
                        rs.getString("morada"),
                        rs.getString("nomePais")
                ));
            }

            rs.close();
            statement.close();
            connection.close();

            tableView.setItems(entidades);

        } catch (SQLException e) {
            Logger.getLogger(ProjetosController.class.getName()).log(Level.SEVERE, null, e);
            showAlert("Erro ao carregar dados", e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleAdd(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addEntidade.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void voltar(ActionEvent actionEvent) {
        // Implementar lógica de voltar, se necessário
    }
}
