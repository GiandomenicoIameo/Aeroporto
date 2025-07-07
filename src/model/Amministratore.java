package model;
import java.util.ArrayList;

public class Amministratore extends Utente {

	// riferimenti a oggetti associati.
	private ArrayList<Volo> voliGestiti = new ArrayList<>();

	// L'amministratore esiste solo se c'è almeno un volo
	// predisposto dal sistema. Il seguente costruttore
	// presuppone l'esistenza di un esemplare di Volo.

	public Amministratore( String username, String password, Volo volo ) {
		super( username, password );
		voliGestiti.add( volo );
	}

	public Amministratore( String username, String password ) {
		super( username, password );
	}

	public Amministratore( String username, String password, ArrayList<Volo> list ) {
		super( username, password );
		voliGestiti = list;
	}

	@Override
	public void setUsername( String username ) {
		super.setUsername( username );
	}

	@Override
	public void setPassword( String password ) {
		super.setPassword( password );
	}

	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	public void inserisciVolo( Volo volo ) {
		voliGestiti.add( volo );
	}

	public boolean rimuoviVolo( Volo volo ) {
		return voliGestiti.remove( volo );
	}

	public Volo trovaVolo( String codice ) {
		for( Volo elemento : voliGestiti ) {
			if( elemento.getCodice().equals( codice ) )
				return elemento;
		} return null;
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

	public boolean checkVolo( Volo volo ) {

		for( Volo element : voliGestiti ) {

			if( element instanceof VoloInPartenza &&
				volo instanceof VoloInPartenza ) {
				return ( ( VoloInPartenza ) element).equals( volo );
			}
			if( element instanceof VoloInArrivo &&
					volo instanceof VoloInArrivo ) {
				return ( ( VoloInArrivo ) element ).equals( volo );
			}

			if( element.equals( volo ) )
				return true;
		}
		return false;
	}
}
