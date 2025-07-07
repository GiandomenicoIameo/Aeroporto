package model;

enum StatoPrenotazione {
	CONFERMATA, SOSPESA, CANCELLATA
}

public class Prenotazione {

	// variabili di istanza.
	private String nomePasseggero;
	private String cognomePasseggero;
	private String numeroBiglietto;
	private String postoAssegnato;
	private String numeroBagagli;
	private String stato;

	// riferimenti agli oggetti asssociati.
	private Generico generico;
	private Volo volo;

	// Un esemplare di Prenotazione esiste solo se esiste un
	// esemplare di Volo. Questo perchè altrimenti non potrebbero
	// essere effettuate prenotazioni.

	/**
	 * Un esemplare di Prenotazione non può esistere se nessun
	 * esemplare di Generico lo crea. Il seguente costruttore può essere
	 * invocato solo esiste già un esemplare di Generico e di Volo.
	 *
	 *
	 * @param volo
	 */

	public Prenotazione( Volo volo, Generico generico ) {
		this.volo = volo;
		this.generico = generico;
	}

	public Prenotazione( Volo volo, Generico generico, String nomePasseggero,
						 String cognomePasseggero, String numeroBiglietto,
						 String postoAssegnato, String numeroBagagli, String stato ) {

		this( volo, generico );

		this.nomePasseggero	   = nomePasseggero;
		this.cognomePasseggero = cognomePasseggero;
		this.numeroBiglietto   = numeroBiglietto;
		this.postoAssegnato    = postoAssegnato;
		this.numeroBagagli     = numeroBagagli;

		setStatoPrenotazione( stato );
	}

	// L'invocazione di tale metodo sarà
	// setStatoPrenotazione( StatoPrenotazione.Confermata )
	public void setStatoPrenotazione( String stato ) {
		this.stato = String.valueOf( StatoPrenotazione.valueOf( stato ) );
	}

	public void setNomePasseggero( String nomePasseggero ) {
		this.nomePasseggero = nomePasseggero;
	}

	public void setCognomePasseggero( String cognomePasseggero ) {
		this.cognomePasseggero = cognomePasseggero;
	}

	public void setNumeroBiglietto( String biglietto ) {
		numeroBiglietto = biglietto;
	}

	public void setPostoAssegnato( String posto ) {
		postoAssegnato = posto;
	}

	public void setNumeroBagagli( String bagagli ) {
		numeroBagagli = bagagli;
	}

	public void setGenerico( Generico generico ) {
		this.generico = generico;
	}

	public Generico getGenerico() {
		return generico;
	}

	public String getStato() {
		return stato;
	}

	public String getNomePasseggero() {
		return nomePasseggero;
	}

	public String getCognomePasseggero() {
		return cognomePasseggero;
	}

	public String getNumeroBiglietto() {
		return numeroBiglietto;
	}

	public String getPostoAssegnato() {
		return postoAssegnato;
	}

	public String getNumeroBagagli() {
		return numeroBagagli;
	}

	public Volo getVoloAssociato() {
		return volo;
	}

	public void rimuoviVolo() {
		this.volo = null;
	}

	/**
	 * Due prenotazioni sono considerata equivalenti se contemporaneamente
	 * possiedono lo stesso nome del passeggero, cognome del passeggero,
	 * e volo associato. Oppure, chiaramente, se le prenotazioni rappresentano
	 * lo stesso oggetto.
	 *
	 * La relazione espressa è una semplice relazione di equivalenza
	 * tra n-uple ordinate. In tal caso si considera solo alcuni
	 * attributi di una prenotazione, ovvero ( volo, nome, cognome ).
	 *
	 * Dunque
	 * ( volo1, nome1, cognome1 ) ~ ( volo2, nome2, cognome2 )
	 * se e solo se ai = bi per ogni i = 1,2.
	 *
	 * @return
	 */
	@Override
	public boolean equals( Object obj ) {
		if( this == obj ) {
			return true;
		}

		if( obj == null || getClass() != obj.getClass() ) {
			return false;
		}

		Prenotazione p = ( Prenotazione ) obj;

		if( p.getVoloAssociato() != this.volo ||
				!p.getNomePasseggero().equals(
						this.getNomePasseggero() ) ||
				!p.getCognomePasseggero().equals(
						this.getCognomePasseggero()))
			return false;

		return true;
	}
}
