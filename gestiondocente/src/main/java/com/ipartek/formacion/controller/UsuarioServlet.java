/* Paquete donde se guardan los servlets que gestionan el trafico de la web.*/
package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.controller.listeners.SessionListener;
import com.ipartek.formacion.dbms.pojo.Persona;

/**
 * @author Raúl de Roba 31/01/17 
 * 
 * <p>Clase Servlet UsuarioServet para la gestión de usuarios.
 * 	  Se entiende por usuario como una persona.</p>
 * 
 */
public class UsuarioServlet extends HttpServlet {
	/* Constante de serialición. */
	private static final long serialVersionUID = 1L;
       
	/* Se declara una RequestDispatcher para redireccionar una url indicada. */
	RequestDispatcher rd;
	
	/* Se recoge la instacia del log pasando como parametro la clase actual.*/
	private static final Logger LOG = Logger.getLogger(UsuarioServlet.class);	
    
	

	 @Override
    /* Metodo que que se ejecuta crear la pagina.
     * Solo se ejecuta 1 vez,al acceder más veces no se crea.*/
	public void init() throws ServletException {
    	/* Se llama al init del padre.En los servlets tiene que ser la última línea,
		 * porque al llamar al padre se sale del metodo.*/
		super.init();
	}
	 
	 /* Metodo a ejecutar al recibir una petición Get. */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		/* Se declara una variable para recoger el numero de operación.*/
		int op = -1;		
		/* Se recoge la operación a realizar. (En String)*/
		String operacion = req.getParameter(Constantes.PAR_OPERACION);	
		/* Se registra la variable donde capturar el mensaje de error.*/
		String mensaje ="";
		/* Se monta estructura para la captura de excepciones.*/
		try{
			/* Se castea la operacion a un entero.*/
			op = Integer.parseInt(operacion);
			/* Se compreuba la operación recibida.*/
			switch (op) {
				/* Se comprueba si se ha recibido la operación de crear.*/
				case Constantes.OP_READ :
					/* Se llama al metodo que cargar la lista de los Usuarios Activos 
					 * en el request. Si no se ha podido cargar se devuelve a la página 
					 * princial.*/
					if (!cargarListaUsuariosActivos(req)){
						/* Sobre el RequestDispatcher se indica una url pra 
						 * redireccionar.
						 * No es una redireccion limpia,con lo que envia parametros.
						 * En este caso request y response.*/
						 rd =  req.getRequestDispatcher(Constantes.JSP_HOME);
						 /* Se carga el mensaje de error.*/
						 mensaje = "Registrese para poder ver sesiones activas.";
						 /* Se manda el mensaje de error al request.*/
						 req.setAttribute(Constantes.ATT_MENSAJE, mensaje);
						 /* Se muestra la traza del error.*/
						 LOG.trace("Intento de ver usuarios activos sin sesión.");
					} 	
					break;
				/* Se comprueba si se ha recibido la operación de expulsar.*/
				case Constantes.OP_EXPULSAR :
					/* Se declara la sesion.*/
					HttpSession sesion = null;
					/* Se recoge la sesion a expulsar.*/
					String sesionId = req.getParameter(Constantes.PAR_SESSIONID);
					/* Se recoge la sesion.*/
					HttpSession session = SessionListener.getHttpSession(sesionId);
					/* Se cancela la sesión.*/
					sesion.invalidate();
					/* Se llama al manda a la página inicial. */
					res.sendRedirect(Constantes.JSP_HOME);
					break;
				default:
					/* En caso que la operacion recibida no sea entero,
					 * se llama al manda a la página inicial. */
					res.sendRedirect(Constantes.JSP_HOME);
			}
			/* Se captura la excepción.*/	
			}catch (Exception e){
				/* En caso que la operacion recibida no sea entero,
				 * se llama al manda a la página inicial. */
				res.sendRedirect(Constantes.JSP_HOME);	
				/* Se lanza la traza del error. */
				LOG.error(e.getMessage());
			}					 
		
		/* Se redirecciona enviando por parametro los request y response 
		 * recibidos por parametro.*/
		rd.forward(req, res);
	}

	/* Metodo que carga la lista de los alumnos en el request pasada por parametro. */
	private boolean cargarListaUsuariosActivos(HttpServletRequest req) {
		/* Se declara la variable que va a comprobar que se haya cargado la
		 * lista correctamente.*/
		Boolean cargaCorrecta = false;
		/* Se recoge la session activa.*/
		HttpSession sesion = req.getSession();
		/* Se recoge el contexto de la sesión (Contexto de Aplicación).*/
    	ServletContext ctx = sesion.getServletContext();
		
		/* Se declara la lista de personas. */
    	List<Persona> personas = null;
    	
    	/* Se coprueba que se tenga una sesión valida.*/
    	if (null != sesion){
    		/* Se recoge la lista de la sesión. */
    		personas = (List<Persona>) ctx.getAttribute(Constantes.ATT_LISTADO_USUARIOS);  		
	    	/* Sobre el RequestDispatcher se indica una url pra redireccionar.
			 * No es una redireccion limpia,con lo que envia parametros.
			 * En este caso request y response.*/
			 rd =  req.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIO);
			 /* Se crea un atributo en la request y se le asigna la lista de personas.*/
			 req.setAttribute(Constantes.ATT_LISTADO_USUARIOS, personas);
			 /* Se devuelve carga de lista correcta.*/
			 cargaCorrecta = true;
    	} 
    	/* Se devuelve el resultado de la carga.*/
    	return cargaCorrecta;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
