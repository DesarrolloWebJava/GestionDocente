package com.ipartek.formacion.controller.listeners;

import java.util.ArrayList;
import java.util.List;

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

	private static final Logger LOG= Logger.getLogger(SessionListener.class);
	private static int totalActiveSessions = 0;
	
	
	
    public SessionListener() {
    	LOG.trace("CONTROL DE SESSION");
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  {
    	totalActiveSessions++;
    	
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
         totalActiveSessions--;
         List<Persona> personas = null;
         HttpSession session=arg0.getSession();
         ServletContext ctx = session.getServletContext();
         
         if(null!=session.getAttribute(Constantes.SESSION_PERSONA)){
        	 Persona persona = (Persona) session.getAttribute(Constantes.SESSION_PERSONA);
        	 LOG.trace(persona.getNombre());
        	 personas = (List<Persona>)ctx.getAttribute("listadoUsuarios");
        	 personas.remove(persona);
        	 ctx.setAttribute("listadoUsuarios", personas);
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
    	HttpSession session= arg0.getSession();
    	List<Persona> personas = null;
    	ServletContext ctx = session.getServletContext();
    	//cargar la lista de personas del contexto de la app
    	personas = (List<Persona>) ctx.getAttribute("listadoUsuarios");
    	
    	if(personas == null){
    		personas = new ArrayList<Persona>();
    	}
    	if(session.getAttribute(Constantes.SESSION_PERSONA)!=null && arg0.getName().equals(Constantes.SESSION_PERSONA)){
    		LOG.trace("usuario registrado");
    		Persona p = (Persona)session.getAttribute(Constantes.SESSION_PERSONA);
    		personas.add(p);
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

	public static int getTotalActiveSessions() {
		return totalActiveSessions;
	}
    
	
}
