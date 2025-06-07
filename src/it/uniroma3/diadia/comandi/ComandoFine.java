package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando implements Comando {
	

	public ComandoFine() {
		
		super("fine");
	}

	@Override
	public void esegui(Partita partita) {
		
		partita.getConsole().mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
		partita.setFinita();
	}
}
