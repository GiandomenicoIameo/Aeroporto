package it.unina.model;
import java.util.*;

public class Gate {

    private List<Volo> voli = new ArrayList<>();
    private String numeroGate;

    public Gate( String numero ) {
        numeroGate = numero;
    }

    public Gate() {
        //Costruttore per l'attività di testing.
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
        voli.remove( volo );
    }

    /* Metodo per
     l'attività
     di testing */
    public int getNumeroVoli() {
        return voli.size();
    }

    public void setNumeroGate( String numero ) {
        numeroGate = numero;
    }

    public String getNumeroGate() {
        return numeroGate;
    }
}
