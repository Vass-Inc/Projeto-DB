package com.projetobd;

import com.projetobd.models.Estado;
import com.projetobd.models.Keyword;
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
        String query = "SELECT P.nomeCurto, P.titulo, P.descricao, P.palavraChave, P.dataInicio, E.tipoEstado " +
                "FROM Projetos P " +
                "JOIN EstadoProjeto EP ON EP.ID_projeto = P.ID_projeto " +
                "JOIN Estado E ON EP.ID_estado = E.ID_estado";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String nomeCurto = resultSet.getString("Nome");
                String titulo = resultSet.getString("Título");
                String descricao = resultSet.getString("descricao");
                String pChave = resultSet.getString("Keywords");
                Date dataInicio = resultSet.getDate("Data Início");
                String tipoEstado = resultSet.getString("Estado");

                Projetos projeto = new Projetos(nomeCurto, titulo, descricao, pChave, dataInicio, tipoEstado);
                Estado estado = new Estado(tipoEstado);
                Keyword keyword = new Keyword(pChave);
                // Assuming Projetos class has a method to add Estado objects
                projeto.addEstado(estado);
                projeto.addKeywords(keyword);

                projetos.add(projeto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projetos;
    }


}
