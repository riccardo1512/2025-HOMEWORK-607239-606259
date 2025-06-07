package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {

	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	private Attrezzo attrezzo;
	
	private static final String MESSAGGIO_REGALO = "Ho ho ho! Ma che bel regalo! Lo poso nella stanza leggermente modificato";
	private static final String MESSAGGIO_NESSUN_REGALO = "Che razza di regalo è un regalo vuoto?!";
	
	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}
	
	@Override
	public String agisci(Partita partita) {
		
		String msg;
		
		if (this.attrezzo!=null) {
			
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			msg = MESSAGGIO_DONO;
		}
		else {
			msg = MESSAGGIO_SCUSE;
		}
		
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {

		if(attrezzo != null) {
			attrezzo.setPeso(attrezzo.getPeso()/2);
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			return MESSAGGIO_REGALO;
		}
		else
			return MESSAGGIO_NESSUN_REGALO;
	}

	public Attrezzo getAttrezzo() {
		return this.attrezzo;
	}

	public void setAttrezzo(Attrezzo attrezzo) {
		this.attrezzo = attrezzo;
	}
}
