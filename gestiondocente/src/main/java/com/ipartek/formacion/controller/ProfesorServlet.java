package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.ipartek.formacion.dbms.pojo.Profesor;
import com.ipartek.formacion.service.ProfesorService;
import com.ipartek.formacion.service.ProfesorServiceImp;

/**
 * Servlet implementation class ProfesorServlet
 */
public class ProfesorServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
    private ProfesorService pS;
    private RequestDispatcher rd;

	@Override
	public void init() throws ServletException {
		pS = new ProfesorServiceImp();
		super.init();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// obtenemos la lista de datos.
				Map<Integer, Profesor> profesores = pS.getAll(); 
				// de la clase constantes del controller
				rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_PROFESORES); 
				request.setAttribute(Constantes.ATT_LISTADO_PROFESORES, profesores);
				rd.forward(request, response);
				
				// fijamos la página profesoresdestino.
				//RequestDispatcher rd = request.getRequestDispatcher("profesores/listado.jsp");
				// añadimos el atributo a la request.
				//request.setAttribute("listado-profesores", profesores);
				// hace la redirección.
				//rd.forward(request, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
