/* Paquete donde se guardan los servlets que gestionan el trafico de la web. */
package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
		try{
			// Se recoge el recurso de propiedades que contiene la internacionalizacion. 
			messages = ResourceBundle.getBundle(
					"com.ipartek.formacion.egunon.controller.i18nmessages", locale);
		}catch(Exception e){			
		
			
		}
		/* Sobre el RequestDispatcher se indica una url pra redireccionar 
		 * a la página principal.
		 * No es una redireccion limpia,con lo que envia parametros.
		 * En este caso request y response.*/
		rd = req.getRequestDispatcher(Constantes.JSP_HOME);
		/* Se redirecciona enviando por parametro los request y response 
		 * recibidos por parametro.*/
		rd.forward(req, res);
	}	

	/* Metodo a ejecutar al recibir una petición Post. */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
