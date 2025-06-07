package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando implements Comando {


	public ComandoSaluta() {

		super("saluta");
	}

	public ComandoSaluta(String parametro) {
		super("saluta", parametro);
	}

	@Override
	public void esegui(Partita partita) {

		if(partita != null)
			if(partita.getStanzaCorrente() != null) 
				if(partita.getStanzaCorrente().getPersonaggio() != null) {
					
					String output = partita.getStanzaCorrente().getPersonaggio().saluta();
					partita.getConsole().mostraMessaggio(output);
				}
					

	}

}
