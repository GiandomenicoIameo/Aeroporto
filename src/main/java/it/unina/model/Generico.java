package it.unina.model;
import java.util.*;

public class Generico extends Utente {

    // variabili di istanza.
    private String nome;
    private String cognome;
    private String telefono;
    private int anni;

    // riferimenti a oggetti associati.
    private List<Prenotazione> prenotazioni = new ArrayList<>();

    /**
     * La creazione di un esemplare di generico richiede di specificare
     * le credenziali di accesso all'account, ovvero username e password.
     *
     * E' bene notare che un utente generico, secondo l'associazione gestisce
     * che la collega alla classe prenotazione, potrebbe in un determinato momento
     * non avere effettuato nessuna prenotazione.
     * Ciò significa che la creazione di un esemplare di Generico non richiede la
     * creazione di un esemplare di Prenotazione.
     *
     *
     * @param username
     * @param password
     */
    public Generico( String username, String password ) {
        super( username, password );
    }

    public Generico( String username, String password, Prenotazione prenotazione,
                     String nome, String cognome, String telefono, int anni ) {

        super( username,password );

        this.nome 	  = nome;
        this.cognome  = cognome;
        this.telefono = telefono;

        this.prenotazioni.add( prenotazione );
        setAnni( anni );
    }

    public Generico( String username, String password, ArrayList<Prenotazione> p,
                     String nome, String cognome, String telefono, int anni ) {

        super( username,password );

        this.nome 	  = nome;
        this.cognome  = cognome;
        this.telefono = telefono;
        this.prenotazioni = p;

        setAnni( anni );
    }

    @Override
    public void setUsername( String username ) {
        super.setUsername( username );
    }

    @Override
    public void setPassword( String password ) {
        super.setPassword( password );
    }

    public void setNome( String nome ) {
        this.nome = nome;
    }

    public void setCognome( String cognome ) {
        this.cognome = cognome;
    }

    public void setTelefono( String telefono ) {
        this.telefono = telefono;
    }

    public void setAnni( int anni ) {
        if( anni < 0 )
            this.anni = 25;
        else
            this.anni = anni;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getAnni() {
        return anni;
    }

    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    /**
     * Il metodo, banalmente, aggiunge la prenotazione all'insieme delle
     * prenotazioni effettuate dall'utente.
     *
     * @param prenotazione corrisponde alla prenotazione da aggiungere all'arrayList.
     */
    public void aggiungiPrenotazione( Prenotazione prenotazione ) {
        prenotazioni.add( prenotazione );

        if( prenotazione.getGenerico() == null )
            prenotazione.setGenerico( this );
    }

    public void rimuoviPrenotazione( Prenotazione prenotazione ) {
        prenotazioni.remove( prenotazione );

        if( prenotazione.getGenerico() != null )
            prenotazione.setGenerico( null );
    }

    public String visualizzaStatoPrenotazione( Prenotazione prenotazione ) {
        if( prenotazioni.contains( prenotazione ) )
            return prenotazione.getStato();
        return "Prenotazione non esistente!";
    }

    public int contaPrenotazioni() {
        return prenotazioni.size();
    }

    public boolean cercaPrenotazione( String codice ) {
        for( Prenotazione p : prenotazioni ) {
            if( p.getVoloAssociato().getCodice().equals( codice ) )
                return true;
        }
        return false;
    }

    public Prenotazione cercaPrenotazione( String codice, String nome,
                                           String cognome, String bagagli ) {
        for( Prenotazione p : prenotazioni ) {
            if( p.getVoloAssociato().getCodice().equals( codice ) &&
                    nome.equals( p.getNomePasseggero() ) &&
                    cognome.equals( p.getCognomePasseggero() ) &&
                    p.getNumeroBagagli().equals( bagagli ) )
                return p;
        }
        return null;
    }

    /**
     * Il metodo ha il compito di verificare se esistono due prenotazioni uguali
     * effettuate da uno stesso utente Generico.
     *
     * Il confronto si basa sul metodo equals() definito nella classe Prenotazione.
     *
     * @param prenotazione
     * @return se esistono due prenotazioni uguali, allora viene restituito true;
     * in caso negativo, viene restituito false.
     */
    public boolean esistePrenotazione( Prenotazione prenotazione ) {

        for( Prenotazione p : prenotazioni ) {
            if( p.equals( prenotazione ) ) return true;
        }
        return false;
    }
}