/* Paquete donde se guardan los servlets que gestionan el trafico de la web.*/
package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.controller.filters.ServletFilter;
import com.ipartek.formacion.service.AlumnoService;


/**
 * @author Raúl de Roba 26/01/17 
 * 
 * <p>Clase Servlet IdiomaServet para la gestión de idiomas.</p>
 * 
 */
public class IdiomaServlet extends HttpServlet {
	
	/* Constante de serialición. */
	private static final long serialVersionUID = 1L;
	/* Se declara una RequestDispatcher para redireccionar una url indicada. */
	RequestDispatcher rd;
	
	/* Se recoge la instacia del log pasando como parametro la clase actual.*/
	private static final Logger LOG = Logger.getLogger(IdiomaServlet.class);	
	
	
	/* Metodo a ejecutar al recibir una petición Get. */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/* Se recoge el idioma del request.*/
		String strCodigo = req.getParameter(Constantes.PAR_IDIOMA);
		/* Se declara la variable para recoger el mensaje de error.*/
		String mensaje = "";
		/* Se monta estructura para la captura de excepciones.*/
		try{
			HttpSession session = req.getSession();
			/* Se declara una variable integer para castear el codigo del idioma y 
			 * gestionarlo con la switch. */
			int codigo = Integer.parseInt(strCodigo);
			/* Se comprueba el idioma.*/
			switch (codigo) {
			/* Si es castellano.*/	
			case Constantes.IDIOMA_CASTELLANO:
				/* Se asigna a la sesion el locale de castellano.*/
				session.setAttribute(Constantes.SESION_IDIOMA, "es_ES");
				break;
				/* Si es esukera.*/	
			case Constantes.IDIOMA_EUSKERA:
				/* Se asigna a la sesion el locale de euskera.*/
				session.setAttribute(Constantes.SESION_IDIOMA, "eu_ES");
				break;
				/* Si es ingles.*/	
			case Constantes.IDIOMA_INGLES:
				/* Se asigna a la sesion el locale de castellano.*/
				session.setAttribute(Constantes.SESION_IDIOMA, "en_EN");
				break;	
			}		
		/* Se captura la excepción.*/	
		}catch(NumberFormatException e){
			/* Se recoge el mensaje de error del fichero de recursos de idioma.*/
			mensaje = "mensaje.error.codigo";
		}
		
		
		/* Se asigna al request el mensaje de error.*/
		req.setAttribute(Constantes.ATT_MENSAJES, mensaje);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
