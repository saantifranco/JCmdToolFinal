package Interfaz;

import javax.swing.JTextField;

import EstructuraXML.Parametro;

public class ValidadorParametro {
	private JTextField texto;
	private Parametro parametro;
	
	public ValidadorParametro(JTextField unTextField, Parametro tag) {
		texto = unTextField;
		parametro = tag;
	}
	
	public JTextField getTexto(){
		return this.texto;
	}
	
	public Boolean validarCmd(){
		try {
			 parametro.validarContenido(texto.getText());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String generarCmd(){
		return texto.getText();
	}
}
