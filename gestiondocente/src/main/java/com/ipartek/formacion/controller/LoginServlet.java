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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Locale locale = new Locale("es_ES");
//		//Si al recoger la session se le pasa true o nada, si no existe la session, me la crea
//		//si se le pasa false, obtiene la ya existente.
//		String language = (String)request.getSession(true).getAttribute("language");
//		
//		if(language!=null){
//			locale = new Locale(language);
//		}
//		ResourceBundle messages=null;
//		try{
//			messages = ResourceBundle.getBundle("com.ipartek.formacion.controller.i18nmessages", locale );
//		}catch (Exception e){
//			System.out.println(e.getMessage());			
//		}
//		rd=request.getRequestDispatcher(Constantes.JSP_HOME);
//		rd.forward(request, response);
		cerrarSession(request);
		response.sendRedirect(Constantes.JSP_HOME);
		return;
	}
	
	private void cerrarSession(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		
		if (session!=null){
			session.invalidate();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username=request.getParameter(Constantes.PAR_USUARIO);
		String password=request.getParameter(Constantes.PAR_PASSWORD);
		
		
		final String user = "admin";
		final String pass = "pass";
		final String user2 = "admin2";
		final String pass2="pass2";
		
		if((user.equals(username)&&pass.equals(password))||(user2.equals(username)&&pass2.equals(password))){
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
			Persona p = new Persona();
			try {
				p.setNombre(username);
				p.setApellidos("anónimo");
				session.setAttribute(Constantes.SESSION_PERSONA, p);
			} catch (PersonaException e) {
				LOG.error(e.getMessage());
			}
			session.setAttribute(Constantes.SESSION_IDIOMA, locale);
			
			//Redireccionaremos a una página
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);
			
		}
		else{
			//Mensaje de error
			String mensaje = "Usuario y/o contraseña incorrectos.";
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);
			request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
			
		}
		rd.forward(request, response);
	}

}
