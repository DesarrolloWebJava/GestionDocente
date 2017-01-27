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
 * Servlet Filter implementation class ServletFilters
 */
public class ServletFilters implements Filter {

    /**
     * Default constructor. 
     */
    public ServletFilters() {
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
		
		// Comprobar si existe la session
		if (request instanceof HttpServletRequest){
			
			HttpSession session = ((HttpServletRequest) request).getSession(false);
			
			String url = ((HttpServletRequest)request).getServletPath();
			System.out.println("---" + url);
			
			// 		a--> puedes seguir cuando haya sesion(sesion!= null y que existe el parametro idioma) o cuando vayas a login.
			//		b--> te mando a index.jsp
			// if ((session != null && session.getAttribute(Constantes.SESSION_IDIOMA) != null) ||  checkWebPages(url)) {
			// 		y sobraria un else. hemos metdo el if dentro del otro.
			if (session == null || session.getAttribute(Constantes.SESSION_IDIOMA) == null){
				
				if (checkWebPages(url)) {
					chain.doFilter(request, response);
					
				}else{
					
					HttpServletResponse resp = (HttpServletResponse) response;
					resp.sendRedirect(Constantes.JSP_HOME);
					return;
				} 
					
				
				// si la session no existe vamos a comprobar si quieres ir a login.do			
	
			}else{
				chain.doFilter(request, response);
			}
			//System.out.println("--" + url);
		}else{
		// pass the request along the filter chain
			chain.doFilter(request, response);
			return;
		}
	}

	
	private boolean checkWebPages(final String path) {
		 	boolean exito = false;
		 	
		 	System.out.println(path + "---" + "/" + Constantes.SERVLET_LOGIN);
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
