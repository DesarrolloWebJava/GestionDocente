package com.ipartek.formacion.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
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
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(LoginServlet.class);
	private RequestDispatcher rd;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Locale locale = new Locale("es_ES");
		//Obtiene el atributo lenguaje de la sesion
		//Si al recoger la sesion se le pasa true o nada, si no existe la sesion, se crea
		//Si es false, obtiene la ya existente y si no existe una, peta
		String language = (String)request.getSession(true).getAttribute("language");
		if (language != null){
			locale = new Locale(language);
		}
		ResourceBundle messages = null;
		try {
			messages = ResourceBundle.getBundle("com.ipartek.formacion.controller.i18nmessages", locale);
		} catch (Exception e) {
			System.out.println("Error :" + e.getMessage());
		}
		rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		rd.forward(request, response);*/
		cerrarSesion(request); 
		response.sendRedirect(Constantes.JSP_HOME);
		return;
	}

	private void cerrarSesion(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if (session != null){
			session.invalidate();
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String user = "admin";
		final String pass = "admin";
		String username = request.getParameter(Constantes.PAR_USUARIO);
		String password = request.getParameter(Constantes.PAR_PASSWORD);
		
		// Las condiciones van en orden, user y luego pass
		// Se compara primero el user con los datos introducidos ya que el valor de user nunca es nulo 
		if (user.equals(username) && pass.equals(password)) {
			// Crearemos la sesion, puede tener 3 valores, 
			// true fuerza una sesion nueva
			// false recoges una existente
			// sin parametros crea una nueva
			HttpSession session = request.getSession(true);
			// Fijamos la duracion de una sesion inactiva
			session.setMaxInactiveInterval(60*10);
			// Cargamos la variable idioma
			// La guardamos en una variable de sesion
			String lang = request.getParameter(Constantes.PAR_IDIOMA);
			int idioma = Integer.parseInt(lang);
			String locale ="";
			switch (idioma) {
			case Constantes.IDIOMA_CASTELLANO:
				locale ="es_ES";
				break;
			case Constantes.IDIOMA_EUSKERA:
				locale ="eu_ES";
				break;
			case Constantes.IDIOMA_INGLES:
				locale ="en_EN";
				break;
			default:
				locale ="es_ES";
				break;
			}
			Persona p = new Persona();
			try {
				p.setNombre(username);
				p.setApellidos("Anonimo");
				// Con esto obtenemos la ID de la sesion del usuario y la almacenamos en el atributo
				p.setSessionID(session.getId());
				session.setAttribute(Constantes.SESSION_PERSONA, p);
				LOG.trace(" Usuario " + username + " trata de iniciar sesion");
			} catch (PersonaException e) {
				LOG.error(e.getMessage());
			}
			session.setAttribute(Constantes.SESSION_IDIOMA, locale);
			// Redireccionamos a una pagina
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);
			
			
		} else {
			// Mostramos un mensaje indicando que los datos introducidos no son correctos
			String mensajeError = "Usuario y/o contraseña incorrectos";
			// Redireccionamos a index.jsp
			request.setAttribute(Constantes.ATT_MENSAJE, mensajeError);
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		}
		rd.forward(request, response);
	}

}
