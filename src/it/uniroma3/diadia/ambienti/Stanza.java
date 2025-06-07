package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.Proprieta;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	private int NUMERO_MASSIMO_ATTREZZI;

	private String nome;

	private Map<String, Attrezzo> attrezzi;
	private Map<Direzione, Stanza> stanzeAdiacenti;

	private AbstractPersonaggio personaggio;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		
		Proprieta proprieta = new Proprieta();
		
		this.NUMERO_MASSIMO_ATTREZZI = Integer.parseInt(proprieta.leggi("NUMERO_MASSIMO_ATTREZZI"));

		this.attrezzi = new HashMap<String, Attrezzo>(NUMERO_MASSIMO_ATTREZZI);
		this.stanzeAdiacenti = new HashMap<Direzione, Stanza>();
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {

		if(direzione == null)
			return;
		else
			this.stanzeAdiacenti.put(direzione, stanza);
	}

	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		
		Direzione d = Direzione.getDirezione(direzione);
		
		if(d == null)
			return;
		else
			this.impostaStanzaAdiacente(d, stanza);
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(Direzione direzione) {
		Stanza stanza = null;

		stanza = this.stanzeAdiacenti.get(direzione);

		return stanza;
	}
	
	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		Stanza stanza = null;
		
		Direzione d = Direzione.getDirezione(direzione);
		
		if(d == null)
			return null;

		stanza = this.stanzeAdiacenti.get(d);

		return stanza;
	}
	
	

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public List<Attrezzo> getAttrezzi() {

		List<Attrezzo> output = new ArrayList<Attrezzo>(this.attrezzi.values());

		return output;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {

		if (this.attrezzi.size() < NUMERO_MASSIMO_ATTREZZI) {

			if(!this.hasAttrezzo(attrezzo.getNome())) {

				this.attrezzi.put(attrezzo.getNome(), attrezzo);
				return true;
			}
		}

		return false;  
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();

		risultato.append(this.nome);

		risultato.append("\nUscite: ");

		for (Direzione direzione : this.stanzeAdiacenti.keySet())
			if (direzione!=null)
				risultato.append(" " + direzione);

		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi.values()) {
			if(attrezzo != null)
				risultato.append(attrezzo.toString()+" ");
		}
		
		if(this.getPersonaggio() != null)
			risultato.append("\nPersonaggio nella stanza: " + this.getPersonaggio().toString());
		
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {

		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {

		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo wanted) {

		if(this.attrezzi.remove(wanted.getNome()) != null)
			return true;
		else
			return false;
	}


	public Set<Direzione> getDirezioni() {

		if(!this.stanzeAdiacenti.isEmpty())
			return stanzeAdiacenti.keySet();
		else
			return null;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if(o == null || !(o instanceof Stanza))
			return false;
		
		Stanza that = (Stanza) o;
		
		return this.getNome().equals(that.getNome());
	}
	
	@Override
	public int hashCode() {
		
		return this.getNome().hashCode();
	}

	public Map<Direzione, Stanza> getMapStanzeAdiacenti() {

		return this.stanzeAdiacenti;
	}

	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

	public Map<Direzione, Stanza> getStanzeAdiacenti() {
		return stanzeAdiacenti;
	}

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}

	




}