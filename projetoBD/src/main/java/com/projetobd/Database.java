package com.projetobd;

import com.projetobd.models.Estado;
import com.projetobd.models.Keyword;
import com.projetobd.models.Projetos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String URL = "jdbc:sqlserver:DEATHSTAR//SQLEXPRESS01/PROJETOS";
    private static final String USER = "";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}