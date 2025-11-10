package org.example.model;
import java.util.*;

enum StatoVolo {
    PROGRAMMATO, DECOLLATO, IN_RITARDO,
    ATTERRATO, CANCELLATO
}

public class Volo {

    // Variabili di istanza
    protected String codice;
    protected String compagniaAerea;
    protected String data;

    protected Orario orarioArrivo;
    protected Orario orarioPartenza;

    protected String stato;
    protected Gate gate;

    // Tale classe non deve possedere il metodo setAdmin(). Se lo possedesse
    // ciò contraddirebbe il fatto che un volo è assegnato esattamente a un
    // amministratore perché tale metodo potrebbe modificarlo.
    //
    // La variabile di istanza deve essere modificata solo nel costruttore.

    // Riferimenti a oggetti associati.
    private Amministratore admin;
    private List<Prenotazione> prenotazioni = new ArrayList<>();

    // Un volo può non essere stato prenotato da nessun utente quindi
    // prenotazioniAssociateAlVolo potrebbe essere anche vuoto.

    // Se un esemplare di Volo viene creato allora necessariamente
    // un esemplare di Amministratore deve essere istanziato per
    // poterlo gestire. Un esemplare di Volo non può esistere
    // senza un esemplare di Amministratore che lo gestisce.

    public Volo( Amministratore admin ) {
        this.admin = admin;
    }

    public Volo( Amministratore admin, Gate gate ) {

        admin.inserisciVolo( this );

        this.stato = StatoVolo.PROGRAMMATO.name();
        this.admin = admin;
        this.gate  = gate;
    }

    public Volo( Amministratore admin, String codice,
                 String compagniaAerea, String data, Orario orarioPartenza,
                 Orario orarioArrivo, String stato, Gate gate ) {

        this( admin );

        this.codice 		= codice;
        this.compagniaAerea = compagniaAerea;
        this.data 			= data;
        this.orarioPartenza = orarioPartenza;
        this.orarioArrivo 	= orarioArrivo;
        this.gate 			= gate;

        setStato( stato );
    }

    public Volo() {

    }

    public void setStato( String stato ) {

        boolean status = false;

        for( StatoVolo elemento : StatoVolo.values() ) {
            if( stato.equals( elemento.name() ) ) {
                this.stato = stato; status = true;
                break;
            }
        }

        // Valore di default per lo stato del volo.
        if( !status )
            this.stato = StatoVolo.PROGRAMMATO.name();
    }

    public void addPrenotazione( Prenotazione p ) {
        prenotazioni.add( p );
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

    public void setOrarioPartenza( Orario orarioPartenza ) {
        this.orarioPartenza = orarioPartenza;
    }

    public void setOrarioArrivo( Orario orarioArrivo ) {
        this.orarioArrivo = orarioArrivo;
    }

    public void setGate( String gate ) {

        if( this.gate != null ) {
            this.gate.setNumeroGate( gate );
        }
    }

    public void rimuoviAdmin() {
        this.admin = null;
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

    public Orario getOrarioPartenza() {
        return orarioPartenza;
    }

    public Orario getOrarioArrivo() {
        return orarioArrivo;
    }

    public String getData() {
        return data;
    }

    public String getGate() {
        return gate.getNumeroGate();
    }

    // Metodi per gestire le prenotazioni.
    public void rimuoviPrenotazione( Prenotazione p ) {
        prenotazioni.remove( p );
    }

    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    /**
     * Due esemplari di Volo sono considerati equivalenti se e solo se
     * si riferiscono allo stesso oggetto, hanno lo stesso codice oppure
     * le date, gli orari di arrivo, di partenza, il numero di gate e lo
     * stato coincidono.
     *
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals( Object obj ) {

        if( this == obj ) {
            return true;
        }

        if( obj == null ) {
            return false;
        }

        Volo volo = ( Volo ) obj;

        if( !volo.getData().equals( data ) )
            return false;

        if( !volo.getOrarioArrivo().equals( orarioArrivo ) )
            return false;

        if( !volo.getOrarioPartenza().equals( orarioPartenza ) )
            return false;

        if( !volo.getStato().equals( stato ) )
            return false;

        return volo.getGate().equals( gate );
    }
}