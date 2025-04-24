package model.utenti;

public class Prenotazione {

	private String nomePasseggero;
	private int numeroBiglietto;

	private int postoAssegnato;
	private int numeroBagagli;

	private Generico generico;
	private Volo volo;

	public Prenotazione( String nomePasseggero, int numeroBiglietto, int postoAssegnato,
						 int numeroBagagli ) {

		this.nomePasseggero  = nomePasseggero;
		this.postoAssegnato  = postoAssegnato;
		this.numeroBiglietto = numeroBiglietto;
		this.numeroBagagli   = numeroBagagli;
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
