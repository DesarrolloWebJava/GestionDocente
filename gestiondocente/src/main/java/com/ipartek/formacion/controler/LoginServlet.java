package com.ipartek.formacion.controler;

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
		
		//Locale por defecto Español
		Locale locale = new Locale("es_ES");
		//si al recoger la sesion se la pasa true o nada , si no existe la sesion me la crea
		// si le paso false obtiene la ya existente
		String lenguaje = (String)request.getSession(true).getAttribute("lenguaje");
		if (lenguaje != null ){
			locale = new Locale(lenguaje);
		}
		ResourceBundle messages = null;
		//Cargar resourceBundle o properties dependiente del idioma
		try{
		// Debemos indicara el package donde se encuentra y el nombre del /properties sin la extension del locale 
			messages = ResourceBundle.getBundle("com.ipartek.formacion.egunon.i18nmesages", locale );
		}catch(Exception e){
			System.out.println(e.getMessage());
			
		}
		
		rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		//hace la redireccion
		rd.forward(request,response); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
	

}
