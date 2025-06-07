package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class MagoTest {


	private Mago mago;
	private Attrezzo attrezzo;
	
	private Partita partita;

	@BeforeEach
	void setUp() throws Exception {
		
		this.attrezzo = new Attrezzo("bacchetta", 3);
		
		this.mago = new Mago("Merlino", "Io sono mago Merlino", null);
		this.partita = new Partita(new Labirinto.LabirintoBuilder().addStanzaIniziale("stanzaIniziale").getLabirinto());
	}
	
	@Test
	void testAgisciAttrezzoNullo() {
		
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("bacchetta"));
		this.mago.agisci(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("bacchetta"));
	}
	
	@Test
	void testAgisciAttrezzoNonNullo() {
		
		this.mago.setAttrezzo(this.attrezzo);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("bacchetta"));
		this.mago.agisci(this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("bacchetta"));
	}
	
	@Test
	void testRiceviRegaloNullo() {
		
		assertEquals(this.mago.riceviRegalo(null, partita), "Che razza di regalo è un regalo vuoto?!");
	}
	
	@Test
	void testRiceviRegaloVero() {
		
		assertEquals(this.mago.riceviRegalo(new Attrezzo("spada", 10), partita), "Ho ho ho! Ma che bel regalo! Lo poso nella stanza leggermente modificato");
		assertEquals(this.partita.getStanzaCorrente().getAttrezzo("spada").getPeso(), 5);
	}
	

	
	/* test dei metodi di AbstractPersonaggio*/

	@Test
	void testGetNome() {

		assertEquals(this.mago.getNome(), "Merlino");
	}
	
	@Test
	void testHaSalutato() {

		assertEquals(this.mago.haSalutato(), false);
		this.mago.saluta();
		assertEquals(this.mago.haSalutato(), true);
	}

	@Test
	void testSaluta() {
		

		assertEquals(this.mago.saluta(), "Ciao, io sono " + "Merlino" + ". " + "Io sono mago Merlino");
		this.mago.saluta();
		assertEquals(this.mago.saluta(), "Ciao, io sono " + "Merlino" + ". " + "Ci siamo gia' presentati!");
	}


}
