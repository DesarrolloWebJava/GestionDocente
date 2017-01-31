package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.controller.listeners.SessionListener;
import com.ipartek.formacion.dbms.pojo.Persona;

/**
 * Servlet implementation class UsuarioConectadoServlet
 */
public class UsuarioConectadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(UsuarioConectadoServlet.class);
	public RequestDispatcher rd=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioConectadoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	List<Persona> personas = null;
    	String sessionId = request.getParameter(Constantes.PAR_SESSION);
    	if (sessionId != null){
    		try{
    			HttpSession session = SessionListener.getHttpSession(sessionId);
    			session.invalidate();
    			rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);
    			request.setAttribute(Constantes.PAR_SESSION,null);
    	 		}catch(Exception e){
    			LOG.error(e.getMessage());
    			request.setAttribute(Constantes.ATT_MENSAJE, "No se puede acceder a la información en este momento");
    			rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);
    			
    		}
    		
    	}
    	else // Se procesa visualizar todo los usuarios conectados
    	{
		try {
			ServletContext ctx = request.getSession(false).getServletContext();
			personas = (List<Persona>) ctx.getAttribute(Constantes.CTX_LISTADO_USUARIOS);
			request.setAttribute(Constantes.ATT_LISTADO_USUARIOS, personas);
			rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);
		
		  } catch (Exception e) {
			LOG.error(e.getMessage());
			request.setAttribute(Constantes.ATT_MENSAJE, "No se puede acceder a la información en este momento");
			rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);
			

		  }
    	}
     rd.forward(request, response);	
	}
}
