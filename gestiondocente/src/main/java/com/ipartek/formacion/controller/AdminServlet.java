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

import com.ipartek.formacion.controller.listeners.SessionListener;
import com.ipartek.formacion.dbms.pojo.Persona;

/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
	private final Logger LOG = Logger.getLogger(AdminServlet.class);
	private static final long serialVersionUID = 1L;
	private RequestDispatcher rd = null;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sessionId = request.getParameter("sessionId");
		if(sessionId==null){//se procesa visualizar todos los usuarios
			try {
				HttpSession session = request.getSession(false);
				ServletContext ctx = session.getServletContext();
				List<Persona> personas = (List<Persona>) ctx.getAttribute(Constantes.CTX_LISTADO_USUARIOS);
				request.setAttribute(Constantes.ATT_LISTADO_USUARIOS, personas);
				rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);
			} catch (NullPointerException e) {
				LOG.equals(e.getMessage());
				request.setAttribute(Constantes.ATT_MENSAJE, "No se puede acceder a la información en este momento");
				rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);
	
			}
		
		}else{// se procesa expulsar a un usuario
			try {
				HttpSession session = SessionListener.getHttpSession(sessionId);
				session.invalidate();
				rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
			} catch (NullPointerException e) {
				LOG.error(e.getMessage());
			}
		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}