package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class IdiomaServlet
 */
public class IdiomaServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(IdiomaServlet.class);
	private static final long serialVersionUID = 1L;
	private RequestDispatcher rd;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.trace("Metodo doGet de IdiomaServlet");
		//Recogere el parametro
		String strCodigo = request.getParameter(Constantes.PAR_IDIOMA);
		String mensaje = "";
		try{
			int codigo = Integer.parseInt(strCodigo);
			//fijare la cariable Locale como variable de session
			//Acceder a la session
			HttpSession session = request.getSession();
			switch(codigo){
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
					session.setAttribute(Constantes.SESSION_IDIOMA, "es_ES");
					
					break;
			}
		}catch(NumberFormatException e){
			e.printStackTrace();
			mensaje = "mensaje.error.codigo";
			LOG.error(e.getMessage());
		}
		//Fijare la variable Locale como variable de session
		
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		//Mandaremos de vuelta index
		rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.trace("Metodo doPost de IdiomaServlet");
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
