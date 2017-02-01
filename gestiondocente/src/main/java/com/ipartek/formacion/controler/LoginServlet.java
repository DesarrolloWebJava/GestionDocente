package com.ipartek.formacion.controler;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.controler.listeners.SessionListener;
import com.ipartek.formacion.dbms.pojo.Persona;
import com.ipartek.formacion.dbms.pojo.exceptions.PersonaException;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	//primero es el LOG
	private static final Logger LOG = Logger.getLogger(LoginServlet.class);
	private static final long serialVersionUID = 1L;
	private RequestDispatcher rd;
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * recogemspo contxto de la aplicacion
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Locale por defecto Español
		//Locale locale = new Locale("es_ES");
		//si al recoger la sesion se la pasa true o nada , si no existe la sesion me la crea
		// si le paso false obtiene la ya existente
	//	String lenguaje = (String)request.getSession(true).getAttribute("lenguaje");
		//if (lenguaje != null ){
		//	locale = new Locale(lenguaje);
		//}
		//ResourceBundle messages = null;
		//Cargar resourceBundle o properties dependiente del idioma
		//try{
		//// Debemos indicara el package donde se encuentra y el nombre del /properties sin la extension del locale 
		//	messages = ResourceBundle.getBundle("com.ipartek.formacion.contoler.i18nmesages", locale );
		//}catch(Exception e){
		//	System.out.println(e.getMessage());
			
		//}
		//RECOGEMOS PARAMETRO OP
		String operacion = request.getParameter(Constantes.PAR_OPERACION);
		int op=-1;
		op = Integer.parseInt(operacion);
		LOG.trace(op);
		switch (op){
			case Constantes.OP_LISTAR_USUARIOS_SESION:
				LOG.trace("Estoy OP_LISTAR_USUARIOS_SESION");
				List<Persona> personas =null;
				//1-recoger el contexto de la app
				HttpSession session =request.getSession();
		    	ServletContext ctx =session.getServletContext();
				//2-caragrame la lista de usuarios conectados
		    
		    		Persona p = (Persona) session.getAttribute(Constantes.SESSION_PERSONA);
		    		LOG.trace("Estoy en recoger usuario");
		    		personas = (List<Persona>)ctx.getAttribute(Constantes.ATT_LISTADO_USUARIOS);
		    		//hay q grabar la lista actualizada
		    		//ctx.setAttribute("listadoUsuario", personas);
		    		//3-pasarsela a la request
		    		session.setAttribute(Constantes.ATT_LISTADO_USUARIOS, personas);
		    		//4-hacer la redireccion
		    		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);
		    		
		    	break;
			case Constantes.OP_CERRAR_SESION:
				LOG.trace("Estoy cerrar session");
				cerrarSession(request);
				response.sendRedirect(Constantes.JSP_HOME);
				return;	
			default:
			}

		rd.forward(request,response);	
	
		
	
	
		
		
		
		//List<Persona> personas =null;
		//1-recoger el contexto de la app
		//HttpSession session =request.getSession();
    	//ServletContext ctx =session.getServletContext();
		//2-caragrame la lista de usuarios conectados
    	//if(null != session.getAttribute(Constantes.SESSION_PERSONA)){
    	//	Persona p = (Persona) session.getAttribute(Constantes.SESSION_PERSONA);
    	//	LOG.trace("Estoy en recoger usuario");
    	//	personas = (List<Persona>)ctx.getAttribute(Constantes.ATT_LISTADO_USUARIOS);
    		//hay q grabar la lista actualizada
    		//ctx.setAttribute("listadoUsuario", personas);
    		//3-pasarsela a la request
    		//session.setAttribute(Constantes.ATT_LISTADO_USUARIOS, personas);
    		//4-hacer la redireccion
    		//rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);
    		//rd.forward(request,response);
    	//}
    	//else{
    	////	response.sendRedirect(Constantes.JSP_HOME);
    	//	return;
    		
    	//}
    	//rd.forward(request,response);
		
    	//cerrarSession(request);
		//response.sendRedirect(Constantes.JSP_HOME);
		//return;

		
	}

	private void cerrarSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		
	}
	
	/**
	 * vamos a crear una sesion de login: con usuario "admin"
	 * y password "pass"
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bandera=0;
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
			LOG.trace("Estoy LOGIN");
			HttpSession session =request.getSession(true);
			
			session.setMaxInactiveInterval(60*15);
			//cargaremos variable idioma
			String lang = request.getParameter(Constantes.PAR_IDIOMA);
			int idioma= Integer.parseInt(lang);
			//recogemos el name del imput recuerdame
			String remenberme =request.getParameter("recuerdame");
			//creamos la cookie, hay una clase que es Cookie
			Cookie c_username = new Cookie("username",username);
			Cookie c_password = new Cookie("password",password);
			
			Cookie c_idioma = new Cookie("lang",lang);
			if(remenberme != null){
				c_username.setMaxAge(60*60*24);
				c_password.setMaxAge(60*60*24);
			}else{
				c_username.setMaxAge(0);
				c_password.setMaxAge(0);
			}
			response.addCookie(c_username);
			response.addCookie(c_password);
			response.addCookie(c_idioma);
			
			
			
			
			
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
			bandera=1;
			/*tengo q instanciar el objeto persona para no perder el data nombre en la app*/
			Persona p = new Persona();
			try{
				p.setNombre(username);
				p.setApellidos("Anonimo");
				session.setAttribute(Constantes.SESSION_PERSONA, p);
				
			}catch (PersonaException e){
				LOG.error(e.getMessage());
			}
			
		
			session.setAttribute("bandera", bandera);
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
			bandera=0;
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
