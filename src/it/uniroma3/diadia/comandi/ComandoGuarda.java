package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando implements Comando {


	public ComandoGuarda() {
		
		this(null);
	}
	
	public ComandoGuarda(String parametro) {
		
		super("guarda", parametro);
	}
	
	
	@Override
	public void esegui(Partita partita) {
		
		if(super.getParametro() != null) {
			
			if(super.getParametro().equals("borsa")) {
				
				partita.getConsole().mostraMessaggio("La tua borsa = " + partita.getGiocatore().getBorsa());
			}
		}
		else
			partita.getConsole().mostraMessaggio(partita.toString());
	}
}
