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
		
		Locale locale = new Locale("es_ES");
		String language = (String) request.getSession(true).getAttribute("language");
		// getSession() si no se pone nada o se pone true sino existe la sesion me la crea
		// con false me devuelve la existente
		if ( language != null ){
			locale = new Locale(language);
		}
		ResourceBundle messages = null;
		
		try{
			messages = ResourceBundle.getBundle("com.ipartek.formacion.controller.i18nmessages",locale);
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
		String username = request.getParameter(Constantes.PAR_USUARIO);
		String password = request.getParameter(Constantes.PAR_PASSWORD);
		final String user = "admin";
		final String passwd = "admin";
		
		if(user.equals(username) && passwd.equals(password)){
			HttpSession session = request.getSession(true);
			session.setMaxInactiveInterval(60*15);
			
			String lang = request.getParameter(Constantes.PAR_IDIOMA);
			int idioma = Integer.parseInt(lang);
			String locale ="";
			switch (idioma) {
			case Constantes.IDIOMA_ES:
				locale = "es_ES";
				break;
			case Constantes.IDIOMA_EU:
				locale = "eu_EU";
				break;
			case Constantes.IDIOMA_EN:
				locale = "en_EN";	
				break;
			default:
				locale = "es_ES";
			}
			session.setAttribute(Constantes.SESSION_IDIOMA, locale);
			rd =  request.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
		}else{
			String mensaje = "Usuario y/o contraseña incorrecta";
			request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
			rd =  request.getRequestDispatcher(Constantes.JSP_HOME);
		}
		rd.forward(request, response);
	}

}
