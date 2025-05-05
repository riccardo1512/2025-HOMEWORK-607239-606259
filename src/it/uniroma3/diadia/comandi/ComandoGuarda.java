package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {

	private String nome;
	private String parametro;
	
	
	public ComandoGuarda() {
		
		this.nome = "guarda";
	}
	
	
	@Override
	public void esegui(Partita partita) {

		partita.getConsole().mostraMessaggio(partita.toString());
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
