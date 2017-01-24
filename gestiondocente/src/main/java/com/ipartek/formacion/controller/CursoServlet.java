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
import com.ipartek.formacion.service.CursoService;
import com.ipartek.formacion.service.CursoServiceImp;

/**
 * Servlet implementation class CursoServlet
 */
public class CursoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CursoService cursoSer;
	private RequestDispatcher rd;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CursoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public void init() throws ServletException {
		cursoSer = new CursoServiceImp();
		super.init();
		// aS = new AlumnoServiceImp(); --> esto aqui no se ejecuta
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
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
					rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
					break;
				case Constantes.OP_READ:
					cargarListaCurso(req);
					break;
				case Constantes.OP_UPDATE: {
					int codigo = -1;
					codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
					Curso curso = cursoSer.getById(codigo);
					rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
					req.setAttribute(Constantes.ATT_CURSO, curso);
				}
					break;
				case Constantes.OP_DELETE: {
					int codigo = -1;
					codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
					cursoSer.delete(codigo);
					req.setAttribute(Constantes.ATT_MENSAJE, "El curso ha sido borrado correctamente");
					cargarListaCurso(req);
				}
					break;
				default:
					cargarListaCurso(req);
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

	/**
	 * @param req
	 */
	private void cargarListaCurso(HttpServletRequest req) {
		// resp.sendRedirect("alumnos/listado.jsp"); --> hace una redireccion
		// limpia.
		// obtenemos la lista de datos.
		List<Curso> listaCurso = cursoSer.getAll();
		// fijamos la página de destino
		rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);
		// añadimos el atributo a la request
		req.setAttribute(Constantes.ATT_LISTADO_CURSOS, listaCurso);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Curso curso = null;
		String mensaje = "";
		try {
			curso = recogerParametros(req);
			// procesaremos UPDATE or INSERT
			if (curso.getCodigo() > curso.CODIGO_NULO) {// update
				cursoSer.update(curso);
				mensaje = "El curso ha sido actualizado correctamente";
			} else {// create
				cursoSer.create(curso);
				mensaje = "El curso ha sido creado correctamente";
			}
			cargarListaCurso(req);
		} catch (Exception e) {
			// redirigir al formulario
			rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
			mensaje = e.getMessage();
			System.out.println(mensaje);
		}
		req.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(req, resp);
	}

	private Curso recogerParametros(HttpServletRequest req) throws Exception {
		Curso curso = new Curso();
		try {
			int codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
			curso.setCodigo(codigo);
			curso.setNombre(req.getParameter(Constantes.PAR_NOMBRE));
			

			String duracion = req.getParameter(Constantes.PAR_DURACION);
			if (!"".equalsIgnoreCase(duracion)) {
				curso.setDuracion(Integer.parseInt(duracion));
			}

			

			String date = req.getParameter(Constantes.PAR_FINICIO);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			curso.setfInicio(dateFormat.parse(date));
			
			String date2 = req.getParameter(Constantes.PAR_FFIN);
		
			SimpleDateFormat dateFormat2 = new SimpleDateFormat(pattern);
			curso.setfFin(dateFormat2.parse(date2));
		} catch (Exception e) {
			throw new Exception("Los datos no son validos: " + e.getMessage());
		}
		return curso;
	}

	
	@Override
	public void destroy() {
		cursoSer = null;
		super.destroy();
	}

}
