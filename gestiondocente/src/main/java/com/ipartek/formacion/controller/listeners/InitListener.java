package com.ipartek.formacion.controller.listeners;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	loadLog4j(arg0);
    }
    private void loadLog4j(ServletContextEvent arg0){
    	Properties props = new Properties();
    	try{
    		//props.load(new FileInputStream(NFICHERO));
    		PropertyConfigurator.configure(this.getClass().getClassLoader().getResourceAsStream(NFICHERO));
    		LOG.debug("log cargado");
    	}catch (Exception e){
    		e.printStackTrace();
    	}
    	
    }
	
}
