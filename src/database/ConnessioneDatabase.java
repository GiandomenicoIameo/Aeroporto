package database;
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
	private String driver = "org.postgresql.Driver";

	private ConnessioneDatabase() {

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection( url, username, password );
		} catch (ClassNotFoundException e) {
			System.err.println("Driver PostgreSQL non trovato!");
			e.printStackTrace();
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
