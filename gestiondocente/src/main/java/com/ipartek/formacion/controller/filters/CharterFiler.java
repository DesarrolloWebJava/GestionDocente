/* Paquete donde se guardan los servlets que gestionan el trafico de la web. */
package com.ipartek.formacion.controller.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
* @author Raúl de Roba 27/01/17 
*
* <p>Clase  CharterFiler que filtra todas las peticiones (*)</p>
* 
*/
public class CharterFiler implements Filter {

    /**
     * Default constructor. 
     */
    public CharterFiler() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/* Metodo que se ejecuta al cargar una página cualquiera (*).*/
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		/* Se asigna al request la codificacion.*/
		req.setCharacterEncoding("UTF-8");
		// Permite que continue el trafico.
		chain.doFilter(req, res);	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
