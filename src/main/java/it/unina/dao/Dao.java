package it.unina.dao;

import java.sql.SQLException;
import java.util.*;

/**
 * Lo scopo della seguente interfaccia è
 * quella di presentare all'utilizzatore un
 * insieme di metodi per interagire con
 * la base di dati.
 */

/*
 * La struttura non deve "forzare" un tipo
 * specifico: non si sa come verrà usata tale struttura.
 *
 * La struttura non deve "forzare" una
 * specifica implementazione: se ne occpuerà
 * un altro team di sviluppo.
 */

public interface Dao<S,T> {

    /**
     * Il seguente metodo ha come obiettivo quello di
     * recuperare un record da una tabella in base
     * alla chiave primaria.
     */
    T get( S key ) throws SQLException;

    /**
     * Il seguente metodo fornisce all'utilizzatore
     * una lista di record proveniente da una tabella.
     */
    List<T> getAll() throws SQLException;

    /**
     * Il metodo add() aggiunge un record a
     * una tabella.
     */
    boolean add( T mioRecord ) throws SQLException;

    /**
     * Il metodo update() modifica una record
     * di una tabella.
     *
     * @param vecchio
     * @param nuovo
     * @return
     * @throws SQLException
     */
    boolean update( T vecchio, T nuovo ) throws SQLException;

    /**
     * delete() elimina un record da
     * una tabella.
     */
    boolean delete( T mioRecord ) throws SQLException;
}