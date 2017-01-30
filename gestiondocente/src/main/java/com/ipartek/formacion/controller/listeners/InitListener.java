package com.ipartek.formacion.controller.listeners;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



/**
 * Application Lifecycle Listener implementation class InitListener
 *
 */
public class InitListener implements ServletContextListener, ServletContextAttributeListener {
	//Constante del fichero de logs
    private static final String NFICHERO = "log4j.properties";
    //obtenemos una instancia del log que le pasamos como parametro el nombre de la clase
    private static final Logger lOG = Logger.getLogger(InitListener.class);
    
    
    /**
     * Default constructor. 
     */
    public InitListener() {
    	
    }

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent scab)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent scab)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent scab)  { 
         // TODO Auto-generated method stub
    }

	/**
	 * Cuando se inicializa la app. Despues de Run As...
	 * Log4J --> Biblioteca
	 * 1º Cargar la configuracion de la app.
	 * 1.1. Añadir la biblioteca al pom.xml
	 * 1.2. Escribir el archivo de configuracion(properties/xml). En nuestro caso properties.
	 * 1.3. Carga del fichero
	 * 
	 * 2º Comprobar si esta bien cargada
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	//
    	loadLog4j(sce);
    }

    /**
     * Este metodo carga el fichero
     * @param sce
     */
	private void loadLog4j(ServletContextEvent sce) {
		//Properties props = new Properties();
		try {
			//Le pasamos props al configurador de properties
			//
			PropertyConfigurator.configure(this.getClass().getClassLoader().getResource(NFICHERO));
			//Ejemplo de mensaje
			lOG.trace("LOG Cargado");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
