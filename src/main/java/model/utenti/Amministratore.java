package model.utenti;
import java.util.ArrayList;

public class Amministratore extends Utente {

	private String nome;
	private ArrayList< Volo > voliGestiti;

	public Amministratore( String nome, String username, String password ) {

		super( username, password );

		this.nome = nome;
		this.voliGestiti = new ArrayList<>();
	}

	public boolean inserisciVolo( Volo volo ) {
		voliGestiti.add( volo );
	}

	public boolean rimuoviVolo( Volo volo ) {
		voliGestiti.remove( volo );
	}

	public boolean modificaAssegnazioneGate( Volo volo ) {

	}
}
