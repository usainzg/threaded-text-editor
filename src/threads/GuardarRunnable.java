package threads;

import javax.swing.JTextArea;

import negocio.GestorAplicacion;

public class GuardarRunnable implements Runnable {

	private JTextArea jTextArea;
	private String name;
	
	
	
	public GuardarRunnable(final JTextArea jTextArea, final String name){
		super();
		this.jTextArea = jTextArea;
		this.name = name;
	}
	
	
	@Override
	public void run() {
		
		GestorAplicacion gestorAplicacion = new GestorAplicacion();
		
		while(true){
			
			try{
				Thread.sleep(120000);
				if(!getTextFromJText().equals("")){
					gestorAplicacion.guardarTexto(getTextFromJText());	
					System.out.println("El thread automatico ha guardado el texto");
				}else{
					System.err.println("Automatico no guardado por estar vacio el jTextArea...");
				}
				
			}catch(InterruptedException e){
				System.out.println("El thread " + name + "se ha parado");
			}
			
		}
	}
	
	public String getTextFromJText(){
		return jTextArea.getText();
	}
	
}
