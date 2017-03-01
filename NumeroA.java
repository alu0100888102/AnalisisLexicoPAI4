//hola soy un comentario
/**comentario muy
sexy*/ import java.util.Scanner;
import static java.lang.System.*;

public class NumeroA {
	public static void main(String args[]){
		Scanner reader = new Scanner(in);
		out.println("Introduzca una cadena de caracteres:\n");
		String cadena = reader.nextLine();
		reader.close();
		
		int numberOfA = 0;
		for (int i =0; i<cadena.length(); i++){
			if((cadena.charAt(i) == 'A') || (cadena.charAt(i) == 'a')){
				numberOfA++;
			}
		}
		out.println("Numero de A o a: " + numberOfA + "\n");
	}
}