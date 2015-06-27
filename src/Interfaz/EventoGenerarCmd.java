package Interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EventoGenerarCmd implements ActionListener{
	
	private String comando;
	
	public EventoGenerarCmd(String cmd) {
			this.comando = cmd;
	}
	 
	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Comando: " + comando);
	}


}
