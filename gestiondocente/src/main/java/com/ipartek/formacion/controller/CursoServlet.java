package com.ipartek.formacion.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.dbms.pojo.exceptions.CursoException;
import com.ipartek.formacion.service.CursoService;
import com.ipartek.formacion.service.CursoServiceImp;

/**
 * Servlet implementation class CursoServlet
 */
public class CursoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CursoService cs;
	private RequestDispatcher rd;

	@Override
	public void init() throws ServletException {
		cs = new CursoServiceImp();
		super.init();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String operacion = request.getParameter(Constantes.PAR_OPERACION);
		int op = -1;
		try {

			op = Integer.parseInt(operacion);

			switch (op) {
			case Constantes.OP_CREATE:
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSOS);
				break;
			case Constantes.OP_READ:
				cargarListaCursos(request);
				break;
			case Constantes.OP_UPDATE: {
				int codigo = -1;
				codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
				Curso curso = cs.getById(codigo);
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSOS);
				request.setAttribute(Constantes.ATT_CURSO, curso);
			}

				break;
			case Constantes.OP_DELETE: {
				int codigo = -1;
				codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
				cs.delete(codigo);

				request.setAttribute(Constantes.ATT_MENSAJE, "El curso ha sido correctamente borrado");
				cargarListaCursos(request);
			}
				break;

			}
		} catch (Exception e) {

			String mensaje = "A opcion del progrma es incorrecta";
			request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		}
		rd.forward(request, response);
	}

	private void cargarListaCursos(HttpServletRequest request) {

		List<Curso> cursos = (List<Curso>) cs.getAll();

		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);

		request.setAttribute(Constantes.ATT_LISTADO_CURSOS, cursos);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Curso curso = null;
		int codigo = -1;
		String mensaje = null;
		try {

			codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
			curso = recogerParametros(request);
			curso.setCodigo(codigo);

			if (curso.getCodigo() > Curso.CODIGO_NULO) {// update
				cs.update(curso);
				mensaje = "El curso ha sido actualizado correctamente";

			} else {// create
				cs.create(curso);
				mensaje = "El curso ha sido creado";

			}
			cargarListaCursos(request);
			rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);

		} catch (Exception e) {
			mensaje = e.getMessage();
			rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSOS);

		}
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(request, response);
	}

	private Curso recogerParametros(HttpServletRequest request) throws Exception {
		Curso curso = new Curso();
		try {
			curso.setNombre(request.getParameter(Constantes.PAR_NOMBRE));
			int dur = Integer.parseInt(request.getParameter(Constantes.PAR_DURACION));
			curso.setDuracion(dur);

			String date = request.getParameter(Constantes.PAR_FINICIO);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			curso.setfInicio(dateFormat.parse(date));

			String date1 = request.getParameter(Constantes.PAR_FFINAL);
			String pattern1 = "dd/MM/yyyy";
			SimpleDateFormat dateFormat1 = new SimpleDateFormat(pattern1);
			curso.setfFinal(dateFormat1.parse(date1));

		} catch (Exception e) {
			e.printStackTrace();
			throw new CursoException("Los datos son incorrectos " + e.getMessage());
		}

		return curso;
	}

	@Override
	public void destroy() {
		cs = null;
		super.destroy();
	}

}
