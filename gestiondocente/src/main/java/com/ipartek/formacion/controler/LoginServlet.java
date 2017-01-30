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
			messages = ResourceBundle.getBundle("com.ipartek.formacion.contoler.i18nmesages", locale );
		}catch(Exception e){
			System.out.println(e.getMessage());
			
		}
		//cerrarSession(request);
	/*	response.sendRedirect(Constantes.JSP_HOME);
		return;*/
		rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		//hace la redireccion
		rd.forward(request,response); 
		
	}

	/*private void cerrarSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		rd.forward(request, response);
		
	}
*/
	/**
	 * vamos a crear una sesion de login: con usuario "admin"
	 * y password "pass"
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//recogemos los parametros
		String username= request.getParameter(Constantes.PAR_USUARIO);
		String password = request.getParameter(Constantes.PAR_PASSWORD);
		
		final String user = "admin";
		final String pass = "pass";
		
		if(user.equals(username) && pass.equals(password)){
			//true --> fuerzas sesion nueva
			//false --> recoges una existente, si no hay devuelve nulo
			//sin parametros-->Si no existe te crea una nueva.
			//crearemos la sesion, yo quiero forzas una nueva
			
			HttpSession session =request.getSession(true);
			
			session.setMaxInactiveInterval(60*15);
			//cargaremos variable idioma
			String lang = request.getParameter(Constantes.PAR_IDIOMA);
			
			int idioma= Integer.parseInt(lang);
		
			String locale ="";
			switch (idioma){
				case Constantes.IDIOMA_CASTELLANO:
					locale= "es_ES";
					break;
				case Constantes.IDIOMA_EUSKERA:
					locale= "eu_ES";
					break;
				case Constantes.IDIOMA_INGLES:
					locale= "en_EN";
					break;
				default:
					locale= "es_ES";
					
			}
			
			session.setAttribute(Constantes.SESSION_IDIOMA, locale);
			session.setAttribute(Constantes.ATT_SESISON, session);
			session.setAttribute("usuario", username);
			//request.setAttribute(Constantes.ATT_ALUMNO, alumno);
			//Redireccionaremos a una pagina determinada
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);
			//hace la redireccion
			 
		}else{
			//nombre y/o password incorrectos
			//redirecionar a login.jsp
			String mensaje = "Usuario y/o Password incorrectos";
			request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		}
		
		rd.forward(request,response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
	

}
