package com.projetobd.controllers;

import com.projetobd.Database;
import com.projetobd.Main;
import com.projetobd.models.Entidade;
import com.projetobd.models.Projetos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DetalhesProjetoController {

    public TextField txtNomeCurto, txtTitulo, txtPalavrasChave, txtDominioCientifico, txtAreaCientifica, txtComp;
    public TextArea txtDescricao;
    public TextField txtNomeEnt, txtEmail, txtEstado, txtPrograma, txtTelefone, txtDesignacao, txtMorada, txtTipoPub;
    public TextField txtPub, txtDepartamento, txtDataInicio, txtDataFim, txtPaisEnt;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnGuardar;
    @FXML
    private MenuButton menuButtonEntidade;

    private int idProjeto;

    private boolean editMode = false;

    @FXML
    public void initialize() {
        loadEntidades();
    }

    public void guardar(ActionEvent actionEvent) {
        if (editMode) {
            setEditableFields(false);
            salvarAlteracoes();
            editMode = false;
        }

        this.btnEditar.setVisible(true);
        this.btnGuardar.setVisible(false);
        this.btnEditar.setDisable(false);
        this.btnGuardar.setDisable(true);
    }

    private void setEditableFields(boolean editable) {
        txtNomeCurto.setEditable(editable);
        txtTitulo.setEditable(editable);
        txtPalavrasChave.setEditable(editable);
        txtDominioCientifico.setEditable(editable);
        txtAreaCientifica.setEditable(editable);
        txtDescricao.setEditable(editable);
        txtNomeEnt.setEditable(editable);
        txtEmail.setEditable(editable);
        txtEstado.setEditable(editable);
        txtPrograma.setEditable(editable);
        txtTelefone.setEditable(editable);
        txtDesignacao.setEditable(editable);
        txtMorada.setEditable(editable);
        txtTipoPub.setEditable(editable);
        txtPub.setEditable(editable);
        txtDepartamento.setEditable(editable);
        txtDataInicio.setEditable(editable);
        txtDataFim.setEditable(editable);
    }

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

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idProjeto);
            System.out.println(idProjeto);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Project found with ID: " + idProjeto);
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
                System.out.println("No project found with ID: " + idProjeto);
            }

            statement.close();
            resultSet.close();


        } catch (SQLException e) {
            Logger.getLogger(DetalhesProjetoController.class.getName()).log(Level.SEVERE, null, e);
            showAlert("Erro ao carregar detalhes do projeto", e.getMessage());
        }
    }


    // CAREFULL WORK AHEAD !!!!
    private void salvarAlteracoes() {
        updateProjetos(idProjeto, txtNomeCurto, txtTitulo, txtDescricao, txtPalavrasChave, txtDataInicio, txtDataFim);
        updateEntidadeProjetos(getSelectedEntidadeId(), idProjeto);
    }

    // WORK DONE !!!!
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
        txtNomeEnt.setText(entidade.getNome());
        txtEmail.setText(entidade.getEmail());
        txtTelefone.setText(entidade.getTelefone());
        txtDesignacao.setText(entidade.getDesignacao());
        txtMorada.setText(entidade.getMorada());
        txtPaisEnt.setText(entidade.getNomePais());
    }

    private int getSelectedEntidadeId() {
        for (MenuItem menuItem : menuButtonEntidade.getItems()) {
            if (menuItem.getText().equals(txtNomeEnt.getText())) {
                Entidade entidade = (Entidade) menuItem.getUserData();
                return entidade.getId();
            }
        }
        return -1; // Or handle the case where no matching entity is found
    }

    // Metodos update
    private void updateProjetos(int idProjeto,TextField txtNomeCurto, TextField txtTitulo, TextArea txtDescricao, TextField txtPalavrasChave, TextField txtDataInicio, TextField txtDataFim) {
        String projetosQuery = "UPDATE Projetos SET " +
                "nomeCurto = ?, titulo = ?, descricao = ?, palavraChave = ?, " +
                "dataInicio = ?, dataFim = ? " +
                "WHERE ID_projeto = ?";
        try(Connection connection = Database.getConnection()) {
            PreparedStatement sta = connection.prepareStatement(projetosQuery);
            sta.setString(1, txtNomeCurto.getText());
            sta.setString(2, txtTitulo.getText());
            sta.setString(3, txtDescricao.getText());
            sta.setString(4, txtPalavrasChave.getText());
            sta.setString(5, txtDataInicio.getText());
            sta.setString(6, txtDataFim.getText());
            sta.setInt(7, idProjeto);
            sta.executeUpdate();
            sta.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateEntidadeProjetos(int idEntidade, int idProjeto) {
        String relacionamentoQuery = "UPDATE ProjetosEntidade SET " +
                "ID_entidade = ?" +
                "WHERE ID_projeto = ?";

        try(Connection connection = Database.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(relacionamentoQuery);
            pstmt.setInt(1, idEntidade);
            pstmt.setInt(2, idProjeto);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Navegação e stuff
    public void editar(ActionEvent actionEvent) {

        setEditableFields(true);
        editMode = true;


        this.btnEditar.setVisible(false);
        this.btnGuardar.setVisible(true);
        this.btnEditar.setDisable(true);
        this.btnGuardar.setDisable(false);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void voltar(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("projetos.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
