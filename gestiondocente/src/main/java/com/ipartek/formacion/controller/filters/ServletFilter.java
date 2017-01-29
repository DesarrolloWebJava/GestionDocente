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

import com.ipartek.formacion.controller.Constantes;

/**
 * Servlet Filter implementation class ServletFilter
 */
public class ServletFilter implements Filter {

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

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */

	
	
	public void doFilter(ServletRequest request, 
			ServletResponse response, FilterChain chain) 
					throws IOException, ServletException {
		
		
		// 1. Comprobar si hay sesion
		//	1.1 Cast request a http.request
		//	1.2 method getSession(bool) if false, sets session to null
		if (request instanceof HttpServletRequest) {
			HttpSession session = ((HttpServletRequest) 
					request).getSession(false);
		
			//	1.3 get url of request
			String url = ((HttpServletRequest) request).getServletPath();		
		
		//si la session es nula o lo es el idioma
		// --> comprueba que es l aurl login y le aplica el filtro, 
		// o sino redirige a casa
		
		if (session == null || 
				session.getAttribute(Constantes.SESSION_IDIOMA) == null) {
			
			if (checkWebPages(url)) {
				chain.doFilter(request, response);
				return;
			} else {
				HttpServletResponse resp = (HttpServletResponse) response;
				resp.sendRedirect(Constantes.JSP_HOME);
				return;
			}
			//sino: aplica filtro
			} else {
				chain.doFilter(request, response);
			
			}
		}
	}	

	private boolean checkWebPages(final String path) {
		boolean exito = false;
		//si la url pasada es el servlet login desde el dir raiz, bien
		if (path.equalsIgnoreCase("/" + Constantes.SERVLET_LOGIN)) {
			exito = true;

		}
		return exito;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
