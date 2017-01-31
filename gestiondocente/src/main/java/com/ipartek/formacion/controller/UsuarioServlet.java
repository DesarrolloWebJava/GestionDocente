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

import com.ipartek.formacion.dbms.pojo.Persona;
import com.ipartek.formacion.service.AlumnoService;

/**
 * Servlet implementation class UsuarioServlet
 */
public class UsuarioServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(UsuarioServlet.class);
	private static final long serialVersionUID = 1L;
	private AlumnoService uS;  
    private RequestDispatcher rd;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			cargarListadoUsuarios(request);
		}catch(Exception e){
			LOG.error(e.getMessage());
			response.sendRedirect(Constantes.JSP_HOME);
			return;
		}	
		rd.forward(request, response);
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
