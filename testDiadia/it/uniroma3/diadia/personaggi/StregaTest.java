package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StregaTest {

	private Strega strega;

	private Partita partita;

	@BeforeEach
	void setUp() throws Exception {

		
		Labirinto.LabirintoBuilder builder = new Labirinto.LabirintoBuilder();
		builder.addStanzaIniziale("StanzaIniziale")
		.addStanza("StanzaRicca")
		.addStanza("StanzaPovera")
		.addStrega("Strega", "Io sono una strega pazzerrella")
		.addAdiacenza("StanzaIniziale", "StanzaRicca", "nord")
		.addAdiacenza("StanzaIniziale", "StanzaPovera", "sud")
		.addAttrezzo("Oro", 1, "StanzaRicca")
		.addPersonaggioInStanza("Strega", "StanzaIniziale");

		this.partita = new Partita(builder.getLabirinto());
		
		this.strega = (Strega) this.partita.getStanzaCorrente().getPersonaggio();
		
		System.out.println("Adiacenti a StanzaIniziale: " + partita.getStanzaCorrente().getMapStanzeAdiacenti().keySet());
	}
	
	@Test
	void testAgisciSenzaAdiacenti() throws Exception {
		// labirinto con una sola stanza senza adiacenze
		Labirinto labirinto = new Labirinto.LabirintoBuilder()
				.addStanzaIniziale("stanzaUnica")
				.addStrega("Strega", "Io sono una strega pazzerrella")
				.addPersonaggioInStanza("Strega", "stanzaUnica")
				.getLabirinto();

		Partita partita = new Partita(labirinto);
		AbstractPersonaggio strega = labirinto.getStanzaIniziale().getPersonaggio();

		// la strega non viene mai salutata, quindi deve provare ad agire e fallire per assenza di stanze
		String risultato = strega.agisci(partita);
		assertEquals("Non c'è nessuna stanza dove io ti possa spostare!", risultato);
		assertEquals("stanzaUnica", partita.getStanzaCorrente().getNome());
	}


	@Test
	void testAgisciSenzaSaluto() {
		// La strega non è stata salutata -> porta nella stanza con meno attrezzi = "StanzaPovera"
		String messaggio = strega.agisci(partita);
		assertEquals("La prossima volta saluta!!! Ti trasferisco nella stanza che contiene meno attrezzi!", messaggio);
		assertEquals("StanzaPovera", partita.getStanzaCorrente().getNome());
	}

	@Test
	void testAgisciConSaluto() {
		strega.saluta();
		String messaggio = strega.agisci(partita);
		assertEquals("Grazie per avermi salutata! Ti trasferisco nella stanza che contiene più attrezzi", messaggio);
		assertEquals("StanzaRicca", partita.getStanzaCorrente().getNome());
	}

	@Test
	void testRiceviRegaloNullo() {
		String risposta = strega.riceviRegalo(null, partita);
		assertEquals("HAHAHAHAHAHAAH", risposta);
	}

	@Test
	void testRiceviRegaloVero() {
		Attrezzo bastone = new Attrezzo("Bastone", 1);
		String risposta = strega.riceviRegalo(bastone, partita);
		assertEquals("HAHAHAHAHAHAAH", risposta);
	}



	/* test dei metodi di AbstractPersonaggio*/

	@Test
	void testGetNome() {

		assertEquals(this.strega.getNome(), "Strega");
	}

	@Test
	void testHaSalutato() {

		assertEquals(this.strega.haSalutato(), false);
		this.strega.saluta();
		assertEquals(this.strega.haSalutato(), true);
	}

	@Test
	void testSaluta() {

		assertEquals(this.strega.saluta(), "Ciao, io sono " + "Strega" + ". " + "Io sono una strega pazzerrella");
		this.strega.saluta();
		assertEquals(this.strega.saluta(), "Ciao, io sono " + "Strega" + ". " + "Ci siamo gia' presentati!");
	}



}
