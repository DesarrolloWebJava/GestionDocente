package com.ipartek.formacion.controler.listeners;

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

import com.ipartek.formacion.controler.Constantes;
import com.ipartek.formacion.dbms.pojo.Persona;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener, HttpSessionActivationListener, HttpSessionBindingListener {
	//primero es el LOG
	private static final Logger LOG = Logger.getLogger(SessionListener.class);
	//vamos a contar el numero de personas activas
	private static  int totalActiveSession = 0;
	
    /**
     * Default constructor. 
     */
    public SessionListener() {
        // TODO Auto-generated constructor stub
    	LOG.trace("Control de session");
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         //al crear una sesion incremetaremos la variable
    	totalActiveSession++;
    	
    }

	/**
     * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
     */
    public void valueBound(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    }

	/**
	 * gestionamos las bajas de la lista de usuarios(listasUsuarios)
	 * 
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	
    	totalActiveSession--;
    	//Acceder a la lista de personas
    	List<Persona> personas =null;
    	//REcogemos la sesion y Acedemos al contexto(ctx)
    	HttpSession session =se.getSession();
    	ServletContext ctx =session.getServletContext();
    	//si tenemos el atributo(pq hay sesiones anomimas)
    	if(null != session.getAttribute(Constantes.SESSION_PERSONA)){
    		Persona p = (Persona) session.getAttribute(Constantes.SESSION_PERSONA);
    		LOG.trace(p.getNombre());
    		personas = (List<Persona>)ctx.getAttribute("listdoUsuario");
    		personas.remove(p);
    		//hay q grabar la lista actualizada
    		ctx.setAttribute("listadoUsuario", personas);
    		
    	}
    	
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
         /*puedo acceder a la sesion*/
    	HttpSession session =se.getSession();
    	//listado de personas
    	List<Persona> personas =null;
    	//cargamos el contexto de la aplicacion
    	ServletContext ctx =session.getServletContext();
    	//TODO carga la lista del personas del contexto de la app
    	personas= (List<Persona>)ctx.getAttribute("listdoUsuario");
    	//comprobar que el atributo no sea nulo
    	if(personas == null){
    		//creamos el objeto
    		//si mñn queremso poner otra cosa q no sea arrayList, solo tendremos q cambianla aqui, es un interfaz.es como en 
    		//AlumnosServiceImp
    		personas = new ArrayList<Persona>();
    	}
    	if(session.getAttribute(Constantes.SESSION_PERSONA) != null){
    		LOG.trace("usuario registrado");
    		Persona p = (Persona) session.getAttribute(Constantes.SESSION_PERSONA);
    		personas.add(p);
    		//guardar atributo en contexto
    		ctx.setAttribute("listadoUsuario", personas);
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
