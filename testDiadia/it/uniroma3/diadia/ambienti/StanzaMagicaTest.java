package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {

	private Stanza stanzaMagica;
	private Stanza stanzaAdiacente;
	
	private Attrezzo spada;
	private Attrezzo scudo;
	private Attrezzo arco;

	@BeforeEach
	void setUp() throws Exception {
		
		this.stanzaMagica = new StanzaMagica("magica", 3);
        this.stanzaAdiacente = new Stanza("n12");
        
        this.spada = new Attrezzo("spada", 3);
        this.scudo = new Attrezzo("scudo", 5);
        this.arco = new Attrezzo("arco", 1);
	}
	
	/* TEST di StanzaMagica */
	
	@Test
	void testAttrezzoMagico() {
		
		assertTrue(stanzaMagica.addAttrezzo(spada));
		assertTrue(stanzaMagica.hasAttrezzo("spada"));
		
		assertTrue(stanzaMagica.addAttrezzo(scudo));
		assertTrue(stanzaMagica.hasAttrezzo("scudo"));
		
		assertTrue(stanzaMagica.addAttrezzo(arco));
		assertFalse(stanzaMagica.hasAttrezzo("arco"));
		assertTrue(stanzaMagica.hasAttrezzo("ocra"));
		assertEquals("ocra", stanzaMagica.getAttrezzo("ocra").getNome());
		assertEquals(this.arco.getPeso() * 2, stanzaMagica.getAttrezzo("ocra").getPeso());
	}
	
	
	
	/* STESSI TEST DI STANZA */
	
	/* TEST DEL METODO addAttrezzo() */
	
	@Test
	void testAddAttrezzo() {
		
		assertTrue(this.stanzaMagica.addAttrezzo(this.spada));
	}

    @Test
    void testAddAttrezzoRaggiuntoLimite() {
        for (int i = 0; i < 10; i++) {
            this.stanzaMagica.addAttrezzo(new Attrezzo("attrezzo" + i, 1));
        }
        assertFalse(this.stanzaMagica.addAttrezzo(this.scudo));
    }
    
    
    /* TEST DEL METODO removeAttrezzo() */
    
    @Test
	void testRemoveAttrezzoNonPresente() {
		assertFalse(this.stanzaMagica.removeAttrezzo(spada));
	}

    @Test
    void testRemoveAttrezzoPresente() {
    	this.stanzaMagica.addAttrezzo(this.spada);
		assertTrue(this.stanzaMagica.removeAttrezzo(spada));	
    }
    
    
    /* TEST DEL METODO hasAttrezzo()*/

	@Test
	void testHasAttrezzoStanzaVuota() {
		assertFalse(this.stanzaMagica.hasAttrezzo("spada"));
	}
	

	@Test
	void testHasAttrezzoConSpada() {
		assertFalse(this.stanzaMagica.hasAttrezzo("spada"));
		this.stanzaMagica.addAttrezzo(this.spada);
		assertTrue(this.stanzaMagica.hasAttrezzo("spada"));
	}
	
	
	/* TEST DEL METODO getAttrezzo() */

	@Test
	void testGetAttrezzoNonEsistente() {
		assertNull(this.stanzaMagica.getAttrezzo("spada"));
	}

	@Test
    void testGetAttrezzoEsistente() {
        this.stanzaMagica.addAttrezzo(this.spada);
        assertEquals(this.spada, this.stanzaMagica.getAttrezzo("spada"));
    }
	
	
	/* TEST DEI METODI get/setStanzaAdiacente() */
	
    @Test
    void testGetStanzaAdiacenteDirezioneNonEsistente() {
        assertNull(this.stanzaMagica.getStanzaAdiacente("sud"));
    }

    @Test
    void testImpostaStanzaAdiacente() {
        this.stanzaMagica.impostaStanzaAdiacente("nord", this.stanzaAdiacente);
        assertEquals(this.stanzaAdiacente, this.stanzaMagica.getStanzaAdiacente("nord"));
    }
	
    @Test
    void testSostituzioneStanzaAdiacente() {
        Stanza nuovaStanza = new Stanza("n13");
        this.stanzaMagica.impostaStanzaAdiacente("nord", this.stanzaAdiacente);
        this.stanzaMagica.impostaStanzaAdiacente("nord", nuovaStanza);
        assertEquals(nuovaStanza, this.stanzaMagica.getStanzaAdiacente("nord"));
    }

}
