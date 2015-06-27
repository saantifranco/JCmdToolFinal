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
	
	public String generarCmd(){
		try {
			if(parametro.validarContenido(texto.getText())){
				System.out.println("Texto sin validar: " + texto.getText());
				return texto.getText();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
