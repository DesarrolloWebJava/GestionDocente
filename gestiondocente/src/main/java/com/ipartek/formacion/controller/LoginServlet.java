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
		
		Locale locale = new Locale("es_Es");
		//Si al recoger la sesion se le pasa true o nada si no existe la sesion...
		//me la crea, si se le pasa false obtiene la ya existente
		
		
		//String languaje = (String)request.getSession().getAttribute("languaje");
		//if(languaje !=null){
			//locale = new Locale(languaje);
		//}
		//ResourceBundle messages = null;
		//try{
			//messages = ResourceBundle.getBundle("com.ipartek.formacion.egunon.controller.i18messages", locale);
			
		//}catch(Exception e){
			//System.out.println(e.getMessage());
			
		//}
		//rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		//rd.forward(request, response);
		
		cerrarSession(request);
		response.sendRedirect(Constantes.JSP_HOME);
		return;
	}
	
	private void cerrarSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session!=null){
			session.invalidate();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter(Constantes.PAR_USUARIO);
		String password = request.getParameter(Constantes.PAR_PASSWORD);
		
		final String user = "admin";
		final String pass = "pass";
		
		if(user.equals(username) && pass.equals(password)) {//
			//Crearemos la sesion
			HttpSession session = request.getSession(true);
			// La sesion puede tener 3 valores
				//TRUE->Fuerzas que te cree una sesion nueva
				//FALSO->Recoges una existente, si no hay devuelve nulo
				//SIN PARAMETROS-> Si no existe, te crea una nueva
			//Le fijamos la duracion de la sesion, en milisegundos
			session.setMaxInactiveInterval(60*15);
			
			//Cargaremos la variable de idioma
			String lang = request.getParameter(Constantes.PAR_IDIOMA);
			int idioma = Integer.parseInt(lang);
			String locale = "";
			switch(idioma){
				case Constantes.IDIOMA_CASTELLANO:
					locale = "es_ES";
					break;
					
				case Constantes.IDIOMA_EUSKERA:
					locale = "eu_ES";
					break;
					
				case Constantes.IDIOMA_INGLES:
					locale = "en_EN";
					break;
				
				default:
					locale = "es_ES";
					
			}
			session.setAttribute(Constantes.SESSION_IDIOMA, locale );
			
			//Le redirereccionaremos a una pagina
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);
			
		}else{
			//Mensaje de error
			String mensaje = "Usuario y/o contaseña incorrectos";
			//Le redirereccionaremos a index.jsp
			request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);
			
			
			
		}
		rd.forward(request, response);
		
	}

}
