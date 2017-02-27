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

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.HeaderTokenizer.Token;

import java.io.*;

public class ALexico {
	ArrayList<Token> tokensPrograma;
	TablaTokens tabla;
	
	public ALexico(){
		tokensPrograma = new ArrayList<Token>();
		tabla = new TablaTokens();
	}
	public void analize(File program){
		int nlinea=0;
		try{
			FileInputStream istream = new FileInputStream(program);
			 
			//Construct BufferedReader from InputStreamReader
			BufferedReader bufferreader = new BufferedReader(new InputStreamReader(istream));
		 
			String line = null;
			boolean comment= false, cadena=false;
			while ((line = bufferreader.readLine()) != null) {
				if(line.isEmpty())
					continue;
				ArrayList<Token> newTokens = tokenize(line, comment, cadena);
				for(Token token: newTokens){
					tokensPrograma.add(token);
				}
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
	
	public ArrayList<Token> tokenize(String line, boolean comment, boolean cadena){
		ArrayList<Token> output = new ArrayList<Token>();
		return output;
	}
}
