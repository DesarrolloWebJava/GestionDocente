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
 * Servlet Filter implementation class ServlerFilter
 */
public class ServlerFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ServlerFilter() {
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
		//mirar si existe sesion
		if (request instanceof HttpServletRequest){
			HttpSession session=((HttpServletRequest)request).getSession(false);
			//si la sesion no existe vamos a comprobar si quieres ir a login.do
			String url=((HttpServletRequest)request).getServletPath();	
			System.out.println(url);
			//a--> puesdes seguir.cuando haya sesion (sesion!=null y que exista el parametro idioma)
			// o cuando vayas a login
			//b--> te mando al index.jsp
			if ((session!=null && session.getAttribute(Constantes.SESSION_IDIOMA)!=null) || checkWebPages(url)){
						chain.doFilter(request, response);
			}else{
				HttpServletResponse resp=(HttpServletResponse)response;
				resp.sendRedirect(Constantes.JSP_HOME);
				return;
			}
		}else{
				chain.doFilter(request, response);
		}	
	
	}

	private boolean checkWebPages(final String path) {
		boolean exito = false;
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
