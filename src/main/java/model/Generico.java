package model;
import java.util.ArrayList;

public class Generico extends Utente {

	private ArrayList<Prenotazione> prenotazioniDiGenerico = new ArrayList<>();

	// protected Generico( String username, String password ) {
	// 	super( username, password );
	// }

	// Un utente generico potrebbe non avere effettuato nessuna prenotazione
	// quindi la creazione di un esemplare di Generico non richiede la
	// creazione di un esemplare di Prenotazione.
	public Generico( String nome ) {
		super.nome = nome;
	}

	// public Generico() {
	// 	prenotazioniDiGenerico.add( new Prenotazione( this ) );
	// }

	public String getNome() {
		return nome;
	}

	public void effettuaPrenotazione( Prenotazione prenotazione ) {
		prenotazioniDiGenerico.add( prenotazione );
		prenotazione.getVoloAssociato().aggiungiPrenotazione( prenotazione );
	}

	public Prenotazione rimuoviPrenotazione( Prenotazione prenotazione ) {
		prenotazioniDiGenerico.remove( prenotazione );
		prenotazione.getVoloAssociato().rimuoviPrenotazione( prenotazione );
		return null;
	}

	public String visualizzaStatoPrenotazione( Prenotazione prenotazione ) {
		if( prenotazioniDiGenerico.contains( prenotazione ) )
			return prenotazione.visualizzaStato();
		return "Prenotazione non esistente!";
	}

	public int contaPrenotazioni() {
		return prenotazioniDiGenerico.size();
	}

	public boolean cercaPrenotazione( String codice ) {
		for( Prenotazione prenotazione : prenotazioniDiGenerico ) {
			if( prenotazione.getVoloAssociato().getCodice().equals( codice ) )
				return true;
		}
		return false;
	}

	public boolean cercaPrenotazione( Prenotazione prenotazione ) {
		return prenotazioniDiGenerico.contains( prenotazione );
	}

	public String visualizzaPrenotazione( Prenotazione prenotazione ) {
		if( prenotazioniDiGenerico.contains( prenotazione ) )
			return prenotazione.toString();
		return "Prenotazione non esistente!";
	}
}
