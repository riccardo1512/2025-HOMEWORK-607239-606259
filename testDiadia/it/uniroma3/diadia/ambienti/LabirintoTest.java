package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {
	
	private Labirinto.LabirintoBuilder labirintoBuilder;
	private Stanza nuovaStanza;

	@BeforeEach
	void setUp() throws Exception {
		this.labirintoBuilder = Labirinto.newBuilder();
		this.nuovaStanza = new Stanza("nuova stanza");
	}
	
	 
    /* TEST LABIRINTO INIZIALE NON NULLO  */

	@Test
	void testLabirintoNotNull() {
		assertNotNull(this.labirintoBuilder);
	}
   
	/* TEST DEL METODO get/setStanzaIniziale() */
	
	@Test
	void testGetSetStanzaIniziale() {
		assertNotEquals(this.labirintoBuilder.getStanzaVincente(), this.nuovaStanza);
		this.labirintoBuilder.addStanzaIniziale(this.nuovaStanza);
		assertEquals(this.labirintoBuilder.getStanzaIniziale(), this.nuovaStanza);
	}
	
	@Test
	void testSetStanzaInizialeNull() {
		this.labirintoBuilder.addStanzaIniziale((String) null);
		assertNull(this.labirintoBuilder.getStanzaIniziale());
		
		this.labirintoBuilder.addStanzaIniziale((Stanza) null);
		assertNull(this.labirintoBuilder.getStanzaIniziale());
	}
	
	/* TEST DEL METODO get/setStanzaVincente() */
	
	@Test
	void testGetSetStanzaVincente() {
		assertNotEquals(this.labirintoBuilder.getStanzaVincente(), this.nuovaStanza);
		this.labirintoBuilder.addStanzaVincente(this.nuovaStanza);
		assertEquals(this.labirintoBuilder.getStanzaVincente(), this.nuovaStanza);
	}
	
	@Test
	void testSetStanzaVincenteNull() {
		this.labirintoBuilder.addStanzaVincente((String) null);
		assertNull(this.labirintoBuilder.getStanzaVincente());
		
		this.labirintoBuilder.addStanzaVincente((Stanza) null);
		assertNull(this.labirintoBuilder.getStanzaVincente());
	}
	
}
