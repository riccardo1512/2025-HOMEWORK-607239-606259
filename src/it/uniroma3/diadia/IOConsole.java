package it.uniroma3.diadia;
import java.util.Scanner;

public class IOConsole implements IO {
	
	Scanner scannerDiLinee;
	
	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	@Override
	public String leggiRiga() {
		this.scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		
		return riga;
	}
	
	public void chiudiScanner() {
		
		this.scannerDiLinee.close();
	}
}
