package com.controleAutorizacao.conexao;

import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private static String url = "jdbc:postgresql://localhost:5432/controleAutorizacao";
    private static String user = "postgres";
    private static String password = "postgres";
    private static String driver = "org.postgresql.Driver";
    private static Connection connection = null;

    static {
        connect();
    }

    public Conexao() {
        connect();
    }

    private static void connect() {
        try {
            if (connection == null) {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        connection = (Connection) Persistence.createEntityManagerFactory("controleAutorizacao");
        return connection;
    }
}
