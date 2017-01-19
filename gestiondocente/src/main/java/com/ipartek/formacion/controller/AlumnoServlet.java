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
	private RequestDispatcher rd;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		aS = new AlumnoServiceImp();
		super.init();
		// aS = new AlumnoServiceImp(); --> esto aqui no se ejecuta
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String operacion = req.getParameter(Constantes.PAR_OPERACION);
		int op = -1;
		try {
			op = Integer.parseInt(operacion);
			switch (op) {
				case Constantes.OP_CREATE:
					// se va redirigir a la pagina alumnos/alumno.jsp
					rd = req.getRequestDispatcher("alumnos/alumno.jsp");
					break;
				case Constantes.OP_READ:
					cargarListaAlumnos(req);
					break;
				case Constantes.OP_UPDATE:
					// aS.getById(codigo)
					// se va redirigir a la pagina alumnos/alumno.jsp
					rd = req.getRequestDispatcher("alumnos/alumno.jsp");
					// req.setAttribute(arg0, arg1);
					break;
				default:
					cargarListaAlumnos(req);
					break;
			}

		} catch (Exception e) {
			// cargarListaAlumnos(req);
			resp.sendRedirect(Constantes.JSP_HOME);
			return;
		}

		// hace la redirección
		rd.forward(req, resp);

	}

	/**
	 * @param req
	 */
	private void cargarListaAlumnos(HttpServletRequest req) {
		// resp.sendRedirect("alumnos/listado.jsp"); --> hace una redireccion
		// limpia.
		// obtenemos la lista de datos.
		List<Alumno> alumnos = aS.getAll();
		// fijamos la página de destino
		rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
		// añadimos el atributo a la request
		req.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, alumnos);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	@Override
	public void destroy() {
		aS = null;
		super.destroy();
	}

}
