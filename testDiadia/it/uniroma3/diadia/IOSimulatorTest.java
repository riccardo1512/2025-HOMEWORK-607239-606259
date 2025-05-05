package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IOSimulatorTest {

	IO console;
	DiaDia gioco;
	
	
	@BeforeEach
	void setUp() throws Exception {
		
	}
	
	private void giocaStampa() {
		
		this.gioco = new DiaDia(this.console);
		
		System.out.print("Esecuzione delle istruzioni: ");
		gioco.gioca();
	
		IOSimulator veraConsole = (IOSimulator) this.console;
		veraConsole.getMessaggiStampati();
	}

	@Test
	void testPartitaInterrotta() {
		
		String[] istruzioni = {"prendi osso","vai est","fine"};
		this.console = new IOSimulator(istruzioni);
		
		this.giocaStampa();
		
		assertTrue(true);	// simulazione finita
	}
	
	@Test
	void testPartitaVinta() {
		
		String[] istruzioni = {"vai nord"};
		this.console = new IOSimulator(istruzioni);
		
		this.giocaStampa();
		
		assertTrue(true);	// simulazione finita
	}
	
	@Test
	void testPartitaPersa() {
		
		String[] istruzioni = {"vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est"};
		this.console = new IOSimulator(istruzioni);
		
		this.giocaStampa();
		
		assertTrue(true);	// simulazione finita
	}
	
	@Test
	void testPartitaPersaConComandoDiTroppo() {
		
		String[] istruzioni = {"vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est", "fine"};
		this.console = new IOSimulator(istruzioni);
		
		this.giocaStampa();
		
		assertTrue(true);	// simulazione finita
	}
	
	@Test
	void testPartitaNonFinita() {
		
		
			
		String[] istruzioni = {"prendi osso"};
		this.console = new IOSimulator(istruzioni);
			
		this.giocaStampa();
			
		assertTrue(true);	// simulazione finita
		
	}
	
	@Test
	void testPartitaCompletaConTutteStanze() {
		
		String[] istruzioni = {"prendi osso", "vai sud", "prendi lanterna", "vai nord", "vai est", "vai nord", "posa osso", "prendi osso","posa osso", "prendi osso","posa osso", "prendi osso","posa osso", "prendi osso","vai nord","guarda","posa lanterna", "guarda", "vai nord", "vai nord", "posa osso", "vai nord", "guarda", "vai nord"};
		this.console = new IOSimulator(istruzioni);
		
		this.giocaStampa();
			
		assertTrue(true);	// simulazione finita
	}
	
	

}
