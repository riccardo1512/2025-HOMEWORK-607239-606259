package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	
	private String attrezzoNecessario;

	public StanzaBuia(String nome, String nomeAttrezzoNecessario) {
		super(nome);

		this.attrezzoNecessario = nomeAttrezzoNecessario;
	}
	
	@Override
	public String getDescrizione() {
		
		return this.toString();
	}
	
	@Override
	public String toString() {
		
		String messaggio = "Qui c'è buio pesto";
		
		if(this.hasAttrezzo(this.attrezzoNecessario))
			messaggio = super.toString();
		
		
		return messaggio;
	}
	
	

}
