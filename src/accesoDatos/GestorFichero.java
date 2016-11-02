package accesoDatos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class GestorFichero {

	private final String fichero = "fichero.txt";
	private static GestorFichero instance = null;
	
	private GestorFichero() {
		
	}

	public static synchronized GestorFichero getInstance() {
		if (instance == null) {
			instance = new GestorFichero();
		}
		return instance;
	}

	public String leerFichero(){
		StringBuffer data = new StringBuffer();
		try {
			BufferedReader in = new BufferedReader(new FileReader(fichero));
	        int c = 0;
	        while ((c = in.read()) != -1) {
	            data.append((char)c);
	        }
	        in.close();	
		}catch(IOException e){
			System.out.println("ERROR al leer en el fichero '" + fichero + "'.");
		}
		return new String(data);
		
	}

	public void escribirFichero(String texto) {
		
		try {
			crearFichero(fichero);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuffer data = new StringBuffer(texto);
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(fichero));
			out.write(data.toString());
	        out.flush();
	        out.close();
		} catch (IOException e) {
			System.out.println("ERROR al escribir en el fichero '" + fichero + "'.");
		}
	}
	
	public void crearFichero(String fichero) throws IOException{
		File archivo = new File(fichero);
		
		try{
			BufferedWriter bw;
			if(!archivo.exists()) {
				bw = new BufferedWriter(new FileWriter(archivo));
				bw.close();
				System.out.println("archivo creado");
			}
		}catch(IOException e){
			System.out.print("No se ha podido crear el archivo!");
		}
		
		
	}
}
