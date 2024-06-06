package com.projetobd.controllers;

import com.projetobd.Database;
import com.projetobd.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddEntidade {

    @FXML
    private TextField txtNome, txtEmail, txtDesignacao, txtTelefone, txtMorada;
    @FXML
    private TextField txtNomePais;
    @FXML
    private Button btnGuardar, btnAdd;
    @FXML
    private MenuButton menuPais;

    private Scene cenaAnterior;

    public void setCenaAnterior(Scene cenaAnterior) {
        this.cenaAnterior = cenaAnterior;
    }

    @FXML
    public void initialize() {
        carregarPaises();
    }

    // Adicionar das Entidades
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
        if (entidadeExiste(nome)) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Entidade já existe.");
            return;
        }

        String sql = "INSERT INTO Entidade (nome, email, telefone, designacao, morada, id_pais) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

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
    void voltar(ActionEvent event) throws IOException {
        if (cenaAnterior != null) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(cenaAnterior);
            stage.show();
        } else {
            showAlert(Alert.AlertType.ERROR, "Erro", "Cena anterior não encontrada.");
        }
    }

    // Select para popular o menu
    private void carregarPaises() {
        String sql = "SELECT nomePais FROM Pais";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            menuPais.getItems().clear();
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

    // Vai buscar o ID do pais para quando for para inserir na tabela Entidades
    private int getIdPais(String nomePais) {
        String sql = "SELECT ID_pais FROM Pais WHERE nomePais = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
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

    public void addPais(ActionEvent actionEvent) {
        this.btnAdd.setVisible(false);
        this.btnGuardar.setVisible(true);
        this.btnAdd.setDisable(true);
        this.btnGuardar.setDisable(false);
        this.txtNomePais.setVisible(true);
        this.txtNomePais.setDisable(false);
    }

    public void guardaPais(ActionEvent actionEvent) {
        String nomePais = txtNomePais.getText();

        if (paisExiste(nomePais)) {
            showAlert(Alert.AlertType.ERROR, "Erro", "País com esse nome já existe.");
            return;
        }

        String sql = "INSERT INTO Pais (nomePais) " +
                "VALUES (?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nomePais);

            pstmt.executeUpdate();
            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Pais adicionado com sucesso!");


            txtNomePais.clear();
            carregarPaises();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao adicionar o País: " + e.getMessage());
        }

        // ---
        this.btnAdd.setVisible(true);
        this.btnGuardar.setVisible(false);
        this.btnAdd.setDisable(false);
        this.btnGuardar.setDisable(true);
        this.txtNomePais.setVisible(false);
        this.txtNomePais.setDisable(true);
    }


    // Verifica se existem
    private boolean entidadeExiste(String nomeEntidade) {
        String sql = "SELECT COUNT(*) FROM Entidade WHERE nome = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nomeEntidade);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao verificar existência da entidade: " + e.getMessage());
        }
        return false;
    }

    private boolean paisExiste(String nomePais) {
        String sql = "SELECT COUNT(*) FROM Pais WHERE nomePais = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nomePais);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao verificar existência do país: " + e.getMessage());
        }
        return false;
    }
}
