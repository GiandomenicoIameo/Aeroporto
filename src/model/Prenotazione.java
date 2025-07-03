package model;

enum StatoPrenotazione {
	Confermata, Sospesa, Cancellata
}

public class Prenotazione {

	private String nomePasseggero;
	private String cognomePasseggero;

	private String numeroBiglietto;
	private String postoAssegnato;
	private String numeroBagagli;

	// Associazioni
	private Generico generico;
	private VoloInPartenza volo;

	private String stato;

	// Un esemplare di Prenotazione esiste solo se esiste un
	// esemplare di Volo. Questo perchè altrimenti non potrebbero
	// essere effettuate prenotazioni.

	// Un esemplare di Prenotazione non può esistere se nessun
	// esemplare di utente lo crea. Il seguente costruttore può essere
	// invocato solo esiste già un esemplare di Generico e di Volo.
	public Prenotazione( Generico generico, VoloInPartenza volo ) {
		this.volo = volo;
		this.generico = generico;

		setStatoPrenotazione( "Confermata" );
	}

	// L'invocazione di tale metodo sarà
	// setStatoPrenotazione( StatoPrenotazione.Confermata )

	public void setStatoPrenotazione( String stato ) {
		this.stato = String.valueOf( StatoPrenotazione.valueOf( stato ) );
	}

	// public void setNomePasseggero( Generico generico ) {
	// 	nomePasseggero = generico.getNome();
	// }
 //
	// public void setCognomePasseggero( Generico generico ) {
	// 	cognomePasseggero = generico.getCognome();
	// }

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

	public VoloInPartenza getVoloAssociato() {
		return volo;
	}

	public Generico getUtenteAssociato() {
		return generico;
	}

	public String[] toStringa() {
		String array[] = { volo.getCodice(), "Napoli", volo.getAeroportoDestinazione(), postoAssegnato };
		return array;
	}

	public boolean equals( Prenotazione prenotazione )  {

		if( this == prenotazione )
			return true;
		else {
			if( !prenotazione.getNomePasseggero().equals( this.getNomePasseggero() ) )
				return false;
			if( !prenotazione.getCognomePasseggero().equals( this.getCognomePasseggero() ) )
				return false;
			if( !( prenotazione.getVoloAssociato() == this.volo ) )
				return false;
			return true;
		}
	}
}
