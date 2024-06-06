package com.projetobd.controllers;

import com.projetobd.Database;
import com.projetobd.Main;
import com.projetobd.models.Projetos;
import com.projetobd.models.Utils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
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

public class ProjetosController {

    @FXML
    private TableView<Projetos> tableView;
    @FXML
    private TableColumn<Projetos, Integer> colunaIdProjeto;
    @FXML
    private TableColumn<Projetos, String> colunaNomeCurto;
    @FXML
    private TableColumn<Projetos, String> colunaTitulo;
    @FXML
    private TableColumn<Projetos, String> colunaPalavraChave;
    @FXML
    private TableColumn<Projetos, Date> colunaDataInicio;
    @FXML
    private TableColumn<Projetos, Date> colunaDataFim;

    @FXML
    private void initialize() {
        carregarDados();

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Projetos projetoSelecionado = tableView.getSelectionModel().getSelectedItem();
                if (projetoSelecionado != null) {
                    mostrarDetalhesProjeto(projetoSelecionado.getIdProjeto());
                }
            }
        });
    }

    private void carregarDados() {
        Utils.projetos.clear();
        try (Connection con = Database.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Projetos")) {

            while (rs.next()) {
                System.err.println(rs.getString("nomeCurto"));
                Utils.projetos.add(new Projetos(
                        rs.getInt("ID_projeto"),
                        rs.getString("nomeCurto"),
                        rs.getString("titulo"),
                        rs.getString("palavraChave"),
                        rs.getDate("dataInicio").toLocalDate(), // Ensure to convert SQL date to LocalDate
                        rs.getDate("dataFim").toLocalDate() // Ensure to convert SQL date to LocalDate
                ));
            }
        } catch (SQLException e) {
            Logger.getLogger(ProjetosController.class.getName()).log(Level.SEVERE, null, e);
            showAlert("Erro ao carregar dados", e.getMessage());
        }

        this.tableView.setItems(Utils.projetos);
        this.colunaIdProjeto.setCellValueFactory(new PropertyValueFactory<>("idProjeto"));
        this.colunaNomeCurto.setCellValueFactory(new PropertyValueFactory<>("nomeCurto"));
        this.colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        this.colunaPalavraChave.setCellValueFactory(new PropertyValueFactory<>("palavraChave"));
        this.colunaDataInicio.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        this.colunaDataFim.setCellValueFactory(new PropertyValueFactory<>("dataFim"));
    }

    private void mostrarDetalhesProjeto(int idProjeto) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("detalhesProjeto.fxml"));
            Parent root = loader.load();

            DetalhesProjetoController controller = loader.getController();
            controller.setIdProjeto(idProjeto);

            Scene scene = new Scene(root);
            Stage stage = (Stage) tableView.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Detalhes do Projeto");
            stage.show();
        } catch (IOException e) {
            showAlert("Erro ao carregar tela de detalhes", e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void handleAdd(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addProjeto.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void voltar(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
