package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IOSimulator implements IO {

	private List<String> righeDaLeggere;  // input simulato (da tastiera)
	private List<String> messaggiStampati; // output simulato (a video)
	
	private int indiceLettura; // per tenere traccia della lettura
	private int indiceStampa;

	public IOSimulator(List<String> istruzioni) {
		
		
		this.righeDaLeggere = istruzioni;
		
		this.messaggiStampati = new ArrayList<String>(istruzioni.size() + 50);
		
		this.indiceLettura = 0;
		this.indiceStampa = 0;
	}

	/* Scrive i messaggi scritti a video */
	@Override
	public void mostraMessaggio(String messaggio) {
		

		//System.out.println("messaggio ricevuto");
		
		if(this.messaggiStampati != null) {
			
			//System.out.println("messaggio salvato");
			
			this.messaggiStampati.add(indiceStampa, messaggio);
			this.indiceStampa++;
		}
		else {
			
			System.out.println("Impossibile salvare ulteriori messaggi, dimensione massima dell'array raggiunta");
		}
	}

	/* Legge i messaggi scritti da tastiera */
	@Override
	public String leggiRiga() {
		
		if (indiceLettura < this.righeDaLeggere.size()) {
			
			String riga = this.righeDaLeggere.get(indiceLettura);
			indiceLettura++;
			
			System.out.print(riga + ", ");
			return riga;
		} else {
			return "fine";	// ritorna il messaggio di fine (che comporta l'esecuzione di un comandoFine)
		}
	}

	public void getMessaggiStampati() {
		
		System.out.println("\n\nStampa di tutti i messaggi ricevuti:\n");

		/* questa volta uso l'iteratore */
		Iterator<String> iteratore = this.messaggiStampati.iterator();
		
		while(iteratore.hasNext()) {
			
			String s = iteratore.next();
			
			System.out.println(s);
		}
		
		System.out.println("\n\n\n");
	}
}
