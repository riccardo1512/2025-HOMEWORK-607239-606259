package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

class PartitaTest {

	private Partita partita;

	@BeforeEach
	void setUp() throws Exception{
		
		Labirinto labirinto = new Labirinto.LabirintoBuilder().getLabirinto();
		this.creaStanze(labirinto);
		
        this.partita = new Partita(labirinto);
	}
	
	/**
	 * Crea tutte le stanze e le porte di collegamento
	 */
	private void creaStanze(Labirinto labirinto) {

		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);

		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");

		Stanza stanzaMagica = new StanzaMagica("magica");
		Stanza stanzaBuia = new StanzaBuia("buia", "lanterna");
		Stanza stanzaBloccata = new StanzaBloccata("buia", Direzione.NORD, "osso");

		/* collega le stanze */
		atrio.impostaStanzaAdiacente(Direzione.NORD, biblioteca);
		atrio.impostaStanzaAdiacente(Direzione.EST, aulaN11);
		atrio.impostaStanzaAdiacente(Direzione.SUD, aulaN10);
		atrio.impostaStanzaAdiacente(Direzione.OVEST, laboratorio);

		aulaN11.impostaStanzaAdiacente(Direzione.EST, laboratorio);
		aulaN11.impostaStanzaAdiacente(Direzione.OVEST, atrio);
		/* aggiunta stanza magica, buia e bloccata*/
		aulaN11.impostaStanzaAdiacente(Direzione.NORD, stanzaMagica);
		stanzaMagica.impostaStanzaAdiacente(Direzione.NORD, stanzaBuia);
		stanzaBuia.impostaStanzaAdiacente(Direzione.NORD, stanzaBloccata);
		stanzaBloccata.impostaStanzaAdiacente(Direzione.NORD, atrio);

		aulaN10.impostaStanzaAdiacente(Direzione.NORD, atrio);
		aulaN10.impostaStanzaAdiacente(Direzione.EST, aulaN11);
		aulaN10.impostaStanzaAdiacente(Direzione.OVEST, laboratorio);

		laboratorio.impostaStanzaAdiacente(Direzione.EST, atrio);
		laboratorio.impostaStanzaAdiacente(Direzione.OVEST, aulaN11);
		biblioteca.impostaStanzaAdiacente(Direzione.SUD, atrio);

		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// il gioco comincia nell'atrio
		labirinto.setStanzaIniziale(atrio);  
		labirinto.setStanzaVincente(biblioteca);
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
	void testSetLabirintoDiverso() throws FileNotFoundException, FormatoFileNonValidoException {
	    Labirinto nuovoLabirinto = new Labirinto.LabirintoBuilder().getLabirinto();
	    this.partita.setLabirinto(nuovoLabirinto);
	    assertEquals(nuovoLabirinto, this.partita.getLabirinto());
	}

	@Test
	void testSetLabirintoNull() {
	    this.partita.setLabirinto(null);
	    assertNull(this.partita.getLabirinto());
	}
}
