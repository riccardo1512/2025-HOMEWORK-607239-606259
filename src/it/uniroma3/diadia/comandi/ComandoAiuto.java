package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando implements Comando {
	

	static final private String[] elencoComandi = {"vai", "aiuto", "prendi", "posa", "guarda", "saluta", "interagisci", "fine"};
	
	public ComandoAiuto() {
		
		super("aiuto");
	}
	
	/**
	 * Stampa informazioni di aiuto.
	 */
	@Override
	public void esegui(Partita partita) {

		String messaggio = "";

		for(int i=0; i< elencoComandi.length; i++) {

			messaggio = messaggio + elencoComandi[i] + " ";
		}
		
		partita.getConsole().mostraMessaggio(messaggio);
	}
}
