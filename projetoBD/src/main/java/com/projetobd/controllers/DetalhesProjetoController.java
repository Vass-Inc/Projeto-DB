package com.projetobd.controllers;

import com.projetobd.Database;
import com.projetobd.Main;
import com.projetobd.models.Projetos;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetalhesProjetoController {
    
    public TextField txtNomeCurto, txtTitulo, txtPalavrasChave, txtDominioCientifico, txtAreaCientifica, txtComp;
    public TextArea txtDescricao;
    public TextField txtNomeEnt, txtEmail, txtEstado, txtPrograma, txtTelefone, txtDesignacao, txtMorada, txtTipoPub;
    public TextField txtPub, txtDepartamento, txtDataInicio, txtDataFim;

    private int idProjeto;

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
        carregarDetalhesProjeto();
    }

    private void carregarDetalhesProjeto() {
        try (Connection connection = Database.getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL ObterDetalhesProjeto(?)}");
            statement.setInt(1, idProjeto);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                txtNomeCurto.setText(resultSet.getString("nomeCurto"));
                txtTitulo.setText(resultSet.getString("titulo"));
                txtDescricao.setText(resultSet.getString("descricao"));
                txtPalavrasChave.setText(resultSet.getString("palavraChave"));
                txtDataInicio.setText(resultSet.getString("dataInicio"));
                txtDataFim.setText(resultSet.getString("dataFim"));
                txtNomeEnt.setText(resultSet.getString("nomeEntidade"));
                txtEmail.setText(resultSet.getString("emailEntidade"));
                txtTelefone.setText(resultSet.getString("telefoneEntidade"));
                txtDesignacao.setText(resultSet.getString("designacaoEntidade"));
                txtMorada.setText(resultSet.getString("moradaEntidade"));
                txtDepartamento.setText(resultSet.getString("nomeDepartamento"));
                txtEstado.setText(resultSet.getString("estado"));
                txtDominioCientifico.setText(resultSet.getString("dominioCientifico"));
                txtAreaCientifica.setText(resultSet.getString("areaCientifica"));
            } else {
                showAlert("Informação", "Projeto não encontrado.");
            }
        } catch (SQLException e) {
            showAlert("Erro ao obter detalhes do projeto", e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void paraTras(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("projetos.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
