package threads;

import javax.swing.JTextArea;

import negocio.GestorAplicacion;

public class RecuperarRunnable implements Runnable{
	
	private String texto;
	private String name;
	private JTextArea jText;
	
	public RecuperarRunnable(final JTextArea jText, final String name) {
		super();
		this.jText = jText;
		this.name = name;
	}
	
	
	@Override
	public void run() {
		GestorAplicacion gestorAplicacion = new GestorAplicacion();
		texto = gestorAplicacion.leerTexto();
		jText.setText(texto);
	}
	
}
