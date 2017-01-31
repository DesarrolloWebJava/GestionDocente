package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
	private static final Logger LOG=Logger.getLogger(UsuarioServlet.class);
	private RequestDispatcher rd;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String sessionid=request.getParameter("sessionid");
		if(sessionid==null){
		try{
			HttpSession session=request.getSession(false);
			ServletContext ctx=session.getServletContext();
			List<Persona> personas=(List<Persona>)ctx.getAttribute(Constantes.CTX_LISTADO_USUARIOS);
			request.setAttribute(Constantes.ATT_LISTADO_USUARIOS, personas);
			rd=request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);

		}catch(Exception e){
			LOG.error(e.getMessage());
			rd=request.getRequestDispatcher(Constantes.JSP_HOME);


		}
		}else{
			try{
				HttpSession session=SessionListener.getHttpSession(sessionid);
				session.invalidate();
				rd=request.getRequestDispatcher(Constantes.JSP_HOME);
			}
			catch(NullPointerException e){
				LOG.error(e.getMessage());
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
