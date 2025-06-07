package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando implements Comando {
	

	public ComandoPrendi() {
		
		super("prendi");
	}
	
	public ComandoPrendi(String nomeAttrezzo) {
		
		super("prendi", nomeAttrezzo);
	}

	@Override
	public void esegui(Partita partita) {
			
		
		if (super.getParametro() == null) {
			
			partita.getConsole().mostraMessaggio("Che attrezzo dalla stanza vuoi prendere ?");
			String nomeAttrezzo = partita.getConsole().leggiRiga();
			super.setParametro(nomeAttrezzo);
		} 
			
	
		Attrezzo a = partita.getStanzaCorrente().getAttrezzo(super.getParametro());
		if(a == null) {
				
			partita.getConsole().mostraMessaggio("L'attrezzo che vuoi prendere non è presente nella stanza");
			return;
		}
			
		partita.getGiocatore().prendi(a);
		partita.getStanzaCorrente().removeAttrezzo(a);
		
		partita.getConsole().mostraMessaggio("Hai preso l'attrezzo " + a.toString());
	}
}
