package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {
	
	private Borsa borsa;
	private Attrezzo spada;
	private Attrezzo scudo;
	private Attrezzo arco;
	
	private Attrezzo piombo;
	private Attrezzo ps;
	private Attrezzo piuma;
	private Attrezzo libro;

	@BeforeEach
	void setUp() throws Exception {
		this.borsa = new Borsa();
		this.spada = new Attrezzo("spada", 5);
		this.scudo = new Attrezzo("scudo", 3);
		this.arco = new Attrezzo("arco", 4);
		
		
		this.piombo = new Attrezzo("piombo", 10);
		this.ps = new Attrezzo("ps", 5);
		this.piuma = new Attrezzo("piuma", 1);
		this.libro = new Attrezzo("libro", 5);
		
	}
	
	
	/* TEST DEL METODO has getPesoMax() */
	
	@Test
	void testDefaultPesoMax() {
		assertEquals(this.borsa.getPesoMax(), 10);	// 10 è valore final static
	}
	
	/* TEST DEL METODO getPeso() */

	@Test
	void testGetPesoConAttrezzo() {
		this.borsa.addAttrezzo(spada);
		assertEquals(5, this.borsa.getPeso());
	}
	
	@Test
	void testGetPesoBorsaVuota() {
		assertEquals(0, this.borsa.getPeso());
	}

	/* TEST DEL METODO hasAttrezzo() */

	@Test
	void testHasAttrezzoNonPresente() {
		assertFalse(this.borsa.hasAttrezzo("spada"));
	}
	
	@Test
	void testHasAttrezzoPresente() {
		this.borsa.addAttrezzo(this.spada);
		assertTrue(this.borsa.hasAttrezzo("spada"));
	}
	
	/* TEST DEL METODO getAttrezzo() */

	@Test
	void testGetAttrezzoNonPresente() {
		assertNull(this.borsa.getAttrezzo("scudo"));
	}
	
	void testGetAttrezzoNonEsistente() {
		assertNull(this.borsa.getAttrezzo("arco"));
	}
	
	@Test
	void testGetAttrezzoPresente() {
		this.borsa.addAttrezzo(spada);
		assertEquals(spada, this.borsa.getAttrezzo("spada"));
	}

	
	/* TEST DEL METODO isEmpty() */
	@Test
    void testBorsaVuota() {
        assertTrue(this.borsa.isEmpty());
    }

    @Test
    void testBorsaNonVuota() {
        this.borsa.addAttrezzo(spada);
        assertFalse(this.borsa.isEmpty());
    }
    
    /* TEST DEL METODO addAttrezzo() */
	
	@Test
	void testAddAttrezzoSuccesso() {
		assertTrue(this.borsa.addAttrezzo(this.spada));
	}
	
	@Test
	void testAddAttrezzoBorsaPiena() {
		assertTrue(this.borsa.addAttrezzo(this.spada));
		assertTrue(this.borsa.addAttrezzo(this.scudo));
		assertFalse(this.borsa.addAttrezzo(this.arco)); // peso massimo già raggiunto
	}
	
	/* TEST DEL METODO removeAttrezzo() */
	
	@Test
	void testRemoveAttrezzoBorsaVuota() {
		assertFalse(this.borsa.removeAttrezzo(this.spada));
	}
	
	@Test
	void testRemoveAttrezzoBorsaConAttrezzo() {
		this.borsa.addAttrezzo(this.spada);
		assertTrue(this.borsa.removeAttrezzo(this.spada));
		assertTrue(this.borsa.isEmpty()); // borsa ormai vuota
	}
	
	@Test
	void testRemoveAttrezziMultipli() {
		assertTrue(this.borsa.addAttrezzo(this.spada));
		assertTrue(this.borsa.addAttrezzo(this.scudo));
		assertTrue(this.borsa.removeAttrezzo(this.spada));
		assertTrue(this.borsa.removeAttrezzo(this.scudo));
		assertFalse(this.borsa.removeAttrezzo(this.spada)); // borsa ormai vuota
	}
	
	
	/* TEST DEI METODI CHE UTILIZZANO COLLEZIONI */
	
	/* metodo per evitare di ripetere gli inserimenti in borsa nei test dei metodi che utilizzano collezioni */
	Attrezzo[] addAttrezziSorted() {
		
		this.borsa = new Borsa(50);	// ho bisogno di una borsa grande
		
		Attrezzo[] array = {this.piombo, this.ps, this.piuma, this.libro};
		
		for(Attrezzo a : array) {
			
			this.borsa.addAttrezzo(a);
		}
		
		return array;
	}
	
	@Test
	void testGetContenutoOrdinatoPerPeso() {
		
		Attrezzo[] array = this.addAttrezziSorted();
		
		Attrezzo[] richiesto = {this.piuma, this.libro, this.ps, this.piombo};
		
		List<Attrezzo> lista = this.borsa.getContenutoOrdinatoPerPeso();
		
		int i = 0;
		
		for (Attrezzo a : lista) {

			assertSame(richiesto[i], a);
			i++;
		}
	}
	
	@Test
	void testGetContenutoOrdinatoPerNome() {
		
		Attrezzo[] array = this.addAttrezziSorted();
		
		Attrezzo[] richiesto = {this.libro, this.piombo, this.piuma, this.ps};
		
		SortedSet<Attrezzo> set = this.borsa.getContenutoOrdinatoPerNome();
		
		int i = 0;
		
		for (Attrezzo a : set) {
			
			assertSame(richiesto[i], a);
			i++;
		}
	}
	
	@Test
	void testGetContenutoRaggruppatoPerPeso() {
		
		Attrezzo[] array = this.addAttrezziSorted();
		
		Map<Integer, Set<Attrezzo>> mappa = this.borsa.getContenutoRaggruppatoPerPeso();
		
		assertEquals("{1=[piuma (1kg)], 5=[libro (5kg), ps (5kg)], 10=[piombo (10kg)]}", mappa.toString());
		
		assertTrue(mappa.get(1).contains(this.piuma));
		assertFalse(mappa.get(1).contains(this.libro));
		assertFalse(mappa.get(1).contains(this.ps));
		assertFalse(mappa.get(1).contains(this.piombo));
		
		assertTrue(mappa.get(5).contains(this.libro));
		assertTrue(mappa.get(5).contains(this.ps));
		assertFalse(mappa.get(5).contains(this.piuma));
		assertFalse(mappa.get(5).contains(this.piombo));
		
		assertTrue(mappa.get(10).contains(this.piombo));
		assertFalse(mappa.get(10).contains(this.piuma));
		assertFalse(mappa.get(10).contains(this.libro));
		assertFalse(mappa.get(10).contains(this.ps));
		
	}
	
	@Test
	void testGetSortedSetOrdinatoPerPeso() {
		
		Attrezzo[] array = this.addAttrezziSorted();
		
		Attrezzo[] richiesto = {this.piuma, this.libro, this.ps, this.piombo};
		
		SortedSet<Attrezzo> set = this.borsa.getSortedSetOrdinatoPerPeso();
		
		int i = 0;
		
		for (Attrezzo a : set) {

			assertSame(richiesto[i], a);
			i++;
		}
	}
}
