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
	private ProfesorService ps;
	private RequestDispatcher rd;

	@Override
	public void init() throws ServletException {
		ps = new ProfesorServiceImp();
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.sendRedirect("profesores/listado.jsp"); --> redireccion
		// limpia.Elimina todos los datosque vienen dados de antes.
		//
		// recogemos la lista de profesores
		Map<Integer, Profesor> profesores = (Map<Integer, Profesor>) ps.getAll();
		// fijamos la pagina de destino
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_PROFESORES);
		// añadimos el atributo a la request
		request.setAttribute(Constantes.ATT_LISTADO_PROFESORES, profesores);
		// hace la redireccion
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
