package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.service.AlumnoService;
import com.ipartek.formacion.service.AlumnoServiceImp;

/**
 * Servlet implementation class AlumnoServlet
 */
public class AlumnoServlet extends HttpServlet {
	

	private static final long serialVersionUID = 1L;
    private AlumnoService aS;
    
    @Override
	public void init() throws ServletException {
    	aS= new AlumnoServiceImp();
    	super.init();
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {	
		
		//resp.sendRedirect("alumnos/listado.jsp"); //limpia
		
		// obtenemos la lista de datos.
		List<Alumno> alumnos = aS.getAll();
		// fijamos la página destino.
		RequestDispatcher rd = request.getRequestDispatcher("alumnos/listado.jsp");
		// añadimos el atributo a la request.
		request.setAttribute("listado-alumnos", alumnos);
		// hace la redirección.
		rd.forward(request, resp);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
}
