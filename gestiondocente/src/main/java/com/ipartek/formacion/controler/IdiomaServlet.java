package com.ipartek.formacion.controler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.service.AlumnoService;

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
		//recogere le parametro de que idoma quiero
		String strCodigo= request.getParameter(Constantes.PAR_IDIOMA);
		String mensaje ="";
		try{
			int codigo =Integer.parseInt(strCodigo);
			//acceder a la variable session, asi creo una session. no se pasa a la request
			HttpSession session=request.getSession();
			
			
			switch(codigo){
				case Constantes.IDIOMA_CASTELLANO:
					//fijare la variable Locale como variable de session
					session.setAttribute(Constantes.SESSION_IDIOMA, "es_ES");
					break;
				case Constantes.IDIOMA_EUSKERA:
					//fijare la variable Locale como variable de session
					session.setAttribute(Constantes.SESSION_IDIOMA, "eu_ES");
					break;
				case Constantes.IDIOMA_INGLES:
					//fijare la variable Locale como variable de session
					session.setAttribute(Constantes.SESSION_IDIOMA, "en_EN");
					break;
				default:
					break;
				
				}
					
			}catch(NumberFormatException e){
				e.printStackTrace();
				mensaje="mensaje.error.codigo";
				request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
			
			}
		
		
		//mandarle de vuelta index
		rd =request.getRequestDispatcher(Constantes.JSP_HOME);
		
		//hace la redireccion
		rd.forward(request,response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
