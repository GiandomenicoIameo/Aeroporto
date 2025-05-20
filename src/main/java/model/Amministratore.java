package model;
import java.util.ArrayList;

public class Amministratore extends Utente {

	private String nome;
	private ArrayList<Volo> voliGestiti;
	private boolean utenteAutenticato = false;

	public Amministratore( String nome, String username, String password ) {
		super( username, password );

		if( login( username, password ) )
			utenteAutenticato = true;

		this.nome = nome;
		this.voliGestiti = new ArrayList<>();
	}

	public boolean setVolo( Volo volo ) {
		if( utenteAutenticato )
			return voliGestiti.add( volo );
		return false;
	}

	public boolean rimuoviVolo( Volo volo ) {
		if( utenteAutenticato )
			return voliGestiti.remove( volo );
		return false;
	}

	// public boolean modificaAssegnazioneGate( Volo volo ) {
 //
	// }
}
