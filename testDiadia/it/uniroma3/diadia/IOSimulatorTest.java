package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		
		List<String> istruzioni = new ArrayList<String>();
		istruzioni.add("prendi osso");
		istruzioni.add("vai est");
		istruzioni.add("fine");
		
		this.console = new IOSimulator(istruzioni);
		
		this.giocaStampa();
		
		assertTrue(true);	// simulazione finita
	}
	
	@Test
	void testPartitaVinta() {
		
		List<String> istruzioni = new ArrayList<String>();
		istruzioni.add("vai nord");
		
		this.console = new IOSimulator(istruzioni);
		
		this.giocaStampa();
		
		assertTrue(true);	// simulazione finita
	}
	
	@Test
	void testPartitaPersa() {
		
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
	void testPartitaPersaConComandoDiTroppo() {
		
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
	void testPartitaNonFinita() {
		
		List<String> istruzioni = new ArrayList<String>();
		istruzioni.add("prendi osso");
		
		this.console = new IOSimulator(istruzioni);
			
		this.giocaStampa();
			
		assertTrue(true);	// simulazione finita
		
	}
	
	@Test
	void testPartitaCompletaConTutteStanze() {
		
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
