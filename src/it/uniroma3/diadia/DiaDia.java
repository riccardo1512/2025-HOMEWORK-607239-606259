package it.uniroma3.diadia;


import java.io.FileNotFoundException;

import it.uniroma3.diadia.ambienti.CaricatoreLabirinto;
import it.uniroma3.diadia.ambienti.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	

	private Partita partita;
	private IO console;

	/*
	public DiaDia(IO c) throws FileNotFoundException, FormatoFileNonValidoException {
		this.partita = new Partita();
		this.console = c;
		this.partita.setConsole(c);
	}
	*/
	
	public DiaDia(Labirinto l, IO c) {
		
		this.partita = new Partita(l);
		this.console = c;
		this.partita.setConsole(c);
	}
	
	

	public void gioca() {
		String istruzione; 
	

		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);
			
		do		
			istruzione = this.console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		
		
		/* partita finita */
		if(this.partita.isFinita()) {
			this.console.mostraMessaggio("La partita è finita!");
			
			/* partita vinta */
			if (this.partita.vinta()) {
				
				this.console.mostraMessaggio("Hai vinto!");
			} else {
				/*partita persa */
				
				
				if(this.partita.getGiocatore().getCfu() == 0)
					this.console.mostraMessaggio("Hai esaurito i CFU");
				
				this.console.mostraMessaggio("Hai perso!!! :(");
				
				
			}
			return true;
		}
		
		return false;
	}
	
	public IOConsole getIOConsole() {
		
		return (IOConsole) this.console;
	}


	public static void main(String[] argc) throws FileNotFoundException, FormatoFileNonValidoException {
		IO io = new IOConsole();
		
		/*
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("LabCampusOne")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("LabCampusOne","Biblioteca",Direzione.OVEST)
				.getLabirinto();
		*/
		
		String nomeFile = "resources/labirinto1.txt";
		
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(nomeFile);
		Labirinto labirinto = caricatore.getLabirinto();
		
		DiaDia gioco = new DiaDia(labirinto, io);
		
		gioco.gioca();
		
		gioco.getIOConsole().chiudiScanner();	// chiusura di System.in
	}
}