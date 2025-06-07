package it.uniroma3.diadia.giocatore;


import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	
	private int cfu;
	private Borsa borsa;
	
	public Giocatore() {
		
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}
	
	public void prendi(Attrezzo attrezzo) {
		
		this.borsa.addAttrezzo(attrezzo);
	}

	public void posa(Attrezzo attrezzo) {

		this.borsa.removeAttrezzo(attrezzo);
	}
	
	
	
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}

	public Borsa getBorsa() {
		return borsa;
	}

	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}	

}
