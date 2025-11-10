package org.example.dao;

import org.example.model.Utente;
import java.sql.*;
import java.util.*;

public interface UtenteDao<T extends Utente> extends Dao<String,T> {

    /**
     * Recupera un record dalla
     * tabella degli utenti.
     *
     * @param username: corrisponde alla chiave primaria nella tabella.
     * @return T: può essere un Utente Generico oppure un Amministratore
     */
    T get( String username ) throws SQLException;

    /**
     * Recupera una lista di record
     * dalla tabella degli utenti.
     *
     *
     */
    List<T> getAll() throws SQLException;

    /**
     * Aggiunge un record alla tabella
     * degli utenti.
     */
    boolean add( T user ) throws SQLException;

    /**
     *  Il metodo update() modifica una record
     * 	della tabella degli utenti.
     *
     * @param vechio
     * @param nuovo
     * @return
     * @throws SQLException
     */
    boolean update( T vechio, T nuovo ) throws SQLException;

    /**
     * Elimina un record dalla tabella
     * degli utenti.
     */
    boolean delete( T user ) throws SQLException;
}