
import java.io.*;

public class Main {
	public static void main(String args[]){
		File input = new File(args[0]);
		File output = new File(args[1]);
		ALexico analizador = new ALexico();
		analizador.analize(input);
		analizador.write(output);
	}
}
