package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String, Attrezzo>attrezzi;

	private int pesoMax;
	private int pesoCorrente;
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<String, Attrezzo>();
		this.pesoCorrente = 0;
		
	}
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		
		if(this.getPeso() + attrezzo.getPeso() <= this.getPesoMax()) {
			
			if(!this.hasAttrezzo(attrezzo.getNome())) {
	    		
	    		this.attrezzi.put(attrezzo.getNome(), attrezzo);
	    		this.pesoCorrente += attrezzo.getPeso();
	    		
	        	return true;
	    	}
		}
		
		return false;
	}
	
	public int getPesoMax() {
		return this.pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		
		return this.attrezzi.get(nomeAttrezzo);
	}

	public int getPeso() {
		
		return pesoCorrente;
	}
	
	public boolean isEmpty() {
		return this.attrezzi.size() == 0;
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		
		return this.attrezzi.containsKey(nomeAttrezzo);
	}
	
	/**
	 * Rimuove un attrezzo dalla borsa
	 * @param Attrezzo wanted
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo wanted) {
		
		if(this.attrezzi.remove(wanted.getNome()) != null) {
			this.pesoCorrente -= wanted.getPeso();
			return true;	
		}
		else
			return false;
	}
	/**
	 * metodo toString(), ritorna gli oggetti ordinati per peso e per nome
	 * @param nessuno
	 * @return Stringa contenente la stampa di tutti gli attrezzi della borsa
	*/
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (Attrezzo a : this.getContenutoOrdinatoPerPeso())
				s.append(a.toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	
	
	
	/**
	 * metodo per ottenere la lista degli attrezzi nella borsa ordinati per peso e per nome
	 * @param nessuno
	 * @return la lista degli attrezzi nella borsa ordinati per peso e quindi, a parità di peso, per nome
	*/
	List<Attrezzo> getContenutoOrdinatoPerPeso() {
		
		List<Attrezzo> output = new ArrayList<Attrezzo>(this.attrezzi.values());
		
		class comparatorePesoNome implements Comparator<Attrezzo>{

			@Override
			public int compare(Attrezzo o1, Attrezzo o2) {

				/* se hanno lo stesso peso, compara per nome */
				if(o1.getPeso() == o2.getPeso()) {
					
					return o1.getNome().compareTo(o2.getNome());
				}
				/* altrimenti compara per peso */
				return o1.getPeso() - o2.getPeso();
			}
		}
		
		Comparator<Attrezzo> comparatorePerPesoEPerNome = new comparatorePesoNome();
		
		Collections.sort(output, comparatorePerPesoEPerNome);
		
		return output;
	}
	
	/**
	 * metodo per ottenere l'insieme degli attrezzi nella borsa ordinati per nome
	 * @return  l'insieme degli attrezzi nella borsa ordinati per nome
	 * */
	SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		
		/* comparatore locale anonimo (senza la creazione di una classe) */
		Comparator<Attrezzo> comparatorePerNome = new Comparator<Attrezzo>() {
			
			@Override
			public int compare(Attrezzo o1, Attrezzo o2) {

				return o1.getNome().compareTo(o2.getNome());
			}
		};
		
		SortedSet<Attrezzo> output = new TreeSet<Attrezzo>(comparatorePerNome);
		
		output.addAll(this.attrezzi.values());
		
		return output;
	}
	
	/**
	 * metodo per ottenere una mappa Map<Integer, Set<Attrezzo>> (peso / setAttrezziDiQuelPeso)
	 * @return restituisce una mappa che associa un intero (rappresentante un peso) con l’insieme (comunque non vuoto) degli attrezzi di tale peso: tutti gli attrezzi dell'insieme che figura come valore hanno lo stesso peso pari all'intero che figura come chiave
	 */
	Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		
		Map<Integer, Set<Attrezzo>> output = new HashMap<Integer, Set<Attrezzo>>();
		
		for(Attrezzo a : this.attrezzi.values()) {
			
			/* chiave già presente */
			if(output.containsKey(a.getPeso())) {
				
				Set<Attrezzo> s = output.get(a.getPeso());
				
				/* set nullo*/
				if(s == null) {
					
					s = new HashSet<Attrezzo>();
				}
				
				s.add(a);
			}
			else {
				/* chiave NON presente */
				
				Set<Attrezzo> s = new HashSet<Attrezzo>();
				s.add(a);
				
				output.put(a.getPeso(), s);
			}
		}
		
		return output;
	}
	
	/**
	 * metodo che restituisce l'insieme degli attrezzi nella borsa
	 * @return l'insieme gli attrezzi nella borsa ordinati per peso e quindi, a parità di peso, per nome*/
	SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		
		
		class comparatorePesoNome implements Comparator<Attrezzo>{

			@Override
			public int compare(Attrezzo o1, Attrezzo o2) {

				/* se hanno lo stesso peso, compara per nome */
				if(o1.getPeso() == o2.getPeso()) {
					
					return o1.getNome().compareTo(o2.getNome());
				}
				/* altrimenti compara per peso */
				return o1.getPeso() - o2.getPeso();
			}
		}
		
		Comparator<Attrezzo> comparatorePerPesoEPerNome = new comparatorePesoNome();
		
		SortedSet<Attrezzo> output = new TreeSet<Attrezzo>(comparatorePerPesoEPerNome);
		
		output.addAll(this.attrezzi.values());
		
		return output;
		
		
	}

}
