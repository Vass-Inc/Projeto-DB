package com.projetobd;

import java.sql.*;

//your.database.domain/yourDBname
public class Database {
    private static final String URL = "jdbc:sqlserver://SQLEXPRESS1/PROJETO";
    private static final String USER = "";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}