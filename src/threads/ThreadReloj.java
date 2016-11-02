package threads;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ThreadReloj extends Thread {
	
	private final JLabel etiqueta;
	private final JTextArea textArea;

	private boolean restart = false;
	private boolean parado = false;
	private int TIEMPO_RELOJ;
	
	private int min;
	private int seg;
	
	public ThreadReloj(String nombre, JLabel etiqueta , JTextArea textArea){
		super(nombre);
		this.etiqueta = etiqueta;
		this.textArea = textArea;
		this.TIEMPO_RELOJ = 600;
		setPriority(NORM_PRIORITY);
	}
	
	public void run(){
		
		super.run();
		reiniciarThread();
		
		while (TIEMPO_RELOJ >= 0) {
			
			if(TIEMPO_RELOJ == 0){
				parado = true;
				textArea.setText("El tiempo ha terminado");
				textArea.setEnabled(false);
				this.interrupt();
				break;
			}
			
			if(seg == 0){
				min = decrementar(min);
				seg = 59;
			}else{
				seg = decrementar(seg);
			}
			
			if( isRestarted() ){
				reiniciarThread();
				restart = false;
			}
			mostrar(min, seg);
			System.out.println(min + ":" + seg);
			
			
			try {
				TIEMPO_RELOJ = decrementar(TIEMPO_RELOJ);
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
			
	}


	private void mostrar(final int min, final int seg) {
		if(seg < 10)
			this.etiqueta.setText(min + ":0" + seg);
		else
			this.etiqueta.setText(min + ":" + seg);
	}

	private int decrementar(int n){
		return n -= 1; 
	}
	
	public boolean isRestarted() {
		return restart;
	}
	
	public boolean isStopped(){
		return parado;
	}
	
	public void reiniciarThread(){
		min = 10;
		seg = 0;
		this.etiqueta.setText(min + "0:0" + seg);
	}
}
