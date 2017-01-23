package com.ipartek.formacion.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
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
	private ProfesorService pS;
	private RequestDispatcher rd;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		pS = new ProfesorServiceImp();
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
					// se va redirigir a la pagina alumnos/alumno.jsp
					rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
					break;
				case Constantes.OP_READ:
					cargarListaProfesores(request);
					break;
				case Constantes.OP_UPDATE: {
					int codigo = -1;
					codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
					Profesor profesor = pS.getById(codigo);
					rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
					request.setAttribute(Constantes.ATT_PROFESOR, profesor);

				}
					break;
				case Constantes.OP_DELETE: {
					int codigo = -1;
					codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
					pS.delete(codigo);
					request.setAttribute(Constantes.ATT_MENSAJE, "El profesor ha sido borrado correctamente");
					cargarListaProfesores(request);
				}
					break;
				default:
					cargarListaProfesores(request);
					break;
			}

		} catch (Exception e) {
			// cargarListaAlumnos(req);
			System.out.println("doGet:" + e.getMessage());
			response.sendRedirect(Constantes.JSP_HOME);
			return;
		}

		// hace la redirección
		rd.forward(request, response);
	}

	/**
	 * @param request
	 */
	private void cargarListaProfesores(HttpServletRequest request) {
		Map<Integer, Profesor> profesores = pS.getAll();
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_PROFESORES);
		request.setAttribute(Constantes.ATT_LISTADO_PROFESORES, profesores);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Profesor profesor = null;
		String mensaje = "";
		int codigo = -1;
		try {
			codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
			profesor = recogerParametros(request);
			profesor.setCodigo(codigo);
			// procesaremos UPDATE or INSERT
			if (profesor.getCodigo() > Profesor.CODIGO_NULO) {// update
				pS.update(profesor);
				mensaje = "El profesor ha sido actualizado correctamente";
			} else {// create
				pS.create(profesor);
				mensaje = "El profesor ha sido creado correctamente";

			}
			cargarListaProfesores(request);
		} catch (NumberFormatException e) {
			mensaje = "Se ha producido una operación inesperada contacte con el administrador del sistema.";
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		} catch (Exception e) {
			if (codigo == -1) {// CREATE
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);

			} else {// UPDATE
				profesor = pS.getById(codigo);
				request.setAttribute(Constantes.ATT_ALUMNO, profesor);
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
			}
			mensaje = e.getMessage();
			System.out.println(mensaje);
		}
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(request, response);
	}

	private Profesor recogerParametros(HttpServletRequest request) throws Exception {
		Profesor profesor = new Profesor();
		try {

			profesor.setNombre(request.getParameter(Constantes.PAR_NOMBRE));
			profesor.setApellidos(request.getParameter(Constantes.PAR_APELLIDOS));
			profesor.setDireccion(request.getParameter(Constantes.PAR_DIRECCION));
			profesor.setDni(request.getParameter(Constantes.PAR_DNI));
			profesor.setEmail(request.getParameter(Constantes.PAR_EMAIL));

			String nSS_s = request.getParameter(Constantes.PAR_NSS);
			if (nSS_s != null && !nSS_s.equals("")) {
				int nss = Integer.parseInt(nSS_s);
				profesor.setnSS(nss);
			}

			String date = request.getParameter(Constantes.PAR_FNACIMIENTO);
			if (date != null && !"".equals(date)) {
				String pattern = "dd/MM/yyyy";
				SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
				profesor.setfNacimiento(dateFormat.parse(date));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Los datos no son validos: " + e.getMessage());
		}
		return profesor;
	}

}
