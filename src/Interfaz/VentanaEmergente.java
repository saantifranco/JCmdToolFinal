package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class VentanaEmergente extends JFrame {
	JTextArea textArea = new JTextArea();
	private JPanel contentPane;

	public VentanaEmergente() {
		super("Salida Comando");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 11, 414, 240);
		contentPane.add(textArea);
		textArea.setVisible(true);
		contentPane.setVisible(true);
	}
	
	void agregarTexto(String string){
		textArea.append(string+ "\n");
	}
	
	void limpiarTexto(){
		textArea.setText("");
	}
}
