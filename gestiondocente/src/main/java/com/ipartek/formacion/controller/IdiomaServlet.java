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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  // recoger el parametro
		String mensaje = "";
		String strCodigo = req.getParameter(Constantes.PAR_IDIOMA);
		try {
			int codigo = Integer.parseInt(strCodigo);
			// fijar la variable locale como variable de sesion
			// acceder a la sesion
			HttpSession session = req.getSession();
			switch(codigo){
			 case Constantes.IDIOMA_CASTELLANO:
				 session.setAttribute(Constantes.SESSION_IDIOMA,"es_ES");
			 break;
			 case Constantes.IDIOMA_EUSKERA:
				 session.setAttribute(Constantes.SESSION_IDIOMA,"eu_ES");
		     break;
			 case Constantes.IDIOMA_INGLES:
				 session.setAttribute(Constantes.SESSION_IDIOMA,"en_EN");
			 break;
			 default:
			 break;
			}
			
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
			mensaje = "mensaje.error.codigo";
		}
		
	 
		req.setAttribute(Constantes.ATT_MENSAJE, mensaje);
	  // mandar de vuelta index
		rd = req.getRequestDispatcher(Constantes.JSP_HOME);
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
