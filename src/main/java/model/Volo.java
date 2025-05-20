package model;
import java.util.ArrayList;

enum StatoVolo {
	Programmato( "Volo programmato" ),
	Decollato( "Volo decollato" ),
	InRitardo( "Volo in ritardo" ),
	Atterrato( "Volo atterrato" ),
	Cancellato( "Volo cancellato" );

	private String descrizione;

	 StatoVolo( String descrizione ) {
        this.descrizione = descrizione;
    }

	public String getDescrizione() {
		return descrizione;
	}
}

public class Volo {

	private String codice;

	private String compagniaAerea;
	private String partenza;
	private String destinazione;

	private String data;
	private String durata;

	private int numeroGate;

	private Amministratore admin;
	private ArrayList<Prenotazione> prenotazioni;

	public Volo( String codice, Amministratore admin, String compagniaAerea, String partenza,
	             String destinazione, String data, String durata ) {

		this.codice 		= codice;
		this.admin 			= admin;
		this.compagniaAerea = compagniaAerea;

		this.partenza     = partenza;
		this.destinazione = destinazione;

		this.data   = data;
		this.durata = durata;

		this.prenotazioni = new ArrayList<>();
	}

	public String visualizzaCodice() {
		return codice;
	}

	public String visualizzaCompagniaAerea() {
		return compagniaAerea;
	}
}
