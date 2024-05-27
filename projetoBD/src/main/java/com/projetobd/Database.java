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
}