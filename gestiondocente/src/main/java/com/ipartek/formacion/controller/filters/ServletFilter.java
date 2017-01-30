/* Paquete donde se guardan los servlets que gestionan el trafico de la web. */
package com.ipartek.formacion.controller.filters;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.controller.Constantes;

/**
* @author Raúl de Roba 27/01/17 
*
* <p>Clase CharterFilter que filtra todas las peticiones a Servlets (*.do)</p>
* 
*/
public class ServletFilter implements Filter {

	/* Se recoge la instacia del log pasando como parametro la clase actual.*/
	private static final Logger LOG = Logger.getLogger(ServletFilter.class);	
	
    /**
     * Default constructor. 
     */
    public ServletFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/* Metodo que se ejecuta al cargar una página servlert(*.do).*/
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		/* Se recoge la sesión pasada en la request.
		 * (Al ser una request de Servlet hay que castearla a Http).*/
		HttpSession session = ((HttpServletRequest) req).getSession(false);
		
		/* Se recorre la dirección del servlet(incluida la '/').*/
		String url = ((HttpServletRequest) req).getServletPath();
		
		/* Se comprueba que exista la sesión o 
		 * que en la sesión se haya recogido el idioma o 
		 * se comprueba si la url contiene los servlets permitidos cuando 
		 * no haya sesisión (De momento solo login.do)..*/
		if (((session != null) && 
			(session.getAttribute(Constantes.SESION_IDIOMA) != null)) ||
			(chequearUrl(url))){
				// Permite que continue el trafico.
				chain.doFilter(req, res);	
		}else{
			// Se redirecciona de vuelta a la página inicial.
			 ((HttpServletResponse) res).sendRedirect(Constantes.JSP_HOME);				
		}
	}

	/* Metodo que comprueba si la */
	private boolean chequearUrl(String url){
		/* Se inicializa la variable sobre la que devolveremos el restuado de la 
		 * comprobación.*/
		Boolean correcto = false;
		/* Se recoge la url pasado por parametro.*/
		String path = url;
		/* Se comrpueba si la url pasada por parametro es login.do*/
		if (path.equalsIgnoreCase("/"+Constantes.SERVLET_LOGIN)){
			/* Se devuelve url correcta.*/
			correcto = true;	
		}
		/* Se devuelve el restultado de la comprobación.*/
		return correcto;
		
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
