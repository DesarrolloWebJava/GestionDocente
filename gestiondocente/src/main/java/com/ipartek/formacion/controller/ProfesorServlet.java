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
				case Constantes.OP_UPDATE:
					// aS.getById(codigo)
					// se va redirigir a la pagina alumnos/alumno.jsp
					rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
					// req.setAttribute(arg0, arg1);
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
		try {
			profesor = recogerParametros(request);
			// procesaremos UPDATE or INSERT
			if (profesor.getCodigo() > Profesor.CODIGO_NULO) {// update
				pS.update(profesor);
				mensaje = "El profesor ha sido actualizado correctamente";
			} else {// create
				pS.create(profesor);
				mensaje = "El profesor ha sido creado correctamente";
			}
			cargarListaProfesores(request);
		} catch (Exception e) {
			// redirigir al formulario
			rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
			mensaje = e.getMessage();
			System.out.println(mensaje);
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
			int nss = Integer.parseInt(request.getParameter(Constantes.PAR_NSS));
			profesor.setnSS(nss);

			String date = request.getParameter(Constantes.PAR_FNACIMIENTO);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			profesor.setfNacimiento(dateFormat.parse(date));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Los datos no son validos: " + e.getMessage());
		}
		return profesor;
	}

}
