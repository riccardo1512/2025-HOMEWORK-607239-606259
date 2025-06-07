package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{
	
	private String ciboPreferito;
	private Attrezzo attrezzo;
	
	private static final String GNAM = "Gnam Gnam Gnam! Woof Woof! (è caduto un oggetto!)";
	private static final String MORSO = "AAAAAHH (sei stato morso!)";
	
	public Cane(String nome, String presentazione, Attrezzo attrezzo, String ciboPreferito) {
		
		super(nome, presentazione);
		this.attrezzo = attrezzo;
		this.ciboPreferito = ciboPreferito;
	}
	
	private String mordi(Partita partita) {
		
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);;
		
		return MORSO;
	}

	@Override
	public String agisci(Partita partita) {
		
		return this.mordi(partita);
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {

		if(attrezzo != null) {
			
			/* se il regalo è il cibo preferito */
			if(attrezzo.getNome().equals(this.ciboPreferito)) {
				
				partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
				this.attrezzo = null;
				return GNAM;
			}
			else 
				return this.mordi(partita);
		}
		else
			return this.mordi(partita);
	}

}
