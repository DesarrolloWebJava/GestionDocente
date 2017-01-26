package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		//Locale por defecto Español
		Locale locale = new Locale("es_ES");
		//Si al recoger la session se le pasa true o nada si no existe la session 
		//me la crea
		//obtener lenguaje de la session del usuario
		String language = (String) request.getSession(true).getAttribute("language");

		if ( language != null ){
		locale = new Locale(language);
		}
		ResourceBundle messages=null;
		try {
			messages = ResourceBundle.getBundle("com.ipartek.formacion.controller.i18nmessages", locale );
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		rd = request.getRequestDispatcher(Constantes.JSP_HOME);
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
