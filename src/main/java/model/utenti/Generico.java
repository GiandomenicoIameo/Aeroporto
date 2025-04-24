package model.utenti;

public class Generico extends Utente {

	private String nome;

	public Generico( String nome, String username, String password ) {
		super( username, password );
		this.nome = nome;
	}
}
