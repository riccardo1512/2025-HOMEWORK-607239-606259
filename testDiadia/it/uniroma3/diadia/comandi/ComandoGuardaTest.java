package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComandoGuardaTest {

	private Comando comando;
	private String parametro;

	
	@BeforeEach
	void setUp() throws Exception {
		
		this.comando = new ComandoGuarda();
		this.parametro = "qualsiasi parametro";
	}

	
	/* TEST del metodo getNome() */
	@Test
	void testgetNome() {
		assertEquals("guarda", comando.getNome());
	}
	
	
	/* TEST del metodo getParametro() */
	@Test
	void testGetParametroNonSpecificato() {
		assertNull(comando.getParametro());
	}
	
	@Test
	void testGetParametroSpecificato() {
		comando.setParametro(this.parametro);
		assertEquals(this.parametro, comando.getParametro());
	}

}
