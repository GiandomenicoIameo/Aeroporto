package it.unina.model;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class OrarioTest {
    private Orario orario;

    // Precondizione
    @Before
    public void setUp() throws Exception {
        orario = new Orario();
    }

    @Test
    public void testSetOraValida() {
        orario.setOra( 3 );
        assertThat( orario.getOra(), equalTo( 3 ) );
    }

    @Test
    public void testSetOraSuperioreAlMassimo() {
        orario.setOra( 26 );
        // assertThat(osservato, atteso)
        assertThat( orario.getOra(), equalTo(0 ) );
    }

    @Test
    public void testSetOraNegativa() {
        orario.setOra( -3 );
        assertThat( orario.getOra(), equalTo( 0 ) );
    }

    @Test
    public void testSetMinutiValidi() {
        orario.setMinuti( 4 );
        assertThat( orario.getMinuti(), equalTo( 4 ) );
    }

    @Test
    public void testSetMinutiSuperioriAlMassimo() {
        orario.setMinuti( 62 );
        assertThat( orario.getMinuti(), equalTo( 0 ) );
    }

    @Test
    public void testSetMinutiNegativi() {
        orario.setMinuti( -2 );
        assertThat( orario.getMinuti(), equalTo( 0 ) );
    }
}