package com.ipartek.formacion.controler.filters;
/**
 * vamos a filtrar solo los servlet
 * solo vamos a dejar acceder a login.do
 */
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

import com.ipartek.formacion.controler.Constantes;

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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// existe la session

		if (request instanceof HttpServletRequest) {
			HttpSession session = ((HttpServletRequest) request).getSession(false);

			String url = ((HttpServletRequest) request).getServletPath() + "/";
			//System.out.println("-" + url);
			if (session == null || session.getAttribute(Constantes.SESSION_IDIOMA) == null) {

				if (checkWebPages(url)) {

					chain.doFilter(request, response);
					return;
				} else {

					HttpServletResponse resp = (HttpServletResponse) response;
					resp.sendRedirect(Constantes.JSP_HOME);
					return;
				}

				// si la session no existe vamos a comprobar si quieres ir a
				// login.do
			}
			//System.out.println("--" + url);
		} else {
			chain.doFilter(request, response);
			return;
		}

	}

	private boolean checkWebPages(final String path) {
		boolean exito = false;
		//System.out.println(path + "-" + "/" + Constantes.SERVLET_LOGIN);
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
