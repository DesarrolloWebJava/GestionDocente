package com.ipartek.formacion.controller.listeners;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.ipartek.formacion.controller.Constantes;
import com.ipartek.formacion.dbms.pojo.Persona;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener, HttpSessionActivationListener, HttpSessionBindingListener {



	private static final Logger log = Logger.getLogger(SessionListener.class);
	// contador
	private static int totalActiveSession = 0;
	
    /**
     * Default constructor. 
     */
    public SessionListener() {
        log.trace("Control de session");
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
        
    	// cuando cree una session, incremento 1 a session
    	
    	totalActiveSession ++;
    	
    	
    	
    }

	/**
     * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
     */
    public void valueBound(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	
    	// cuando elimino una session, decremento 1 a session
    	totalActiveSession --;
    }

	/**
     * @see HttpSessionActivationListener#sessionDidActivate(HttpSessionEvent)
     */
    public void sessionDidActivate(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
      
    	HttpSession session = arg0.getSession();
    	List<Persona> personas = null;
    	ServletContext ctx = session.getServletContext();
    	// TODO cargar la lista de personas del contexto de la aplicacion
    	personas = (List<Persona>) ctx.getAttribute("listadoUsuarios");
    	// este evento se me ejecuta cada vez que se le añade un atributo.
    	if (personas == null) {
    		personas = new ArrayList<Persona>();
    	}
    	// como se comprueva? 
    	if ( session.getAttribute(Constantes.SESSION_PERSONA)!= null){
    		log.error("usuario registrado");
    		Persona p = (Persona) session.getAttribute(Constantes.SESSION_PERSONA);
    		personas.add(p);
    		// como guardo datos en el ctx??
    		ctx.setAttribute("listadoUsuarios", personas);
    	}
    	
    	
    	
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionActivationListener#sessionWillPassivate(HttpSessionEvent)
     */
    public void sessionWillPassivate(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionBindingListener#valueUnbound(HttpSessionBindingEvent)
     */
    public void valueUnbound(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    }
	
	
    
    
	public static int getTotalActiveSession() {
		return totalActiveSession;
	}
    
}
