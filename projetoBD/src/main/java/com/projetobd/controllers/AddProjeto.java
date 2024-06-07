package com.projetobd.controllers;

import com.projetobd.Database;
import com.projetobd.Main;
import com.projetobd.models.Entidade;
import com.projetobd.models.Estado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

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
    @FXML
    private MenuButton menuEstado;

    public void initialize() {
        loadEntidades();
        carregarEstados();
    }

    @FXML
    private void adicionar() {
        String dominioCientifico = txtDominioCientifico.getText();
        String areaCientifica = txtAreaCientifica.getText();
        String nomeDepartamento = txtDepartamento.getText();
        String tipo = txtPub.getText();
        String valor = txtTipoPub.getText();
        String keyword = txtPalavrasChave.getText();

        try {
            Connection connection = Database.getConnection();
            connection.setAutoCommit(false);

            int idProjeto = inserirProjeto(connection);
            int idEntidade = getSelectedEntidadeId();
            int idEstado = getIdEstado();

            int idDominio = inserirTipoDominio(connection, dominioCientifico);
            int idArea = inserirTipoArea(connection, areaCientifica);
            int idDepartamento = inserirNomeDepartamento(connection, nomeDepartamento);
            int idPublicacao = inserirPublicacao(connection, tipo, valor);
            int idKeyword = inserirKeywords(connection, keyword);

            inserirEntidadeProjeto(connection, idEntidade, idProjeto);
            inserirDominioProjeto(connection, idDominio, idProjeto);
            inserirAreaProjeto(connection, idArea, idProjeto);
            inserirPublicacaoProjeto(connection, idPublicacao, idProjeto);
            inserirKeywordProjeto(connection, idKeyword, idProjeto);

            connection.commit();
            connection.setAutoCommit(true);

            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // INSERTS

    private int inserirProjeto(Connection conn) throws SQLException {
        String sqlProjetos = "INSERT INTO Projetos (nomeCurto, titulo, descricao, palavraChave, dataInicio, dataFim)" +
                " VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmtProjetos = conn.prepareStatement(sqlProjetos, Statement.RETURN_GENERATED_KEYS)) {
            pstmtProjetos.setString(1, txtNomeCurto.getText().isEmpty() ? null : txtNomeCurto.getText());
            pstmtProjetos.setString(2, txtTitulo.getText().isEmpty() ? null : txtTitulo.getText());
            pstmtProjetos.setString(3, txtDescricao.getText().isEmpty() ? null : txtDescricao.getText());
            pstmtProjetos.setString(4, txtPalavrasChave.getText().isEmpty() ? null : txtPalavrasChave.getText());
            try {
                pstmtProjetos.setDate(5, txtDataInicio.getText().isEmpty() ? null : java.sql.Date.valueOf(txtDataInicio.getText()));
            } catch (IllegalArgumentException e) {
                pstmtProjetos.setDate(5, null);
            }

            try {
                pstmtProjetos.setDate(6, txtDataFim.getText().isEmpty() ? null : java.sql.Date.valueOf(txtDataFim.getText()));
            } catch (IllegalArgumentException e) {
                pstmtProjetos.setDate(6, null);
            }

            pstmtProjetos.executeUpdate();

            try (ResultSet generatedKeys = pstmtProjetos.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        }
        return -1;
    }
    private int inserirTipoDominio(Connection conn, String dominioCientifico) throws SQLException {
        String sql = "INSERT INTO TipoDominio (dominioCientifico) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, dominioCientifico);
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        }
        return -1;
    }
    private int inserirTipoArea(Connection conn, String areaCientifica) throws SQLException {
        String sql = "INSERT INTO TipoArea (areaCientifica) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, areaCientifica);
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        }
        return -1;
    }
    private int inserirNomeDepartamento(Connection conn, String nomeDepartamento) throws SQLException {
        String sql = "INSERT INTO NomeDepartamento (nomeDepartamento) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, nomeDepartamento);
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        }
        return -1;
    }
    private int inserirPublicacao(Connection conn, String tipo, String valor) throws SQLException {
        String sql = "INSERT INTO Publicacao (tipo, valor) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, tipo);
            pstmt.setString(2, valor);
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        }
        return -1;
    }
    private int inserirKeywords(Connection conn, String keyword) throws SQLException {
        String sql = "INSERT INTO Keywords (keyword) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, keyword);
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        }
        return -1;
    }

    // Relações
    private int inserirEntidadeProjeto(Connection conn, int idEntidade, int idProjeto) throws SQLException {
        String sql = "INSERT INTO ProjetosEntidade (ID_entidade, ID_projeto) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idEntidade);
            pstmt.setInt(2, idProjeto);
            return pstmt.executeUpdate();
        }
    }
    private int inserirDominioProjeto(Connection conn, int idDominio, int idProjeto) throws SQLException {
        String sql = "INSERT INTO DominioProjeto (ID_dominio, ID_projeto) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idDominio);
            pstmt.setInt(2, idProjeto);
            return pstmt.executeUpdate();
        }
    }
    private int inserirAreaProjeto(Connection conn, int idAreaCientifica, int idProjeto) throws SQLException {
        String sql = "INSERT INTO AreaProjeto (ID_areaCientifica, ID_projeto) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAreaCientifica);
            pstmt.setInt(2, idProjeto);
            return pstmt.executeUpdate();
        }
    }
    private int inserirPublicacaoProjeto(Connection conn, int idPublicacao, int idProjeto) throws SQLException {
        String sql = "INSERT INTO PublicacaoProjeto (ID_publicacao, ID_projeto) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPublicacao);
            pstmt.setInt(2, idProjeto);
            return pstmt.executeUpdate();
        }
    }
    private int inserirKeywordProjeto(Connection conn, int idKeyword, int idProjeto) {
        String sql = "INSERT INTO KeywordProjeto (ID_projeto, ID_keyword) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idProjeto);
            pstmt.setInt(2, idKeyword);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Metodos alheios
    private void carregarEstados() {
        try {
            String sql = "SELECT ID_tipoEstado, estado FROM TipoEstado";
            Connection conn = Database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            menuEstado.getItems().clear();
            while (rs.next()) {
                MenuItem menuItem = new MenuItem(rs.getString("estado"));
                Estado estado = new Estado(
                  rs.getInt("ID_tipoEstado"),
                  rs.getString("estado")
                );
                menuItem.setUserData(estado);
                menuItem.setOnAction(event -> selectEstado(estado));
                menuEstado.getItems().add(menuItem);
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao carregar estados: " + e.getMessage());
        }
    }

    private void selectEstado(Estado estado){
        txtEstado.setText(estado.getEstado());
    }

    private int getIdEstado() {
        for (MenuItem menuItem : menuEstado.getItems()) {
            if (menuItem.getText().equals(txtEstado.getText())) {
                Estado estado = (Estado) menuItem.getUserData();
                return estado.getIdEstado();
            }
        }
        return -1;
    }

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

    private void selectEntidade(@NotNull Entidade entidade) {
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

    private void showAlert(Alert.AlertType error, String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
