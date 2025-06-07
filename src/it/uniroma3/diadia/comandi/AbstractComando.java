package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando {
	
	private String nome;
	private String parametro;
	
	
	public AbstractComando(String nome, String parametro) {
		
		this.nome = nome;
		this.parametro = parametro;
	}
	
	public AbstractComando(String nome) {
		
		this(nome, null);
	}
	
	@Override
	abstract public void esegui(Partita partita);	// da implementare in tutti i comandi

	@Override
	public final void setParametro(String parametro) {
		
		this.parametro = parametro;
	}
	
	@Override
	public final String getNome() {
		
		return this.nome;
	}
	
	@Override
	public final String getParametro() {
		
		return this.parametro;
	}
}
