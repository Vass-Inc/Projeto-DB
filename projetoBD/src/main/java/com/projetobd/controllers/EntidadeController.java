package com.projetobd.controllers;

import com.projetobd.Database;
import com.projetobd.Main;
import com.projetobd.models.Entidade;
import com.projetobd.models.Projetos;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EntidadeController {


    private void configurarColunas() {
        this.colunaIdEntidade.setCellValueFactory(new PropertyValueFactory<>("idProjeto"));
        this.colunaNome.setCellValueFactory(new PropertyValueFactory<>("nomeCurto"));
        this.colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        this.colunaPalavraChave.setCellValueFactory(new PropertyValueFactory<>("palavraChave"));
        this.colunaDataInicio.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        this.colunaDataFim.setCellValueFactory(new PropertyValueFactory<>("dataFim")); // Ensure column matches property
    }

    private void carregarDados() {
        try {
            Connection connection = Database.getConnection();
            String query = "SELECT p.ID_projeto, p.nomeCurto, p.titulo, p.palavraChave, p.dataInicio, p.dataFim FROM Projetos p";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                entidade.add(new Entidade(
                        rs.getInt("ID_projeto"),
                        rs.getString("nomeCurto"),
                        rs.getString("titulo"),
                        rs.getString("palavraChave"),
                        rs.getString("dataInicio"),
                        rs.getString("dataFim"),
                        rs.getString("nomePais")
                ));
            }

            rs.close();
            statement.close();
            connection.close();

            tableView.setItems(projetos);

        } catch (SQLException e) {
            Logger.getLogger(ProjetosController.class.getName()).log(Level.SEVERE, null, e);
            showAlert("Erro ao carregar dados", e.getMessage());
        }
    }

    public void handleAdd(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addEntidade.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void voltar(ActionEvent actionEvent) {
    }
}
