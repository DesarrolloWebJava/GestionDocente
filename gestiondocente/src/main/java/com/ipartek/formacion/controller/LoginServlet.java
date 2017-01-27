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

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RequestDispatcher rd;   


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Locale locale=new Locale("es_ES");
		//si al recoger la sesion se le pasa true o nada si no existe la sesion me la crea
		//si se le pasa false obtiene la ya existente
		String language=(String)request.getSession(true).getAttribute("language");
		if(language !=null){
			locale=new Locale(language);
		}
		ResourceBundle messages=null;
			try{
			   messages = ResourceBundle.getBundle("com.ipartek.formacion.controller.i18nmesages", locale );
			}catch(Exception e){
				System.out.println(e.getMessage());
			}		
		rd=request.getRequestDispatcher(Constantes.JSP_HOME);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter(Constantes.PAR_USUARIO);
		String password=request.getParameter(Constantes.PAR_PASSWORD);
		final String user="admin";
		final String pass="admin";
		//poner primero pass porque password puede ser nulo
		//cuando algo puede ser nulo se pone lo que no puede ser nulo nunca primero
		if(user.equals(username) && pass.equals(password)){
			//crear la sesion
			//true---> fuerzas que te cree una sesion nueva
			//false--> recoges una existente, si no hay devuelve nulo
			//sin parametros--> si no existe te crea una nueva.
			HttpSession session=request.getSession(true);
			
			//seria mantener la sesion abierta durante 15 minutos de inactividad.
			//(cuando pasan los 15 minutos sin hacer nada se desconecta) se pone en segundos.
			session.setMaxInactiveInterval(60*15);
		
			//cargar la variable de idioma
			String lang=request.getParameter(Constantes.PAR_IDIOMA);
			int idioma=Integer.parseInt(lang);
			String locale="";
			switch (idioma){
				case Constantes.IDIOMA_CASTELLANO:
					locale="es_ES";
					break;
				case Constantes.IDIOMA_EUSKERA:
					locale="eu_ES";
					break;
				case Constantes.IDIOMA_INGLES:
					locale="en_EN";
					break;
					default:
						locale="es_ES";
						break;
			}
			session.setAttribute(Constantes.SESSION_IDIOMA, locale);
			//redireccion a una pagina
			rd=request.getRequestDispatcher(Constantes.JSP_HOME);
		}else{
			//cargar mensaje de error sin dar pistas de cual es un error para que no pueda introducir contraseñas hasta conseguir entrar
			String mensaje="Usuario y/o contraseña incorrectos";
			//le redireccionaremos a index.jsp
			rd=request.getRequestDispatcher(Constantes.JSP_HOME);
			request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		}
		rd.forward(request, response);
	}

}
