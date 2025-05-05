package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {

	private Stanza stanza;
	private Stanza stanzaAdiacente;
	private Attrezzo spada;
	private Attrezzo scudo;

	@BeforeEach
	void setUp() throws Exception {
		
		this.stanza = new Stanza("n11");
        this.stanzaAdiacente = new Stanza("n12");
        this.spada = new Attrezzo("spada", 3);
        this.scudo = new Attrezzo("scudo", 5);
	}
	
	/* TEST DEL METODO addAttrezzo() */
	
	@Test
	void testAddAttrezzo() {
		
		assertTrue(this.stanza.addAttrezzo(this.spada));
	}

    @Test
    void testAddAttrezzoRaggiuntoLimite() {
        for (int i = 0; i < 10; i++) {
            this.stanza.addAttrezzo(new Attrezzo("attrezzo" + i, 1));
        }
        assertFalse(this.stanza.addAttrezzo(this.scudo));
    }
    
    
    /* TEST DEL METODO removeAttrezzo() */
    
    @Test
	void testRemoveAttrezzoNonPresente() {
		assertFalse(this.stanza.removeAttrezzo(spada));
	}

    @Test
    void testRemoveAttrezzoPresente() {
    	this.stanza.addAttrezzo(this.spada);
		assertTrue(this.stanza.removeAttrezzo(spada));	
    }
    
    
    /* TEST DEL METODO hasAttrezzo()*/

	@Test
	void testHasAttrezzoStanzaVuota() {
		assertFalse(this.stanza.hasAttrezzo("spada"));
	}
	

	@Test
	void testHasAttrezzoConSpada() {
		assertFalse(this.stanza.hasAttrezzo("spada"));
		this.stanza.addAttrezzo(this.spada);
		assertTrue(this.stanza.hasAttrezzo("spada"));
	}
	
	
	/* TEST DEL METODO getAttrezzo() */

	@Test
	void testGetAttrezzoNonEsistente() {
		assertNull(this.stanza.getAttrezzo("spada"));
	}

	@Test
    void testGetAttrezzoEsistente() {
        this.stanza.addAttrezzo(this.spada);
        assertEquals(this.spada, this.stanza.getAttrezzo("spada"));
    }
	
	
	/* TEST DEI METODI get/setStanzaAdiacente() */
	
    @Test
    void testGetStanzaAdiacenteDirezioneNonEsistente() {
        assertNull(this.stanza.getStanzaAdiacente("sud"));
    }

    @Test
    void testImpostaStanzaAdiacente() {
        this.stanza.impostaStanzaAdiacente("nord", this.stanzaAdiacente);
        assertEquals(this.stanzaAdiacente, this.stanza.getStanzaAdiacente("nord"));
    }
	
    @Test
    void testSostituzioneStanzaAdiacente() {
        Stanza nuovaStanza = new Stanza("n13");
        this.stanza.impostaStanzaAdiacente("nord", this.stanzaAdiacente);
        this.stanza.impostaStanzaAdiacente("nord", nuovaStanza);
        assertEquals(nuovaStanza, this.stanza.getStanzaAdiacente("nord"));
    }

}
