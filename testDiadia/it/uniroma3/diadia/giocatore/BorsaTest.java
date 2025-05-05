package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {
	
	private Borsa borsa;
	private Attrezzo spada;
	private Attrezzo scudo;

	@BeforeEach
	void setUp() throws Exception {
		this.borsa = new Borsa();
		this.spada = new Attrezzo("spada", 5);
		this.scudo = new Attrezzo("scudo", 3);
	}
	
	
	/* TEST DEL METODO has getPesoMax() */
	
	@Test
	void testDefaultPesoMax() {
		assertEquals(this.borsa.getPesoMax(), 10);	// 10 è valore final static
	}
	
	/* TEST DEL METODO getPeso() */

	@Test
	void testGetPesoConAttrezzo() {
		this.borsa.addAttrezzo(spada);
		assertEquals(5, this.borsa.getPeso());
	}
	
	@Test
	void testGetPesoBorsaVuota() {
		assertEquals(0, this.borsa.getPeso());
	}

	/* TEST DEL METODO hasAttrezzo() */

	@Test
	void testHasAttrezzoNonPresente() {
		assertFalse(this.borsa.hasAttrezzo("spada"));
	}
	
	@Test
	void testHasAttrezzoPresente() {
		this.borsa.addAttrezzo(this.spada);
		assertTrue(this.borsa.hasAttrezzo("spada"));
	}
	
	/* TEST DEL METODO getAttrezzo() */

	@Test
	void testGetAttrezzoNonPresente() {
		assertNull(this.borsa.getAttrezzo("scudo"));
	}
	
	void testGetAttrezzoNonEsistente() {
		assertNull(this.borsa.getAttrezzo("arco"));
	}
	
	@Test
	void testGetAttrezzoPresente() {
		this.borsa.addAttrezzo(spada);
		assertEquals(spada, this.borsa.getAttrezzo("spada"));
	}

	
	/* TEST DEL METODO isEmpty() */
	@Test
    void testBorsaVuota() {
        assertTrue(this.borsa.isEmpty());
    }

    @Test
    void testBorsaNonVuota() {
        this.borsa.addAttrezzo(spada);
        assertFalse(this.borsa.isEmpty());
    }
    
    /* TEST DEL METODO addAttrezzo() */
	
	@Test
	void testAddAttrezzoSuccesso() {
		assertTrue(this.borsa.addAttrezzo(this.spada));
	}
	
	@Test
	void testAddAttrezzoBorsaPiena() {
		assertTrue(this.borsa.addAttrezzo(this.spada));
		assertTrue(this.borsa.addAttrezzo(this.spada));
		assertFalse(this.borsa.addAttrezzo(this.spada)); // peso massimo già raggiunto
	}
	
	/* TEST DEL METODO removeAttrezzo() */
	
	@Test
	void testRemoveAttrezzoBorsaVuota() {
		assertFalse(this.borsa.removeAttrezzo(this.spada));
	}
	
	@Test
	void testRemoveAttrezzoBorsaConAttrezzo() {
		this.borsa.addAttrezzo(this.spada);
		assertTrue(this.borsa.removeAttrezzo(this.spada));
		assertTrue(this.borsa.isEmpty()); // borsa ormai vuota
	}
	
	@Test
	void testRemoveAttrezziMultipli() {
		assertTrue(this.borsa.addAttrezzo(this.spada));
		assertTrue(this.borsa.addAttrezzo(this.scudo));
		assertTrue(this.borsa.removeAttrezzo(this.spada));
		assertTrue(this.borsa.removeAttrezzo(this.scudo));
		assertFalse(this.borsa.removeAttrezzo(this.spada)); // borsa ormai vuota
	}
}
