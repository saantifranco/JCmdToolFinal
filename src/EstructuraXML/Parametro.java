package EstructuraXML;

import javax.swing.JOptionPane;

public class Parametro extends Tag {
	String tipoValidacion; // Indica si debe ser solicitado al usuario o ya se esta proveyendo
	int cotaMin = -9999; // Mínimo de caracteres a ingresar en un determinado Tag
	int cotaMax = -9999; // Máximo de caracteres a ingresar en un determinado Tag
	
	public String getTipoValidacion(){
		return this.tipoValidacion;
	}

	public void setTipoValidacion(String unString){
		this.tipoValidacion = unString;
	}
	
	public int getCotaMin() {
		return cotaMin;
	}

	public void setCotaMin(int cotaMin) {
		this.cotaMin = cotaMin;
	}

	public int getCotaMax() {
		return cotaMax;
	}

	public void setCotaMax(int cotaMax) {
		this.cotaMax = cotaMax;
	}
	
	public Boolean validarContenido(String unValor){
		if((unValor.length() >= cotaMin && unValor.length() <= cotaMax) || cotaMin == cotaMax){
			if(tipoValidacion != "obligatorio") return true;
			if(unValor.length() == 0 && tipoValidacion == "obligatorio") throw new RuntimeException("El valor ingresado no es válido");
			else return true;
		}
		else JOptionPane.showMessageDialog(null, "Parametro "+this.getValor()+" no válido.");
			//throw new RuntimeException("El valor ingresado no es válido");
		return null;
	}
}
