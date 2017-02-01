package com.ipartek.formacion.controller.listeners;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.controller.Constantes;
import com.ipartek.formacion.dbms.pojo.Persona;

/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(AdminServlet.class);
	private RequestDispatcher rd;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*// acceder a la lista de personas
    	List<Persona> personas = null;
    	// acceder a la session
    	HttpSession session = request.getSession();
    	// acceder al servlet ctx
    	ServletContext ctx = session.getServletContext();
    	// Recoger la variable del ctx
    	personas = (List<Persona>) ctx.getAttribute(Constantes.CTX_LISTADO_USUARIOS);
		
    	request.setAttribute(Constantes.ATT_LISTADO_USUARIOS, personas);
    	rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);
   
    	rd.forward(request, response);*/

    	
				String sessionid = request.getParameter("sessionid");
				if (sessionid == null) {// se procesa visualizar todos los ususarios
		 								// concetados
		 			try {
		 				HttpSession session = request.getSession(false);
		 				ServletContext ctx = session.getServletContext();
		 				List<Persona> personas = (List<Persona>)ctx.getAttribute(Constantes.CTX_LISTADO_USUARIOS);
		 				request.setAttribute(Constantes.ATT_LISTADO_USUARIOS, personas);
		 				rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);
		 			} catch (NullPointerException e) {
		 				log.error(e.getMessage());
		 				request.setAttribute(Constantes.ATT_MENSAJE, "No se puede acceder a la información en este momento");
		 				rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		 			}
		 		} else {// se procesa expulsar a un usuario
		 			try {
		 				HttpSession session = SessionListener.getHttpSession(sessionid);
		 				session.invalidate();
		 				rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
		 			} catch (NullPointerException e) {
		 				log.error(e.getMessage());
		 			}
		  		}
		 		rd.forward(request, response);
		  }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
