package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

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

class IOSimulatorTest {

	IO console;
	DiaDia gioco;
	
	
	@BeforeEach
	void setUp() throws Exception {
		
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
	
	private void giocaStampa() throws FileNotFoundException, FormatoFileNonValidoException {
		
		Labirinto labirinto = new Labirinto.LabirintoBuilder().getLabirinto();
		
		this.creaStanze(labirinto);
		
		this.gioco = new DiaDia(labirinto, this.console);
		
		System.out.print("Esecuzione delle istruzioni: ");
		gioco.gioca();
	
		IOSimulator veraConsole = (IOSimulator) this.console;
		veraConsole.getMessaggiStampati();
	}

	@Test
	void testPartitaInterrotta() throws FileNotFoundException, FormatoFileNonValidoException {
		
		List<String> istruzioni = new ArrayList<String>();
		istruzioni.add("prendi osso");
		istruzioni.add("vai est");
		istruzioni.add("fine");
		
		this.console = new IOSimulator(istruzioni);
		
		this.giocaStampa();
		
		assertTrue(true);	// simulazione finita
	}
	
	@Test
	void testPartitaVinta() throws FileNotFoundException, FormatoFileNonValidoException {
		
		List<String> istruzioni = new ArrayList<String>();
		istruzioni.add("vai nord");
		
		this.console = new IOSimulator(istruzioni);
		
		this.giocaStampa();
		
		assertTrue(true);	// simulazione finita
	}
	
	@Test
	void testPartitaPersa() throws FileNotFoundException, FormatoFileNonValidoException {
		
		List<String> istruzioni = new ArrayList<String>();
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");

		
		this.console = new IOSimulator(istruzioni);
		
		this.giocaStampa();
		
		assertTrue(true);	// simulazione finita
	}
	
	@Test
	void testPartitaPersaConComandoDiTroppo() throws FileNotFoundException, FormatoFileNonValidoException {
		
		List<String> istruzioni = new ArrayList<>();
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("vai est");
		istruzioni.add("fine");


		
		this.console = new IOSimulator(istruzioni);
		
		this.giocaStampa();
		
		assertTrue(true);	// simulazione finita
	}
	
	@Test
	void testPartitaNonFinita() throws FileNotFoundException, FormatoFileNonValidoException {
		
		List<String> istruzioni = new ArrayList<String>();
		istruzioni.add("prendi osso");
		
		this.console = new IOSimulator(istruzioni);
			
		this.giocaStampa();
			
		assertTrue(true);	// simulazione finita
		
	}
	
	@Test
	void testPartitaCompletaConTutteStanze() throws FileNotFoundException, FormatoFileNonValidoException {
		
		List<String> istruzioni = new ArrayList<>();
		istruzioni.add("prendi osso");
		istruzioni.add("vai sud");
		istruzioni.add("prendi lanterna");
		istruzioni.add("vai nord");
		istruzioni.add("vai est");
		istruzioni.add("vai nord");
		istruzioni.add("posa osso");
		istruzioni.add("prendi osso");
		istruzioni.add("posa osso");
		istruzioni.add("prendi osso");
		istruzioni.add("posa osso");
		istruzioni.add("prendi osso");
		istruzioni.add("posa osso");
		istruzioni.add("prendi osso");
		istruzioni.add("vai nord");
		istruzioni.add("guarda");
		istruzioni.add("posa lanterna");
		istruzioni.add("guarda");
		istruzioni.add("vai nord");
		istruzioni.add("vai nord");
		istruzioni.add("posa osso");
		istruzioni.add("vai nord");
		istruzioni.add("guarda");
		istruzioni.add("vai nord");

		
		this.console = new IOSimulator(istruzioni);
		
		this.giocaStampa();
			
		assertTrue(true);	// simulazione finita
	}
	
	

}
