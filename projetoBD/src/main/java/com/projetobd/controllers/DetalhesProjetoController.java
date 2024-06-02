package com.projetobd.controllers;

import com.projetobd.Database;
import com.projetobd.Main;
import com.projetobd.models.Projetos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DetalhesProjetoController {
    
    public TextField txtNomeCurto, txtTitulo, txtPalavrasChave, txtDominioCientifico, txtAreaCientifica, txtComp;
    public TextArea txtDescricao;
    public TextField txtNomeEnt, txtEmail, txtEstado, txtPrograma, txtTelefone, txtDesignacao, txtMorada, txtTipoPub;
    public TextField txtPub, txtDepartamento, txtDataInicio, txtDataFim;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;

    private int idProjeto;

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
        carregarDetalhesProjeto();
    }

    private void carregarDetalhesProjeto() {
        try (Connection connection = Database.getConnection()) {
            String query = "SELECT DISTINCT " +
                    "    p.nomeCurto, " +
                    "    p.titulo, " +
                    "    p.descricao, " +
                    "    p.palavraChave, " +
                    "    p.dataInicio, " +
                    "    p.dataFim, " +
                    "    e.nome AS nomeEntidade, " +
                    "    e.email AS emailEntidade, " +
                    "    e.telefone AS telefoneEntidade, " +
                    "    e.designacao AS designacaoEntidade, " +
                    "    e.morada AS moradaEntidade, " +
                    "    nd.nomeDepartamento, " +
                    "    te.estado, " +
                    "    td.dominioCientifico, " +
                    "    ta.areaCientifica " +
                    "FROM " +
                    "    Projetos p " +
                    "JOIN " +
                    "    ProjetosEntidade pe ON p.ID_projeto = pe.ID_projeto " +
                    "JOIN " +
                    "    Entidade e ON pe.ID_entidade = e.ID_entidade " +
                    "JOIN " +
                    "    Departamento d ON e.ID_entidade = d.ID_membro " +
                    "JOIN " +
                    "    NomeDepartamento nd ON d.ID_departamento = nd.ID_departamento " +
                    "JOIN " +
                    "    EstadoProjeto ep ON p.ID_projeto = ep.ID_projeto " +
                    "JOIN " +
                    "    TipoEstado te ON ep.ID_tipoEstado = te.ID_tipoEstado " +
                    "JOIN " +
                    "    DominioProjeto dp ON p.ID_projeto = dp.ID_projeto " +
                    "JOIN " +
                    "    TipoDominio td ON dp.ID_dominio = td.ID_dominio " +
                    "JOIN " +
                    "    AreaProjeto ap ON p.ID_projeto = ap.ID_projeto " +
                    "JOIN TipoArea ta ON ap.ID_areaCientifica = ta.ID_areaCientifica " +
                    "WHERE p.ID_projeto = ?";

            CallableStatement statement = connection.prepareCall(query);
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
                Stage stage = (Stage) txtNomeCurto.getScene().getWindow();
                if (stage != null) {
                    stage.close();
                }
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

    public void editar(ActionEvent actionEvent) {
    }

    public void guardar(ActionEvent actionEvent) {

    }
}
