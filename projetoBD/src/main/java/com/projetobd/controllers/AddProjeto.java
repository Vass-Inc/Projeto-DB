package com.projetobd.controllers;

import com.projetobd.Database;
import com.projetobd.Main;
import com.projetobd.models.Entidade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;

public class AddProjeto {

    @FXML
    public TextField txtNomeCurto, txtTitulo, txtPalavrasChave, txtDominioCientifico, txtAreaCientifica, txtComp;
    @FXML
    public TextArea txtDescricao;
    @FXML
    public TextField txtNomeEntidade, txtEmailEntidade, txtEstado, txtPrograma, txtTelefoneEntidade, txtDesignacaoEntidade, txtMoradaEntidade, txtTipoPub;
    @FXML
    public TextField txtPub, txtDepartamento, txtDataInicio, txtDataFim, txtPaisEntidade;
    @FXML
    private MenuButton menuButtonEntidade;

    public void initialize() {
        loadEntidades();
    }

    @FXML
    private void adicionar() {
        try {
            Connection conn = Database.getConnection();
            conn.setAutoCommit(false);

            String sqlProjetos = "INSERT INTO Projetos (nomeCurto, titulo, descricao, palavraChave, dataInicio, dataFim, ID_entidade)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmtProjetos = conn.prepareStatement(sqlProjetos, Statement.RETURN_GENERATED_KEYS);

            pstmtProjetos.setString(1, txtNomeCurto.getText());
            pstmtProjetos.setString(2, txtTitulo.getText());
            pstmtProjetos.setString(3, txtDescricao.getText());
            pstmtProjetos.setString(4, txtPalavrasChave.getText());
            pstmtProjetos.setDate(5, java.sql.Date.valueOf(txtDataInicio.getText()));
            pstmtProjetos.setDate(6, java.sql.Date.valueOf(txtDataFim.getText()));


            pstmtProjetos.executeUpdate();

            ResultSet generatedKeys = pstmtProjetos.getGeneratedKeys();
            int projetoId = -1;
            if (generatedKeys.next()) {
                projetoId = generatedKeys.getInt(1);
            }
            generatedKeys.close();
            pstmtProjetos.close();
            conn.commit();
            conn.setAutoCommit(true);

            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metodos alheios
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
        txtNomeEntidade.setText(entidade.getNome());
        txtEmailEntidade.setText(entidade.getEmail());
        txtTelefoneEntidade.setText(entidade.getTelefone());
        txtDesignacaoEntidade.setText(entidade.getDesignacao());
        txtMoradaEntidade.setText(entidade.getMorada());
        txtPaisEntidade.setText(entidade.getNomePais());
    }

    private int getSelectedEntidadeId() {
        for (MenuItem menuItem : menuButtonEntidade.getItems()) {
            if (menuItem.getText().equals(txtNomeEntidade.getText())) {
                Entidade entidade = (Entidade) menuItem.getUserData();
                return entidade.getId();
            }
        }
        return -1; // Or handle the case where no matching entity is found
    }

    private void clearFields() {
        txtNomeCurto.clear();
        txtTitulo.clear();
        txtDescricao.clear();
        txtPalavrasChave.clear();
        txtDataInicio.clear();
        txtDataFim.clear();
        txtNomeEntidade.clear();
        txtEmailEntidade.clear();
        txtTelefoneEntidade.clear();
        txtDesignacaoEntidade.clear();
        txtMoradaEntidade.clear();
        txtPaisEntidade.clear();
        txtDominioCientifico.clear();
        txtAreaCientifica.clear();
        txtPub.clear();
        txtTipoPub.clear();
        txtDepartamento.clear();
    }

    // Botões de Navegação
    public void addEntidade(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addEntidade.fxml"));
        Parent root = fxmlLoader.load();

        // Pegue o controlador da nova janela
        AddEntidade controlador = fxmlLoader.getController();
        // Passe a cena atual para o controlador da nova janela
        controlador.setCenaAnterior(((Node) actionEvent.getSource()).getScene());

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void voltar(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("projetos.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
