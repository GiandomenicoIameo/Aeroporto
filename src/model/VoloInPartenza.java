package model;

public class VoloInPartenza extends Volo {

	// variabili di istanza.
	private boolean[] posti;
	private int numeroMinimoPosti;
	private String aeroportoDestinazione;

	public VoloInPartenza( Amministratore admin, String codice,
						   String compagniaAerea, String data, Orario orarioPartenza,
						   Orario orarioArrivo, String stato, Gate gate,
						   String aeroportoDestinazione, int numeroPosti ) {

		super( admin, codice, compagniaAerea, data,
				orarioPartenza, orarioArrivo, stato, gate );

		this.aeroportoDestinazione = aeroportoDestinazione;
		setNumeroMinimoPosti( numeroPosti );
	}

	public VoloInPartenza( Amministratore admin, Gate gate ) {
		super( admin,gate );
	}

	public void setNumeroMinimoPosti( int numeroPosti ) {
		posti = new boolean[ numeroPosti ];
		numeroMinimoPosti = ( numeroPosti * 3 ) / 4;
	}

	public void setAeroportoDestinazione( String aeroporto ) {
		aeroportoDestinazione = aeroporto;
	}

	public String getAeroportoDestinazione() {
		return aeroportoDestinazione;
	}

	public int getNumeroPosti() {
		return posti.length;
	}

	/**
	 * Il metodo verifica se sono stati occupati il numero minimo
	 * di posti nell'aereo per confermare la prenotazione.
	 *
	 * @return Se il test ha successo viene restituito true;
	 * in caso di fallimento, ovvero il numero di posti occupati è
	 * minore della variabile numeroMinimoPosti, viene restituito false.
	 *
	 */
	public boolean numeroMinimoPostiSuperato() {
		return numeroPostiOccupati() >= numeroMinimoPosti;
	}

	/**
	 * Il metodo scorre l'array di posti per contare il
	 * numero di posti occupati. Dal punto di vista dell'
	 * implementazione significa trovare tutti gli indici i
	 * tale che posti[ i ] = true.
	 *
	 * counter è una variabile contatore, e ha il compito
	 * di tenere traccia, del numero
	 * di posti occupati ad ogni punto dell'iterazione.
	 *
	 * @return corrisponde al numero di posti occupati.
	 */
	public int numeroPostiOccupati() {

		int counter = 0;
		for( int pos = 0; pos < posti.length; pos++ ) {
			if( posti[ pos ] ) counter++;
		}
		return counter;
	}

	public void riassegnaPosto( int posto ) {
		posti[ posto ] = false;
	}

	/**
	 * Il metodo scorre l'array di posti[] fino a quando
	 * non viene trovato il primo posto disponibile all'interno
	 * dell'aereo. Ma questo significa trovare il primo indice
	 * 				i tale che posti[ i ] = false.
	 * Una volta trovato viene posto a true per contrassegnarlo
	 * come occupato.
	 *
	 * @return corrisponde al numero di posto che è stato occupato. Se
	 * viene restituito -1 allora significa che l'aereo non ha posti disponibili.
	 */
	public int occupaPosto() {

		final int POSTI_NON_DISPONIBILI = -1;

		for( int pos = 0; pos < posti.length; pos++ ) {
			if( !posti[ pos ] ) {
				posti[ pos ] = true; return pos;
			}
		}
		return POSTI_NON_DISPONIBILI;
	}

	public boolean equals( Volo volo ) {

		if ( volo instanceof VoloInPartenza ) {
			if ( super.equals( volo ) ) {

				return ( ( VoloInPartenza ) volo ).getAeroportoDestinazione().equals(
						aeroportoDestinazione );
			}
		}
		return false;
	}
}
