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
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Locale locale = new Locale("es_ES");
		// si al recoger la sesion se le pasa true o nada , automaticamente si
		// no existe la sesion , crea na nueva sesion.
		// SI se le pasa false obtien ya la existente
		String language = (String) request.getSession().getAttribute("language");

		if (language != null) {

			locale = new Locale(language);
		}

		ResourceBundle messages = null;
		try {
			messages = ResourceBundle.getBundle("com.ipartek.formacion.controller.i18nmessages", locale);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter(Constantes.PAR_USUARIO);
		String password = request.getParameter(Constantes.PAR_PASSWORD);
		final String user = "admin";
		final String passw = "passw";

		if (user.equals(username) && passw.equals(password)) {// crearemos la
																// sesion,
			// cargaremos variable idioma, redireccionaremos a una pagina

			HttpSession session = request.getSession(true);
			// le fijamos tiempo
			session.setMaxInactiveInterval(60 * 15);
			// cargamos la variable
			String lenguaje = request.getParameter(Constantes.PAR_IDIOMA);
			int idioma = Integer.parseInt(lenguaje);
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
			// redireccion
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);

		} else {// mostrar un mensaje, redireccionaremos a una pagina

			String mensaje = "Usuario y/o contraseña incorrecta";
			request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);

		}
		rd.forward(request, response);

	}

}
