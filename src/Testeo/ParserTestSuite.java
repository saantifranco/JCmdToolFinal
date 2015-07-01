package Testeo;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import EstructuraXML.Aplicacion;
import EstructuraXML.Parametro;
import EstructuraXML.SubAplicacion;
import Parser.CPoolXMLHandler;
import Parser.Parser;

public class ParserTestSuite {

	@Test
	  public void UnParserQueParseaUnXMLConCuatroAplicacionesRegistraUnaEstructuraConCuatroElementos() {

		Parser parser = new Parser();
		CPoolXMLHandler handler = new CPoolXMLHandler();
		parser.setHandler(handler);
		
	    parser.parsearXml("algoritmos2_tp.xml");

	    assertEquals(handler.getContenidoXml().size(), 5);
	  }
	
	@Test
	  public void UnParserQueParseaUnXMLConCuatroAplicacionesRegistraCorrectamenteLaEstructuraDelXML() {

		Parser parser = new Parser();
		CPoolXMLHandler handler = new CPoolXMLHandler();
		parser.setHandler(handler);
		
	    parser.parsearXml("algoritmos2_tp.xml");
		
	    assertEquals(handler.getContenidoXml().size(), 5);
	    assertEquals(handler.getContenidoXml().get(0).getTipoTag(), "Aplicacion");
	    assertEquals(handler.getContenidoXml().get(0).getValor(), "Elegir aplicacion");
	    assertEquals(handler.getContenidoXml().get(1).getTipoTag(), "Aplicacion");
	    assertEquals(handler.getContenidoXml().get(1).getValor(), "FFmpeg");
	    assertEquals(handler.getContenidoXml().get(2).getTipoTag(), "Aplicacion");
	    assertEquals(handler.getContenidoXml().get(2).getValor(), "shutdown");
	    assertEquals(handler.getContenidoXml().get(3).getTipoTag(), "Aplicacion");
	    assertEquals(handler.getContenidoXml().get(3).getValor(), "exiftool");
	    assertEquals(handler.getContenidoXml().get(4).getTipoTag(), "Aplicacion");
	    assertEquals(handler.getContenidoXml().get(4).getValor(), "git");
	  }
	
	@Test
	  public void UnParserQueParseaUnXMLConEstructurasAnidadasRegistraCorrectamenteLaEstructuraYSubEstructurasDelXML() {

		Parser parser = new Parser();
		CPoolXMLHandler handler = new CPoolXMLHandler();
		parser.setHandler(handler);
		Aplicacion aplicacion = new Aplicacion();
		List<SubAplicacion> subApps = new ArrayList<SubAplicacion>();
		SubAplicacion subApp = new SubAplicacion();
		List<Parametro> parametros = new ArrayList<Parametro>();
		
	    parser.parsearXml("algoritmos2_tp.xml");
	    aplicacion = handler.getContenidoXml().get(4);
	    subApps = aplicacion.getSubAplicacioes();
	    subApp = subApps.get(6);	
	    parametros = subApp.getParametros();

	    assertEquals(aplicacion.getTipoTag(), "Aplicacion");
	    assertEquals(aplicacion.getValor(), "git");
	    assertEquals(aplicacion.getSubAplicacioes().size(), 11);
	    assertEquals(subApps.get(0).getTipoTag(), "SubApp");
	    assertEquals(subApps.get(5).getUsoReal(), "commit");
	    assertEquals(subApps.get(6).getUsoReal(), "push");
	    assertEquals(subApps.get(2).getUsoReal(), "clone");
	    assertEquals(subApp.getTipoTag(), "SubApp");
	    assertEquals(subApp.getUsoReal(), "push");
	    assertEquals(subApp.getParametros().size(), 3);
	    assertEquals(parametros.get(0).getTipoTag(), "Parametro");
	    assertEquals(parametros.get(0).getValor(), "Rama a pushear");
	    assertEquals(parametros.get(0).getTipoValidacion(), "obligatorio");
	    assertEquals(parametros.get(1).getValor(), "Usuario");
	    assertEquals(parametros.get(1).getTipoValidacion(), "obligatorio");
	    assertEquals(parametros.get(2).getValor(), "Password");
	    assertEquals(parametros.get(2).getTipoValidacion(), "obligatorio");
	    assertEquals(parametros.get(2).getCotaMin(), 6);
	    assertEquals(parametros.get(2).getCotaMax(), 12);
	  }
	
}
