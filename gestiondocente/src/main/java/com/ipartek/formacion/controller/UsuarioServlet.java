package com.ipartek.formacion.controller;

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

import com.ipartek.formacion.controller.listeners.SessionListener;
import com.ipartek.formacion.dbms.pojo.Persona;

/**
 * Servlet implementation class UsuarioServlet
 */
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(LoginServlet.class);
    private RequestDispatcher rd;   

       
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionId=request.getParameter("sessionid");
		if(sessionId==null){//es un parametro que se envia desde listado.jsp que si es null entonces se muestra todo
		try{
		List<Persona> personas=null;
		HttpSession session=request.getSession(false);
		ServletContext ctx=session.getServletContext();
		//if(null!=session.getAttribute(Constantes.SESSION_PERSONA)){
    		Persona persona=(Persona)session.getAttribute(Constantes.SESSION_PERSONA);
    		LOG.trace(persona.getNombre());
    		personas=(List<Persona>)ctx.getAttribute(Constantes.CTX_LISTADO_USUARIOS);
    		
    		rd=request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);
    		request.setAttribute(Constantes.ATT_LISTADO_USUARIOS, personas);
    	//}
		}catch(NullPointerException e){//se procesa a expulsar
			LOG.error(e.getMessage());
			request.setAttribute(Constantes.ATT_MENSAJE,"No se puede acceder a la inforación en este momento");
			rd=request.getRequestDispatcher(Constantes.JSP_HOME);
		}
		}else{
			try{
				HttpSession session=SessionListener.getHttpSession(sessionId);
				session.invalidate();
				rd=request.getRequestDispatcher(Constantes.JSP_HOME);	
			}catch(NullPointerException e){
				LOG.error(e.getMessage());
			}
		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
