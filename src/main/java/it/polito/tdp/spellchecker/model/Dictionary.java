package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	
	List<String> dizionario; //lo definisco qui per poterlo richiamare nel controller
	Long tempo;
	
	public void loadDictionary(String language) {
		dizionario=new LinkedList<String>(); //inizializzandolo qui ogni volta che scelgo la lingua lo ricrea
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

	/*public List<String> spellCheckText(String testo){
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
		tempo=(end-start)/10^9; //divido per averlo in secondi da nanosecondi
		
		return paroleErrate;
	}
	*/
	
	public List<String> spellCheckTextLinear(String testo) {
		List<String> paroleErrate=new LinkedList<String>();
		int i=0;
		testo=testo.toLowerCase();
		testo=testo.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");  
		String [] parole=testo.split(" ");
		
		Long start=System.nanoTime();
		for(String s: parole) {
			while(i<=dizionario.size()) {
				if(dizionario.get(i).compareTo(s)==0)
					break;
				if(i==dizionario.size())
					paroleErrate.add(s);
				i++;
			}
		}
		Long end=System.nanoTime();
		tempo=(end-start)/10^9; 
		
		return paroleErrate;
	}
	
	/*public int spellCheckTextDichotomic(int[] array, int elemento) {  

		     int start = 0, end = array.length - 1, centro = 0;
		     while (start <= end)
		     {
		         centro = (start + end) / 2;
		         if (elemento < array[centro])
		         {
		             end = centro - 1;
		         }
		         else
		         {
		             if (elemento > array[centro])
		                 start = centro + 1;
		             else
		                 return centro; // Caso: elemento==array[centro]
		         }
		     }
		     return -1;
		 }*/
	
	
	public long getTempo() {
		return tempo;
	}
}
