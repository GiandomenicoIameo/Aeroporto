package model;
import java.util.ArrayList;

enum StatoVolo {
	Programmato, Decollato, InRitardo,
	Atterrato, Cancellato
}

public class Volo {

	private String codice;

	private String compagniaAerea;
	private final String partenza = "Napoli";
	private String destinazione;

	private String data;
	private String durata;

	private int numeroGate;

	private String stato;

	// Tale classe non deve possedere il metodo setAdmin(). Se lo possedesse
	// ciò contraddirebbe il fatto che un volo è assegnato esattamente a un
	// amministratore perché tale metodo potrebbe modificarlo.
	//
	// La variabile di istanza deve essere modificata solo nel costruttore.
	private Amministratore admin;

	// Un volo può non essere stato prenotato da nessun utente quindi
	// prenotazioniAssociateAlVolo potrebbe essere anche vuoto.
	private ArrayList<Prenotazione> prenotazioniAssociateAlVolo = new ArrayList<>();

	// Se un esemplare di Volo viene creato allora necessariamente
	// un esemplare di Amministratore deve essere istanziato per
	// poterlo gestire. Un esemplare di Volo non può esistere
	// senza un esemplare di Amministratore che lo gestisce.

	public Volo() {
		this.stato = StatoVolo.Programmato.name();
		this.admin = new Amministratore( this );
	}

	public Volo( Amministratore admin ) {
		admin.inserisciVolo( this );

		this.stato = StatoVolo.Programmato.name();
		this.admin = admin;
	}

	public void setStato( String stato ) {

		for( StatoVolo elemento : StatoVolo.values() ) {
			if( stato.equals( elemento.name() ) ) {
				this.stato = stato; break;
			}
		}
	}

	public void setCodice( String codice ) {
		this.codice = codice;
	}

	public void setCompagnia( String compagnia ) {
		compagniaAerea = compagnia;
	}

	public void setDestinazione( String destinazione ) {
		this.destinazione = destinazione;
	}

	public void setData( String data ) {
		this.data = data;
	}

	public void setDurata( String durata ) {
		this.durata = durata;
	}

	public void setGate( int gate ) {
		this.numeroGate = gate;
	}

	public Amministratore getAdmin() {
		return admin;
	}

	public String getCodice() {
		return codice;
	}

	public String getStato() {
		return stato;
	}

	public String getCompagniaAerea() {
		return compagniaAerea;
	}

	public void aggiungiPrenotazione( Prenotazione prenotazione ) {
		prenotazioniAssociateAlVolo.add( prenotazione );
	}

	public void rimuoviPrenotazione( Prenotazione prenotazione ) {
		prenotazioniAssociateAlVolo.remove( prenotazione );
	}

	public int contaPrenotazioni() {
		return prenotazioniAssociateAlVolo.size();
	}

	public String toString() {
		return String.format( "%s: %s\n%s: %s\n%s: %s\n%s: %s\n%s: %s\n%s: %s\n%s: %d\n%s: %s\n",
					"Codice volo", 	  	     	 codice,
					"Compagnia aerea", 	     	 compagniaAerea,
					"Aeroporto di partenza",     partenza,
					"Aeroporto di destinazione", destinazione,
					"Data di partenza", 		 data,
					"Duarata del volo",			 durata,
					"Numero gate",				 numeroGate,
					"Stato del volo",			 stato );
	}
}
