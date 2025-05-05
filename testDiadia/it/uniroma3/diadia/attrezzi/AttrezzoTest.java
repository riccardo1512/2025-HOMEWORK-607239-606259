package it.uniroma3.diadia.attrezzi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AttrezzoTest {
	
	
	Attrezzo vuoto;
	Attrezzo spada;

	@BeforeEach
	void setUp() throws Exception {
		
		this.vuoto = new Attrezzo(null, 0);
		this.spada = new Attrezzo("spada", 5);
	}
	
	
	/* TEST del metodo getNome() */
	
	@Test
	void testGetNomeAttrezzoNullo() {
		assertNull(vuoto.getNome());
	}
	
	@Test
	void testGetNomeAttrezzoSpada() {
		assertEquals("spada", spada.getNome());
	}
	
	/* TEST del metodo getPeso() */
	@Test
	void testGetPesoAttrezzoNullo() {
		assertEquals(0, vuoto.getPeso());
	}
	
	@Test
	void testGetPesoAttrezzoSpada() {
		assertEquals(5, spada.getPeso());
	}

}
