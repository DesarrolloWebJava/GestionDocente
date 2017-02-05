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
	private static final Logger LOG = Logger.getLogger(LoginServlet.class);
	private static final long serialVersionUID = 1L;
    private RequestDispatcher rd;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.trace("Metodo doGet de LoginServlet");
		//Nuevo objeto Locale
//		Locale locale = new Locale("es_ES");
//		//Obtenemos el atributo lenguaje de la sesion
//		//Si al recoger la sesion se le pasa true o nada, si no existe la sesion me la crea
//		//Si es false obtiene la ya existente y si no hay casca
//		String language = (String)request.getSession(true).getAttribute("language");
//		if(language != null){
//			locale = new Locale(language);
//		}
//		ResourceBundle messages = null;
//		try{
//			//Cargamos los datos
//			messages = ResourceBundle.getBundle("com.ipartek.formacion.controller.i18nmesages", locale );
//		}catch(Exception e){
//			System.out.println(e.getMessage());
//		}
//		rd = request.getRequestDispatcher(Constantes.JSP_HOME);
//		rd.forward(request, response);
		//Llamamos al metodo cerrar sesion
		cerrarSesion(request);
		//Lo manda a la home una vez cerrada la sesion
		response.sendRedirect(Constantes.JSP_HOME);
		//Se pone para que no se quede colgado el sendRedirect()
		return;
	}
	
	/**
	 * Cierra la sesion
	 * @param request Recibe una request
	 */
	private void cerrarSesion(HttpServletRequest request){
		LOG.trace("Metodo cerrarSesion de LoginServlet");
		HttpSession session = request.getSession(false);
		if(session!=null){
			session.invalidate();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.trace("Metodo doPost de LoginServlet");
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
			//Recogemos la variable de recuerdame (el name del input)
			String rememberme = request.getParameter(Constantes.PAR_RECUERDAME);
			//Creamos la cookie(clave, valor)
			Cookie c_username = new Cookie("username", username);
			Cookie c_passname = new Cookie("passname", password);
			Cookie c_language = new Cookie("leng", locale);
			Cookie c_lang = new Cookie("lang", lang);
			//Si el check esta marcado...
			if(rememberme!=null){
				//tiempo de expiracion (60seg * 60 min * 24h = 1 dia)
				c_username.setMaxAge(60*60*24);
				c_passname.setMaxAge(60*60*24);
				c_language.setMaxAge(60*60*24);
				c_lang.setMaxAge(60*60*24);
			}else{ //El check no esta marcado...
				//tiempo de expiracion 0 seg.
				c_username.setMaxAge(0);
				c_passname.setMaxAge(0);
				c_language.setMaxAge(0);
				c_lang.setMaxAge(0);
			}
			//Le manda una cookie en la respuesta
			response.addCookie(c_username);
			response.addCookie(c_passname);
			response.addCookie(c_language);
			response.addCookie(c_lang);
			
			//Creamos una persona para cargar los datos
			Persona p = new Persona();
			try{
				//Cargamos el nombre y apellido (de momento mal)
				p.setNombre(username);
				p.setApellidos("Anonimo");
				//Guardamos la id de sesion en la persona	
				p.setSessionID(session.getId());
				//Guardamos la variable en una variable de sesion
				session.setAttribute(Constantes.SESSION_PERSONA, p);
			}catch(PersonaException e){
				LOG.error(e.getMessage());
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
