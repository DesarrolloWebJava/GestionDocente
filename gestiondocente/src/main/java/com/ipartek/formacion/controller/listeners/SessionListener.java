package com.ipartek.formacion.controller.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private static Map<String, HttpSession> map = new HashMap<String, HttpSession>();
	
    /**
     * Default constructor. 
     */
    public SessionListener() {
        log.trace("Control de session");
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
        
    	// cuando cree una session, incremento 1 a session
    	totalActiveSession ++;	
    	
    	String id = arg0.getSession().getId();
    	log.debug("session created : " + id);
    	// STORE THE SESSOIN FOR EXAMPLE IN DATABASE
    	map.put(id, arg0.getSession());
    	
    }
    
    public static HttpSession getHttpSession(String sessionID) {
    	 		return map.get(sessionID);
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
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	// cuando elimino una session, decremento 1 a session
    	totalActiveSession --;
    	
    	
    	// acceder a la lista de personas
    	List<Persona> personas = null;
    	// acceder a la session
    	HttpSession session = arg0.getSession();
    	// acceder al servlet ctx
    	ServletContext ctx = session.getServletContext();
    	
    	
    	if(null != session.getAttribute(Constantes.SESSION_PERSONA)){
    		Persona persona = (Persona) session.getAttribute(Constantes.SESSION_PERSONA);
    		log.trace(persona.getNombre());
    		// acceder a la lista
    		personas = (List<Persona>) ctx.getAttribute(Constantes.CTX_LISTADO_USUARIOS); //que hacer con esa lista? Quitar el obj persona
    		personas.remove(persona);
    		// Guardar en la lista Actualizada
    		ctx.setAttribute(Constantes.CTX_LISTADO_USUARIOS, personas);
    	}
    	String sessionId = arg0.getSession().getId();
    	map.remove(sessionId);
    	log.trace(sessionId + " Eliminada");
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
    public void attributeAdded(HttpSessionBindingEvent arg0)  { // no es una request. es especial
    	
      
    	HttpSession session = arg0.getSession();
    	List<Persona> personas = null;
    	ServletContext ctx = session.getServletContext(); // esta parte es NUEVA
    	// TODO cargar la lista de personas del contexto de la aplicacion
    	personas = (List<Persona>) ctx.getAttribute(Constantes.CTX_LISTADO_USUARIOS);
    	// este evento se me ejecuta cada vez que se le añade un atributo.
    	if (personas == null) {
    		personas = new ArrayList<Persona>();
    	}
    	// como se comprueba? 
    	if ( session.getAttribute(Constantes.SESSION_PERSONA)!= null && arg0.getName().equals(Constantes.SESSION_PERSONA)){
    		log.error("usuario registrado");
    		Persona p = (Persona) session.getAttribute(Constantes.SESSION_PERSONA);
    		personas.add(p);
    		// como guardo datos en el ctx??
    		ctx.setAttribute(Constantes.CTX_LISTADO_USUARIOS, personas); // esta parte es NUEVA
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
