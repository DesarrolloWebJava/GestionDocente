package com.ipartek.formacion.controller;

import java.io.IOException;
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
	
	private static final Logger log = Logger.getLogger(LoginServlet.class);
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
		//si al recoger lasesion se le pasa true o nada, no existe la sesion. me la crea.
		// si se le pasa false obtiene la ya existente.
		String language = (String) request.getSession().getAttribute("language");
		
		if(language!=null){
			locale = new Locale(language);
		}
		ResourceBundle messages = null;
		try{
			messages = ResourceBundle.getBundle("com.ipartek.formacion.controller.i18nmesages", locale);
			}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		rd.forward(request, response);	
		
		//---------------  Cerrar sesion -------------------
		
		cerrarSession(request);
		response.sendRedirect(Constantes.JSP_HOME);
		return;
		
		}

	//--------------- METODO Cerrar sesion -------------------
	
	private void cerrarSession(HttpServletRequest request){
		
		HttpSession session = request.getSession(false);
		if(session != null){
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
		final String pass = "pass";
		
		if (user.equals(username) && pass.equals(password)) {//
			// crearemos la session
			// cargaremos la variable de idioma
			// le redireccionaremos a una página
			HttpSession session = request.getSession(true);
			// tres valores el constructor
			// true --> forzas que te cree una session nueva
			// falso ---> recoges una existente, si no hay devuelve nuloç
			// sin parametros --> si no existe te crea una nueva
			
			session.setMaxInactiveInterval(60*15); // duracion de la session
			
			//fijamos la variable de idioma. recogemos la constante de idiomas.
			String lang = request.getParameter(Constantes.PAR_IDIOMA);
			// la guardamos en una variable de session
			
			
			// -------------------- COOKIE ---------------------------
			
			String remenberme = request.getParameter("recuerdame"); // recoge el name del input del index.jsp
			
			// USERNAME
			
				//Como crear una cookie??
			Cookie c_username = new Cookie("cusername", username);
			if(remenberme != null) { // el check esta marcado (!=)
				c_username.setMaxAge(60*60*24); // un dia
			}else{ // el check no esta marcado (!=)
				c_username.setMaxAge(0);
			}
			response.addCookie(c_username);
			
			
			// PASSWORD
			Cookie c_password = new Cookie("cpassword", password);
			if(remenberme != null) { // el check esta marcado (!=)
				c_password.setMaxAge(60*60*24);
			}else{ // el check no esta marcado (!=)
				c_password.setMaxAge(0);
			}
			response.addCookie(c_password);
			
			
			// IDIOMA
			Cookie c_idioma = new Cookie("idioma", lang);
			if(remenberme != null) { // el check esta marcado (!=)
				c_idioma.setMaxAge(60*60*24);
			}else{ // el check no esta marcado (!=)
				c_idioma.setMaxAge(0);
			}
			response.addCookie(c_idioma);
			
			
			
			int idioma = Integer.parseInt(lang);
			String locale= "";
			
			switch(idioma) {
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
			
			Persona p = new Persona();
			try {
				p.setNombre(username);
				p.setApellidos("Anonimo");
				p.setSessionId(session.getId());
				session.setAttribute(Constantes.SESSION_PERSONA, p); // si no quiero guardar
			} catch (PersonaException e) {
				log.error(e.getMessage());	
			}
			
			session.setAttribute(Constantes.SESSION_IDIOMA, locale);
			// le redireccionaremos a una página			
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);
			
			
			
		} else {
			// mensaje de error
			String mensaje = "Usuario y/o constraseña incorrectos";
			// le redireccionaremos a una página
			request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		}
		rd.forward(request, response);	
	}
}
