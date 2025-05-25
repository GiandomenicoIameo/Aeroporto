package model;
import java.util.ArrayList;

public class Amministratore extends Utente {

	private ArrayList<Volo> voliGestiti = new ArrayList<>();

	/*
	protected Amministratore( String username, String password ) {
		super( username, password );
	}*/

	// L'amministratore esiste solo se c'è almeno un volo
	// predisposto dal sistema. Il seguente costruttore
	// presuppone l'esistenza di un esemplare di Volo.

	public Amministratore( Volo volo ) {
		voliGestiti.add( volo );
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

	public void visualizzaVoliAssociati() {
		for( Volo elemento : voliGestiti )
			System.out.println( elemento );
	}

	public void modificaStatoVolo( Volo volo, String stato ) {
		if ( voliGestiti.contains( volo ) )
			volo.setStato( stato );
	}

	// public boolean modificaAssegnazioneGate( Volo volo ) {
 //
	// }
}
