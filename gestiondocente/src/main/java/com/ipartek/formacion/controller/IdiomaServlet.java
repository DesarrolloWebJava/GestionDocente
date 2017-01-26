package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class IdiomaServlet
 */
public class IdiomaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RequestDispatcher rd;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdiomaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recoger el parametro
		String strCodigo = request.getParameter(Constantes.PAR_IDIOMA);
		String mensaje = "";
		
		try{
			int codigo = Integer.parseInt(strCodigo);
			// fijare la variable Locale como variable de sesion
			//acceder a la session
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
		}catch (NumberFormatException e){
			e.printStackTrace();
			mensaje = "mensaje.error.codigo";
		}
		
		// fijare la variable "Locale" como variable de session.
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		
		// Mandarte de vuelta index
		
		rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
