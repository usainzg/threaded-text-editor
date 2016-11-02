package negocio;

import accesoDatos.GestorFichero;

public class GestorAplicacion {
	
	public GestorAplicacion(){ }
	
	
	public void guardarTexto(String texto) {
		GestorFichero gestorFich = GestorFichero.getInstance();
		gestorFich.escribirFichero(texto);
	}
	public String leerTexto() {
		GestorFichero gestorFich = GestorFichero.getInstance();
		return gestorFich.leerFichero();
	}
}
