package it.uniroma3.diadia;

public class IOSimulator implements IO {



	private String[] righeDaLeggere;  // input simulato (da tastiera)
	private String[] messaggiStampati; // output simulato (a video)
	
	private int indiceLettura; // per tenere traccia della lettura
	private int indiceStampa;

	public IOSimulator(String[] istruzioni) {
		
		
		this.righeDaLeggere = istruzioni;
		this.messaggiStampati = new String[istruzioni.length + 50];
		
		this.indiceLettura = 0;
		this.indiceStampa = 0;
	}

	/* Scrive i messaggi scritti a video */
	@Override
	public void mostraMessaggio(String messaggio) {
		

		//System.out.println("messaggio ricevuto");
		
		if(this.indiceStampa < this.messaggiStampati.length) {
			
			//System.out.println("messaggio salvato");
			
			this.messaggiStampati[indiceStampa] = messaggio;
			this.indiceStampa++;
		}
		else {
			
			System.out.println("Impossibile salvare ulteriori messaggi, dimensione massima dell'array raggiunta");
		}
	}

	/* Legge i messaggi scritti da tastiera */
	@Override
	public String leggiRiga() {
		
		if (indiceLettura < righeDaLeggere.length) {
			
			String riga = righeDaLeggere[indiceLettura];
			indiceLettura++;
			
			System.out.print(riga + ", ");
			return riga;
		} else {
			return "fine";	// ritorna il messaggio di fine (che comporta l'esecuzione di un comandoFine)
		}
	}

	public void getMessaggiStampati() {
		
		System.out.println("\n\nStampa di tutti i messaggi ricevuti:\n");

		for(int i = 0; i <  this.indiceStampa; i++) {
			
			System.out.println(this.messaggiStampati[i]);
		}
		
		System.out.println("\n\n\n");
	}
}
