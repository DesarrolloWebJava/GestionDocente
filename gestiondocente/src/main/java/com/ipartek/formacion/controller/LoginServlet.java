package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.pojo.Persona;
import com.ipartek.formacion.dbms.pojo.exceptions.PersonaException;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher rd;
	private static final Logger LOG=Logger.getLogger("LoginServlet");

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*		Locale locale=new Locale("es_ES");
		String language=(String)request.getSession(true).getAttribute("language");
		if(language!=null){
			locale=new Locale(language);
		}
		ResourceBundle messages =null;
		try{
		 messages = ResourceBundle.getBundle("com.ipartek.formacion.controller.i18nmessages", locale );
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		rd=request.getRequestDispatcher(Constantes.JSP_HOME);
		rd.forward(request, response);*/
		cerrarSession(request);
		response.sendRedirect(Constantes.JSP_HOME);
		return;

	}
	private void cerrarSession(HttpServletRequest request)
	{
		HttpSession session=request.getSession(false);
		if(session!=null){
			session.invalidate();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter(Constantes.PAR_USUARIO);
		String password=request.getParameter(Constantes.PAR_PASSWORD);
		final String user="admin";
		final String pass="pass";
		String mensaje="";
		if(user.equals(username)&&pass.equals(password)){
			//crearemos la session
			HttpSession session=request.getSession(true);
			session.setMaxInactiveInterval(60*15);
			//cargaremos la variable idioma
			String lang=request.getParameter(Constantes.PAR_IDIOMA);
			int idioma=Integer.parseInt(lang);
			String locale="";
			switch(idioma){
			case Constantes.IDIOMA_ES:
				locale="es_ES";
				break;
			case Constantes.IDIOMA_EU:
				locale="eu_ES";
				break;
			case Constantes.IDIOMA_EN:
				locale="en_EN";
				break;
				default:
					locale="es_ES";
					break;
			
			}
			Persona p=new Persona();
			try {
				p.setNombre(username);
				p.setApellidos("Anonimo");
				session.setAttribute(Constantes.SESSION_PERSONA, p);
			} catch (PersonaException e) {
				LOG.error(e.getMessage());
			}
			session.setAttribute(Constantes.SESSION_IDIOMA, locale);
			//le redireccionaremos a una pág
			rd=request.getRequestDispatcher(Constantes.JSP_HOME);
			
		}else{
			mensaje="Usuario y/ó contraseña incorrectos";
			request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
			rd=request.getRequestDispatcher(Constantes.JSP_HOME);
		}
		rd.forward(request, response);
		

	}

}
