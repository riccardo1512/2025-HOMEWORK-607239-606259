package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaProtectedTest {

	private StanzaProtected stanzaMagicaProtected;
	private Stanza stanzaAdiacente;
	
	private Attrezzo spada;
	private Attrezzo scudo;
	private Attrezzo arco;

	@BeforeEach
	void setUp() throws Exception {
		
		this.stanzaMagicaProtected = new StanzaMagicaProtected("magica protected", 3);
        this.stanzaAdiacente = new Stanza("n12");
        
        this.spada = new Attrezzo("spada", 3);
        this.scudo = new Attrezzo("scudo", 5);
        this.arco = new Attrezzo("arco", 1);
	}
	
	/* TEST di StanzaMagica */
	
	@Test
	void testAttrezzoMagico() {
		
		assertTrue(stanzaMagicaProtected.addAttrezzo(spada));
		assertTrue(stanzaMagicaProtected.hasAttrezzo("spada"));
		
		assertTrue(stanzaMagicaProtected.addAttrezzo(scudo));
		assertTrue(stanzaMagicaProtected.hasAttrezzo("scudo"));
		
		assertTrue(stanzaMagicaProtected.addAttrezzo(arco));
		assertFalse(stanzaMagicaProtected.hasAttrezzo("arco"));
		assertTrue(stanzaMagicaProtected.hasAttrezzo("ocra"));
		assertEquals("ocra", stanzaMagicaProtected.getAttrezzo("ocra").getNome());
		assertEquals(this.arco.getPeso() * 2, stanzaMagicaProtected.getAttrezzo("ocra").getPeso());
	}
	
	
	
	/* STESSI TEST DI STANZA */
	
	/* TEST DEL METODO addAttrezzo() */
	
	@Test
	void testAddAttrezzo() {
		
		assertTrue(this.stanzaMagicaProtected.addAttrezzo(this.spada));
	}

    @Test
    void testAddAttrezzoRaggiuntoLimite() {
        for (int i = 0; i < 10; i++) {
            this.stanzaMagicaProtected.addAttrezzo(new Attrezzo("attrezzo" + i, 1));
        }
        assertFalse(this.stanzaMagicaProtected.addAttrezzo(this.scudo));
    }
    
    
    /* TEST DEL METODO removeAttrezzo() */
    
    @Test
	void testRemoveAttrezzoNonPresente() {
		assertFalse(this.stanzaMagicaProtected.removeAttrezzo(spada));
	}

    @Test
    void testRemoveAttrezzoPresente() {
    	this.stanzaMagicaProtected.addAttrezzo(this.spada);
		assertTrue(this.stanzaMagicaProtected.removeAttrezzo(spada));	
    }
    
    
    /* TEST DEL METODO hasAttrezzo()*/

	@Test
	void testHasAttrezzoStanzaVuota() {
		assertFalse(this.stanzaMagicaProtected.hasAttrezzo("spada"));
	}
	

	@Test
	void testHasAttrezzoConSpada() {
		assertFalse(this.stanzaMagicaProtected.hasAttrezzo("spada"));
		this.stanzaMagicaProtected.addAttrezzo(this.spada);
		assertTrue(this.stanzaMagicaProtected.hasAttrezzo("spada"));
	}
	
	
	/* TEST DEL METODO getAttrezzo() */

	@Test
	void testGetAttrezzoNonEsistente() {
		assertNull(this.stanzaMagicaProtected.getAttrezzo("spada"));
	}

	@Test
    void testGetAttrezzoEsistente() {
        this.stanzaMagicaProtected.addAttrezzo(this.spada);
        assertEquals(this.spada, this.stanzaMagicaProtected.getAttrezzo("spada"));
    }
	
	
	/* TEST DEI METODI get/setStanzaAdiacente() */
	
    @Test
    void testGetStanzaAdiacenteDirezioneNonEsistente() {
        assertNull(this.stanzaMagicaProtected.getStanzaAdiacente("sud"));
    }

    @Test
    void testImpostaStanzaAdiacente() {
        this.stanzaMagicaProtected.impostaStanzaAdiacente("nord", this.stanzaAdiacente);
        assertEquals(this.stanzaAdiacente, this.stanzaMagicaProtected.getStanzaAdiacente("nord"));
    }
	
    @Test
    void testSostituzioneStanzaAdiacente() {
        Stanza nuovaStanza = new Stanza("n13");
        this.stanzaMagicaProtected.impostaStanzaAdiacente("nord", this.stanzaAdiacente);
        this.stanzaMagicaProtected.impostaStanzaAdiacente("nord", nuovaStanza);
        assertEquals(nuovaStanza, this.stanzaMagicaProtected.getStanzaAdiacente("nord"));
    }

}
