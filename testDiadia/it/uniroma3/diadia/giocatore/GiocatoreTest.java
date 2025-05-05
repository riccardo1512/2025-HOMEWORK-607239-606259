package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class GiocatoreTest {
	
	private Giocatore giocatore;
	private Attrezzo spada;
    private Attrezzo scudo;
    private Attrezzo armatura;

	@BeforeEach
	void setUp() throws Exception {
		
		this.giocatore = new Giocatore();
		this.spada = new Attrezzo("spada", 5);
        this.scudo = new Attrezzo("scudo", 3);
        this.armatura = new Attrezzo("armatura", 7);
	}
	
	 /* TEST DEL METODO getCfu() */

    @Test
    void testCfuIniziali() {
        assertEquals(20, this.giocatore.getCfu());
    }
    
    /* TEST DEL METODO setCfu() */

    @Test
    void testSetCfu() {
        this.giocatore.setCfu(15);
        assertEquals(15, this.giocatore.getCfu());
    }
    
    /* TEST DEL METODO getBorsa() */

    @Test
    void testBorsaNonNull() {
        assertNotNull(this.giocatore.getBorsa());
    }
    
    @Test
	void testBorsaNull() {
		this.giocatore.setBorsa(null);
		assertNull(this.giocatore.getBorsa());
	}

    @Test
    void testBorsaVuotaInizialmente() {
        assertTrue(this.giocatore.getBorsa().isEmpty());
    }
	
	
    /* TEST DEL METODO prendi() */
    
    @Test
    void testPrendiAttrezzo() {
    	assertFalse(this.giocatore.getBorsa().hasAttrezzo("spada"));
        this.giocatore.prendi(this.spada);
        assertTrue(this.giocatore.getBorsa().hasAttrezzo("spada"));
    }
    
    @Test
    void testPrendiAttrezziMultipli() {
    	assertFalse(this.giocatore.getBorsa().hasAttrezzo("spada"));
    	assertFalse(this.giocatore.getBorsa().hasAttrezzo("scudo"));
    	this.giocatore.prendi(this.spada);
    	this.giocatore.prendi(this.scudo);
    	assertTrue(this.giocatore.getBorsa().hasAttrezzo("spada"));
    	assertTrue(this.giocatore.getBorsa().hasAttrezzo("scudo"));
    }
    
    @Test
    void testPrendiTroppiAttrezzi() {
    	assertFalse(this.giocatore.getBorsa().hasAttrezzo("spada"));
    	assertFalse(this.giocatore.getBorsa().hasAttrezzo("armatura"));
    	this.giocatore.prendi(this.spada);
    	this.giocatore.prendi(this.armatura);
    	assertTrue(this.giocatore.getBorsa().hasAttrezzo("spada"));
    	assertFalse(this.giocatore.getBorsa().hasAttrezzo("armatura"));	// giocatore non ha preso l'armatura perché troppo pesante
    }
    
    /* TEST DEL METODO posa() */

    @Test
    void testPosaAttrezzo() {
        this.giocatore.prendi(this.spada);
        this.giocatore.posa(this.spada);
        assertFalse(this.giocatore.getBorsa().hasAttrezzo("spada"));
    }
    
    @Test
    void testPosaAttrezziMultipli() {
    	this.giocatore.prendi(this.spada);
        this.giocatore.prendi(this.scudo);
        this.giocatore.posa(this.spada);
        this.giocatore.posa(this.scudo);
        assertFalse(this.giocatore.getBorsa().hasAttrezzo("spada"));
        assertFalse(this.giocatore.getBorsa().hasAttrezzo("scudo"));
    	
    }

    @Test
    void testPosaAttrezzoNonPresente() {
        assertFalse(this.giocatore.getBorsa().hasAttrezzo("spada"));
        this.giocatore.posa(spada);
        assertFalse(this.giocatore.getBorsa().hasAttrezzo("spada"));
    }

}
