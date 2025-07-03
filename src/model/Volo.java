package model;
import java.util.ArrayList;

enum StatoVolo {
	Programmato, Decollato, InRitardo,
	Atterrato, Cancellato
}

public class Volo {

	protected String codice;
	protected String compagniaAerea;

	protected String data;
	protected String orarioArrivo;
	protected String orarioPartenza;

	protected String stato;
	protected Gate gate;

	// Tale classe non deve possedere il metodo setAdmin(). Se lo possedesse
	// ciò contraddirebbe il fatto che un volo è assegnato esattamente a un
	// amministratore perché tale metodo potrebbe modificarlo.
	//
	// La variabile di istanza deve essere modificata solo nel costruttore.
	private Amministratore admin;

	// Un volo può non essere stato prenotato da nessun utente quindi
	// prenotazioniAssociateAlVolo potrebbe essere anche vuoto.

	// Se un esemplare di Volo viene creato allora necessariamente
	// un esemplare di Amministratore deve essere istanziato per
	// poterlo gestire. Un esemplare di Volo non può esistere
	// senza un esemplare di Amministratore che lo gestisce.

	public Volo() {

	}

	public Volo( Amministratore admin, Gate gate ) {

		admin.inserisciVolo( this );
		gate.aggiungiVolo( this );

		this.stato = StatoVolo.Programmato.name();
		this.admin = admin;
		this.gate = gate;
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

	public void setData( String data ) {
		this.data = data;
	}

	public void setOrarioPartenza( String orarioPartenza ) {
		this.orarioPartenza = orarioPartenza;
	}

	public void setOrarioArrivo( String orarioArrivo ) {
		this.orarioArrivo = orarioArrivo;
	}

	public void setGate( Gate gate ) {
		this.gate = gate;
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

	public String getOrarioPartenza() {
		return orarioPartenza;
	}

	public String getOrarioArrivo() {
		return orarioArrivo;
	}

	public String getData() {
		return data;
	}

	public String getGate() {
		return gate.getNumeroGate();
	}
}
