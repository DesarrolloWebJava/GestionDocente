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
	//Guardamos en la constante statica LOG de tipo Logger el nombre de la clase
	private static final Logger LOG = Logger.getLogger(SessionListener.class);
	//Variable contador inicializaza en 0 para contar el numero de sesiones
	private static int totalActiveSessions = 0;

	/**
     * Default constructor. 
     */
    public SessionListener() {
        LOG.trace("Control de session");
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         // Al crear una session que se incremente la variable contador totalActiveSessions
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
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	// Al destruiruna session que se decremente la variable contador totalActiveSessions
    	totalActiveSessions--;
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
         //le pasamos la sesion y cogemos los valores
    	HttpSession session = se.getSession();
    	//Creamos una lista de personas
    	List<Persona> personas = null;
    	//Obtener el contexto de la session
    	ServletContext ctx = session.getServletContext();
    	//TODO Cargamos la lista de personas del contexto de la app
    	personas = (List<Persona>)ctx.getAttribute(Constantes.ATT_LISTADO_USUARIOS);
    	//Primero comprobamos que el atributo no sea nulo
    	if(personas == null){
    		personas = new ArrayList<Persona>();
    	}
    	if(session.getAttribute(Constantes.SESSION_PERSONA)!= null){
    		LOG.trace("Usuario registrado");
    		//Obtenemos el atributo de la persona y lo guardamos en p
    		Persona p = (Persona)session.getAttribute(Constantes.SESSION_PERSONA);
    		//Añadimos la persona a la lista
    		personas.add(p);
    		ctx.setAttribute(Constantes.ATT_LISTADO_USUARIOS, personas);
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
