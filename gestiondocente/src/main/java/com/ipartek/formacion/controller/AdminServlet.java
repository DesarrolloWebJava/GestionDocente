package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//non default imports
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.apache.log4j.Logger;

import com.ipartek.formacion.controller.listeners.SessionListener;
import com.ipartek.formacion.dbms.pojo.Persona;
import com.ipartek.formacion.dbms.pojo.exceptions.PersonaException;

/**
 * Servlet implementation class UsuarioServlet
 */
public class AdminServlet extends HttpServlet {
	
	private static final Logger LOG = Logger.getLogger(AdminServlet.class);
	private static final long serialVersionUID = 1L;
	private RequestDispatcher rd;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String sessionId = request.getParameter("sessionId");
		if (sessionId == null){
			try {
				HttpSession session = request.getSession(false);
				ServletContext ctx = session.getServletContext();
				List<Persona> usuarios = (List<Persona>) ctx.getAttribute(Constantes.CTX_LISTADO_USUARIOS);
				request.setAttribute(Constantes.ATT_LISTADO_USUARIOS, usuarios);
				rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);
				rd.forward(request, response);
			} catch (NullPointerException e) {
					LOG.equals(e.getMessage());
					request.setAttribute(Constantes.ATT_MENSAJE, "No se puede acceder a la información.");
					rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);
					rd.forward(request, response);
				}
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
}
