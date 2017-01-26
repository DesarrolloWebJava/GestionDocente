package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
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
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 Locale locale = new Locale("es_ES");	
	 // Si al recoger la session se le pasa true o nada si no existe la sesion la crea
	 // si se le pasa false obtiene la ya existente
	 String language = (String) request.getSession(true).getAttribute("language");
	 
	 if (language != null){
		 locale = new Locale(language);
	 }
	 
	 ResourceBundle message = null;
	 
	 try {
		ResourceBundle messages = ResourceBundle.getBundle("com.ipartek.formacion.controller.i18nmessages", locale);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.print(e.getMessage());
	}
	
		 
	 rd = request.getRequestDispatcher(Constantes.JSP_HOME);
	 rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
