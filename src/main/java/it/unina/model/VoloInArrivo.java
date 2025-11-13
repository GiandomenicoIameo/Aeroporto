package it.unina.model;

public class VoloInArrivo extends Volo {

    private String aeroportoOrigine;

    public VoloInArrivo( Amministratore admin, String codice,
                         String compagniaAerea, String data, Orario orarioPartenza,
                         Orario orarioArrivo, String stato, Gate gate, String aeroportoOrigine ) {

        super( admin, codice, compagniaAerea, data,
                orarioPartenza, orarioArrivo, stato, gate );
        this.aeroportoOrigine = aeroportoOrigine;
    }

    public String getAeroportoOrigine() {
        return aeroportoOrigine;
    }

    /**
     *
     * Due esemplari di VoloInArrivo sono considerati equivalenti se e solo se
     * si riferiscono allo stesso oggetto, hanno lo stesso codice oppure
     * le date, gli orari di arrivo, di partenza, il numero di gate, la compagnia, lo
     * 	stato e l'aeroporto di partenza coincidono.
     *
     */
    public boolean equals( Volo volo ) {

        if ( volo instanceof VoloInArrivo ) {

            if ( super.equals( volo ) ) {
                return ( ( VoloInArrivo ) volo ).getAeroportoOrigine().equals(
                        aeroportoOrigine );
            }
        }
        return false;
    }
}