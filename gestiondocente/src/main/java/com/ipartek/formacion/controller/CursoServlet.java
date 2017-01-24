package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.service.CursoService;
import com.ipartek.formacion.service.CursoServiceImp;

/**
 * Servlet implementation class CursoServlet
 */
public class CursoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CursoService cS;
	private RequestDispatcher rd;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		cS = new CursoServiceImp();
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int operacion = -1;
		String mensaje = "";
		try {
			operacion = recogerOperacion(request);
			switch (operacion) {
				case Constantes.OP_CREATE:
					break;
				case Constantes.OP_READ: {
					cargarListaCursos(request);
				}
					break;
				case Constantes.OP_UPDATE:
					int codigo = cargarCodigo(request);
					procesarUpdate(codigo, request);

					break;
				case Constantes.OP_DELETE:
					break;
				default:

			}
		} catch (Exception e) {
			cargarListaCursos(request);
			e.printStackTrace();
		}

		rd.forward(request, response);
	}

	private void procesarUpdate(int codigo, HttpServletRequest request) {
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);
		request.setAttribute(Constantes.ATT_CURSO, cS.getById(codigo));
	}

	private int cargarCodigo(HttpServletRequest request) {
		int codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
		return codigo;
	}

	private void cargarListaCursos(HttpServletRequest request) {
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);
		request.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, cS.getAll());
	}

	private int recogerOperacion(HttpServletRequest request) {
		int op = Integer.parseInt(request.getParameter(Constantes.PAR_OPERACION));
		return op;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		rd.forward(request, response);
	}

}
