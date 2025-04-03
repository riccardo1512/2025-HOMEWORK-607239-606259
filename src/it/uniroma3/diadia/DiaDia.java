package it.uniroma3.diadia;


import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

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
	
	static final private String[] elencoComandi = {"vai", "aiuto", "prendi", "posa", "fine"};

	private Partita partita;
	private IOConsole console;

	public DiaDia(IOConsole c) {
		this.partita = new Partita();
		this.console = c;
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
		Comando comandoDaEseguire = new Comando(istruzione);
		
		if(comandoDaEseguire.getNome() == null)
			return false;

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
			
		/* comandi aggiunti */
		else if(comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			this.console.mostraMessaggio("Comando sconosciuto");
		
		
		
		if(this.partita.isFinita()) {
			this.console.mostraMessaggio("La partita è finita!");
			
			if (this.partita.vinta()) {
				this.console.mostraMessaggio("Hai vinto!");
				return true;
			} else
				this.console.mostraMessaggio("Hai perso!!! :(");
		}
		
		return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		
		String messaggio = "";
		
		for(int i=0; i< elencoComandi.length; i++) {
			
			messaggio = messaggio + elencoComandi[i] + " ";
		}
		this.console.mostraMessaggio(messaggio);
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		
		if (direzione == null) {
			
			this.console.mostraMessaggio("Dove vuoi andare ?");
			direzione = this.console.leggiRiga();
		} 
		
		
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			this.console.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu - 1);
		}
		this.console.mostraMessaggio(this.partita.toString());
	}
	
	private void prendi(String nomeAttrezzo) {
		
		if (nomeAttrezzo == null) {
			
			this.console.mostraMessaggio("Che attrezzo dalla stanza vuoi prendere ?");
			nomeAttrezzo = this.console.leggiRiga();
		} 
			
	
		Attrezzo a = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		if(a == null) {
				
			this.console.mostraMessaggio("L'attrezzo che vuoi prendere non è presente nella stanza");
			return;
		}
			
		this.partita.getGiocatore().prendi(a);
		this.partita.getStanzaCorrente().removeAttrezzo(a);
		
		this.console.mostraMessaggio("Hai preso l'attrezzo " + a.toString());
	}
	
	private void posa(String nomeAttrezzo) {
		
		if (nomeAttrezzo == null) {

			this.console.mostraMessaggio("Che attrezzo dalla stanza vuoi posare ?");
			nomeAttrezzo = this.console.leggiRiga();
		} 
	
		
		Attrezzo a = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		if(a == null) {

			this.console.mostraMessaggio("L'attrezzo che vuoi prendere non è presente nella borsa");
			return;
		}

		this.partita.getGiocatore().posa(a);
		this.partita.getStanzaCorrente().addAttrezzo(a);

		this.console.mostraMessaggio("Hai posato l'attrezzo " + a.toString());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole c = new IOConsole();
		DiaDia gioco = new DiaDia(c);
		
		gioco.gioca();
	}
}