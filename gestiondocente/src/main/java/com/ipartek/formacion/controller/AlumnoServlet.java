package com.ipartek.formacion.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.dbms.pojo.exceptions.PersonaException;
import com.ipartek.formacion.service.AlumnoService;
import com.ipartek.formacion.service.AlumnoServiceImp;

/**
 * Servlet implementation class AlumnoServlet
 */
public class AlumnoServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(AlumnoServlet.class);
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
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNOS);
				break;
			case Constantes.OP_READ:
				cargarListaAlumnos(request);
				break;
			case Constantes.OP_UPDATE: {
				int codigo = -1;
				codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
				Alumno alumno = as.getById(codigo);
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNOS);
				request.setAttribute(Constantes.ATT_ALUMNO, alumno);

			}
				break;
			case Constantes.OP_DELETE: {
				int codigo = -1;
				codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
				as.delete(codigo);
				request.setAttribute(Constantes.ATT_MENSAJE, "El alumno ha sido borrado correctamente");
				cargarListaAlumnos(request);
			}
				break;
			default:
				cargarListaAlumnos(request);
				break;

			}

		} catch (Exception e) {

			// cargarListaAlumnos(request);
			response.sendRedirect(Constantes.JSP_HOME);
			return;
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
		Alumno alumno = null;
		String mensaje = null;
		int codigo = -1;
		try {

			codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));

			alumno = recogerParametros(req);
			alumno.setCodigo(codigo);
			// procesar insert y update
			if (alumno.getCodigo() > Alumno.CODIGO_NULO) {// update
				as.update(alumno);
				mensaje = "El alumno ha sido actuliazado correctamente";
			} else {// create
				as.create(alumno);
				mensaje = "El alumno se ha creado correctamente";
			}
			cargarListaAlumnos(req);

		} catch (Exception e) {
			// redirijo formulario de crear usuario
			if (codigo == -1) {
				rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNOS);
				System.out.println("Se ha producido un operacion inesperada");
				mensaje = e.getMessage();
			} else {
				alumno = as.getById(codigo);
				rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNOS);
				req.setAttribute(Constantes.ATT_ALUMNO, alumno);
				mensaje = e.getMessage();
			}
			System.out.println(mensaje);
			LOG.error(e.getMessage() + " valor del codigo del alumno " + alumno.getCodigo());
		}

		req.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(req, resp);
	}

	private Alumno recogerParametros(HttpServletRequest req) throws Exception {
		Alumno alumno = new Alumno();
		try {

			alumno.setNombre(req.getParameter(Constantes.PAR_NOMBRE));
			alumno.setApellidos(req.getParameter(Constantes.PAR_APELLIDOS));
			alumno.setDireccion(req.getParameter(Constantes.PAR_DIRECCION));
			alumno.setDni(req.getParameter(Constantes.PAR_DNI));
			alumno.setEmail(req.getParameter(Constantes.PAR_EMAIL));

			if (req.getParameter(Constantes.PAR_NHERMANOS) != null
					&& !"".equals(req.getParameter(Constantes.PAR_NHERMANOS))) {
				alumno.setnHermanos(Integer.parseInt(req.getParameter(Constantes.PAR_NHERMANOS)));
			}
			if ("1".equals(req.getParameter(Constantes.PAR_ACTIVO))) {
				alumno.setActivo(true);

			} else {

				alumno.setActivo(false);
			}

			String date = req.getParameter(Constantes.PAR_FNACIMIENTO);
			if (date != null && "" != date) {
				String pattern = "dd/MM/yyyy";
				SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
				alumno.setfNacimiento(dateFormat.parse(date));
			}

		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
			throw new PersonaException("Los datos son incorrectos " + e.getMessage());
		}
		return alumno;
	}

	@Override
	public void destroy() {
		as = null;
		super.destroy();
	}

}
