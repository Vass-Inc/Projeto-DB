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
}
