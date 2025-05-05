package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	
	private String direzioneBloccata;
	private String attrezzoNecessario;

	public StanzaBloccata(String nome, String nomeDirezioneBloccata, String nomeAttrezzoNecessario) {
		super(nome);

		this.direzioneBloccata = nomeDirezioneBloccata;
		this.attrezzoNecessario = nomeAttrezzoNecessario;
	}
	
	
	/**
     * Restituisce la stanza adiacente nella direzione specificata
     * se è presente l'attrezzo sbloccante,
     * altrimenti ritorna un riferimento alla stanza corrente
     * @param direzione
     */
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		
		if(direzione.equals(this.direzioneBloccata)) {
			
			if(super.hasAttrezzo(attrezzoNecessario))
				return super.getStanzaAdiacente(direzione);
			else
				return this;
		}
			
		
		return super.getStanzaAdiacente(direzione);
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

}
