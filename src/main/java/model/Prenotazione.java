package model;

enum StatoPrenotazione {
	Confermata( "Prenotazione confermata" ),
	Sospesa( "Prenotazione sospesa" ),
	Cancellata( "Prenotazione cancellata" );

	private String descrizione;

	 StatoPrenotazione( String descrizione ) {
        this.descrizione = descrizione;
    }

	public String getDescrizione() {
		return descrizione;
	}
}

public class Prenotazione {

	private String nomePasseggero;
	private int numeroBiglietto;

	private int postoAssegnato;
	private int numeroBagagli;

	private Generico generico;
	private Volo volo;

	private StatoPrenotazione stato;

	public Prenotazione() {
		this.stato = StatoPrenotazione.Confermata;
	}

	public String visualizzaStato() {
		return stato.getDescrizione();
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
}
