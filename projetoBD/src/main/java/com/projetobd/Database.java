package com.projetobd;

import java.sql.*;

public class Database {


    private static final String URL = "jdbc:sqlserver://DESKTOP-G4IKJLG\\SQLEXPRESS:55758;databaseName=PROJETO;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASSWORD = "p";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}