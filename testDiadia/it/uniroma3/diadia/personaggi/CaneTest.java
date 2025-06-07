package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class CaneTest {
	
	private Cane cane;
	private Attrezzo attrezzo;
	
	private Partita partita;

	@BeforeEach
	void setUp() throws Exception {
		
		this.attrezzo = new Attrezzo("torcia", 3);
		this.cane = new Cane("Cagnolone", "woof woof", this.attrezzo, "croccantini");
		this.partita = new Partita(new Labirinto.LabirintoBuilder().addStanzaIniziale("stanzaIniziale").getLabirinto());
	}
	
	@Test
	void testAgisciMordi() {
		
		assertEquals(partita.getGiocatore().getCfu(), 20);
		this.cane.agisci(partita);
		assertEquals(partita.getGiocatore().getCfu(), 19);
	}

	@Test
	void testRiceviRegaloGradito() {
		
		assertEquals(partita.getGiocatore().getCfu(), 20);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("torcia"));
		Attrezzo regalo = new Attrezzo("croccantini", 1);
		this.cane.riceviRegalo(regalo, partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("torcia"));
		assertEquals(partita.getGiocatore().getCfu(), 20);
	}
	
	@Test
	void testRiceviRegaloNonGradito() {
		
		assertEquals(partita.getGiocatore().getCfu(), 20);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("torcia"));
		Attrezzo regalo = new Attrezzo("regaloBrutto", 1);
		this.cane.riceviRegalo(regalo, partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("torcia"));
		assertEquals(partita.getGiocatore().getCfu(), 19);
	}

	
	
	/* test dei metodi di AbstractPersonaggio*/

	@Test
	void testGetNome() {

		assertEquals(this.cane.getNome(), "Cagnolone");
	}
	
	@Test
	void testHaSalutato() {

		assertEquals(this.cane.haSalutato(), false);
		this.cane.saluta();
		assertEquals(this.cane.haSalutato(), true);
	}

	@Test
	void testSaluta() {
		

		assertEquals(this.cane.saluta(), "Ciao, io sono " + "Cagnolone" + ". " + "woof woof");
		this.cane.saluta();
		assertEquals(this.cane.saluta(), "Ciao, io sono " + "Cagnolone" + ". " + "Ci siamo gia' presentati!");
	}


}
