package org.example.implement.postgresql;

import org.example.model.Amministratore;
import org.example.dao.UtenteDao;
import org.example.database.ConnessioneDatabase;

import java.util.*;
import java.sql.*;

public class AmministratoreImplementDao implements UtenteDao<Amministratore> {

    private Connection connection;

    public AmministratoreImplementDao() {

        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch( SQLException e ) {
            e.printStackTrace();
        }
    }

    /**
     * Recupera un record dalla
     * tabella degli utenti amministratori.
     */
    public Amministratore get( String username ) throws SQLException {

        List<Amministratore> list = new ArrayList<>();

        PreparedStatement stmt = connection.prepareStatement(
                "SELECT * FROM \"Amministratori\" WHERE \"Username\" = ?" );
        stmt.setString( 1, username );

        ResultSet result = stmt.executeQuery();
        if ( result.next() ) {

            String user = result.getString( 1 );
            String pass = result.getString( 2 );

            Amministratore admin = new Amministratore( user, pass );

            return admin;
        }

        return null;
    }

    /**
     * Recupera una lista di record
     * dalla tabella degli utenti amministratori.
     */
    public List<Amministratore> getAll() throws SQLException {

        List<Amministratore> list = new ArrayList<>();

        PreparedStatement stmt = connection.prepareStatement(
                "SELECT * FROM \"Amministratori\"" );

        ResultSet result = stmt.executeQuery();
        while ( result.next() ) {

            String username = result.getString( 1 );
            String password = result.getString( 2 );

            Amministratore admin = new Amministratore( username, password  );

            list.add( admin );
        }

        return list;
    }

    /**
     * Aggiunge un record alla tabella
     * degli utenti amministratori.
     */
    public boolean add( Amministratore admin ) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO \"Amministratori\" ( \"Username\", \"Password\" ) " +
                        "VALUES ( ?,? )" );

        stmt.setString( 1, admin.getUsername() );
        stmt.setString( 2, admin.getPassword() );

        return stmt.executeUpdate() > 0;
    }

    public boolean update( Amministratore vecchio, Amministratore nuovo ) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "UPDATE \"Amministratori\" " +
                        "SET \"Username\" = ?, \"Password\" = ? " +
                        "WHERE \"Username\" = ?" );

        stmt.setString( 1, nuovo.getUsername() );
        stmt.setString( 2, nuovo.getPassword() );
        stmt.setString( 3, vecchio.getUsername() );

        // L'istruzione seguente esegue la query SQL e restituisce
        // un intero che rappresenta il numero di righe coinvolte( modificate,
        //inserite o cancellate ). Se il numero di righe coinvolte è maggiore di
        // di 0 allora il metodo restituisce true, false in caso contrario.
        return stmt.executeUpdate() > 0;
    }

    /**
     * Elimina un record dalla tabella
     * degli utenti generici.
     */
    public boolean delete( Amministratore admin ) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "DELETE FROM \"Amministratori\" WHERE \"Username\" = ?" );

        stmt.setString( 1, admin.getUsername() );
        return stmt.executeUpdate() > 0;
    }
}
