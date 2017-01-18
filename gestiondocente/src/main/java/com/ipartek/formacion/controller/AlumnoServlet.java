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
		aS = new AlumnoServiceImp();
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Forma 1 -> Redireccion Limpia.
		//resp.sendRedirect("alumnos/listado.jsp");
		
		// Obtenemos la lista de datos
		List<Alumno> alumnos = aS.getAll();
		// Añadimos el atributo a la request de la manera clave-valor
		req.setAttribute("listado-alumnos",alumnos);
		
		// Forma 2 -> Engancha la peticion original.
		// Fijamos la pagina de destino
		RequestDispatcher rd= req.getRequestDispatcher("alumnos/listado.jsp");
		// Hace la redireccion
		rd.forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	
}
