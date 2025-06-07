package it.uniroma3.diadia;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Proprieta {
	
	Properties prop;
	
	public Proprieta(){
		
		this.prop = new Properties();
		
		/*
		this.prop.setProperty("CFU_INIZIALI", "20");
		this.prop.setProperty("DEFAULT_PESO_MAX_BORSA", "10");
		
		this.prop.store(new FileWriter("resources/diadia.properties"), null);
		*/
		
		try {
			prop.load(new FileReader("resources/diadia.properties"));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public String leggi(String chiave) {
		
		return this.prop.getProperty(chiave);
	}
	
	public void scrivi(String chiave, String valore) {
		
		this.prop.setProperty(chiave, valore);
		
		try {
			this.prop.store(new FileWriter("resources/diadia.properties"), null);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}

}
