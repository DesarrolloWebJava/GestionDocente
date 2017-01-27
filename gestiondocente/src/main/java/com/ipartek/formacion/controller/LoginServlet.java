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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Nuevo objeto Locale
		Locale locale = new Locale("es_ES");
		//Obtenemos el atributo lenguaje de la sesion
		//Si al recoger la sesion se le pasa true o nada, si no existe la sesion me la crea
		//Si es false obtiene la ya existente y si no hay casca
		String language = (String)request.getSession(true).getAttribute("language");
		if(language != null){
			locale = new Locale(language);
		}
		ResourceBundle messages = null;
		try{
			//Cargamos los datos
			messages = ResourceBundle.getBundle("com.ipartek.formacion.controller.i18nmesages", locale );
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recogemos los parametros
		String username = request.getParameter(Constantes.PAR_USUARIO);
		String password = request.getParameter(Constantes.PAR_PASSWORD);
		//valores hardcodeados
		final String user = "admin";
		final String pass = "admin";
		//Comprobamos 
		//Es mejor asi que al reves pporque password/username podria ser nulo dando nullPointerEception
		if(user.equals(username) && pass.equals(password)){
			//Crearemos la sesion. Es true porque queremos que la cree aunque exista que se sobreescriba
			//true           --> Fuerzas que te cree una session nueva
			//false          --> Recoges una existente, si no devuelve nulo
			//Sin parametros --> Si no hay existe crea un nueva
			HttpSession session = request.getSession(true);
			//Configuramos la duracion de inactividad de la session (60seg * 15) = 15min
			session.setMaxInactiveInterval(60 * 15);
			//Cargaremos la variable de idioma
			String lang = request.getParameter(Constantes.PAR_IDIOMA);
			int idioma = Integer.parseInt(lang);
			//El locale es es_ES etc..
			String locale = "";
			switch(idioma){
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
			//Guardamos la variable en una variable de sesion
			session.setAttribute(Constantes.SESSION_IDIOMA, locale);
			//Redireccionaremos a una pagina
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		}else{
			//Mostrar mensaje
			String mensaje = "Usuario y/o contraseña incorrectos";
			//Guardamos el mensaje como atributo
			request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
			//Redireccionaremos a index.jsp
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		}
		rd.forward(request, response);
	}

}
