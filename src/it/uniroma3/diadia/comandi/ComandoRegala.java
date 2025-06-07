package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando implements Comando {
	
	private static final String NESSUN_PERSONAGGIO = "Impossibile regalare, non è presente nessun personaggio nella stanza";
	private static final String NESSUN_ATTREZZO = "Impossibile regalare, non possiedi alcun attrezzo di questo tipo";

	public ComandoRegala() {

		super("regala");
	}

	public ComandoRegala(String nomeAttrezzo) {
		super("regala", nomeAttrezzo);
	}

	@Override
	public void esegui(Partita partita) {
		
		if(partita != null)
			if(partita.getStanzaCorrente() != null) 
				if(partita.getStanzaCorrente().getPersonaggio() != null) {

					Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(super.getParametro());

					if(attrezzo != null) {

						if(partita.getStanzaCorrente().getPersonaggio() != null) {

							String msg = partita.getStanzaCorrente().getPersonaggio().riceviRegalo(attrezzo, partita);
							partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo);
							
							partita.getConsole().mostraMessaggio(msg);
						}
						else
							partita.getConsole().mostraMessaggio(NESSUN_PERSONAGGIO);
					}
					else
						partita.getConsole().mostraMessaggio(NESSUN_ATTREZZO);

				}
	}
}
