package it.uniroma3.diadia.personaggi;

import java.util.Collection;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	private static final String TRASFERIMENTO_BUONO = "Grazie per avermi salutata! Ti trasferisco nella stanza che contiene più attrezzi";
	private static final String TRASFERIMENTO_CATTIVO = "La prossima volta saluta!!! Ti trasferisco nella stanza che contiene meno attrezzi!";
	private static final String IMPOSSIBILE_TRASFERIMENTO = "Non c'è nessuna stanza dove io ti possa spostare!";
	private static final String RISATA = "HAHAHAHAHAHAAH";

	public Strega(String nome, String presentazione) {

		super(nome, presentazione);
	}

	private Stanza stanzaMinAttrezzi(Partita partita) {

		Stanza stanzaMin = null;
		int minimo = partita.getStanzaCorrente().getAttrezzi().size();

		Collection<Stanza> stanzeAdiacenti = partita.getStanzaCorrente().getMapStanzeAdiacenti().values();

		for(Stanza s : stanzeAdiacenti) {


			if(s.getAttrezzi().size() <= minimo) {

				stanzaMin = s;
				minimo = s.getAttrezzi().size();
			}
		}

		return stanzaMin;
	}

	private Stanza stanzaMaxAttrezzi(Partita partita) {

		Stanza stanzaMin = null;
		int massimo = -1;

		Collection<Stanza> stanzeAdiacenti = partita.getStanzaCorrente().getMapStanzeAdiacenti().values();

		for(Stanza s : stanzeAdiacenti) {


			if(s.getAttrezzi().size() > massimo) {

				stanzaMin = s;
				massimo = s.getAttrezzi().size();
			}
		}

		return stanzaMin;
	}

	@Override
	public String agisci(Partita partita) {

		Stanza s;

		if(super.haSalutato())
			s = this.stanzaMaxAttrezzi(partita);
		else 
			s = this.stanzaMinAttrezzi(partita);

		if(s != null) {
			
			partita.setStanzaCorrente(s);

			if(super.haSalutato())
				return TRASFERIMENTO_BUONO;
			else
				return TRASFERIMENTO_CATTIVO;
		}
		else
			return IMPOSSIBILE_TRASFERIMENTO;
	}


	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {

		return RISATA;
	}

}
