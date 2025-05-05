package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {

	private Stanza stanzaBuia;
	private Stanza stanzaAdiacente;
	private Attrezzo spada;
	private Attrezzo scudo;
	
	private Attrezzo lanterna;

	@BeforeEach
	void setUp() throws Exception {
		
		this.stanzaBuia = new StanzaBuia("stanza buia", "lanterna");
        this.stanzaAdiacente = new Stanza("adiacente");
        this.spada = new Attrezzo("spada", 3);
        this.scudo = new Attrezzo("scudo", 5);
        
        this.lanterna = new Attrezzo("lanterna", 1);
	}
	
	/* TEST DI STANZA BUIA */
	
	@Test
	void testGetDescrizioneBuia() {
		
		assertEquals("Qui c'è buio pesto", this.stanzaBuia.getDescrizione());
	}
	
	@Test
	void testGetDescrizioneBuiaConAttrezzoNecessario() {
		
		assertEquals("Qui c'è buio pesto", this.stanzaBuia.getDescrizione());
		this.stanzaBuia.addAttrezzo(this.lanterna);
		assertTrue(this.stanzaBuia.hasAttrezzo("lanterna"));
		assertNotEquals("Qui c'è buio pesto", this.stanzaBuia.getDescrizione());
	}
	
	
	
	/* STESSI TEST DI STANZA */
	
	/* TEST DEL METODO addAttrezzo() */
	
	@Test
	void testAddAttrezzo() {
		
		assertTrue(this.stanzaBuia.addAttrezzo(this.spada));
	}

    @Test
    void testAddAttrezzoRaggiuntoLimite() {
        for (int i = 0; i < 10; i++) {
            this.stanzaBuia.addAttrezzo(new Attrezzo("attrezzo" + i, 1));
        }
        assertFalse(this.stanzaBuia.addAttrezzo(this.scudo));
    }
    
    
    /* TEST DEL METODO removeAttrezzo() */
    
    @Test
	void testRemoveAttrezzoNonPresente() {
		assertFalse(this.stanzaBuia.removeAttrezzo(spada));
	}

    @Test
    void testRemoveAttrezzoPresente() {
    	this.stanzaBuia.addAttrezzo(this.spada);
		assertTrue(this.stanzaBuia.removeAttrezzo(spada));	
    }
    
    
    /* TEST DEL METODO hasAttrezzo()*/

	@Test
	void testHasAttrezzoStanzaVuota() {
		assertFalse(this.stanzaBuia.hasAttrezzo("spada"));
	}
	

	@Test
	void testHasAttrezzoConSpada() {
		assertFalse(this.stanzaBuia.hasAttrezzo("spada"));
		this.stanzaBuia.addAttrezzo(this.spada);
		assertTrue(this.stanzaBuia.hasAttrezzo("spada"));
	}
	
	
	/* TEST DEL METODO getAttrezzo() */

	@Test
	void testGetAttrezzoNonEsistente() {
		assertNull(this.stanzaBuia.getAttrezzo("spada"));
	}

	@Test
    void testGetAttrezzoEsistente() {
        this.stanzaBuia.addAttrezzo(this.spada);
        assertEquals(this.spada, this.stanzaBuia.getAttrezzo("spada"));
    }
	
	
	/* TEST DEI METODI get/setStanzaAdiacente() */
	
    @Test
    void testGetStanzaAdiacenteDirezioneNonEsistente() {
        assertNull(this.stanzaBuia.getStanzaAdiacente("sud"));
    }

    @Test
    void testImpostaStanzaAdiacente() {
        this.stanzaBuia.impostaStanzaAdiacente("nord", this.stanzaAdiacente);
        assertEquals(this.stanzaAdiacente, this.stanzaBuia.getStanzaAdiacente("nord"));
    }
	
    @Test
    void testSostituzioneStanzaAdiacente() {
        Stanza nuovaStanza = new Stanza("n13");
        this.stanzaBuia.impostaStanzaAdiacente("nord", this.stanzaAdiacente);
        this.stanzaBuia.impostaStanzaAdiacente("nord", nuovaStanza);
        assertEquals(nuovaStanza, this.stanzaBuia.getStanzaAdiacente("nord"));
    }

}
