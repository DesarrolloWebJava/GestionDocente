package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//Nuestros importes:
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Usamos Request Dipatcher
	private RequestDispatcher rd;
	
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//Este servlet cuando se activa lee el atributo language de la sesion y 
		//aplica un resourceBundle sobre su url
		
		
		//creamos la variable locale
		Locale locale = new Locale("es_ES");
		
		// (Contra lo normal, que es coger paras) Coge el Atributo language y lo guarda.
		String language = (String) request.getSession(true).getAttribute("language");
		
		// si tiene valor, lo guarda como locale
		if (language != null) {
			locale = new Locale(language);
		}
		
		//se cra un ResourceBundle:  each ResourceBundle is a 
		//set of related subclasses that share the same base name.
		ResourceBundle messages = null;
		
		//se le asignan los i18 al rb
		try {
			messages = ResourceBundle.getBundle("com.ipartek.formacion.controller.i18nmessages", locale);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//redirigimos a home
		rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		rd.forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//Este servlet para efectuar su actividad recoge 2 parametros:
		// username
		// password
		String username = request.getParameter(Constantes.PAR_USUARIO);
		String password = request.getParameter(Constantes.PAR_PASSWORD);
		final String user = "admin";
		final String pass = "pass";
		
		//Y los compara con valores prefijados
		if (user.equals(username) && pass.equals(password)) {//
	
		/////// Si coinciden: crea sesion nueva y recoge otro parametro:
		//idioma
		// crearemos la session
			HttpSession session = request.getSession(true);
			// true ---> forzas que te cree una session nueva.
			// falso --> recoges una existente, si no hay devuelve nulo.
			// sin parametros --> si no existe te crea una nueva.
			
			// le fijamos duración
			session.setMaxInactiveInterval(60 * 15);
			// cargaremos la variable de idioma
			String lang = request.getParameter(Constantes.PAR_IDIOMA);
		//y le pasa un objeto locale a la session
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
			session.setAttribute(Constantes.SESSION_IDIOMA, locale);
		//redirige a home con sesion
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);
			
		//////// Si nocoinciden, redirige a home
		} else {
			// mensaje de error
			String mensaje = "Usuario y/o constraseña incorrectos";
			// le redireccionaremos a index.jsp
			request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		}
		//redireccion final
		rd.forward(request, response);
	}

}