/* Paquete donde se guardan los servlets que gestionan eventos de la web. */
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
* @author Raúl de Roba 30/01/17 
*
* <p>Clase SessionListener que gestiona los eventos de sesion.</p>
* 
*/
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener, HttpSessionActivationListener, HttpSessionBindingListener {

	/* Se recoge la instacia del log pasando como parametro la clase actual.*/
	private static final Logger LOG = Logger.getLogger(SessionListener.class);
	/* Contador de sesiones activas.*/
	private static int sesionesActivas = 0;
	/* Se crea el mapa donde guardar todas las sesiones activas.*/
	private static Map<String, HttpSession> mapaSesiones =  new HashMap<String, HttpSession>();
	
    /**
     * Default constructor. 
     */
    public SessionListener() {
    	/* Se muestra traza de la creación dela clase. */
    	LOG.trace(this.getClass().getName());
    }

	/* Metodo que se ejecuta al crear la sesión. */
    public void sessionCreated(HttpSessionEvent se)  { 
    	/* Se incrementa el numero de sesiones.*/
    	sesionesActivas++;
    	/* Se recoge el id de sesion.*/
    	String sesionId = se.getSession().getId();
    	/* Se guarda la sesión en el mapa con la id de sesión como clave.*/
    	mapaSesiones.put(sesionId,se.getSession());
    }

	/**
     * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
     */
    public void valueBound(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    }

	/* Metodo que se ejecuta al destruir la sesión. */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	/* Se decrementa el numero de sesiones.*/
    	sesionesActivas--;
    	/* Se declara la lsita de personas. */
    	List<Persona> personas = null;
    	/* Se recoge la sesión.*/
    	HttpSession sesion = se.getSession();
    	/* Se recoge la el contexto de la sesión (Contexto de Aplicación).*/
    	ServletContext ctx = sesion.getServletContext();
    	
    	/* Se comprueba  que la sesión contenega una persona y 
    	 * no sea una sesión anonima.*/
    	if (null != sesion.getAttribute(Constantes.SESION_PERSONA)){
    		/* Se recoge la persona. */
    		Persona persona = 
    				          (Persona) sesion.getAttribute(Constantes.SESION_PERSONA);
    		/* Registramos una traza del borrado.*/
    		LOG.trace(persona.getNombre() + " Borrado de la Sesión.");
    		/* Se recoge la lista de la sesión.*/
    		personas = (List<Persona>) ctx.getAttribute(Constantes.CTX_LISTADO_USUARIOS);
    		/* Se elimina la persona de la lista. */
    		personas.remove(persona);
    		/* Se devuelve la lsita al contexto de la aplicación.*/
    		ctx.setAttribute(Constantes.CTX_LISTADO_USUARIOS, personas);
    	}
    	/* Se reoge el ide de la sesión actual.*/
    	String sessionId = se.getSession().getId();
    	/* Se elimina la sesión del mapa de sesiones.*/
    	mapaSesiones.remove(sessionId);
    	/* Se guarda la traza de la sesión eliminada.*/
		LOG.trace(sessionId + " Eliminada");
    	
    }
    
    /* Metodo que devuelve la sesión*/
    public static HttpSession getHttpSession(String sessionID) {
    	/* Se devuelve la sesión recogida del mapa de sesiones.*/
		return mapaSesiones.get(sessionID);
	}
	/**
     * @see HttpSessionActivationListener#sessionDidActivate(HttpSessionEvent)
     */
    public void sessionDidActivate(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }

	/* Metodo que se ejecuta al asignar un atributo. */
    public void attributeAdded(HttpSessionBindingEvent se)  { 
    	/* Se recoge la sesión.*/
    	HttpSession session  = se.getSession();
    	/* Se declara la lsita donde recoger las personas activas.*/
    	List<Persona> personas = null;
    	/* Se declara el contexto de la aplicación y se recoge de la sesion. */
    	ServletContext ctx = session.getServletContext();
    	/* Se comprueba si está creada la lista de persona.*/
    	if (personas == null){
    		/* Se instancia la lista.*/
    		//personas = new ArrayList<Persona>();    		
    		personas = new ArrayList<Persona>();    		
    	}
    	/* Se comprueba que la sesión tenga persona y 
    	 * que el nombre del atributo sea persona.*/
    	if(session.getAttribute(Constantes.SESION_PERSONA) != null
    	   && se.getName().equals(Constantes.SESION_PERSONA)){
    		/* Se traza que la persona existe.*/
    		LOG.trace("Usuario registrado.");
    		/* Se castea la persona de la sesión. */
    		Persona persona = 
    				         (Persona) session.getAttribute(Constantes.SESION_PERSONA);
    		/* Se añade la persona a la ista.*/
    		personas.add(persona);

    		/* Se asigna la lista a la sesión.*/
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

	/**
	 * @return sesionesActivas 
	 * Se devuelve el atributo sesionesActivas. 
	 */
	public static int getSesionesActivas() {
		/* Se devuelve el atributo sesionesActivas.*/
		return sesionesActivas;
	}
	
}
