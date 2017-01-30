package com.ipartek.formacion.controler.listeners;
import java.util.Properties;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



/**
 * Application Lifecycle Listener implementation class initListener
 *
 */
public class InitListener implements ServletContextAttributeListener, ServletContextListener {
	
	private static final  String nFichero = "log4j.properties"; 
	private static final Logger log = Logger.getLogger(InitListener.class);
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

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     * la logica de carga va a estar aqui
     * cargaremos la biblioteca log 4J(que espar los log)
     * 1-cargar la configuracion
     * 		añadir al biblioteca al pom.xml(creamos un fichero united text file y lo guadmos en src/main/resources)
     * 		hacer archivo de configuracion:property o xml b(q se guarda en src/main/resources un tetx file )
     * 		cargar el fichero(creamos el fichero, para ello creamos un metodo)
     * 2-detectar si se ha cargado bien
     * 3-
     */
    public void contextInitialized(ServletContextEvent sce)  { 
        loadLog4j(sce);
    }

    private void loadLog4j(ServletContextEvent sce) {
		Properties props = new Properties();
		try {
			// props.load(this.getClass().getClassLoader().getResource());
			PropertyConfigurator.configure(this.getClass().getClassLoader().getResource(nFichero));
			log.trace("LOG cargado");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
