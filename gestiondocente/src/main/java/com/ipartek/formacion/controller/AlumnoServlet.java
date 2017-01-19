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
	private RequestDispatcher rd;

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
		String operacion = request.getParameter(Constantes.PAR_OPERACION);
		int op = -1;

		try {
			op = Integer.parseInt(operacion);
			switch (op) {
			case Constantes.OP_CREATE:
				// se va a redirigir a la pagina alumnos/alumno.do
				rd = request.getRequestDispatcher("alumnos/alumno.jsp");
				break;
			case Constantes.OP_READ:
				cargarListaAlumnos(request);
				break;
			case Constantes.OP_UPDATE:

				rd = request.getRequestDispatcher("alumnos/alumno.jsp");
				break;
			default:
				response.sendRedirect(Constantes.JSP_HOME);
				break;

			}

		} catch (Exception e) {

			// cargarListaAlumnos(request);
			response.sendRedirect(Constantes.JSP_HOME);
		}
		// hace la redireccion
		rd.forward(request, response);

	}

	private void cargarListaAlumnos(HttpServletRequest request) {
		// response.sendRedirect("alumnos/listado.jsp"); --> redireccion
		// limpia.Elimina todos los datosque vienen dados de antes.
		//
		// recogemos la lista de alumnos
		List<Alumno> alumnos = as.getAll();
		// fijamos la pagina de destino
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
		// añadimos el atributo a la request
		request.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, alumnos);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	public void destroy() {
		as = null;
		super.destroy();
	}

}
