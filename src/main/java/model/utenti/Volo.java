package model.utenti;

public class Volo {

	private String codice;

	private String compagniaAerea;
	private String partenza;
	private String destinazione;

	private String data;
	private String durata;

	private int numeroGate;
	private Amministratore admin;

	public Volo( String codice, Amministratore admin, String compagniaAerea, String partenza,
	             String destinazione, String data, String durata ) {

		this.codice 		= codice;
		this.admin 			= admin;
		this.compagniaAerea = compagniaAerea;

		this.partenza     = partenza;
		this.destinazione = destinazione;

		this.data   = data;
		this.durata = durata;
	}

	public String visualizzaCodice() {
		return codice;
	}

	public String visualizzaCompagniaAerea() {
		return compagniaAerea;
	}
}
