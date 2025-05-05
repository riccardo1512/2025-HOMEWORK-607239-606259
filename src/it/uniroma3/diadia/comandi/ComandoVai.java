package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando{
	
	private String nome;
	private String direzione;
	
	public ComandoVai() {
	
		this(null);
	}
	
	public ComandoVai(String parametro) {
		
		this.direzione = parametro;
		this.nome = "vai";
	}
	
	/**
	 * esecuzione del comando
	 */
	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	@Override
	public void esegui(Partita partita) {
		
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;

		if (this.direzione == null) {
			
			partita.getConsole().mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
			return;
		} 
		
		
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if (prossimaStanza == null)
			partita.getConsole().mostraMessaggio("Direzione inesistente");
		else {
			partita.setStanzaCorrente(prossimaStanza);
			int cfu = partita.getGiocatore().getCfu();
			partita.getGiocatore().setCfu(cfu - 1);
		}
		partita.getConsole().mostraMessaggio(partita.toString());
	}
	
	@Override
	public void setParametro(String parametro) {
		
		this.direzione = parametro;
	}

	@Override
	public String getNome() {

		return this.nome;
	}

	@Override
	public String getParametro() {
		
		return this.direzione;
	}

}
