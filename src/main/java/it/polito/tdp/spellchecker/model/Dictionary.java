package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	
	Set<String> dizionario; //lo definisco qui per poterlo richiamare nel controller
	Long tempo;
	
	public void loadDictionary(String language) {
		dizionario=new HashSet<String>(); //inizializzandolo qui ogni volta che scelgo la lingua lo ricrea
		String l;
		if(language.equals("English")) 
			l="C:\\Users\\Francesca\\Desktop\\POLI\\TECNICHE DI PROGRAMMAZIONE\\LAIBeclipse\\Lab03\\src\\main\\resources\\English.txt";  //mi serve il percorso del file
		else
			l="C:\\Users\\Francesca\\Desktop\\POLI\\TECNICHE DI PROGRAMMAZIONE\\LAIBeclipse\\Lab03\\src\\main\\resources\\Italian.txt";
		
		try {
			FileReader fr = new FileReader(l);
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
			// Aggiungere parola alla struttura dati
				dizionario.add(word);
			}
			br.close();
			 } catch (IOException e){
			System.out.println("Errore nella lettura del file");
			}
	}

	public List<String> spellCheckText(String testo){
		List<String> paroleErrate=new LinkedList<String>();
		testo=testo.toLowerCase();
		testo=testo.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");  //serve per togliere tutta la punteggiatura e sostituirla con vuoto ""
		String [] parole=testo.split(" ");
		
		Long start=System.nanoTime();
		for(String s: parole) {
			if(!dizionario.contains(s))
				paroleErrate.add(s);
		}
		Long end=System.nanoTime();
		tempo=(end-start)*10^9; //moltiplico per averlo in secondi da nanosecondi
		
		return paroleErrate;
	}
	
	public long getTempo() {
		return tempo;
	}
}
