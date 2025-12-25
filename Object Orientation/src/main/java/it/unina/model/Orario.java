package it.unina.model;

public class Orario {

    // variabili di istanza
    private int ora;    // 0 - 23
    private int minuti; // 0 - 59

    /**
     * Il costruttore preso in esame imposta i valori per le variabili ora e minuti
     * usando il formato orario universale. Se i valori passati in input al
     * costruttore non soddisfano i requisiti, il metodo assicura comunque
     * che l'oggetto si trovi in uno stato coerente.
     *
     * @param ora
     * @param minuti
     */
    public Orario( int ora, int minuti ) {
        setOrario( ora, minuti );
    }

    public Orario( int ora ) {
        this( ora,0 );
    }

    /**
     *  Inizializza ogni variabile di istanza a 0 e
     *  assicura che che gli oggetti si trovino in uno
     *  stato coerente.
     */
    public Orario() {
        this( 0,0 );
    }

    public void setOrario( int ora, int minuti ) {
        setOra( ora );
        setMinuti( minuti );
    }

    public void setOra( int ora ) {

        if( ora < 0 || ora > 23 ) {
            this.ora = 0;
        } else {
            this.ora = ora;
        }
    }

    public void setMinuti( int minuti ) {

        if( minuti < 0 || minuti > 59 ) {
            this.minuti = 0;
        } else {
            this.minuti = minuti;
        }
    }

    public int getOra() {
        return ora;
    }

    public int getMinuti() {
        return minuti;
    }

    /**
     * Due esemplari di Orario sono considerati equivalenti se e solo se
     * si riferiscono allo stesso oggetto oppure l'ora e i minuti coincidono.
     *
     * @return Se i due esemplari sono equivalenti secondo la relazione di
     * equivalenza definita sopra allora viene restituito true; altrimenti false.
     */

    @Override
    public boolean equals( Object obj ) {
        if( this == obj ) {
            return true;
        }

        if( obj == null || getClass() != obj.getClass() ) {
            return false;
        }

        Orario orario = ( Orario ) obj;

        return ( ora == orario.getOra() &&
                minuti == orario.getMinuti() );
    }
}
