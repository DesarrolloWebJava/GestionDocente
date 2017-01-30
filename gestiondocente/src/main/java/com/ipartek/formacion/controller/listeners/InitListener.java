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

	
	private static final String NFICHERO = "log4j.properties";
	private static final Logger LOG = Logger.getLogger(InitListener.class);
	
    /**
     * Default constructor. 
     */
    public InitListener() {
    }
    
    public void contextInitialized(ServletContextEvent sce)  { 
       loadLog4j(sce);
   }
	private void loadLog4j(ServletContextEvent sce) {
		//Properties props = new Properties();
		try {
			//props.load(new FileInputStream(NFICHERO));
			// Carga para la clase actual que se encuentra en la carpeta SRC y 
			//despues carga el recurso de carpeta resources
			PropertyConfigurator.configure(this.getClass().getClassLoader().getResource(NFICHERO));
			LOG.trace("LOG cargado");
		} 
		 catch (Exception e) {
			
			e.printStackTrace();
		}
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
     */
  
	
}
