package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {
	
	public Comando costruisciComando(String istruzione) {
		
		if(istruzione == null) {
			return new ComandoNonValido();
		}
			

		Scanner scannerDiParole = new Scanner(istruzione);
		
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
		
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); // prima parola: nome del comando
		if (scannerDiParole.hasNext()) 
			parametro = scannerDiParole.next(); // seconda parola: eventuale parametro
		
		scannerDiParole.close();
		

		if(nomeComando == null)
			return new ComandoNonValido();


		StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
		nomeClasse.append(Character.toUpperCase(nomeComando.charAt(0)));
		nomeClasse.append(nomeComando.substring(1));
		
		try {
			comando = (Comando) Class.forName(nomeClasse.toString()).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			
			return new ComandoNonValido();
		}
		
		if(comando == null)
			comando = new ComandoNonValido();
		

			comando.setParametro(parametro);
			
		
		
		
		return comando;
	}

}
