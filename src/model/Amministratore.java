package model;
import java.util.ArrayList;

public class Amministratore extends Utente {

	private ArrayList<Volo> voliGestiti;

	/*
	protected Amministratore( String username, String password ) {
		super( username, password );
	}*/

	// L'amministratore esiste solo se c'è almeno un volo
	// predisposto dal sistema. Il seguente costruttore
	// presuppone l'esistenza di un esemplare di Volo.

	public Amministratore( String username, String password ) {
		voliGestiti = new ArrayList<>();

		this.username = username;
		this.password = password;
	}

	public Amministratore( Volo volo ) {
		voliGestiti = new ArrayList<>();
		voliGestiti.add( volo );
	}

	public Amministratore() {

	}

	public void setUsername( String username ) {
		super.setUsername( username );
	}

	public void setPassword( String password ) {
		super.setPassword( password );
	}

	public String getUsername() {
		return super.getUsername();
	}

	public String getPassword() {
		return super.getPassword();
	}

	/* public Amministratore() {
		voliGestiti.add( new Volo( this ) );
	} */

	public void inserisciVolo( Volo volo ) {
		voliGestiti.add( volo );
	}

	public boolean rimuoviVolo( Volo volo ) {
		return voliGestiti.remove( volo );
	}

	public void visualizzaVolo( Volo volo ) {
		if ( voliGestiti.contains( volo ) )
			System.out.print( volo.toString() );
		else
			System.out.println( "Volo non esistente!" );
	}

	public Volo trovaVolo( String codice ) {
		for( Volo elemento : voliGestiti ) {
			if( elemento.getCodice().equals( codice ) )
				return elemento;
		} return null;
	}

	public void visualizzaVoliAssociati() {
		for( Volo elemento : voliGestiti )
			System.out.println( elemento );
	}

	public void modificaStatoVolo( Volo volo, String stato ) {
		if ( voliGestiti.contains( volo ) )
			volo.setStato( stato );
	}

	public int contaVoliGestiti() {
		return voliGestiti.size();
	}

	public ArrayList<Volo> getVoliGestiti() {
		return voliGestiti;
	}

	// public boolean modificaAssegnazioneGate( Volo volo ) {
 //
	// }
}
