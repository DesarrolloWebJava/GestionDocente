/* Paquete donde se guardan los servlets que gestionan eventos de la web. */
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
    		/* Se instancia la lista. */
    		personas = new ArrayList<Persona>();
    		
    		
    	}
    	/* Se comprueba que la sesión tenga persona.*/
    	if(session.getAttribute(Constantes.SESION_PERSONA) != null){
    		/* Se traza que la persona existe.*/
    		LOG.trace("Usuario registrado.");
    		/* Se castea la persona de la sesión. */
    		Persona persona = 
    				         (Persona) session.getAttribute(Constantes.SESION_PERSONA);
    		/* Se añade la persona a la ista.*/
    		personas.add(persona);
    		
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
