package it.uniroma3.diadia.ambienti;

import java.io.*;
import java.util.*;


import it.uniroma3.diadia.personaggi.AbstractPersonaggio;


public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	private static final String STANZE_BUIE_MARKER = "StanzeBuie:";
	private static final String STANZE_BLOCCATE_MARKER = "StanzeBloccate:";
	private static final String STANZE_MAGICHE_MARKER = "StanzeMagiche:";
	private static final String CANI_MARKER = "Cani:";
	private static final String MAGHI_MARKER = "Maghi:";
	private static final String STREGHE_MARKER = "Streghe:";
	private static final String PERSONAGGI_NELLE_STANZE_MARKER = "PersonaggiNelleStanze:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */

	private Labirinto.LabirintoBuilder builder;
	private LineNumberReader reader;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		
		this.reader = new LineNumberReader(new FileReader(nomeFile));

		this.builder = new Labirinto.LabirintoBuilder();
		
		this.leggiLabirinto(nomeFile);
	}

	private void leggiLabirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {

		this.carica();
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECollocaAttrezzi();

			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeBloccate();
			this.leggiECreaStanzeMagiche();

			this.leggiInizialeEvincente();
			this.leggiEImpostaUscite();


			this.leggiECreaCani();
			this.leggiECreaMaghi();
			this.leggiECreaStreghe();
			this.leggiECollocaPersonaggi();


		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length()).trim();
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);

		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {

			this.builder.addStanza(nomeStanza);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		while (scanner.hasNext()) {
			result.add(scanner.next().trim());  // <-- .trim() è ESSENZIALE
		}
		scanner.close();
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER).trim();
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER).trim();
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.builder.addStanzaIniziale(nomeStanzaIniziale.trim());
		this.builder.addStanzaVincente(nomeStanzaVincente.trim());
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			try {
				int peso = Integer.parseInt(pesoAttrezzo);
				this.builder.addAttrezzo(nomeAttrezzo, peso, nomeStanza);
			} catch (NumberFormatException e) {
				check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
			}
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.builder.getListaStanze().containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {

		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);

		for (String uscita : separaStringheAlleVirgole(specificheUscite)) {
			try (Scanner scannerDiLinea = new Scanner(uscita)) {
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("la stanza di partenza"));
				String stanzaPartenza = scannerDiLinea.next();

				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("la direzione dell'uscita della stanza " + stanzaPartenza));
				String dir = scannerDiLinea.next();

				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("la stanza di destinazione dell'uscita della stanza " + stanzaPartenza));
				String stanzaDestinazione = scannerDiLinea.next();

				this.impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
			}
		}
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);

		this.builder.addAdiacenza(stanzaDa, nomeA, dir);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
	
	public Labirinto getLabirinto() {
		
		return this.builder.getLabirinto();
	}
	
	public Labirinto.LabirintoBuilder getBuilder() {
		
		return this.builder;
	}


	/* funzioni aggiunte */

	// Funzioni per stanze speciali:

	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
		String riga = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		for (String specifica : separaStringheAlleVirgole(riga)) {
			Scanner scanner = new Scanner(specifica);
			String nomeStanza = scanner.next();
			String attrezzoLuce = scanner.next();
			this.builder.addStanzaBuia(nomeStanza, attrezzoLuce);
			scanner.close();
		}
	}

	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException {
		String riga = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		for (String specifica : separaStringheAlleVirgole(riga)) {
			Scanner scanner = new Scanner(specifica);
			String nomeStanza = scanner.next();
			String direzioneBloccata = scanner.next();
			String attrezzoSbloccante = scanner.next();
			this.builder.addStanzaBloccata(nomeStanza, direzioneBloccata, attrezzoSbloccante);
			scanner.close();
		}
	}

	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException {
		String riga = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		for (String specifica : separaStringheAlleVirgole(riga)) {
			Scanner scanner = new Scanner(specifica);
			String nomeStanza = scanner.next();
			int sogliaMagica;
			if(scanner.hasNextInt()) {
				sogliaMagica = scanner.nextInt();
				this.builder.addStanzaMagica(nomeStanza, sogliaMagica);
			}
			else
				this.builder.addStanzaMagica(nomeStanza);

			scanner.close();
		}
	}

	// Funzioni per personaggi

	private void leggiECreaCani() throws FormatoFileNonValidoException {
		String riga = this.leggiRigaCheCominciaPer(CANI_MARKER);
		for (String specifica : separaStringheAlleVirgole(riga)) {
			try (Scanner scanner = new Scanner(specifica)) {
				check(scanner.hasNext(), msgTerminazionePrecoce("nome del cane"));
				String nome = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("presentazione del cane " + nome));
				String presentazione = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("nome attrezzo regalo del cane " + nome));
				String nomeAttrezzo = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("cibo preferito del cane " + nome));
				String ciboPreferito = scanner.next();

				this.builder.addCane(nome, presentazione, nomeAttrezzo, ciboPreferito);
				scanner.close();
			}
		}
	}

	private void leggiECreaMaghi() throws FormatoFileNonValidoException {
		String riga = this.leggiRigaCheCominciaPer(MAGHI_MARKER);
		for (String specifica : separaStringheAlleVirgole(riga)) {
			Scanner sc = new Scanner(specifica);
			String nome = sc.next();
			String presentazione = sc.next();
			String attrezzo = sc.hasNext() ? sc.next() : "";
			
			this.builder.addMago(nome, presentazione, attrezzo);
			sc.close();
		}
	}

	private void leggiECreaStreghe() throws FormatoFileNonValidoException {
		String riga = this.leggiRigaCheCominciaPer(STREGHE_MARKER);
		for (String specifica : separaStringheAlleVirgole(riga)) {
			Scanner sc = new Scanner(specifica);
			String nome = sc.next();
			String presentazione = sc.next();
		
			this.builder.addStrega(nome, presentazione);
			sc.close();
		}
	}

	private void leggiECollocaPersonaggi() throws FormatoFileNonValidoException {
		String riga = this.leggiRigaCheCominciaPer(PERSONAGGI_NELLE_STANZE_MARKER);
		for (String specifica : separaStringheAlleVirgole(riga)) {
			Scanner sc = new Scanner(specifica);
			String nomePersonaggio = sc.next();
			String nomeStanza = sc.next();
			AbstractPersonaggio personaggio = this.builder.getListaPersonaggi().get(nomePersonaggio);
			check(personaggio != null, "Personaggio " + nomePersonaggio + " non definito");
			check(isStanzaValida(nomeStanza), "Stanza " + nomeStanza + " non definita");
			
			this.builder.addPersonaggioInStanza(nomePersonaggio, nomeStanza);
			sc.close();
		}
	}
}