package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher rd;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Locale por defecto Español
		//Locale locale = new Locale("es_ES");
		//Si al recoger la session se le pasa true o nada si no existe la session 
		//me la crea
		//obtener lenguaje de la session del usuario
		/*String language = (String) request.getSession(true).getAttribute("language");

		if ( language != null ){
		locale = new Locale(language);
		}
		ResourceBundle messages=null;
		try {
			messages = ResourceBundle.getBundle("com.ipartek.formacion.controller.i18nmessages", locale );
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		rd.forward(request, response);*/
		cerrarSession(request);
		response.sendRedirect(Constantes.JSP_HOME);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter(Constantes.PAR_USUARIO);
		String password = request.getParameter(Constantes.PAR_PASSWORD);
		final String user = "admin";
		final String pass = "pass";
		if(user.equals(username)&&pass.equals(password)){
			//crearemos la session
			HttpSession session = request.getSession(true);
			// true ----> forzas que te cree una session nueva.
			// false----> recoges una existente, si no hay devuelve nulo.
			//sin parametro--> si no existe te crea una nueva
			
			//le fijamos duracion
			session.setMaxInactiveInterval(60*15);
			//cargaremos la variable de idioma
			String lang = request.getParameter(Constantes.PAR_IDIOMA);
			int idioma = Integer.parseInt(lang);
			String locale = "";
			switch (idioma) {
			case Constantes.IDIOMA_CASTELLANO:
				locale = "es_ES";
				break;
			case Constantes.IDIOMA_EUSKERA:
				locale = "eu_ES";
				break;
			case Constantes.IDIOMA_INGLES:
				locale = "en_EN";
				break;

			default:
				locale = "es_ES";
				break;
			}
			session.setAttribute(Constantes.SESSION_IDIOMA,locale);
			//le redireccionaremos a una pagina
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		} else{
			//mensaje de error
			String mensaje = "Usuario y/o contraseña incorrectos";
			//le redireccionaremos a index.jsp
			request.setAttribute(Constantes.ATT_MENSAJE,mensaje);
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);	
		}
		rd.forward(request, response);
	}
	
	private void cerrarSession(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session!=null){
			session.invalidate();
		}
	}

}
