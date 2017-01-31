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

import com.ipartek.formacion.dbms.pojo.Persona;


/**
 * Servlet implementation class UsuarioServlet
 */
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RequestDispatcher rd;
	
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			HttpSession sesion = request.getSession(false); 
			List <Persona> personas=null;
			ServletContext ctx=sesion.getServletContext();
			personas=(List<Persona>)ctx.getAttribute("listadoUsuarios");
			rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIO_CONECTADOS);
			// añadimos el atributo a la request
			request.setAttribute("listadoUsuarios", personas);

		} catch (Exception e) {
			// cargarListaAlumnos(req);
			System.out.println(e.getMessage());
			response.sendRedirect(Constantes.JSP_HOME);
			return;
		}

		// hace la redirección
		rd.forward(request, response);

	}

	/**
	 * @param req
	 */
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
