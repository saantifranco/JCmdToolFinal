package Interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EventoGenerarCmd implements ActionListener{
	
	private String comando;
	JTextArea textAreaSalida;

	public EventoGenerarCmd(String cmd, JTextArea refTextArea) {
			this.comando = cmd;
			textAreaSalida = refTextArea;
	}	 

	@Override
	public void actionPerformed(ActionEvent e) {
		Runtime cmd = Runtime.getRuntime();
		try
		{
			Process process = cmd.exec(comando);
			BufferedReader read = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			textAreaSalida.setText("");
			String linea;
			
			while((linea = read.readLine()) != null){
				textAreaSalida.append(linea+"\n");
			}
			
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	//@Override
	public void actionPerformed2(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Comando final : " + comando);
		System.out.println("Comando final: " + comando);
	}
}
