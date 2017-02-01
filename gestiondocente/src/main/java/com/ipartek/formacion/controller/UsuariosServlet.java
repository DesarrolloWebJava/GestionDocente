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
 * Servlet implementation class UsuariosServlet
 */
public class UsuariosServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(UsuariosServlet.class);
	private static final long serialVersionUID = 1L;
    private RequestDispatcher rd;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuariosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionID = request.getParameter(Constantes.PAR_SESSION_ID);
		if(sessionID == null){ //Se procesa visualizar todos los usuarios conectados
			try{
				//Recogemos la sesion en la variable session
				HttpSession session = request.getSession(false);
				//Guardamos el contenido del contexto en la variable ctx
				ServletContext ctx = session.getServletContext();
				//Creamos una lista y cargamos la lista con la lista de usuarios(sesiones) del contexto
				List<Persona> personas = (List<Persona>)ctx.getAttribute(Constantes.CTX_LISTADO_USUARIOS);
				//La mandamos a listado.jsp de la carpeta usuarios
				rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);
				return;
			}catch(NullPointerException e){
				LOG.error(e.getMessage());
				request.setAttribute(Constantes.ATT_MENSAJE, "No se puede acceder");
				//La mandamos a listado.jsp de la carpeta usuarios
				rd = request.getRequestDispatcher(Constantes.JSP_HOME);
			}
		}else{ //Se procesa expulsar a un usuario
			try{
				//
				HttpSession session = SessionListener.getHttpSession(sessionID);
				//Echa de la sesion
				session.invalidate();
				rd = request.getRequestDispatcher(Constantes.JSP_HOME);
			}catch(NullPointerException e){
				LOG.error(e.getMessage());
			}
		}
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
