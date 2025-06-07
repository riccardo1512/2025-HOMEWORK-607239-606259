package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComandoRegalaTest {
	
	private Comando comando;
	private String nomeAttrezzo;

	@BeforeEach
	void setUp() throws Exception {
		
		this.comando = new ComandoRegala();
		this.nomeAttrezzo = "attrezzo";
	}

	
	/* TEST del metodo getNome() */
	@Test
	void testgetNome() {
		assertEquals("regala", comando.getNome());
	}
	
	
	/* TEST del metodo getParametro() */
	@Test
	void testGetParametroNonSpecificato() {
		assertNull(comando.getParametro());
	}
	
	@Test
	void testGetParametroSpecificato() {
		comando.setParametro(this.nomeAttrezzo);
		assertEquals(this.nomeAttrezzo, comando.getParametro());
	}

}
