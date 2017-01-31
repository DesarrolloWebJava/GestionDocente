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
	private static final Logger LOG=Logger.getLogger(SessionListener.class);
	private static int totalActiveSessions=0;
	private static Map<String,HttpSession> sesiones=new HashMap<String,HttpSession>();

    /**
     * Default constructor. 
     */
    public SessionListener() {
        // TODO Auto-generated constructor stub
    	LOG.trace("Control de Sesión");
    }

	public static int getTotalActiveSessions() {
		return totalActiveSessions;
	}

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         totalActiveSessions++;
         sesiones.put(se.getSession().getId(), se.getSession());
    }
    public static HttpSession getHttpSession(String id){
    	return sesiones.get(id);
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
         totalActiveSessions--;
         List<Persona> personas=null;
         HttpSession session=se.getSession();
         ServletContext ctx=session.getServletContext();
         
         if(null!=session.getAttribute(Constantes.SESSION_PERSONA)){
        	 Persona persona=(Persona)session.getAttribute(Constantes.SESSION_PERSONA);//recojo la posicion de memoria del objeto persona
        	 LOG.trace(persona.getNombre());
        	 personas=(List<Persona>)ctx.getAttribute(Constantes.CTX_LISTADO_USUARIOS);
        	 personas.remove(persona);//elimino la referencia en esa posición de memoria
        	 ctx.setAttribute(Constantes.CTX_LISTADO_USUARIOS, personas);
         }
         sesiones.remove(se.getSession().getId());
         LOG.trace(se.getSession().getId()+" eliminada");
        	 
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
    public void attributeAdded(HttpSessionBindingEvent se)  { 
         HttpSession session=se.getSession();
         List<Persona> personas=null;

         ServletContext ctx=session.getServletContext();
         
         personas=(List<Persona>)ctx.getAttribute(Constantes.CTX_LISTADO_USUARIOS);

         if(personas==null){
        	 personas=new ArrayList<Persona>();


         }
         
         if(session.getAttribute(Constantes.SESSION_PERSONA)!=null
        		 && se.getName().equals(Constantes.SESSION_PERSONA)){
        	 
        	 LOG.trace("Evento añadir atributo persona a sesión");
        	 Persona p=(Persona)session.getAttribute(Constantes.SESSION_PERSONA);
        	 personas.add(p);
        	 ctx.setAttribute(Constantes.CTX_LISTADO_USUARIOS, personas);
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
	
}
