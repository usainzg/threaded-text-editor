package threads;

import javax.swing.JTextArea;

import negocio.GestorAplicacion;

public class ThreadGuardar extends Thread {
	private JTextArea jTextArea;
	private String name;


	public ThreadGuardar(final JTextArea jText, final String name){
		super();
		this.jTextArea = jText;
		this.name = name;
	}

	
	public void run() {
		GestorAplicacion gestor = new GestorAplicacion();
		if(!jTextArea.getText().equals("")){
			gestor.guardarTexto(getTextFromJText());
			System.out.println("El thread NO automatico ha guardado");
		}else{
			System.err.println("Automatico no guardado por estar vacio el jTextArea...");
		}
		
	}
	
	public String getTextFromJText(){
		return jTextArea.getText();
	}
	
}
