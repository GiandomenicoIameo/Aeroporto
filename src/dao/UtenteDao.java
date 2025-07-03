package dao;

import model.Utente;


import java.sql.*;
import java.util.ArrayList;

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
	ArrayList<T> getAll() throws SQLException;

	/**
	* Aggiunge un record alla tabella
	* degli utenti.
	*/
	boolean add( T user ) throws SQLException;

	/**
	* Elimina un record dalla tabella
	* degli utenti.
	*/
	boolean delete( T user ) throws SQLException;
}
