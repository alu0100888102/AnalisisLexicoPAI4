/**
 * PRACTICA 4: ANALIZADOR LEXICO
 * 
 * Creamos una clase capaz de analizar un programa en java y devolver los token
 * 
 * @author alu0100888102
 * @version 1.0
 * Ángel Hamilton Lopez
 * alu0100888102@ull.es
 */

import java.util.*;
import java.io.*;

public class ALexico {
	ArrayList<AlToken> tokensPrograma;
	TablaTokens tabla;
	
	public ALexico(){
		tokensPrograma = new ArrayList<AlToken>();
		tabla = new TablaTokens();
	}
	public void analize(File program){
		int nlinea=0;
		try{
			FileInputStream istream = new FileInputStream(program);
			 
			//Construct BufferedReader from InputStreamReader
			BufferedReader bufferreader = new BufferedReader(new InputStreamReader(istream));
		 
			String line = null;
			//Comment indica si es un comentario y Cadena si es una cadena literal (entre"").
			Boolean comment= false, cadena=false;
			while ((line = bufferreader.readLine()) != null) {
				if(line.isEmpty())
					continue;
				ArrayList<AlToken> newTokens = tokenize(line, nlinea , comment, cadena);
				if(!comment)
					tokensPrograma.addAll(newTokens);
				nlinea++;
			}
			bufferreader.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Error en el fichero: no se encuentra " + e);
			System.exit(1);
		}
		catch(IOException e){
			System.out.println("Error en el fichero: error de entrada/salida " + e);
			System.exit(1);
		}
		catch(IllegalArgumentException e){
			System.out.println("Linea "+nlinea+" Error en el fichero: error de entrada/salida " + e);
			System.exit(1);
		}
	}
	
	public ArrayList<AlToken> tokenize(String line, int nlinea, Boolean comment, Boolean cadena){
		ArrayList<AlToken> output = new ArrayList<AlToken>();
		String nuevaPalabra;
		int comienzoPalabra = 0;
		for(int i =0; i< line.length(); i++){
			char ch = line.charAt(i);
			if(ch == '\b' || ch == '\t'){
				if(nuevaPalabra != null && !comment){
					String temp = tabla.getToken(nuevaPalabra);
					AlToken tempToken;
					if(cadena && !temp.matches("QUOTE") && !temp.matches("SINGLE_QUOTE"))
						tempToken = new AlToken(nlinea, comienzoPalabra, "LITERAL_STRING", nuevaPalabra);
					else
						tempToken = new AlToken(nlinea, comienzoPalabra, temp, nuevaPalabra);
					if(temp.matches("QUOTE") && temp.matches("SINGLE_QUOTE"))
						cadena = !cadena;
					if(temp.matches("END_COMMENT"))
						comment = false;
					if(temp.matches("COMMENT"))
						comment = true;
					output.add(tempToken);
				}
				comienzoPalabra = i+1;
			}
			if(ch == )
		}
		return output;
	}
}
