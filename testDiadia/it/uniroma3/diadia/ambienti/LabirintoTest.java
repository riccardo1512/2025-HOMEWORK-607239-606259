package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {
	
	private Labirinto labirinto;
	private Stanza nuovaStanza;

	@BeforeEach
	void setUp() throws Exception {
		this.labirinto = new Labirinto();
		this.nuovaStanza = new Stanza("nuova stanza");
	}
	
	 
    /* TEST LABIRINTO INIZIALE NON NULLO  */

	@Test
	void testLabirintoNotNull() {
		assertNotNull(this.labirinto);
	}
	
	/* TEST DEL METODO getStanzaIniziale() */

    @Test
    void testGetStanzaInizialeDefault() {
        assertEquals("Atrio", this.labirinto.getStanzaIniziale().getNome());
    }

    /* TEST DEL METODO getStanzaVincente() */

    @Test
    void testGetStanzaVincenteDefault() {
        assertEquals("Biblioteca", this.labirinto.getStanzaVincente().getNome());
    }
   
	/* TEST DEL METODO setStanzaIniziale() */
	
	@Test
	void testSetStanzaIniziale() {
		assertNotEquals(this.labirinto.getStanzaVincente(), this.nuovaStanza);
		this.labirinto.setStanzaIniziale(this.nuovaStanza);
		assertEquals(this.labirinto.getStanzaIniziale(), this.nuovaStanza);
	}
	
	@Test
	void testSetStanzaInizialeNull() {
		this.labirinto.setStanzaIniziale(null);
		assertNull(this.labirinto.getStanzaIniziale());
	}
	
	/* TEST DEL METODO setStanzaVincente() */
	
	@Test
	void testSetStanzaVincente() {
		assertNotEquals(this.labirinto.getStanzaVincente(), this.nuovaStanza);
		this.labirinto.setStanzaVincente(this.nuovaStanza);
		assertEquals(this.labirinto.getStanzaVincente(), this.nuovaStanza);
	}
	
	@Test
	void testSetStanzaVincenteNull() {
		this.labirinto.setStanzaVincente(null);
		assertNull(this.labirinto.getStanzaVincente());
	}
	
}
