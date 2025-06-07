package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando implements Comando {
	
	public ComandoNonValido() {
		
		super("comando non valido");
	}

	@Override
	public void esegui(Partita partita) {
		
		partita.getConsole().mostraMessaggio("Il comando NON è un comando valido");
	}
}
