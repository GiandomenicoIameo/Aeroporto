package implement.postgresql;

import model.Amministratore;
import model.Generico;
import dao.UtenteDao;
import database.ConnessioneDatabase;

import java.sql.*;
import java.util.ArrayList;

public class GenericoImplementDao implements UtenteDao<Generico> {

	private Connection connection;

	public GenericoImplementDao() {

			try {
				connection = ConnessioneDatabase.getInstance().getConnection();
			} catch( SQLException e ) {
				e.printStackTrace();
			}
	}

	/**
	* Recupera un record dalla
	* tabella degli utenti generici.
	*/
	public Generico get( String username ) throws SQLException {

			PreparedStatement stmt = connection.prepareStatement(
				"SELECT * FROM \"Generici\" WHERE \"Username\" = ?" );
			stmt.setString( 1,username );

			ResultSet result = stmt.executeQuery();
			if ( result.next() ) {

				String user 	= result.getString( 1 );
				String pass 	= result.getString( 2 );

				String nome 	= result.getString( 3 );
				String telefono = result.getString( 4 );
				int anni 		= result.getInt( 5 );

				Generico generico = new Generico( user, pass );

				generico.setTelefono( telefono );
				generico.setNome( nome );
				generico.setAnni( anni );

				return generico;
			}

			return null;
	}

	/**
	* Recupera una lista di record
	* dalla tabella degli utenti generici.
	*/
	public ArrayList<Generico> getAll() throws SQLException {

			ArrayList<Generico> list = new ArrayList<>();

			PreparedStatement stmt = connection.prepareStatement(
				"SELECT * FROM \"Generici\"" );

			ResultSet result = stmt.executeQuery();
			while ( result.next() ) {

				String username = result.getString( 1 );
				String password = result.getString( 2 );

				String nome 	= result.getString( 3 );
				String telefono = result.getString( 4 );
				int anni 		= result.getInt( 5 );

				Generico generico = new Generico( username,password );

				generico.setTelefono( telefono );
				generico.setNome( nome );
				generico.setAnni( anni );

				list.add( generico );
			}

			return list;
	}

	/**
	* Aggiunge un record alla tabella
	* degli utenti generici.
	*/
	public boolean add( Generico generico ) throws SQLException {

			PreparedStatement stmt = connection.prepareStatement(
					"INSERT INTO \"Generici\" ( Username,Password,Nome,Telefono,Anni ) " +
					"VALUES ( ?,?,?,? )" );

			stmt.setString( 1, generico.getUsername() );
			stmt.setString( 2, generico.getPassword() );

			stmt.setString( 3, generico.getNome() );
			stmt.setString( 4, generico.getTelefono() );

			stmt.setInt( 5, generico.getAnni() );

			return stmt.executeUpdate() > 0;
	}

	public boolean update( Generico vecchio, Generico nuovo ) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(
				"UPDATE \"Generico\" " +
						"SET \"Username\" = ?, SET \"Password\" = ? " +
						"WHERE \"Username = ?" );

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
	public boolean delete( Generico generico ) throws SQLException {

			PreparedStatement stmt = connection.prepareStatement(
				"DELETE FROM \"Generici\" WHERE \"Username\" = ?" );

			stmt.setString( 1, generico.getUsername() );
			return stmt.executeUpdate() > 0;
	}
 }


