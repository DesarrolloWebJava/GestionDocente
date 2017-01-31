package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.controller.listeners.SessionListerner;
import com.ipartek.formacion.dbms.pojo.Persona;

/**
 * Servlet implementation class UsuarioServlet
 */
public class UsuarioServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(UsuarioServlet.class);
	private static final long serialVersionUID = 1L;
    private RequestDispatcher rd;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionId = request.getParameter("sessionid");
		if(sessionId == null){
			cargarListaUsuarios(request);	
		} else {
			try{
				HttpSession session = SessionListerner.getHttpSession(sessionId);
				session.invalidate();
				cargarListaUsuarios(request);	
				LOG.trace(Constantes.JSP_LISTADO_USUARIOS);
			}catch(Exception e){
				LOG.error(e.getMessage());
			}
		}
		rd.forward(request, response);
	}

	private void cargarListaUsuarios(HttpServletRequest request) {
		try{
			cargarListadoUsuarios(request);
		}catch(Exception e){
			LOG.equals(e.getMessage());
			 request.setAttribute(Constantes.ATT_MENSAJE, "No se puede acceder a la información en este momento");
			 rd = request.getRequestDispatcher(Constantes.JSP_HOME);
			 
		}
	}

	private void cargarListadoUsuarios(HttpServletRequest request) {
		
				List<Persona> usuarios = null;
			
				HttpSession session = request.getSession();
				ServletContext ctx = session.getServletContext();
			
				usuarios = (List<Persona>)ctx.getAttribute(Constantes.CTX_LISTADO_USUARIOS); 
			
				rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);
				request.setAttribute(Constantes.ATT_LISTADO_USUARIOS, usuarios);
			
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
