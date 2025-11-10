package org.example.model;
import java.util.*;

public class Gate {

    private List<Volo> voli = new ArrayList<>();
    private String numeroGate;

    public Gate( String numero ) {
        numeroGate = numero;
    }

    public boolean checkVolo( VoloInPartenza volo ) {

        for( Volo element : voli ) {
            if ( !element.getData().equals(volo.getData() ) ) {
                continue;
            }

            if ( element instanceof VoloInPartenza &&
                    volo.getOrarioPartenza().equals(
                            element.getOrarioPartenza() ) ||
                    element instanceof VoloInArrivo &&
                            volo.getOrarioPartenza().equals(
                                    element.getOrarioArrivo() ))
                return false;

        }
        return true;
    }

    public boolean checkVolo( VoloInArrivo volo ) {

        for( Volo element : voli ) {

            if ( !volo.getData().equals( element.getData() ) ) {
                continue;
            }

            if( element instanceof VoloInArrivo &&
                    volo.getOrarioArrivo().equals(
                            element.getOrarioArrivo() ) ||
                    element instanceof VoloInPartenza &&
                            volo.getOrarioArrivo().equals(
                                    element.getOrarioPartenza() ) )
                return false;
        }
        return true;
    }

    public void aggiungiVolo( Volo volo ) {
        voli.add( volo );
    }

    public void rimuoviVolo( Volo volo ) {
        if( voli.contains( volo ) )
            voli.remove( volo );
    }

    public void setNumeroGate( String numero ) {
        numeroGate = numero;
    }

    public String getNumeroGate() {
        return numeroGate;
    }
}
