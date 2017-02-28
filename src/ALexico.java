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
			//Comment indica si es un comentario.
			Boolean comment= false;
			while ((line = bufferreader.readLine()) != null) {
				if(line.isEmpty())
					continue;
				ArrayList<AlToken> newTokens = tokenize(line, nlinea , comment);
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
	
	public ArrayList<AlToken> tokenize(String line, int nlinea, Boolean comment){
		ArrayList<AlToken> output = new ArrayList<AlToken>();
		//En  nuevaPalabra se iran almacenando los caracteres
		String nuevaPalabra = null;
		//este indice se actualiza cada vez que empezamos una palabra nueva
		int comienzoPalabra = 0;
		for(int i =0; i< line.length(); i++){
			//cojemos el caracter en la posicion i y comprobamos nuevapalabra, que estará formada por los anteriores caracteres
			char ch = line.charAt(i);
			String temp = tabla.getToken(nuevaPalabra);
			if(temp.matches("COMMENT"))
				comment = true;
			if(temp.matches("END_COMMENT"))
				comment = false;

			if(ch == '\b' || ch == '\t'){
				if(nuevaPalabra != null && !comment){
					AlToken tempToken;
					//si la cadena es un string sin terminar, añadimos el espacio al string y seguimos con el bucle
					if(temp.matches("UNFINISHED_STRING")){
						nuevaPalabra += " ";
						continue;
					}
					//si no, entonces añadimos nuevapalabra a la salida. los espacios no se guardan.
					else
						tempToken = new AlToken(nlinea, comienzoPalabra, temp, nuevaPalabra);
					output.add(tempToken);
				}
				//la nueva palabra comenzará en el siguiente caracter
				comienzoPalabra = i+1;
				nuevaPalabra = null;
				continue;
			}
			
			if(ch == '\'' || ch == '\"'){
				if(nuevaPalabra != null && !comment){
					AlToken tempToken= null;
					//si no es un string a medio hacer, lo anterior es una palabra.
					if(!temp.matches("UNFINISHED_STRING")){
						tempToken = new AlToken(nlinea, comienzoPalabra, temp, nuevaPalabra);
						output.add(tempToken);
						nuevaPalabra = new String();
						nuevaPalabra += ch;
						temp = tabla.getToken(nuevaPalabra);
					}
					//si es un string a medio hacer, añadimos el caracter
					else{
						nuevaPalabra += ch;
						temp = tabla.getToken(nuevaPalabra);
					}
					//si el string está terminado con esto, lo añadimos
					if(temp.matches("STRING")){
						tempToken = new AlToken(nlinea, comienzoPalabra, temp, nuevaPalabra);
						output.add(tempToken);
						nuevaPalabra= null;
					}
				}
				//si no hay palabra, añadimos la " a una nueva palabra
				if(nuevaPalabra == null && !comment){
					nuevaPalabra = new String();
					comienzoPalabra = i;
					nuevaPalabra += ch;
				}
				continue;
			}
			
			if(Character.isLetterOrDigit(ch) || ch == '_'){
				//si son parte de un identificador y ya hay una palabra
				if(!comment && nuevaPalabra != null){
					nuevaPalabra += ch;
				}
				//si son parte de un identificador y no hay palabra los añadimos a una nueva
				if(!comment && nuevaPalabra == null){
					nuevaPalabra = new String();
					comienzoPalabra = i;
					nuevaPalabra += ch;
				}
				continue;
			}
			
			
			if (ch == '.'){
				//un punto puede ser parte de un float o para indicar un metodo.
				if(temp.matches("INT")){
					nuevaPalabra += ch;
				}
				else{
					AlToken tempToken = new AlToken(nlinea, comienzoPalabra, temp, nuevaPalabra);
					output.add(tempToken);
					nuevaPalabra= new String();
					nuevaPalabra += ch;
					temp = tabla.getToken(nuevaPalabra);
					tempToken = new AlToken(nlinea, comienzoPalabra, temp, nuevaPalabra);
					output.add(tempToken);
					nuevaPalabra = null;
					
				}
				continue;
			}
			
			//en otro caso.
			AlToken tempToken = new AlToken(nlinea, comienzoPalabra, temp, nuevaPalabra);
			output.add(tempToken);
			nuevaPalabra= new String();
			nuevaPalabra += ch;
			temp = tabla.getToken(nuevaPalabra);
			tempToken = new AlToken(nlinea, comienzoPalabra, temp, nuevaPalabra);
			output.add(tempToken);
			nuevaPalabra = null;
		}
		return output;
	}
}
