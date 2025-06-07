package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando implements Comando {
		
	public ComandoPosa() {
		
		super("posa");
	}
	
	public ComandoPosa(String nomeAttrezzo) {
		
		super("posa", nomeAttrezzo);
	}

	@Override
	public void esegui(Partita partita) {


		if (super.getParametro() == null) {

			partita.getConsole().mostraMessaggio("Che attrezzo dalla stanza vuoi posare ?");
			String nomeAttrezzo = partita.getConsole().leggiRiga();
			super.setParametro(nomeAttrezzo);
		} 
	
		
		Attrezzo a = partita.getGiocatore().getBorsa().getAttrezzo(super.getParametro());
		if(a == null) {

			partita.getConsole().mostraMessaggio("L'attrezzo che vuoi prendere non è presente nella borsa");
			return;
		}

		partita.getGiocatore().posa(a);
		partita.getStanzaCorrente().addAttrezzo(a);

		partita.getConsole().mostraMessaggio("Hai posato l'attrezzo " + a.toString());
		
	}
}
