/* Paquete donde se guardan los servlets que gestionan el trafico de la web. */
package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;


/**
 * @author Raúl de Roba 26/01/17 
 * 
 * <p>Clase Servlet LogiServet para la gestión del login.</p>
 * 
 */
public class LoginServlet extends HttpServlet {
	/* Constante de serialición. */
	private static final long serialVersionUID = 1L;
	/* Se declara una RequestDispatcher para redireccionar una url indicada. */
	RequestDispatcher rd;       
  

	/* Metodo a ejecutar al recibir una petición Get. */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			                                       throws ServletException, IOException {
		/* Se iniciliza el Locale por defecto a español. (Internacionalización).*/
		Locale locale = new Locale("es_ES");
		/* Se declara la variable donde se recoge el lenguaje de la sesión.
		 * Al recoger una sesión,si por parametro recibe falso devuelve nulo,
		 * por lo que pasamos true para crearla. */
		String language = (String) req.getSession(true).getAttribute("languaje");
		/* Se comrpueba si la sesión poseía lenguaje.*/
		if (language!=null){
			/* Se instancia el locale con el lenguaje de sesión.*/
			locale = new Locale(language);			
		}
		
		/* Se declara la variable donde contener el recurso de propiedades 
		 * que contiene la internacionalizacion */
		ResourceBundle messages = null;
		/* Se monta estructura para la captura de excepciones.*/
		try{
			// Se recoge el recurso de propiedades que contiene la internacionalizacion. 
			messages = ResourceBundle.getBundle(
					"com.ipartek.formacion.egunon.controller.i18nmessages", locale);
		/* Se captura la excepción.*/	
		}catch(Exception e){			
		
			
		}
		/* Sobre el RequestDispatcher se indica una url pra redireccionar 
		 * a la página principal.
		 * No es una redireccion limpia,con lo que envia parametros.
		 * En este caso request y response.*/
		rd = req.getRequestDispatcher(Constantes.JSP_HOME);
		/* Se redirecciona enviando por parametro los request y response 
		 * recibidos por parametro.*/
		rd.forward(req, resp);
	}	

	/* Metodo a ejecutar al recibir una petición Post. */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		/* Se definen las constantes del usuario administrador.*/
		final String usuarioAdmin = "admin";
		final String claveAdmin = "admin";		
		/* Se recogen los parametros del login (Usuario y Clave).*/
		String usuario = req.getParameter(Constantes.PAR_USUARIO);
		String password = req.getParameter(Constantes.PAR_PASSWORD);

		/* Se comprueba si está accediendo el administrador. */
		if (usuarioAdmin.equals(usuario) && claveAdmin.equals(password)){
			/* Se recoge la sesión.
			 * (Con true forzamos a crear una nueva sesión aunque ya existiera una.) 
			 * (Con falso devuelve la existente o nulo si no existe.)
			 * (Sin parametro,si no existe sesion la crea nueva.) */
			HttpSession session = req.getSession(true);
			/* Se establece la vida máxima inactiva de la sesion.
			 * (15 min. x 60 seg./min.)*/
			session.setMaxInactiveInterval(15 * 60);
			
			/* Se recoge el idioma del request.*/
			String idiomaStr = req.getParameter(Constantes.PAR_IDIOMA);
			/* Se guarda el idioma en la sesion.*/
			int idioma = Integer.parseInt(idiomaStr);
			/* Se declara la variable para recoger el locale.*/
			String locale = "";
			/* Se comprueba el idioma recogido.*/
			switch (idioma) {
				/* Se comprueba si el idioma es castellano.*/
				case Constantes.IDIOMA_CASTELLANO:
					/* Se asigna el locale de castellano.*/
					locale = "es_ES";				
					break;
				/* Se comprueba si el idioma es ingles.*/
				case Constantes.IDIOMA_INGLES:
					/* Se asigna el locale de ingles.*/
					locale = "en_EN";				
					break;
				/* Se comprueba si el idioma es euskera.*/
				case Constantes.IDIOMA_EUSKERA:
					/* Se asigna el locale de euskera.*/
					locale = "eu_ES";				
					break;
				/* Si se recibe cualquier otro valor se asigna castellano.*/
				default:
					/* Se asigna el locale de castellano.*/
					locale = "es_ES";
			}
			/* Se asigna el locale a la sesion.*/
			session.setAttribute(Constantes.SESION_IDIOMA,locale);
			/* Se redirecciona a la página principal.*/
			rd = req.getRequestDispatcher(Constantes.JSP_HOME);
		}else {
			// TODO
		}
		/* Se redirecciona a la Url indicada.*/
		rd.forward(req, res);	
	}
	
	

}

