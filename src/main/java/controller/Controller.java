package controller;

public class Controller {

	private Autenticazione autenticazione;

	public boolean login( String username, String password ) {

		autenticazione = new Autenticazione();

		String user = autenticazione.getUsername();
		String pass = autenticazione.getPassword();

		if( user.equals( username ) && pass.equals( password ) )
			return true;
		else
			return false;
	}
}
