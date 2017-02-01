package com.ipartek.formacion.controller;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.pojo.Persona;
import com.ipartek.formacion.dbms.pojo.exceptions.PersonaException;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(LoginServlet.class);
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
		
		/*
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
		*/
		cerrarSession(request);
		response.sendRedirect(Constantes.JSP_HOME);
		return;
		
	}
	private void cerrarSession(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if (session != null){
				session.invalidate();
		}
		
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter(Constantes.PAR_USUARIO);
		String password = request.getParameter(Constantes.PAR_PASSWORD);
		final String user = "admin";
		final String passwd = "admin";
		final String user2 = "pepe";
		final String passwd2 = "pepe";
		
		if((user.equals(username) && passwd.equals(password))||(user2.equals(username) && passwd2.equals(password))){
			HttpSession session = request.getSession(true);
			session.setMaxInactiveInterval(60*15);
			
			String lang = request.getParameter(Constantes.PAR_IDIOMA);
			String rememberme = request.getParameter("recuerdame");
			
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
			
			Cookie c_username = new Cookie("username", username);
			Cookie c_password = new Cookie("password", password);
			Cookie c_language = new Cookie("language", locale);
			
			
			if (rememberme != null){
				
				c_username.setMaxAge(60 * 60 * 24);
				c_password.setMaxAge(60 * 60 * 24);
				c_language.setMaxAge(60 * 60 * 24);
				
			}else{
				
				c_username.setMaxAge(0);
				c_password.setMaxAge(0);
				c_language.setMaxAge(0);
			}
		
			response.addCookie(c_username);
			response.addCookie(c_password);
			response.addCookie(c_language);
			
			
			Persona p = new Persona();
			try {
				p.setNombre(username);
				p.setApellidos("anonimo");
				p.setId(session.getId());
				session.setAttribute(Constantes.SESSION_PERSONA, p);
			} catch (PersonaException e) {
				LOG.error(e.getMessage());
			}
			session.setAttribute(Constantes.SESSION_IDIOMA, locale);
			rd =  request.getRequestDispatcher(Constantes.JSP_HOME);
		}else{
			String mensaje = "Usuario y/o contraseña incorrecta";
			request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
			rd =  request.getRequestDispatcher(Constantes.JSP_HOME);
		}
		rd.forward(request, response);
	}

}
