package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComandoVaiTest {

	private Comando comando;
	private String direzione;

	
	@BeforeEach
	void setUp() throws Exception {
		
		this.comando = new ComandoVai();
		this.direzione = "nord";
	}

	
	/* TEST del metodo getNome() */
	@Test
	void testgetNome() {
		assertEquals("vai", comando.getNome());
	}
	
	
	/* TEST del metodo getParametro() */
	@Test
	void testGetParametroNonSpecificato() {
		assertNull(comando.getParametro());
	}
	
	@Test
	void testGetParametroSpecificato() {
		comando.setParametro(this.direzione);
		assertEquals(this.direzione, comando.getParametro());
	}
}
