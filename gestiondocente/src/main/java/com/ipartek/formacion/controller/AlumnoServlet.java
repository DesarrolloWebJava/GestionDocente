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

	private AlumnoService as;

	@Override
	public void init() throws ServletException {
		as = new AlumnoServiceImp();
		super.init();
	}

	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.sendRedirect("alumnos/listado.jsp"); --> redireccion
		// limpia.Elimina todos los datosque vienen dados de antes.
		//
		// recogemos la lista de alumnos
		List<Alumno> alumnos = as.getAll();
		// fijamos la pagina de destino
		RequestDispatcher rd = request.getRequestDispatcher("alumnos/listado.jsp");
		// añadimos el atributo a la request
		request.setAttribute("listado-alumnos", alumnos);
		// hace la redireccion
		rd.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
