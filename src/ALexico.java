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
import java.util.concurrent.atomic.AtomicBoolean;
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
			AtomicBoolean comment = new AtomicBoolean(false);
			while ((line = bufferreader.readLine()) != null) {
				if(line.isEmpty())
					continue;
				ArrayList<AlToken> newTokens = tokenize(line, nlinea , comment);
				if(!comment.get())
					tokensPrograma.addAll(newTokens);
				nlinea++;
			}
			tokensPrograma.add(new AlToken(nlinea,0,"EOF",""));
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
	
	public ArrayList<AlToken> tokenize(String line, int nlinea, AtomicBoolean comment){
		boolean lineComment = comment.get();
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
				comment.getAndSet(true);
			if(temp.matches("END_COMMENT"))
				comment.getAndSet(false);

			if(Character.isWhitespace(ch)){
				if(nuevaPalabra != null && !lineComment){
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
				if(nuevaPalabra != null && !lineComment){
					AlToken tempToken= null;
					//si no es un string a medio hacer, lo anterior es una palabra.
					if(!temp.matches("UNFINISHED_STRING")){
						tempToken = new AlToken(nlinea, comienzoPalabra, temp, nuevaPalabra);
						output.add(tempToken);
						nuevaPalabra = new String();
						nuevaPalabra += ch;
						temp = tabla.getToken(nuevaPalabra);
						comienzoPalabra = i;
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
						comienzoPalabra = i+1;
						continue;
					}
				}
				//si no hay palabra, añadimos la " a una nueva palabra
				if(nuevaPalabra == null && !lineComment){
					nuevaPalabra = new String();
					comienzoPalabra = i;
					nuevaPalabra += ch;
				}
				continue;
			}
			
			if(Character.isLetterOrDigit(ch) || ch == '_'){
				//si son parte de un identificador y ya hay una palabra
				if(!lineComment && nuevaPalabra != null){
					nuevaPalabra += ch;
				}
				//si son parte de un identificador y no hay palabra los añadimos a una nueva
				if(!lineComment && nuevaPalabra == null){
					nuevaPalabra = new String();
					comienzoPalabra = i;
					nuevaPalabra += ch;
				}
				continue;
			}
			
			
			if (ch == '.' && !lineComment){
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
			
			//Si es una / podia ser el inicio o final de un comentario
			if(ch == '/'){
				if(temp.matches("UNFINISHED_STRING")){
					nuevaPalabra += ch;
					continue;
				}
				//si no forma parte de una cadena comprobalos el token que se forma con el / y el siguiente (podria ser // o /* o /**
				String tempstring = new String();
				tempstring = tempstring + ch + line.charAt(i+1);
				String checkComment = tabla.getToken(tempstring);
				if(checkComment.matches("COMMENT")){
					comment.getAndSet(true);
					lineComment = true;
					nuevaPalabra = null;
				}
				if(checkComment.matches("LINE_COMMENT")){
					lineComment = true;
					nuevaPalabra = null;
				}
				//si no lo es comprobamos si es el final de un comentario */
				if(i >0){
					tempstring = new String();
					tempstring = tempstring + line.charAt(i-1)+ ch;
					checkComment = tabla.getToken(tempstring);
					if(checkComment.matches("END_COMMENT")){
						comment.getAndSet(false);
						lineComment = false;
						nuevaPalabra = null;
						continue;
					}
				}
			}
			//en otro caso.
			if(temp.matches("UNFINISHED_STRING")){
				nuevaPalabra += ch;
				continue;
			}
			if(!lineComment){
				if(nuevaPalabra != null){
					AlToken tempToken = new AlToken(nlinea, comienzoPalabra, temp, nuevaPalabra);
					output.add(tempToken);
					comienzoPalabra = i+1;
				}
				nuevaPalabra= new String();
				nuevaPalabra += ch;
				temp = tabla.getToken(nuevaPalabra);
				AlToken tempToken = new AlToken(nlinea, comienzoPalabra, temp, nuevaPalabra);
				output.add(tempToken);
				nuevaPalabra = null;
				comienzoPalabra = i+1;
			}
			if(lineComment){
				nuevaPalabra = null;
				comienzoPalabra = i+1;
			}
		}
		return output;
	}
	
	public void write(File output){
		try{
			FileWriter writer = new FileWriter(output);
			for(int i =0; i < tokensPrograma.size(); i++){
				writer.write(tokensPrograma.get(i)+"\n");
			}
			writer.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Error en el fichero: no se encuentra " + e);
			System.exit(1);
		}
		catch(IOException e){
			System.out.println("Error en el fichero: error de entrada/salida " + e);
			System.exit(1);
		}
	}
}
