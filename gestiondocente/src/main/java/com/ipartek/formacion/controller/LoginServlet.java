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
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Locale locale = new Locale("es_ES");
		//si al recoger lasesion se le pasa true o nada, no existe la sesion. me la crea.
		// si se le pasa false obtiene la ya existente.
		String language = (String) request.getSession().getAttribute("language");
		
		if(language!=null){
			locale = new Locale(language);
		}
		ResourceBundle messages = null;
		try{
		messages = ResourceBundle.getBundle("com.ipartek.formacion.controller.i18nmesages", locale);
		}
		catch (Exception e){
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
