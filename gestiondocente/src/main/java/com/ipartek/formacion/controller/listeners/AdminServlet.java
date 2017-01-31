package com.ipartek.formacion.controller.listeners;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.controller.Constantes;
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
		try {
			ServletContext ctx = request.getSession(false).getServletContext();
			List<Persona> personas = (List<Persona>) ctx.getAttribute(Constantes.CTX_LISTADO_USUARIOS);
			request.setAttribute(Constantes.ATT_LISTADO_USUARIOS, personas);
			rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_USARIOS);
			rd.forward(request, response);
		} catch (NullPointerException e) {
			LOG.equals(e.getMessage());
			request.setAttribute(Constantes.ATT_MENSAJE, "No se puede acceder a la información en este momento");
			rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_USARIOS);
			rd.forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
