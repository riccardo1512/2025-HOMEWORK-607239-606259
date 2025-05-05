package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	
	private String nome;
	private String parametro;
	
	static final private String[] elencoComandi = {"vai", "aiuto", "prendi", "posa", "guarda", "fine"};
	
	public ComandoAiuto() {
		
		this.nome = "aiuto";
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

	@Override
	public void setParametro(String parametro) {

		this.parametro = parametro;
	}
	
	
	@Override
	public String getParametro() {
		
		return this.parametro;
	}

	@Override
	public String getNome() {

		return this.nome;
	}

}
