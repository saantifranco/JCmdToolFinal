package Interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import EstructuraXML.Aplicacion;

public class EventoContinuar implements ActionListener {

	private VentanaSwing ventana;
	
	public EventoContinuar(VentanaSwing ventanaSwing) {
		ventana = ventanaSwing;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(ventana.tieneParametrosValidos())
			ventana.generarBotonCmd();
		else {
			//ventana.remove(ventana.getRefBotonComando());
			return;
		}

	}

}
