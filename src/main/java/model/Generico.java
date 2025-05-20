package model;
import java.util.ArrayList;

public class Generico extends Utente {

	private String nome;
	private ArrayList<Prenotazione> prenotazioni;
	private boolean utenteAutenticato = false;

	public Generico( String nome, String username, String password ) {
		super( username, password );

		if( login( username, password ) )
			utenteAutenticato = true;

		this.nome = nome;
		this.prenotazioni = new ArrayList<>();

	}

	public boolean setPrenotazione( Prenotazione prenotazione ) {
		if( utenteAutenticato )
			return prenotazioni.add( prenotazione );
		return false;
	}

	public boolean rimuoviPrenotazione( Prenotazione prenotazione ) {
		if( utenteAutenticato )
			return prenotazioni.remove( prenotazione );
		return false;
	}

	public String visualizzaStatoPrenotazione( Prenotazione prenotazione ) {
		if( utenteAutenticato ) {
			if( prenotazioni.contains( prenotazione ) )
				return prenotazione.visualizzaStato();
			return "Prenotazione inesistente";
		}
		return "Utente non autenticato";
	}

	public int contaPrenotazioni() {
		return prenotazioni.size();
	}

	public boolean cercaPrenotazione( Prenotazione prenotazione ) {
		if( utenteAutenticato )
			return prenotazioni.contains( prenotazione );
		return false;
	}
}
