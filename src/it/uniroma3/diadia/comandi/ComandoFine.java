package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
	
	private String nome;
	private String parametro;
	
	public ComandoFine() {
		
		this.nome = "fine";
	}

	@Override
	public void esegui(Partita partita) {
		
		partita.getConsole().mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
		partita.setFinita();
	}

	@Override
	public void setParametro(String parametro) {
		
		this.parametro = parametro;
	}

	@Override
	public String getNome() {

			return this.nome;
	}

	@Override
	public String getParametro() {
		
		return this.parametro;
	}

}
