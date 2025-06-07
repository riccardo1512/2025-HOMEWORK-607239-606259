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
        assertNull(this.stanza.getStanzaAdiacente(Direzione.SUD));
    }

    @Test
    void testImpostaStanzaAdiacente() {
        this.stanza.impostaStanzaAdiacente(Direzione.NORD, this.stanzaAdiacente);
        assertEquals(this.stanzaAdiacente, this.stanza.getStanzaAdiacente(Direzione.NORD));
    }
	
    @Test
    void testSostituzioneStanzaAdiacente() {
        Stanza nuovaStanza = new Stanza("n13");
        this.stanza.impostaStanzaAdiacente(Direzione.NORD, this.stanzaAdiacente);
        this.stanza.impostaStanzaAdiacente(Direzione.NORD, nuovaStanza);
        assertEquals(nuovaStanza, this.stanza.getStanzaAdiacente(Direzione.NORD));
    }

}
