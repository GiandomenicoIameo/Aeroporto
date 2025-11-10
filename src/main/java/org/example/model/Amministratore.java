package org.example.model;
import java.util.*;

public class Amministratore extends Utente {

    // riferimenti a oggetti associati.
    private List<Volo> voliGestiti = new ArrayList<>();

    // L'amministratore esiste solo se c'è almeno un volo
    // predisposto dal sistema. Il seguente costruttore
    // presuppone l'esistenza di un esemplare di Volo.

    public Amministratore( String username, String password, Volo volo ) {
        super( username, password );
        voliGestiti.add( volo );
    }

    public Amministratore( String username, String password ) {
        super( username, password );
    }

    public Amministratore( String username, String password, List<Volo> list ) {
        super( username, password );
        voliGestiti = list;
    }

    @Override
    public void setUsername( String username ) {
        super.setUsername( username );
    }

    @Override
    public void setPassword( String password ) {
        super.setPassword( password );
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    public void inserisciVolo( Volo volo ) {
        voliGestiti.add( volo );
    }

    public void rimuoviVolo( Volo volo ) {
        voliGestiti.remove( volo );
    }

    public Volo trovaVolo( String codice ) {
        for( Volo elemento : voliGestiti ) {
            if( elemento.getCodice().equals( codice ) )
                return elemento;
        } return null;
    }

    public void modificaStatoVolo( Volo volo, String stato ) {
        if ( voliGestiti.contains( volo ) )
            volo.setStato( stato );
    }

    public int contaVoliGestiti() {
        return voliGestiti.size();
    }

    public List<Volo> getVoliGestiti() {
        return voliGestiti;
    }

    public boolean checkVolo( Volo volo ) {

        if( checkCodice( volo ) )
            return true;

        for( Volo element : voliGestiti ) {

            if( volo instanceof VoloInPartenza )
                return ( ( VoloInPartenza ) volo ).equals(
                        element );
            else
                return ( ( VoloInArrivo ) volo ).equals(
                        element );
        }
        // Questa istruzione viene eseguita se l'amministratore
        // in un dato momento non possiede voli gestiti.
        return false;
    }

    private boolean checkCodice( Volo volo ) {

        for( Volo element : voliGestiti ) {
            if( volo.getCodice().equals(
                    element.getCodice() ) )
                return true;
        }
        return false;
    }
}
