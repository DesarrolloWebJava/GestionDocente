package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.service.CursoService;
import com.ipartek.formacion.service.CursoServiceImp;
import com.ipartek.formacion.service.Util;

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
				case Constantes.OP_CREATE: {
					procesarCreateOrUpdate(request);
				}
					break;
				case Constantes.OP_READ: {
					cargarListaCursos(request);
				}
					break;
				case Constantes.OP_UPDATE: {
					int codigo = cargarCodigo(request);
					Curso curso = cS.getById(codigo);
					request.setAttribute(Constantes.ATT_CURSO, curso);
					procesarCreateOrUpdate(request);
				}
					break;
				case Constantes.OP_DELETE: {
					int codigo = cargarCodigo(request);
					cS.delete(codigo);
					cargarListaCursos(request);
				}
					break;
				default:
			}
		} catch (Exception e) {
			cargarListaCursos(request);
			e.printStackTrace();
		}

		rd.forward(request, response);
	}

	private void procesarCreateOrUpdate(HttpServletRequest request) {
		rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);

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

		Curso curso = null;
		String mensaje = "";
		int codigo = -1;
		try {
			codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
			curso = recogerParametros(request);
			curso.setCodigo(codigo);
			// procesaremos UPDATE or INSERT
			if (curso.getCodigo() > Curso.CODIGO_NULO) {// update
				cS.update(curso);
				mensaje = "El curso ha sido actualizado correctamente";
			} else {// create
				cS.create(curso);
				mensaje = "El curso ha sido creado correctamente";
			}
			cargarListaCursos(request);
		} catch (NumberFormatException e) {
			mensaje = "Se ha producido una operación inesperada contacte con el administrador del sistema.";
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		} catch (Exception e) {
			if (codigo == -1) {// CREATE
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);

			} else {// UPDATE
				curso = cS.getById(codigo);
				request.setAttribute(Constantes.ATT_ALUMNO, curso);
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
			}
			mensaje = e.getMessage();
			System.out.println(mensaje);
		}
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(request, response);
	}

	private Curso recogerParametros(HttpServletRequest request) throws Exception {
		Curso curso = new Curso();
		try {
			String duracion = request.getParameter(Constantes.PAR_DURACION);
			int nDuracion = Integer.parseInt(duracion);
			curso.setDuracion(nDuracion);
			String fInicio = request.getParameter(Constantes.PAR_FINICIO);
			String fFin = request.getParameter(Constantes.PAR_FFIN);
			if (fInicio != null && !"".equalsIgnoreCase(fInicio)) {
				curso.setfInicio(Util.parseLatinDate(fInicio));
			}
			if (fFin != null && !"".equalsIgnoreCase(fFin)) {
				curso.setfFin(Util.parseLatinDate(fFin));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return curso;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	@Override
	public void destroy() {
		this.cS = null;
		super.destroy();
	}

}
