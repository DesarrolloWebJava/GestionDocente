package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class UsuariosActivosServlet
 */
public class UsuariosActivosServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(LoginServlet.class);
	private static final long serialVersionUID = 1L;
	private RequestDispatcher rd;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuariosActivosServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sessionID = request.getParameter("sessionID");
		if (sessionID == null) {
			try {
				ServletContext context = request.getSession().getServletContext();
				List<Persona> personas = null;
				personas = (List<Persona>) context.getAttribute(Constantes.CTX_LISTADO_USUARIOS);

				if (personas == null) {

					personas = new ArrayList<Persona>();
				}

				request.setAttribute(Constantes.ATT_LISTADO_USUARIOS, personas);
				rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);
				LOG.trace("Listado usuarios cargado");

				rd.forward(request, response);

			} catch (NullPointerException e) {

				rd = request.getRequestDispatcher(Constantes.JSP_HOME);

				LOG.error(e.getMessage());

				rd.forward(request, response);
			}
		} else {
			try {
				HttpSession session = SessionListener.getHttpSession(sessionID);
				session.invalidate();
				rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);
				rd.forward(request, response);
			} catch (NullPointerException e) {

				LOG.error(e.getMessage());

			}
		}
		// cerrarSesionUsuario(request);

	}

	// private void cerrarSesionUsuario(HttpServletRequest request) {

	// Map<String, Persona> personas = new TreeMap<String, Persona>();

	// }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
