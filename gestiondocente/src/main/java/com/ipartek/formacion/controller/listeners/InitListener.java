/* Paquete donde se guardan los servlets que gestionan eventos de la web. */
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
* @author Raúl de Roba 30/01/17 
*
* <p>Clase InitListener que gestiona el evento de despliege delcontexto.</p>
* 
*/
public class InitListener 
                      implements ServletContextListener, ServletContextAttributeListener {

    /* Se define el nombre del fichero de propiedades de los log (LOG4J).*/
	private static final String NFICHERO = "log4j.properties";
	/* Se recoge la instacia del log pasando como parametro la clase actual.*/
	private static final Logger LOG = Logger.getLogger(InitListener.class);	
	
	/**
     * Default constructor. 
     */
    public InitListener() {
        // TODO Auto-generated constructor stub
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

	/* Metodo que se ejecuta cuando se inicializa el contexto de toda la aplicación.
	 * (Despliegue de la aplicación en el servidor.).*/
    public void contextInitialized(ServletContextEvent sce)  { 
        /* Se llama al metodo que carga la biblioteca del fichero Log.*/ 
    	loadLog4j(sce);
    }

    /* Metodo que carga la biblioteca del fichero Log.*/
	private void loadLog4j(ServletContextEvent sce) {
		/* Se declara la variable de propierties.*/
		Properties prop = new Properties();
		
		/* Se crea una estructura que capturara los errores.*/
		try {
			/* Se carga el fichero de propiedades con la clase.*/
			PropertyConfigurator.configure(this.getClass().getClassLoader().getResource(NFICHERO));
			/* Se graba el mensaje de éxito.*/
			LOG.trace("LOG cargado");
		} catch (Exception e){
		}
		
	}

	
	
}
