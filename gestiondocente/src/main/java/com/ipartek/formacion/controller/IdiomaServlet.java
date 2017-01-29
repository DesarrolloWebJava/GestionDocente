package com.ipartek.formacion.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import request dispatcher y httpsession
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class IdiomaServlet
 */
public class IdiomaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher rd;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//para funcionar: RECOGE EL PARAMETRO del idioma y lo castea a integer:
		// lo compara con los int asociados en constantes
		//y le PASA A SU JSP EL ATRIBUTO idioma
		//Tambien pasa un mensahe, y redirige a home
		String strCodigo = request.getParameter(Constantes.PAR_IDIOMA);
		String mensaje = "";
		try {
			int codigo = Integer.parseInt(strCodigo);
			// fijare la variable Locale como variable de session
			// acceder a la session
			HttpSession session = request.getSession();
			switch (codigo) {
				case Constantes.IDIOMA_CASTELLANO:
					session.setAttribute(Constantes.SESSION_IDIOMA, "es_ES");
					break;
				case Constantes.IDIOMA_EUSKERA:
					session.setAttribute(Constantes.SESSION_IDIOMA, "eu_ES");
					break;
				case Constantes.IDIOMA_INGLES:
					session.setAttribute(Constantes.SESSION_IDIOMA, "en_EN");
					break;
				default:
					break;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			mensaje = "mensaje.error.codigo";
		}
		// TRACEAMOS EL MENSAJE DE ERROR
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		// mandarte de vuelta index
		rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// El doPost no hace nada
		doGet(request, response);
	}

}
