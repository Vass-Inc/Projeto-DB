package com.projetobd.controllers;

import com.projetobd.Database;
import com.projetobd.Main;
import com.projetobd.models.Projetos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private TableColumn<Projetos, String> colunaPalavraChave;
    @FXML
    private TableColumn<Projetos, String> colunaDataInicio;
    @FXML
    private TableColumn<Projetos, String> colunaEstado;

    private final ObservableList<Projetos> projetos = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        carregarDados();

    }

    private void carregarDados() {
        try {
            Connection connection = Database.getConnection();

            String query = "SELECT p.ID_projeto, p.nomeCurto, p.titulo, p.palavraChave, p.dataInicio, e.estado " +
                    "FROM Projetos p " +
                    "JOIN EstadoProjeto ep ON p.ID_projeto = ep.ID_projeto " +
                    "JOIN TipoEstado e ON  ep.ID_tipoEstado = e.ID_tipoEstado";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                projetos.add(new Projetos(
                        rs.getInt("ID_projeto"),
                        rs.getString("nomeCurto"),
                        rs.getString("titulo"),
                        rs.getString("palavraChave"),
                        rs.getString("dataInicio"),
                        rs.getString("estado")
                ));
            }
        } catch (SQLException var3) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, (String)null, var3);
        }
        this.tableView.setItems(projetos);

        this.colunaIdProjeto.setCellValueFactory(new PropertyValueFactory<>("ID_Projeto"));
        this.colunaNomeCurto.setCellValueFactory(new PropertyValueFactory<>("nomeCurto"));
        this.colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        this.colunaPalavraChave.setCellValueFactory(new PropertyValueFactory<>("palavraChave"));
        this.colunaDataInicio.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        this.colunaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));


    }


    private void mostrarDetalhesProjeto(int idProjeto) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/projetobd/views/detalhesProjeto.fxml"));
            Parent root = loader.load();

            DetalhesProjetoController controller = loader.getController();
            controller.setIdProjeto(idProjeto);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
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

    public void handleDelete(ActionEvent actionEvent) {

    }
}
