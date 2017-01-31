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

import com.ipartek.formacion.controller.AlumnoServlet;
import com.ipartek.formacion.controller.Constantes;
import com.ipartek.formacion.dbms.pojo.Persona;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener, HttpSessionActivationListener, HttpSessionBindingListener {
	
	//la sesion se crea ANTES de que se añada el atributo
	
	private static final Logger LOG = Logger.getLogger(SessionListener.class);
    //declaro un contador para recoger elnúmero de personas que están activas
	private static int totalActiveSessions = 0;
	private static Map<String, HttpSession> map = new HashMap<String, HttpSession>();
	
	/**
     * Default constructor. 
     */
    public SessionListener() {


    	LOG.trace("control de sesión");
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
        totalActiveSessions++;
        String id = arg0.getSession().getId();
		LOG.debug("session created : " + id);
		map.put(id, arg0.getSession());
	}


	/**
     * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
     */
    public void valueBound(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    //lo siguiente salta si la sesión se destruye con éxito
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         totalActiveSessions--;
         List<Persona> personas = null;
         HttpSession session = arg0.getSession();
         ServletContext ctx = session.getServletContext();
         
         if(null!= session.getAttribute(Constantes.SESSION_PERSONA)
        		 ){
        	 Persona persona = (Persona) session.getAttribute(Constantes.SESSION_PERSONA);
        	 LOG.trace(persona.getNombre());
        	 personas = (List<Persona>) ctx.getAttribute("Constantes.ATT_LISTADO_USUARIOS");
        	 personas.remove(persona);
        	 ctx.setAttribute("Constantes.ATT_LISTADO_USUARIOS", personas);
         }
    }

	/**
     * @see HttpSessionActivationListener#sessionDidActivate(HttpSessionEvent)
     */
    public void sessionDidActivate(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
        
    	//este evento se ejecuta cada vez que se añade un attributo
    	
    	 HttpSession session = arg0.getSession();
         List<Persona> personas = null;
         ServletContext ctx = session.getServletContext();
         //TODO: cargar lista personas contexto de la appp
         personas = (List<Persona>) ctx.getAttribute("Constantes.ATT_LISTADO_USUARIOS");
         //el contexto es común para todos los ususarios
         
         if(personas == null){
        	 personas = new ArrayList<Persona>();
         }
         if(session.getAttribute(Constantes.SESSION_PERSONA) != null) {
        	 LOG.trace("Usuario registrado.");
        	 Persona p = (Persona) session.getAttribute(Constantes.SESSION_PERSONA);
        	 personas.add(p);
        	 ctx.setAttribute("Constantes.ATT_LISTADO_USUARIOS", personas);
         }
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionActivationListener#sessionWillPassivate(HttpSessionEvent)
     */
    public void sessionWillPassivate(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionBindingListener#valueUnbound(HttpSessionBindingEvent)
     */
    public void valueUnbound(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	public static int getTotalActiveSessions() {
		return totalActiveSessions;
	}

	public static HttpSession getHttpSession(String sessionId) {
		return map.get(sessionId);
	}

	
}
