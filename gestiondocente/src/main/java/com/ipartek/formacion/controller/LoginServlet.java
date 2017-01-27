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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Locale locale = new Locale("es_ES");
		//Si al recoger la session se le pasa true o nada, si no existe la session, me la crea
		//si se le pasa false, obtiene la ya existente.
		String language = (String)request.getSession(true).getAttribute("language");
		
		if(language!=null){
			locale = new Locale(language);
		}
		ResourceBundle messages=null;
		try{
			messages = ResourceBundle.getBundle("com.ipartek.formacion.controller.i18nmessages", locale );
		}catch (Exception e){
			System.out.println(e.getMessage());			
		}
		rd=request.getRequestDispatcher(Constantes.JSP_HOME);
		rd.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username=request.getParameter(Constantes.PAR_USUARIO);
		String password=request.getParameter(Constantes.PAR_PASSWORD);
		System.out.println(username);
		System.out.println(password);
		
		final String user = "admin";
		final String pass = "pass";
		
		if(user.equals(username)&&pass.equals(password)){
			//Crearemos la session
			HttpSession session = request.getSession(true);
			//true =session nueva
			//false = recoges una existente, si no hay, devuelve nulo
			//sin parametros= si no hay, te crea una nueva
			
			session.setMaxInactiveInterval(60*15);//fijamos la duracion
			//Cargaremos la variable de idioma
			String lang = request.getParameter(Constantes.PAR_IDIOMA);
			int idioma = Integer.parseInt(lang);
			String locale="";
			switch (idioma){
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
			//Redireccionaremos a una página
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);
			
		}
		else{
			//Mensaje de error
			String mensaje = "Usuario y/o contraseña incorrectos.";
			request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
			//Redireccionaremos a index.jsp		
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		}
		rd.forward(request, response);
	}

}
