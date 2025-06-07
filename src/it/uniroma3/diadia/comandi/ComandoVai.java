package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando implements Comando{

	public ComandoVai() {

		super("vai");
	}

	public ComandoVai(String direzione) {

		super("vai", direzione);
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

		if (super.getParametro() == null) {

			partita.getConsole().mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
			return;
		} 

		Direzione d = Direzione.getDirezione(super.getParametro());
		

		prossimaStanza = stanzaCorrente.getStanzaAdiacente(d);
		if (prossimaStanza == null)
			partita.getConsole().mostraMessaggio("Direzione inesistente");
		else {
			partita.setStanzaCorrente(prossimaStanza);
			int cfu = partita.getGiocatore().getCfu();
			partita.getGiocatore().setCfu(cfu - 1);
		}
		partita.getConsole().mostraMessaggio(partita.toString());
	}
}
