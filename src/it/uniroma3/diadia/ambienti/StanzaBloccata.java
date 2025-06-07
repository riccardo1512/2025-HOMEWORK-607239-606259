package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	
	private Direzione direzioneBloccata;
	private String attrezzoNecessario;

	public StanzaBloccata(String nome, Direzione direzioneBloccata, String nomeAttrezzoNecessario) {
		super(nome);

		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoNecessario = nomeAttrezzoNecessario;
	}
	
	public StanzaBloccata(String nome, String direzioneBloccata, String nomeAttrezzoNecessario) {
		
		super(nome);
		
		Direzione d = null;

		switch(direzioneBloccata) {

			case "nord":
			d = Direzione.NORD;
			break;
			
			case "est":
			d = Direzione.EST;
			break;

			case "sud":
			d = Direzione.SUD;
			break;

			case "ovest":
			d = Direzione.OVEST;
			break;
		}

		this.direzioneBloccata = d;
		this.attrezzoNecessario = nomeAttrezzoNecessario;
	}
	
	
	/**
     * Restituisce la stanza adiacente nella direzione specificata
     * se è presente l'attrezzo sbloccante,
     * altrimenti ritorna un riferimento alla stanza corrente
     * @param direzione
     */
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		
		if(direzione.equals(this.direzioneBloccata)) {
			
			if(super.hasAttrezzo(attrezzoNecessario))
				return super.getStanzaAdiacente(direzione);
			else
				return this;
		}
			
		
		return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		
		Direzione d = Direzione.getDirezione(direzione);
		
		
		if(super.hasAttrezzo(attrezzoNecessario))
			return super.getStanzaAdiacente(d);
		else
			return this;
	}
	
	@Override
	public String getDescrizione() {
        return this.toString();
    }
	
	@Override
	public String toString() {
		
		String messaggio = "Questa stanza è una stanza bloccata, per sbloccare la direzione " + this.direzioneBloccata + " posa un oggetto " + this.attrezzoNecessario;
		
		return messaggio;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if(o == null || !(o instanceof StanzaBloccata))
			return false;
		
		StanzaBloccata that = (StanzaBloccata) o;
		
		return this.getNome().equals(that.getNome()) && this.getAttrezzoNecessario().equals(that.getAttrezzoNecessario()) && this.getDirezioneBloccata().equals(that.getDirezioneBloccata());
	}

	public Direzione getDirezioneBloccata() {
		return direzioneBloccata;
	}

	public String getAttrezzoNecessario() {
		return attrezzoNecessario;
	}


}
