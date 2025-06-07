package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Labirinto {

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	private Labirinto() {

	}

	
	
	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}


	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}


	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}


	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}
	
	public static LabirintoBuilder newBuilder() throws FileNotFoundException, FormatoFileNonValidoException {
	    return new LabirintoBuilder();
	}
	
	
	
	/* classe statica nidificata LabirintoBuilder*/
	public static class LabirintoBuilder {

		private Labirinto labirinto;

		Map<String, Stanza> mappaStanze;
		private Map<String, AbstractPersonaggio> nome2personaggio;
		Stanza ultimaStanzaAggiunta;

		public LabirintoBuilder() throws FileNotFoundException, FormatoFileNonValidoException {

			this.labirinto = new Labirinto();
			this.mappaStanze = new HashMap<String, Stanza>();
			this.nome2personaggio = new HashMap<String, AbstractPersonaggio>();
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
		
		public LabirintoBuilder addStanza(Stanza stanza) {

			/* NON possono esistere stanze con lo stesso nome */
			if(!this.mappaStanze.containsKey(stanza.getNome())) {

				
				this.mappaStanze.put(stanza.getNome(), stanza);

				ultimaStanzaAggiunta = stanza;
			}

			return this;
		}

		public LabirintoBuilder addStanzaIniziale(String nomeStanzaIniziale) {
			
			if(nomeStanzaIniziale == null) {
				this.labirinto.setStanzaIniziale(null);
				return this;
			}

			this.addStanza(nomeStanzaIniziale);

			if(this.mappaStanze.containsKey(nomeStanzaIniziale))
				this.labirinto.setStanzaIniziale(this.getListaStanze().get(nomeStanzaIniziale));
		
			// tiene traccia
			return this;
		}
		
		public LabirintoBuilder addStanzaIniziale(Stanza stanzaIniziale) {
			
			if(stanzaIniziale == null) {
				this.labirinto.setStanzaIniziale(null);
				return this;
			}

			this.addStanza(stanzaIniziale);

			if(this.mappaStanze.containsKey(stanzaIniziale.getNome()))
				this.labirinto.setStanzaIniziale(this.getListaStanze().get(stanzaIniziale.getNome()));
			
			// tiene traccia
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String nomeStanzaVincente) {
			
			if(nomeStanzaVincente == null) {
				this.labirinto.setStanzaVincente(null);
				return this;
			}

			this.addStanza(nomeStanzaVincente);

			if(this.mappaStanze.containsKey(nomeStanzaVincente))
				this.labirinto.setStanzaVincente(this.getListaStanze().get(nomeStanzaVincente));
			
			// tiene traccia
			return this;
		}
		
		public LabirintoBuilder addStanzaVincente(Stanza stanzaVincente) {
			
			if(stanzaVincente == null) {
				this.labirinto.setStanzaVincente(null);
				return this;
			}

			this.addStanza(stanzaVincente);

			if(this.mappaStanze.containsKey(stanzaVincente.getNome()))
				this.labirinto.setStanzaVincente(this.getListaStanze().get(stanzaVincente.getNome()));
		
			// tiene traccia
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nome, Direzione direzioneBloccata, String nomeAttrezzoNecessario) {

			if(!this.mappaStanze.containsKey(nome)) {

				Stanza stanzaBloccata = new StanzaBloccata(nome, direzioneBloccata, nomeAttrezzoNecessario);

				this.mappaStanze.put(nome, stanzaBloccata);

				ultimaStanzaAggiunta = stanzaBloccata;
			}

			// tiene traccia
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nome, String direzioneBloccata, String nomeAttrezzoNecessario) {

			if(!this.mappaStanze.containsKey(nome)) {

				Stanza stanzaBloccata = new StanzaBloccata(nome, direzioneBloccata, nomeAttrezzoNecessario);

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
		
		public LabirintoBuilder addStanzaMagica(String nome) {

			if(!this.mappaStanze.containsKey(nome)) {

				Stanza stanzaMagica = new StanzaMagica(nome);

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
		
		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso, String nomeStanza) {

			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			if(this.mappaStanze.get(nomeStanza) != null)
				this.mappaStanze.get(nomeStanza).addAttrezzo(attrezzo);

			// tiene traccia
			return this;
		}

		/** aggiunge un'adiacenza tra stanze così: 
		 * stanza1 --(direzione)--> stanza2
		 * @param 3 Stringhe contenenti: nomeStanzaUno, nomeStanzaDue. Direzione: direzione
		 * */
		public LabirintoBuilder addAdiacenza(String nomeStanzaUno, String nomeStanzaDue, Direzione direzione) {
			/* stanza1 --(direzione)--> stanza2 */



			Stanza stanzaUno = this.mappaStanze.get(nomeStanzaUno);
			Stanza stanzaDue = this.mappaStanze.get(nomeStanzaDue);

			if(stanzaUno!= null && stanzaDue != null) {

				stanzaUno.impostaStanzaAdiacente(direzione, stanzaDue);
			}

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
		
		public LabirintoBuilder addCane(String nome, String presentazione, String nomeAttrezzo, String ciboPreferito) {

			Attrezzo regalo = new Attrezzo(nomeAttrezzo, 1); // Peso simbolico
			Cane cane = new Cane(nome, presentazione, regalo, ciboPreferito);
			this.nome2personaggio.put(nome, cane);
			return this;
		}
		
		public LabirintoBuilder addMago(String nome, String presentazione, String nomeAttrezzo) {

			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, 1);
			Mago mago = new Mago(nome, presentazione, attrezzo);
			this.nome2personaggio.put(nome, mago);
			return this;
		}
		
		public LabirintoBuilder addStrega(String nome, String presentazione) {

			Strega strega = new Strega(nome, presentazione);
			this.nome2personaggio.put(nome, strega);
			return this;
		}
		
		public LabirintoBuilder addPersonaggioInStanza(String nomePersonaggio, String nomeStanza) {
			AbstractPersonaggio personaggio = this.nome2personaggio.get(nomePersonaggio);
			if (personaggio == null)
				throw new IllegalArgumentException("Personaggio '" + nomePersonaggio + "' non definito");

			Stanza stanza = this.mappaStanze.get(nomeStanza);
			if (stanza == null)
				throw new IllegalArgumentException("Stanza '" + nomeStanza + "' non definita");

			stanza.setPersonaggio(personaggio);
			return this;
		}

		public Labirinto getLabirinto() {

			return this.labirinto;
		}
		
		public Stanza getStanzaVincente() {
			
			return this.getLabirinto().getStanzaVincente();
		}

		public Stanza getStanzaIniziale() {
			return this.getLabirinto().getStanzaIniziale();
		}

		public Map<String, Stanza> getListaStanze() {

			return this.mappaStanze;
		}
		
		public Map<String, AbstractPersonaggio> getListaPersonaggi() {

			return this.nome2personaggio;
		}

	}
}
