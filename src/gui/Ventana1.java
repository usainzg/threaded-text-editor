package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import threads.GuardarRunnable;
import threads.RecuperarRunnable;
import threads.ThreadGuardar;
import threads.ThreadReloj;
import utilidades.Utilidades;

public class Ventana1 extends JFrame implements ActionListener, WindowListener{

	private static final long serialVersionUID = 1L;
	
	private final JTextArea textArea;
	private final JButton btnGuardar;
	private final JButton btnLimpiar;
	private final JButton btnRecuperar;
	private final JLabel lblTiempo;
	private final JButton btnSalir;
	
	private final Utilidades util = new Utilidades();

	private ThreadReloj threadReloj;
	private Thread threadGuardar;
	private Thread threadGuardarCadaDos;
	private Thread threadRecuperar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana1 frame = new Ventana1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ventana1() {
		addWindowListener(this);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 423);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel1 = new JPanel();
		contentPane.add(panel1, BorderLayout.NORTH);
		panel1.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblTitulo = new JLabel("Escritura creativa r\u00E1pida");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel1.add(lblTitulo);
		
		JLabel lblInstrucciones = new JLabel("Instrucciones:\r\nTienes 10 min para escribir.\r\nTu texto se autoguarda cada 2 min.");
		panel1.add(lblInstrucciones);
		
		JPanel panel2 = new JPanel();
		contentPane.add(panel2, BorderLayout.WEST);
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		textArea.setPreferredSize(new Dimension(300, 280));
		panel2.add(textArea);
		
		
		JPanel panel3 = new JPanel();
		contentPane.add(panel3, BorderLayout.EAST);
		GridBagLayout gbl_panel3 = new GridBagLayout();
		gbl_panel3.columnWidths = new int[]{0, 0};
		gbl_panel3.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel3.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel3.setLayout(gbl_panel3);

		JLabel lblTiempoRestante = new JLabel("Tiempo restante");
		lblTiempoRestante.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblTiempoRestante = new GridBagConstraints();
		gbc_lblTiempoRestante.insets = new Insets(0, 0, 5, 0);
		gbc_lblTiempoRestante.gridx = 0;
		gbc_lblTiempoRestante.gridy = 0;
		panel3.add(lblTiempoRestante, gbc_lblTiempoRestante);
		
		lblTiempo = new JLabel("00:00");
		lblTiempo.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblTiempo = new GridBagConstraints();
		gbc_lblTiempo.insets = new Insets(0, 0, 5, 0);
		gbc_lblTiempo.gridx = 0;
		gbc_lblTiempo.gridy = 1;
		panel3.add(lblTiempo, gbc_lblTiempo);
		
		btnGuardar = new JButton("Guardar");
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.insets = new Insets(0, 0, 5, 0);
		gbc_btnGuardar.gridx = 0;
		gbc_btnGuardar.gridy = 3;
		panel3.add(btnGuardar, gbc_btnGuardar);
		
		btnLimpiar = new JButton("Limpiar");
		GridBagConstraints gbc_btnLimpiar = new GridBagConstraints();
		gbc_btnLimpiar.insets = new Insets(0, 0, 5, 0);
		gbc_btnLimpiar.gridx = 0;
		gbc_btnLimpiar.gridy = 4;
		panel3.add(btnLimpiar, gbc_btnLimpiar);
		
		btnRecuperar = new JButton("Recuperar \u00FAltimo");
		GridBagConstraints gbc_btnRecuperar = new GridBagConstraints();
		gbc_btnRecuperar.gridx = 0;
		gbc_btnRecuperar.gridy = 5;
		panel3.add(btnRecuperar, gbc_btnRecuperar);
		
		JPanel panel4 = new JPanel();
		contentPane.add(panel4, BorderLayout.SOUTH);
		panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnSalir = new JButton("Salir de la aplicaci\u00F3n");
		panel4.add(btnSalir);
		
		btnSalir.addActionListener(this);
		btnGuardar.addActionListener(this);
		btnLimpiar.addActionListener(this);
		btnRecuperar.addActionListener(this);
		
		threadReloj = new ThreadReloj("threadReloj", lblTiempo, textArea);
		threadGuardarCadaDos = new Thread(new GuardarRunnable(textArea, "GuardarCadaDos"));
		threadGuardarCadaDos.start();
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton boton = (JButton) e.getSource();
		
		if (boton == btnSalir) {
			salir();
		} else if(boton == btnGuardar) {
			threadGuardar = new Thread(new ThreadGuardar(textArea, "threadGuardar"));
			threadGuardar.start();
		} else if(boton == btnLimpiar) {
			util.limpiar(textArea);
		} else if(boton == btnRecuperar) {
			threadRecuperar = new Thread(new RecuperarRunnable(textArea, "RecuperarThread"));
			threadRecuperar.start();
		}
		
	}
	
	private void iniciar() {
		
		if(!threadReloj.isAlive()){
			threadReloj = new ThreadReloj("threadReloj", lblTiempo, textArea);
			threadReloj.start();
		}
	}
	
	private void salir() {
	
		if(threadReloj.isAlive()){
			threadReloj.interrupt();
		}
		
		if(threadGuardarCadaDos.isAlive()){
			threadGuardarCadaDos.interrupt();
		}
		
		System.exit(0);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		iniciar();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
