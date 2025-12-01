package it.unina.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GateTest {
    private Gate gate;
    private Volo volo;

    @Before
    public void setUp() throws Exception {
        gate = new Gate();
        volo = new Volo();

        /*
         Gli assertNotNull qui
         sono (tecnicamente) superflui
         */

        /*
        In Java l'istruzione new Gate() non può mai restituire null.
        Se fallisce, il programma si blocca con un'eccezione
        prima ancora di arrivare all'assert.
         */
    }

    @Test
    public void aggiungiUnVoloValido() {
        gate.aggiungiVolo( volo );
        assertThat( gate.getNumeroVoli(), equalTo( 1 ) );
    }

    @Test
    public void aggiungiUnVoloNonValido() {
        gate.aggiungiVolo( null );
        assertThat( gate.getNumeroVoli(), equalTo( 0 ) );
    }

    @Test
    public void aggiungiVoliDuplicati() {
        gate.aggiungiVolo( volo );
        gate.aggiungiVolo( volo );
        assertThat( gate.getNumeroVoli(), equalTo( 1) );
    }

    @Test
    public void rimuoviUnVoloValido() {
        gate.aggiungiVolo( volo );
        gate.rimuoviVolo( volo );
        assertThat( gate.getNumeroVoli(), equalTo( 0 ) );
    }

    @Test
    public void rimuoviUnVoloNonValido() {
        gate.aggiungiVolo( null );
        gate.rimuoviVolo( null );
        assertThat( gate.getNumeroVoli(), equalTo( 0) );
    }

    @Test
    public void rimuoviVoliDuplicati() {
        gate.aggiungiVolo( volo );
        gate.aggiungiVolo( volo );
        gate.rimuoviVolo( volo );
        assertThat( gate.getNumeroVoli(), equalTo( 0 ));
    }
}