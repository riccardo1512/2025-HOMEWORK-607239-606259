package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/* utilizza la tecnica del method-chaining */

public class LabirintoBuilder {
	
	private Labirinto labirinto;
	
	Map<String, Stanza> mappaStanze;
	Stanza ultimaStanzaAggiunta;
	
	public LabirintoBuilder() {
		
		this.labirinto = new Labirinto();
		this.mappaStanze = new HashMap<String, Stanza>();
	}
	
	/**
	 * metodo che aggiunge una stanza e la mette all'interno della mappa
	 * @param Stringa contenente il nome della stanza da aggiungere (ATT: il nome deve essere univoco)
	 * @return riferimento all'oggetto stanza
	 * */
	public LabirintoBuilder addStanza(String nome) {
		
		/* NON possono esistere stanze con lo stesso nome */
		if(!this.mappaStanze.containsKey(nome)) {
			
			Stanza stanza = new Stanza(nome);
			this.mappaStanze.put(nome, stanza);
			
			ultimaStanzaAggiunta = stanza;
		}
		
		return this;
	}
	
	public LabirintoBuilder addStanzaIniziale(String nomeStanzaIniziale) {
		
		this.addStanza(nomeStanzaIniziale);
		
		if(this.mappaStanze.containsKey(nomeStanzaIniziale))
			this.labirinto.setStanzaIniziale(this.ultimaStanzaAggiunta);
		
		// tiene traccia
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String nomeStanzaVincente) {
		
		this.addStanza(nomeStanzaVincente);
		
		if(this.mappaStanze.containsKey(nomeStanzaVincente))
			this.labirinto.setStanzaVincente(this.ultimaStanzaAggiunta);
		
		// tiene traccia
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String nome, String nomeDirezioneBloccata, String nomeAttrezzoNecessario) {
		
		if(!this.mappaStanze.containsKey(nome)) {
			
			Stanza stanzaBloccata = new StanzaBloccata(nome, nomeDirezioneBloccata, nomeAttrezzoNecessario);
			
			this.mappaStanze.put(nome, stanzaBloccata);
			
			ultimaStanzaAggiunta = stanzaBloccata;
		}
		
		// tiene traccia
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
		
		if(!this.mappaStanze.containsKey(nome)) {
			
			Stanza stanzaMagica = new StanzaMagica(nome, soglia);
			
			this.mappaStanze.put(nome, stanzaMagica);
			
			ultimaStanzaAggiunta = stanzaMagica;
		}
		
		// tiene traccia
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nome, String nomeAttrezzoNecessario) {
		
		if(!this.mappaStanze.containsKey(nome)) {
			
			Stanza stanzaBuia = new StanzaBuia(nome, nomeAttrezzoNecessario);
			
			this.mappaStanze.put(nome, stanzaBuia);
			
			ultimaStanzaAggiunta = stanzaBuia;
		}
		
		// tiene traccia
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nome, int peso) {
		
		Attrezzo attrezzo = new Attrezzo(nome, peso);
		this.ultimaStanzaAggiunta.addAttrezzo(attrezzo);
		
		// tiene traccia
		return this;
	}
	
	/** aggiunge un'adiacenza tra stanze così: 
	 * stanza1 --(direzione)--> stanza2
	 * @param 3 Stringhe contenenti: nomeStanzaUno, nomeStanzaDue, direzione
	 * */
	public LabirintoBuilder addAdiacenza(String nomeStanzaUno, String nomeStanzaDue, String direzione) {
		/* stanza1 --(direzione)--> stanza2 */
		
		
		
		Stanza stanzaUno = this.mappaStanze.get(nomeStanzaUno);
		Stanza stanzaDue = this.mappaStanze.get(nomeStanzaDue);
		
		if(stanzaUno!= null && stanzaDue != null) {
			
			stanzaUno.impostaStanzaAdiacente(direzione, stanzaDue);
		}
		
		// tiene traccia
		return this;
	}
	
	public Labirinto getLabirinto() {
		
		return this.labirinto;
	}

	public Map<String, Stanza> getListaStanze() {

		return this.mappaStanze;
	}

}
