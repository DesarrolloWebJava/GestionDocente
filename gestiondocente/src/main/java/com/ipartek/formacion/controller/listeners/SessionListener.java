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

import com.ipartek.formacion.controller.Constantes;
import com.ipartek.formacion.dbms.pojo.Persona;

import org.apache.log4j.Logger;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
public class SessionListener implements HttpSessionBindingListener, HttpSessionActivationListener, HttpSessionAttributeListener, HttpSessionListener {

		private static final Logger LOG = Logger.getLogger(SessionListener.class);
		private static int totalactiveSession =0;
    /**
     * Default constructor. 
     */
    public SessionListener() {
       LOG.trace("Creado el control  SessionListener");
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
    	 totalactiveSession++;
    }

	/**
     * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
     */
    public void valueBound(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionActivationListener#sessionDidActivate(HttpSessionEvent)
     */
    public void sessionDidActivate(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	totalactiveSession--;
    	List<Persona>personas=null;
    	HttpSession session = arg0.getSession();
    	ServletContext ctx= session.getServletContext();
    	
    	if(null!=session.getAttribute(Constantes.SESION_PERSONA)){
    		Persona per= (Persona)session.getAttribute(Constantes.SESION_PERSONA);
    		LOG.trace(per.getNombre());
    		personas=(List<Persona>)ctx.getAttribute("listadoUsuarios");
    		personas.remove(per);
    		ctx.setAttribute("listadoUsuarios", personas);
    	}
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
      HttpSession sesion =arg0.getSession();
      List <Persona> personas=null;
      ServletContext ctx=sesion.getServletContext();
      personas=(List<Persona>)ctx.getAttribute("listadoUsuarios");
      if(personas==null){
    	  personas= new ArrayList<Persona>();
      }
      if(sesion.getAttribute(Constantes.SESION_PERSONA)!=null
    		  && arg0.getName().equals(Constantes.SESION_PERSONA)){
    	LOG.trace("Usuario Registrado"); 
    	Persona per= (Persona) sesion.getAttribute(Constantes.SESION_PERSONA);
    	personas.add(per);
    	ctx.setAttribute("listadoUsuarios", personas);
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
        
    }

	public static int getTotalactiveSession() {
		return totalactiveSession;
	}
	
}
