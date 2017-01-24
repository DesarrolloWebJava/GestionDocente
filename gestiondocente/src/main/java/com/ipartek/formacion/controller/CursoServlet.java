package com.ipartek.formacion.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.service.AlumnoService;
import com.ipartek.formacion.service.AlumnoServiceImp;
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
     * @see HttpServlet#HttpServlet()
     */
    
    public void init() throws ServletException{
    	cS = new CursoServiceImp();
		super.init();  	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String operacion = req.getParameter(Constantes.PAR_OPERACION);
		int op = -1;
		try {
			op = Integer.parseInt(operacion);
			switch (op) {
				case Constantes.OP_CREATE:
					// se va redirigir a la pagina alumnos/alumno.jsp
					rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
					break;
				case Constantes.OP_READ:
					cargarListaCursos(req);
					break;
				case Constantes.OP_UPDATE: {
					int codigo = -1;
					codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
					Curso curso = cS.getById(codigo);
					rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
					req.setAttribute(Constantes.ATT_CURSO, curso);
				}
					break;
				case Constantes.OP_DELETE: {
					int codigo = -1;
					codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
					cS.delete(codigo);
					req.setAttribute(Constantes.ATT_MENSAJE, "El curso ha sido borrado correctamente");
					cargarListaCursos(req);
				}
					break;
				default:
					cargarListaCursos(req);
					break;
			}

		} catch (Exception e) {
			// cargarListaAlumnos(req);
			System.out.println(e.getMessage());
			resp.sendRedirect(Constantes.JSP_HOME);
			return;
		}

		// hace la redirección
		rd.forward(req, resp);

	}

	private void cargarListaCursos(HttpServletRequest req) {
		
		List<Curso> cursos = cS.getAll();
		rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);
		req.setAttribute(Constantes.ATT_LISTADO_CURSOS, cursos);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Curso curso = null;
		String mensaje = "";
		int codigo = -1;
		try {

			codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));

			curso = recogerParametros(req);
			curso.setCodigo(codigo);
			// procesaremos UPDATE or INSERT
			if (curso.getCodigo() > Curso.CODIGO_NULO) {// update
				cS.update(curso);
				mensaje = "El curso ha sido actualizado correctamente";
			} else {// create
				cS.create(curso);
				mensaje = "El curso ha sido creado correctamente";
			}
			cargarListaCursos(req);
		} catch (NumberFormatException e) {
			mensaje = "Se ha producido una operación inesperada contacte con el administrador del sistema.";
			rd = req.getRequestDispatcher(Constantes.JSP_HOME);
			e.printStackTrace();
		} catch (Exception e) {
			if (codigo == -1) {// CREATE
				rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);

			} else {// UPDATE
				curso = cS.getById(codigo);
				req.setAttribute(Constantes.ATT_CURSO, curso);
				rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
			}
			mensaje = e.getMessage();
			System.out.println(mensaje);
			e.printStackTrace();
		}
		req.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(req, resp);
	}

	private Curso recogerParametros(HttpServletRequest req) throws ParseException {
		Curso curso = new Curso();
		curso.setNombre(req.getParameter(Constantes.PAR_NOMBRE));
		
		curso.setDuracion(Integer.parseInt(req.getParameter(Constantes.PAR_DURACION)));
		
		String date = req.getParameter(Constantes.PAR_FECHAINICIO);
		if (date != null && !"".equals(date)) {
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			curso.setFechaInicio(dateFormat.parse(date));
		}
		
		date = req.getParameter(Constantes.PAR_FECHAFIN);
		if (date != null && !"".equals(date)) {
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			curso.setFechaFin(dateFormat.parse(date));
		}
		
		return curso;
	}
	
	@Override
	public void destroy() {
		cS = null;
		super.destroy();
	}

}
