package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando implements Comando {

	private static final String MESSAGGIO_CON_CHI = "Con chi dovrei interagire?...";
	private String messaggio;
	
	public ComandoInteragisci() {
		
		super("interagisci");
	}
	
	public ComandoInteragisci(String parametro) {
		super("interagisci", parametro);
	}
	
	
	@Override
	public void esegui(Partita partita) {
		
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			this.messaggio = personaggio.agisci(partita);
			partita.getConsole().mostraMessaggio(this.messaggio);

		} else partita.getConsole().mostraMessaggio(MESSAGGIO_CON_CHI);
	}
	
	public String getMessaggio() {
		return this.messaggio;
	}
}
