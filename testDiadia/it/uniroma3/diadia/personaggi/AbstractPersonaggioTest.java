package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class AbstractPersonaggioTest {
	
	private AbstractPersonaggio personaggioAstratto;
	
	private Attrezzo attrezzo;

	@BeforeEach
	void setUp() throws Exception {
		
		this.attrezzo = new Attrezzo("bacchetta", 3);
		this.personaggioAstratto = new Mago("Merlino", "Io sono un grande mago", attrezzo);
	}

	@Test
	void testGetNome() {

		assertEquals(this.personaggioAstratto.getNome(), "Merlino");
	}
	
	@Test
	void testHaSalutato() {

		assertEquals(this.personaggioAstratto.haSalutato(), false);
		this.personaggioAstratto.saluta();
		assertEquals(this.personaggioAstratto.haSalutato(), true);
	}

	@Test
	void testSaluta() {
		

		assertEquals(this.personaggioAstratto.saluta(), "Ciao, io sono " + "Merlino" + ". " + "Io sono un grande mago");
		this.personaggioAstratto.saluta();
		assertEquals(this.personaggioAstratto.saluta(), "Ciao, io sono " + "Merlino" + ". " + "Ci siamo gia' presentati!");
	}

}
