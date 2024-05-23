package com.projetobd;

import com.projetobd.models.Projetos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String URL = "";
    private static final String USER = "sa";
    private static final String PASSWORD = "p";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


    public static List<Projetos> getAllProjetos() {
        List<Projetos> projetos = new ArrayList<>();
        String query = "SELECT ID_projeto, nomeCurto, titulo, descricao, palavraChave, dataInicio, dataFim FROM Projetos_(Lista)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int idProjeto = resultSet.getInt("ID_projeto");
                String nomeCurto = resultSet.getString("nomeCurto");
                String titulo = resultSet.getString("titulo");
                String descricao = resultSet.getString("descricao");
                Date dataInicio = resultSet.getDate("dataInicio");
                Date dataFim = resultSet.getDate("dataFim");

                Projetos projeto = new Projetos(idProjeto, nomeCurto, titulo, descricao, dataInicio, dataFim);
                projetos.add(projeto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projetos;
    }

    public static void addProjeto(Projetos projeto) {
        String insertProjetoQuery = "INSERT INTO Projetos_(Lista) (nomeCurto, titulo, descricao, dataInicio, dataFim) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement projetoStatement = connection.prepareStatement(insertProjetoQuery, Statement.RETURN_GENERATED_KEYS)) {

            // Inserir projeto
            projetoStatement.setString(1, projeto.getNome());
            projetoStatement.setString(2, projeto.getTitulo());
            projetoStatement.setString(3, projeto.getDescricao());
            projetoStatement.setDate(4, projeto.getDataInicio());
            projetoStatement.setDate(5, projeto.getDataFim());
            projetoStatement.executeUpdate();

            ResultSet generatedKeys = projetoStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int projetoId = generatedKeys.getInt(1);
                projeto.setIdProjeto(projetoId);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addKeywordToProjeto(int projetoId, String keyword) throws SQLException {
        String insertKeywordQuery = "INSERT INTO Keywords (keyword) VALUES (?)";
        String insertKeywordProjetoQuery = "INSERT INTO Keyword_Projeto (ID_projeto, ID_keyword) VALUES (?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement keywordStatement = connection.prepareStatement(insertKeywordQuery, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement keywordProjetoStatement = connection.prepareStatement(insertKeywordProjetoQuery)) {

            // Inserir palavra-chave
            keywordStatement.setString(1, keyword);
            keywordStatement.executeUpdate();

            ResultSet generatedKeys = keywordStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int keywordId = generatedKeys.getInt(1);

                // Inserir relação entre projeto e palavra-chave
                keywordProjetoStatement.setInt(1, projetoId);
                keywordProjetoStatement.setInt(2, keywordId);
                keywordProjetoStatement.executeUpdate();
            }
        }
    }

    private static void addDominioToProjeto(int projetoId, int dominioId) throws SQLException {
        String insertDominioProjetoQuery = "INSERT INTO Domínio_Cientifico (ID_projeto, ID_dominio) VALUES (?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(insertDominioProjetoQuery)) {

            statement.setInt(1, projetoId);
            statement.setInt(2, dominioId);
            statement.executeUpdate();
        }
    }

    private static void addAreaToProjeto(int projetoId, int areaId) throws SQLException {
        String insertAreaProjetoQuery = "INSERT INTO Área_Cientifica (ID_projeto, ID_areaCientifica) VALUES (?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(insertAreaProjetoQuery)) {

            statement.setInt(1, projetoId);
            statement.setInt(2, areaId);
            statement.executeUpdate();
        }
    }

    private static void addEntidadeToProjeto(int projetoId, int entidadeId) throws SQLException {
        String insertEntidadeProjetoQuery = "INSERT INTO Entidade (ID_projeto, ID_entidade) VALUES (?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(insertEntidadeProjetoQuery)) {

            statement.setInt(1, projetoId);
            statement.setInt(2, entidadeId);
            statement.executeUpdate();
        }
    }

    private static void addEstadoToProjeto(int projetoId, int estadoId) throws SQLException {
        String insertEstadoProjetoQuery = "INSERT INTO Estado_do_Projeto (ID_projeto, ID_tipoEstado) VALUES (?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(insertEstadoProjetoQuery)) {

            statement.setInt(1, projetoId);
            statement.setInt(2, estadoId);
            statement.executeUpdate();
        }
    }

    private static void addFinanciamentoToProjeto(int projetoId, int financiamentoId) throws SQLException {
        String insertFinanciamentoProjetoQuery = "INSERT INTO Financiamento (ID_projeto, ID_tipoFinanciamento) VALUES (?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(insertFinanciamentoProjetoQuery)) {

            statement.setInt(1, projetoId);
            statement.setInt(2, financiamentoId);
            statement.executeUpdate();
        }
    }
}
