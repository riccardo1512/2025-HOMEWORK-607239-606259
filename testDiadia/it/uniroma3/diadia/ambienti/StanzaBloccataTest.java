package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	

	Stanza stanzaBloccata;
	Stanza stanzaAdiacente;
	Attrezzo spada;
	Attrezzo scudo;
	
	Attrezzo chiave;

	@BeforeEach
	void setUp() throws Exception {
		
		this.stanzaBloccata = new StanzaBloccata("bloccata", Direzione.NORD, "chiave");
		this.stanzaAdiacente = new Stanza("adiacente");
		
		this.spada = new Attrezzo("spada", 3);
		this.scudo = new Attrezzo("scudo", 5);
		
		this.chiave = new Attrezzo("chiave", 1);
	}
	
	/* TEST DI STANZABLOCCATA  */
	

	/* TEST DEI METODI get/setStanzaAdiacente() */
	
    @Test
    void testGetStanzaAdiacenteDirezioneNonEsistente() {
        assertNull(this.stanzaBloccata.getStanzaAdiacente(Direzione.SUD));
    }

    @Test
    void testImpostaStanzaAdiacenteDirezioneBloccata() {
        this.stanzaBloccata.impostaStanzaAdiacente(Direzione.NORD, this.stanzaAdiacente);
        assertEquals(this.stanzaBloccata, this.stanzaBloccata.getStanzaAdiacente(Direzione.NORD));
    }
    
    @Test
    void testImpostaStanzaAdiacenteDirezioneNonBloccata() {
        this.stanzaBloccata.impostaStanzaAdiacente(Direzione.SUD, this.stanzaAdiacente);
        assertEquals(this.stanzaAdiacente, this.stanzaBloccata.getStanzaAdiacente(Direzione.SUD));
    }
	
    @Test
    void testSostituzioneStanzaAdiacenteDirezioneNonBloccata() {
        Stanza nuovaStanza = new Stanza("n13");
        this.stanzaBloccata.impostaStanzaAdiacente(Direzione.EST, this.stanzaAdiacente);
        this.stanzaBloccata.impostaStanzaAdiacente(Direzione.EST, nuovaStanza);
        assertEquals(nuovaStanza, this.stanzaBloccata.getStanzaAdiacente(Direzione.EST));
    }
    
    @Test
    void testSostituzioneStanzaAdiacenteDirezioneBloccata() {
        Stanza nuovaStanza = new Stanza("n13");
        this.stanzaBloccata.impostaStanzaAdiacente(Direzione.NORD, this.stanzaAdiacente);
        this.stanzaBloccata.impostaStanzaAdiacente(Direzione.NORD, nuovaStanza);
        assertEquals(this.stanzaBloccata, this.stanzaBloccata.getStanzaAdiacente(Direzione.NORD));
    }
    
    @Test
    void testGetStanzaAdiacenteDirezioneBloccataConAttrezzoNecessario() {
    	
    	this.stanzaBloccata.impostaStanzaAdiacente(Direzione.NORD, this.stanzaAdiacente);
    	 assertEquals(this.stanzaBloccata, this.stanzaBloccata.getStanzaAdiacente(Direzione.NORD));
    	 this.stanzaBloccata.addAttrezzo(this.chiave);
    	 assertEquals(this.stanzaAdiacente, this.stanzaBloccata.getStanzaAdiacente(Direzione.NORD));
    }


	
	/* TEST DEL METODO getDescrizione */
	@Test
	void testGetDescrizione() {
		
		assertEquals("Questa stanza è una stanza bloccata, per sbloccare la direzione " + "NORD" + " posa un oggetto " + "chiave", this.stanzaBloccata.getDescrizione());
	}
	
	
	/* STESSI TEST DI STANZA */
	
	/* TEST DEL METODO addAttrezzo() */
	
	@Test
	void testAddAttrezzo() {
		
		assertTrue(this.stanzaBloccata.addAttrezzo(this.spada));
	}

    @Test
    void testAddAttrezzoRaggiuntoLimite() {
        for (int i = 0; i < 10; i++) {
            this.stanzaBloccata.addAttrezzo(new Attrezzo("attrezzo" + i, 1));
        }
        assertFalse(this.stanzaBloccata.addAttrezzo(this.scudo));
    }
    
    
    /* TEST DEL METODO removeAttrezzo() */
    
    @Test
	void testRemoveAttrezzoNonPresente() {
		assertFalse(this.stanzaBloccata.removeAttrezzo(spada));
	}

    @Test
    void testRemoveAttrezzoPresente() {
    	this.stanzaBloccata.addAttrezzo(this.spada);
		assertTrue(this.stanzaBloccata.removeAttrezzo(spada));	
    }
    
    
    /* TEST DEL METODO hasAttrezzo()*/

	@Test
	void testHasAttrezzoStanzaVuota() {
		assertFalse(this.stanzaBloccata.hasAttrezzo("spada"));
	}
	

	@Test
	void testHasAttrezzoConSpada() {
		assertFalse(this.stanzaBloccata.hasAttrezzo("spada"));
		this.stanzaBloccata.addAttrezzo(this.spada);
		assertTrue(this.stanzaBloccata.hasAttrezzo("spada"));
	}
	
	
	/* TEST DEL METODO getAttrezzo() */

	@Test
	void testGetAttrezzoNonEsistente() {
		assertNull(this.stanzaBloccata.getAttrezzo("spada"));
	}

	@Test
    void testGetAttrezzoEsistente() {
        this.stanzaBloccata.addAttrezzo(this.spada);
        assertEquals(this.spada, this.stanzaBloccata.getAttrezzo("spada"));
    }
}
