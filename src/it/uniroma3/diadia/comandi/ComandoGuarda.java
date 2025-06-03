package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {

	private String nome;
	private String parametro;
	
	public ComandoGuarda() {
		
		this(null);
	}
	
	public ComandoGuarda(String parametro) {
		
		this.nome = "guarda";
		this.parametro = parametro;
	}
	
	
	@Override
	public void esegui(Partita partita) {
		
		if(this.parametro != null) {
			
			if(this.parametro.equals("borsa")) {
				
				partita.getConsole().mostraMessaggio("La tua borsa = " + partita.getGiocatore().getBorsa());
			}
		}
		else
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
