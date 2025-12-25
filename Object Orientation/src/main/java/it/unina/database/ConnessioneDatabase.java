package it.unina.database;

import java.sql.*;

public class ConnessioneDatabase {

    private static ConnessioneDatabase instance;
    public Connection connection = null;

    /**
     * Una connessione alla base di dati
     * è definita anche dalle credenziali
     * di accesso.
     */
    private String username = "postgres";
    private String password = "root";

    private String url = "jdbc:postgresql://localhost:5432/postgres";

    private ConnessioneDatabase() {

        try {
            connection = DriverManager.getConnection( url, username, password );
        } catch (SQLException e) {
            System.err.println("Errore nella connessione al database!");
            e.printStackTrace();
        }
    }

    public static ConnessioneDatabase getInstance() throws SQLException {

        if( instance == null || instance.connection.isClosed() )
            instance = new ConnessioneDatabase();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
