package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

class PartitaTest {

	private Partita partita;

	@BeforeEach
	void setUp() throws Exception{
		
        this.partita = new Partita();
	}
	
	/* TEST DEL METODO vinta() */
	
	@Test
    void testPartitaNonVintaInizialmente() {
        assertFalse(this.partita.vinta());
    }
	
	@Test
	void testPartitaVintaStanzaVincente() {
		this.partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.vinta());
	}
	
	@Test
	void testPartitaPersaCfuEsauriti() {
		this.partita.getGiocatore().setCfu(0);
		assertFalse(this.partita.vinta());
	}
	
	
	/* TEST DEL METODO isFinita() */
	@Test
	void testPartitaNonFinitaInizialmente() {
		assertFalse(this.partita.isFinita());
	}

	@Test
    void testPartitaFinita() {
        this.partita.setFinita();
        assertTrue(this.partita.isFinita());
    }
	
	@Test
    void testPartitaFinitaCfuEsauriti() {
        this.partita.getGiocatore().setCfu(0);
        assertTrue(this.partita.isFinita());
    }
	
	
	/* TEST DEL METODO setGiocatore()*/
	
	@Test
	void testSetGiocatoreDiverso() {
	    Giocatore nuovoGiocatore = new Giocatore();
	    assertNotEquals(this.partita.getGiocatore(), nuovoGiocatore);
	    
	    this.partita.setGiocatore(nuovoGiocatore);
	    assertEquals(nuovoGiocatore, this.partita.getGiocatore());
	}
	
	@Test
	void testSetGiocatoreNull() {
	    this.partita.setGiocatore(null);
	    assertNull(this.partita.getGiocatore());
	}
	
	/* TEST DEL METODO setStanzaCorrente() */
    @Test
    void testSetStanzaDiversa() {
        Stanza nuovaStanza = new Stanza("nuova");
        this.partita.setStanzaCorrente(nuovaStanza);
        assertEquals(nuovaStanza, this.partita.getStanzaCorrente());
    }
    
    @Test
	void SetStanzaCorrenteNull() {
		
		this.partita.setStanzaCorrente(null);
		assertNull(this.partita.getStanzaCorrente());
	}

    
    /* TEST DEL METODO getLabirinto() */
    @Test
    void testGetLabirintoNonNullo() {
        assertNotNull(this.partita.getLabirinto());
    }
    
    
    /* TEST DEL METODO setLabirinto() */
    @Test
	void testSetLabirintoDiverso() {
	    Labirinto nuovoLabirinto = new Labirinto();
	    this.partita.setLabirinto(nuovoLabirinto);
	    assertEquals(nuovoLabirinto, this.partita.getLabirinto());
	}

	@Test
	void testSetLabirintoNull() {
	    this.partita.setLabirinto(null);
	    assertNull(this.partita.getLabirinto());
	}
}
