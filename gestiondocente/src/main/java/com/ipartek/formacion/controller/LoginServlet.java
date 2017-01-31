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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.pojo.Persona;
import com.ipartek.formacion.dbms.pojo.exceptions.PersonaException;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(LoginServlet.class);
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
	
		//	Locale locale = new Locale("es_ES");	
		//	 // Si al recoger la session se le pasa true o nada si no existe la sesion la crea
		//	 // si se le pasa false obtiene la ya existente
		//	 String language = (String) request.getSession(true).getAttribute("language");
		//	 
		//	 if (language != null){
		//		 locale = new Locale(language);
		//	 }
		//	 
		//	 ResourceBundle message = null;
		//	 
		//	 try {
		//		ResourceBundle messages = ResourceBundle.getBundle("com.ipartek.formacion.controller.i18nmessages", locale);
		//	} catch (Exception e) {
		//		e.printStackTrace();
		//		System.out.print(e.getMessage());
		//	}
		//	
		//		 
		//	 rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		//	 rd.forward(request, response);
		
		cerrarSesion(request);
		response.sendRedirect(Constantes.JSP_HOME);
		return;
		
	}
	
	private void cerrarSesion(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if (session!=null){
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
       
       if (user.equals(username) && pass.equals(password))
       {
    	   // crearemos la sesion 
    	   
    	   // true --> fuerzas a crear una sesion nueva
    	   // false --> recoges una existente si existe,si no hay devuelve null
    	   // sin parametros -> Si no hay te crea una nueva
    	   HttpSession session = request.getSession(true);
    	   
    	   // fijamos la duracion de inactividad, entendiendo actividad como peticiones al servidor get-post
    	   session.setMaxInactiveInterval(60 * 15);
    	   
    	   // cargaremos la variable de idioma
    	   String lang = request.getParameter(Constantes.PAR_IDIOMA);
    	   int idioma = Integer.parseInt(lang);
    	   String locale="";
    	   
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
    	   		break;
    	   }// fin del switch 
    	   
    	   session.setAttribute(Constantes.SESSION_IDIOMA, locale);
    	  
    	   Persona persona = new Persona();
    	   try {
			persona.setNombre(username);
			persona.setApellidos("Anonimo");
			persona.setSessionId(session.getId());
			session.setAttribute(Constantes.SESSION_PERSONA, persona);
		   } catch (PersonaException e) {
			LOG.error(e.getMessage());
	       }//fin del catch
    	   
    	   
    	   // redireccionaremos a una pagina estandar
    	   rd = request.getRequestDispatcher(Constantes.JSP_HOME);
    	   
       }
       else{
    	   // mensaje de error
    	   String mensaje="Usuario y/o password incorrecto";
    	   // le redireccionaremos a una pagina index.jsp
    	   request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
    	   rd = request.getRequestDispatcher(Constantes.JSP_HOME);
    	   
       }
       
       rd.forward(request, response);
       
	}
	


}
