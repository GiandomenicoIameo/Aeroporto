package model;

enum StatoPrenotazione {
	Confermata, Sospesa, Cancellata
}

public class Prenotazione {

	private String nomePasseggero;
	private int numeroBiglietto;

	private int postoAssegnato;
	private int numeroBagagli;

	private Generico generico;
	private Volo volo;

	private String stato;

	// Un esemplare di Prenotazione esiste solo se esiste un
	// esemplare di Volo. Questo perchè altrimenti non potrebbero
	// essere effettuate prenotazioni.

	// Un esemplare di Prenotazione non può esistere se nessun
	// esemplare di utente lo crea. Il seguente costruttore può essere
	// invocato solo esiste già un esemplare di Generico e di Volo.
	public Prenotazione( Generico generico, Volo volo ) {
		stato = StatoPrenotazione.Confermata.name();
		this.volo = volo;
		this.generico = generico;
	}

	public Volo getVoloAssociato() {
		return volo;
	}

	public void setNomePasseggero( String nome ) {
		nomePasseggero = nome;
	}

	public void setNumeroBiglietto( int biglietto ) {
		numeroBiglietto = biglietto;
	}

	public void setPostoAssegnato( int posto ) {
		postoAssegnato = posto;
	}

	public void setNumeroBagagli( int bagagli ) {
		numeroBagagli = bagagli;
	}

	public String visualizzaStato() {
		return stato;
	}

	public String visualizzaNomePasseggero() {
		return nomePasseggero;
	}

	public int visualizzaNumeroBiglietto() {
		return numeroBiglietto;
	}

	public int visualizzaPostoAssegnato() {
		return postoAssegnato;
	}

	public int visualizzaNumeroBagagli() {
		return numeroBagagli;
	}

	public String toString() {
		return String.format( "%s: %s\n%s: %s\n%s: %d\n%s: %d\n%s: %d\n%s: %s\n",
					"Volo associato",     volo.getCodice(),
					"Nome passeggero", 	  nomePasseggero,
					"Numero biglieto", 	  numeroBiglietto,
					"Posto assegnato", 	  postoAssegnato,
					"Numero bagagli",  	  numeroBagagli,
					"Stato Prenotazione", stato );
	}
}
