package com.projetobd.controllers;

import com.projetobd.Database;
import com.projetobd.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddEntidade {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtDesignacao;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtMorada;

    @FXML
    private MenuButton menuPais;

    @FXML
    public void initialize() {
        carregarPaises();
    }

    @FXML
    void adicionar(ActionEvent event) {
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String designacao = txtDesignacao.getText();
        String telefone = txtTelefone.getText();
        String morada = txtMorada.getText();
        String pais = menuPais.getText();

        int idPais = getIdPais(pais);
        if (idPais == -1) {
            showAlert(Alert.AlertType.ERROR, "Erro", "País selecionado não encontrado.");
            return;
        }

        String sql = "INSERT INTO Entidade (nome, email, telefone, designacao, morada, id_pais) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.setString(3, telefone);
            pstmt.setString(4, designacao);
            pstmt.setString(5, morada);
            pstmt.setInt(6, idPais);

            pstmt.executeUpdate();
            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Entidade adicionada com sucesso!");

            // Limpar campos após inserção
            txtNome.clear();
            txtEmail.clear();
            txtDesignacao.clear();
            txtTelefone.clear();
            txtMorada.clear();
            menuPais.setText("Países");

        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao adicionar a entidade: " + e.getMessage());
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao carregar a tela principal: " + e.getMessage());
        }
    }

    private void carregarPaises() {
        String sql = "SELECT nomePais FROM Pais"; // Assume uma tabela 'Pais' com uma coluna 'nome'

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String paisNome = rs.getString("nomePais");
                MenuItem menuItem = new MenuItem(paisNome);
                menuItem.setOnAction(event -> menuPais.setText(paisNome));
                menuPais.getItems().add(menuItem);
            }

        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao carregar países: " + e.getMessage());
        }
    }

    private int getIdPais(String nomePais) {
        String sql = "SELECT ID_pais FROM Pais WHERE nomePais = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nomePais);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID_pais");
                }
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao buscar id do país: " + e.getMessage());
        }
        return -1;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
