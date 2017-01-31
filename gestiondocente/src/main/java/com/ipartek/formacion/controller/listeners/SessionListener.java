package com.ipartek.formacion.controller.listeners;

import java.io.LineNumberInputStream;
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
	//Guardamos en la constante statica LOG de tipo Logger el nombre de la clase
	private static final Logger LOG = Logger.getLogger(SessionListener.class);
	//Variable contador inicializaza en 0 para contar el numero de sesiones
	private static int totalActiveSessions = 0;
	//Creamos un Map para guardar los sessiones
	public static Map<String, HttpSession> map = new HashMap<String, HttpSession>();
	
	/**
     * Default constructor. 
     */
    public SessionListener() {
        LOG.trace("Control de session");
    }

	/**
	 * Cuando se crea una nueva sesion
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         // Al crear una session que se incremente la variable contador totalActiveSessions
    	totalActiveSessions++;
    	//Guardamos en el string la id
    	String id = se.getSession().getId();
    	LOG.debug("Session created: "+ id);
    	//Cargamos sesion en el map con el ID y la sesion
    	map.put(id, se.getSession());
    }

    /**
     * Me devuelve la sesion dandome la sesionId
     * @param sessionID Le damos una id de sesion
     * @return Nos devuelve la sesion que coincide con la key id sesion
     */
    public static HttpSession getHttpSession(String sessionID){
    	return map.get(sessionID);
    }
	/**
     * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
     */
    public void valueBound(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    }

	/**
	 * Si la sesion se destruye CON EXITO, se ejecuta este codigo
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	// Al destruiruna session que se decremente la variable contador totalActiveSessions
    	totalActiveSessions--;
    	//Creamos una lista de profesores vacia
    	List<Persona> personas = null;
    	//Recogemos la sesion
    	HttpSession session = se.getSession();
    	//Accedemos al contexto de la sesion
    	ServletContext ctx = session.getServletContext();
    	//Si no existe el atributo persona...
    	if(null != session.getAttribute(Constantes.SESSION_PERSONA)){
    		//Recogemos los atributos de la persona 
    		Persona persona = (Persona) session.getAttribute(Constantes.SESSION_PERSONA);
    		LOG.trace(persona.getNombre());
    		//recogemos la lista del contexto
    		personas = (List<Persona>)ctx.getAttribute(Constantes.ATT_LISTADO_USUARIOS);
    		//le quitamos la persona a la lista
    		personas.remove(persona);
    		//Actualizamos la lista de usuarios(sesiones) en el contexto
    		ctx.setAttribute(Constantes.ATT_LISTADO_USUARIOS, personas);
    	}
    	String sessionId = se.getSession().getId();
    	map.remove(sessionId);
    	LOG.trace(sessionId + "Eliminada");
    }

	/**
     * @see HttpSessionActivationListener#sessionDidActivate(HttpSessionEvent)
     */
    public void sessionDidActivate(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
	 * Cuando se añaden atributos a la sesion
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
    	//el atributo de la sesion es diferente de nula Y el nombre de la session del atributo es igual que el de la constante...
    	if(session.getAttribute(Constantes.SESSION_PERSONA)!= null && se.getName().equals(Constantes.SESSION_PERSONA)){
    		LOG.trace("Usuario registrado");
    		//Obtenemos el atributo de la persona y lo guardamos en p
    		Persona p = (Persona)session.getAttribute(Constantes.SESSION_PERSONA);
    		//Añadimos la persona a la lista
    		personas.add(p);
    		//Añadimos la lista como atributos a el contexto de la app
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
