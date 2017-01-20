package com.ipartek.formacion.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
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

		String operacion = request.getParameter(Constantes.PAR_OPERACION);
		int op = -1;

		try {
			op = Integer.parseInt(operacion);
			switch (op) {
			case Constantes.OP_CREATE:
				// se va a redirigir a la pagina alumnos/alumno.do
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESORES);
				break;
			case Constantes.OP_READ:
				cargarListaProfesores(request);
				break;
			case Constantes.OP_UPDATE:

				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESORES);
				break;
			default:
				cargarListaProfesores(request);
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

	private void cargarListaProfesores(HttpServletRequest request) {
		// TODO Auto-generated method stub
		// recogemos la lista de profesores
		Map<Integer, Profesor> profesores = (Map<Integer, Profesor>) ps.getAll();
		// fijamos la pagina de destino
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_PROFESORES);
		// añadimos el atributo a la request
		request.setAttribute(Constantes.ATT_LISTADO_PROFESORES, profesores);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Profesor profesor = null;
		String mensaje = null;

		try {
			profesor = recogerParametros(request);

			// procesar insert y update
			if (profesor.getCodigo() > Profesor.CODIGO_NULO) {// update
				ps.update(profesor);
				mensaje = "El profesor ha sido actuliazado correctamente";
			} else {// create
				ps.create(profesor);
				mensaje = "El profesor se ha creado correctamente";
			}
			cargarListaProfesores(request);
		} catch (Exception e) {
			// redirijo formulario de crear usuario
			rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESORES);
			mensaje = e.getMessage();

		}
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(request, response);
	}

	private Profesor recogerParametros(HttpServletRequest request) throws Exception {
		Profesor profesor = new Profesor();
		try {
			int codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
			profesor.setCodigo(codigo);
			profesor.setNombre(request.getParameter(Constantes.PAR_NOMBRE));
			profesor.setApellidos(request.getParameter(Constantes.PAR_APELLIDOS));
			profesor.setDireccion(request.getParameter(Constantes.PAR_DIRECCION));
			profesor.setDni(request.getParameter(Constantes.PAR_DNI));
			profesor.setEmail(request.getParameter(Constantes.PAR_EMAIL));

			if (request.getParameter(Constantes.PAR_NSS) != null
					&& !"".equals(request.getParameter(Constantes.PAR_NSS))) {
				profesor.setnSS(Integer.parseInt(request.getParameter(Constantes.PAR_NSS)));
			}

			String date = request.getParameter(Constantes.PAR_FNACIMIENTO);
			if (date != null && "" != date) {
				String pattern = "dd/MM/yyyy";
				SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
				profesor.setfNacimiento(dateFormat.parse(date));
			}

		} catch (

		Exception e) {
			e.printStackTrace();
			throw new Exception("Los datos son incorrectos " + e.getMessage());
		}
		return profesor;
	}

}
