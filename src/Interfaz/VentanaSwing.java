package Interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import EstructuraXML.Aplicacion;
import EstructuraXML.Parametro;
import EstructuraXML.SubAplicacion;
import EstructuraXML.Tag;
import Parser.CPoolXMLHandler;
import Parser.Parser;

import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class VentanaSwing extends JFrame {

	JPanel contentPane;
	JPanel refPanelListaApps = new JPanel();
	JPanel refPanelListaSubApps = new JPanel();
	JPanel refPanelListaParametros = new JPanel();
	JButton refBotonComando = new JButton("Generar comando");
	String comandoParcial;
	Parser parser = new Parser();
	CPoolXMLHandler handler = new CPoolXMLHandler();
	List<ValidadorParametro> validadores= new ArrayList<ValidadorParametro>();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaSwing frame = new VentanaSwing();
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
	public VentanaSwing() {
		super("JCmdTool: Java Command Tool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		
		parser.setHandler(handler);
	    parser.parsearXml("algoritmos2_tp.xml");
	    List<Aplicacion> apps = new ArrayList<Aplicacion>();
	    apps.addAll(handler.getContenidoXml());
	    
		//setLayout(new FlowLayout());
	    //Container contentPane = getContentPane();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(3, 1));
		setContentPane(contentPane);
		contentPane.add(refPanelListaApps, 0);
		contentPane.add(refPanelListaSubApps, 1);
		contentPane.add(refPanelListaParametros, 2);
		
		this.generarPanelAplicaciones(apps);
	}
	 
	  	 //System.out.println(appElegida.getValor());
	  	 //System.out.println(appElegida.getSubTags().size());

	public void generarPanelAplicaciones(List<Aplicacion> apps) {
		 //Panel Aplicaciones
		 contentPane.remove(refPanelListaApps);
		 JPanel panelListaApps = new JPanel();
		 panelListaApps.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		 panelListaApps.setLayout(null);
		 JLabel label = new JLabel("Aplicaciones :");
		 label.setBounds(25, 10, 65, 14);
		 panelListaApps.add(label);
		 Vector<String> aplicaciones = new Vector<String>();
		 for(int i = 0; i < apps.size(); i++){
			 aplicaciones.addElement(apps.get(i).getValor());
		 }
		 JComboBox<String> comboBoxApps = new JComboBox<String>(aplicaciones);
		 comboBoxApps.setBounds(100, 10, 144, 20);
		 comboBoxApps.setSelectedIndex(3);
		 //System.out.println(comboBoxApps.getSelectedItem());
		 panelListaApps.add(comboBoxApps);
		 panelListaApps.setVisible(true);
		 comboBoxApps.addItemListener(new EventoSubAplicaciones(this, apps, comboBoxApps));
		 refPanelListaApps = panelListaApps;
		 contentPane.add(refPanelListaApps, 0); 
		 
		 //JButton botonIrASubApps = new JButton("Ver SubApps");
		 //botonIrASubApps.setBounds(249, 5, 95, 23);
		 //panelListaApps.add(botonIrASubApps);
		 //botonIrASubApps.addActionListener(new EventoSubApps(this, apps, cp, comboBoxApps));
		 //SOLUCION VIEJA, YA NO NECESITO EL BOTON

	}

	public void generarPanelSubAplicaciones(Aplicacion appElegida) {
		contentPane.remove(refPanelListaSubApps);
		//Panel SubApps
		JPanel panelListaSubApps = new JPanel();
		JLabel label = new JLabel("Sub-Aplicaciones :");
		label.setBounds(25, 60, 65, 14);
		panelListaSubApps.add(label);
		//panelListaSubApps.setLayout(new FlowLayout());
		panelListaSubApps.setLayout(null);
		Vector<String> subApps = new Vector<String>();
		for(int i = 0; i < appElegida.getSubAplicacioes().size(); i++){
				 subApps.addElement(appElegida.getSubAplicacioes().get(i).getValor());
		}
		JComboBox<String> comboBoxSubApps = new JComboBox<String>(subApps);
		comboBoxSubApps.setBounds(100, 60, 144, 20);
		comboBoxSubApps.setSelectedIndex(0);
		//System.out.println(comboBoxApps.getSelectedItem());
		panelListaSubApps.add(comboBoxSubApps);
		panelListaSubApps.setVisible(true);
		comboBoxSubApps.addItemListener(new EventoParametros(this, appElegida, comboBoxSubApps));
		refPanelListaSubApps = panelListaSubApps;
		contentPane.add(panelListaSubApps, 1);
	}
	
	/*public void generarPanelParametros(Container cp, TagCompuesto subAppElegida) {
		cp.remove(refPanelListaParametros);
		//Panel Parametros
		JPanel panelListaParametros = new JPanel();
		panelListaParametros.add(new JLabel("Parámetros :"));
		panelListaParametros.setLayout(new FlowLayout());
		Vector<String> parametros = new Vector<String>();
		for(int i = 0; i < subAppElegida.getSubTags().size(); i++){
			parametros.addElement(subAppElegida.getSubTags().get(i).getValor());
		}
		JComboBox<String> comboBoxApps = new JComboBox<String>(parametros);
		comboBoxApps.setSelectedIndex(0);
		//System.out.println(comboBoxApps.getSelectedItem());
		panelListaParametros.add(comboBoxApps);
		panelListaParametros.setVisible(true);
		cp.add(panelListaParametros);
		refPanelListaParametros = panelListaParametros;
	}*/

	public void generarPanelParametros(SubAplicacion subAppElegida, String nombreSubAppElegida) {
		contentPane.remove(refPanelListaParametros);
		List<Parametro> parametros = new ArrayList<Parametro>();
		parametros = subAppElegida.getParametros();
		
		//Panel Parametros
		JPanel panelListaParametros = new JPanel();
		panelListaParametros.setLayout(new FlowLayout());
		JLabel label = new JLabel("Parámetros:");
		panelListaParametros.add(label);
		String auxS;
		int auxInt = 110;

		for(int i = 0; i < parametros.size(); i++){
			 auxS = parametros.get(i).getValor();
			 auxInt = auxInt + 50;
			 JLabel labelAux = new JLabel(auxS);
			 panelListaParametros.add(labelAux);
			 JTextField texto = new JTextField(20);
			 panelListaParametros.add(texto);
			 setJTexFieldChanged(texto);
			 ValidadorParametro validador = new ValidadorParametro(texto, parametros.get(i));
			 validadores.add(validador);
		}
		this.generarBotonCmd(validadores);
		panelListaParametros.add(refBotonComando);
		refPanelListaParametros = panelListaParametros;
		contentPane.add(panelListaParametros, 2);
	}
	
	public void generarBotonCmd(List<ValidadorParametro> validadores){
		contentPane.remove(refBotonComando);
		JButton botonComando = new JButton("Generar comando");
		botonComando.addActionListener(new EventoGenerarCmd(this.generarCmd(validadores)));
		refBotonComando = botonComando;
	}

	public String generarCmd(List<ValidadorParametro> validadores) {
		String comando;
		comando = (String) ((JComboBox<String>) refPanelListaApps.getComponent(1)).getSelectedItem();
		comando = comando+" "+(String) ((JComboBox<String>) refPanelListaSubApps.getComponent(1)).getSelectedItem();
		System.out.println("EL cmd parcial: "+comando+comandoParcial );
		return comando+comandoParcial;
	}

	private String textoParametros(List<ValidadorParametro> validadores) {
		String textoParametros = "";
		int i = 0;
		while(i < validadores.size()){
			textoParametros = textoParametros + " " + (String) validadores.get(i).generarCmd();
			i++;
		}
		System.out.println("Texto: " + textoParametros);
		return textoParametros;
	}
	
    private void setJTexFieldChanged(JTextField txt)
    {
        DocumentListener documentListener = new DocumentListener() {
 
        @Override
        public void changedUpdate(DocumentEvent documentEvent) {
            printIt(documentEvent, txt);
        }
 
        @Override
        public void insertUpdate(DocumentEvent documentEvent) {
            printIt(documentEvent, txt);
        }
 
        @Override
        public void removeUpdate(DocumentEvent documentEvent) {
            printIt(documentEvent, txt);
        }
        };
        txt.getDocument().addDocumentListener(documentListener);
 
    }
 
    private void printIt(DocumentEvent documentEvent, JTextField txt) {
        DocumentEvent.EventType type = documentEvent.getType();
 
        if (type.equals(DocumentEvent.EventType.CHANGE))
        {
        	comandoParcial = "";
        	for(int i=0; i<validadores.size(); i++){
        		comandoParcial = comandoParcial + " " + validadores.get(i).getTexto().getText();
        	}
        	//System.out.println("Change: " + comandoParcial);
        	this.generarBotonCmd(validadores);
        }
        else if (type.equals(DocumentEvent.EventType.INSERT))
        {
        	comandoParcial = "";
        	for(int i=0; i<validadores.size(); i++){
        		comandoParcial = comandoParcial + " " + validadores.get(i).getTexto().getText();
        	}
        	//System.out.println("Instert: " + comandoParcial);
        	this.generarBotonCmd(validadores);
        	//refBotonComando.addActionListener(new EventoGenerarCmd(this.generarCmd(validadores)));
        }
        else if (type.equals(DocumentEvent.EventType.REMOVE))
        {
        	comandoParcial = "";
        	for(int i=0; i<validadores.size(); i++){
        		comandoParcial = comandoParcial + " " + validadores.get(i).getTexto().getText();
        	}
        	//System.out.println("Remove: " + comandoParcial);
        	this.generarBotonCmd(validadores);
        	//refBotonComando.addActionListener(new EventoGenerarCmd(this.generarCmd(validadores)));
        }
   }
   
	
}
