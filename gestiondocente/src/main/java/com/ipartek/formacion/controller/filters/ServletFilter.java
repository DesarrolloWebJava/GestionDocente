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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			
			//Tenemos que hacer un Casting para convertir el request a HttpServletRequest 
			HttpSession session = ((HttpServletRequest)request).getSession(false);
			
			//getServletPath muestra /url del servlet
			String url = ((HttpServletRequest)request).getServletPath();
			//Si la sesion es diferente de nula Y el atributo de sesion 
			//idioma es diferente de nulo (Existe sesion) O si la url NO es /login.do...
			if((session != null && session.getAttribute(Constantes.SESSION_IDIOMA)!= null) || checkWebPages(url)) {
				// Le dejamos pasar
				chain.doFilter(request,  response);
				return;
			}else{
				//Sino te redirecciona al index.jsp
				HttpServletResponse resp = (HttpServletResponse) response;
				resp.sendRedirect(Constantes.JSP_HOME);
				return;
			}
	}	

	/**
	 * Devuelve true si la ruta es la del login del servlet /login.do
	 * @param path ruta
	 * @return un booleano
	 */
	private boolean checkWebPages(final String path) {
		boolean exito = false;
		if(path.equalsIgnoreCase("/"+Constantes.SERVLET_LOGIN)){
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
