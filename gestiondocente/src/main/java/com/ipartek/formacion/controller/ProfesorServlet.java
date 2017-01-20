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
	private ProfesorService pS;
	private RequestDispatcher rd;
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<Integer, Profesor> profesores = pS.getAll();

		/*rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_PROFESORES);
		
		req.setAttribute(Constantes.ATT_LISTADO_PROFESORES, profesores);
		
		rd.forward(req, resp);*/
		
		String operacion = req.getParameter(Constantes.PAR_OPERACION);
		int op = -1;
		try {
			op = Integer.parseInt(operacion);
			switch (op) {
				case Constantes.OP_CREATE:
					// se va redirigir a la pagina profesores/profesor.jsp
					rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
					break;
				case Constantes.OP_READ:
					cargarListaProfesores(req);
					break;
				case Constantes.OP_UPDATE:
					rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
					break;
				default:
					cargarListaProfesores(req);
					break;
			}

		} catch (Exception e) {
			// cargarListaAlumnos(req);
			resp.sendRedirect(Constantes.JSP_HOME);
			return;
		}

		// hace la redirección
		rd.forward(req, resp);

	}

	private void cargarListaProfesores(HttpServletRequest req) {
		Map<Integer, Profesor> profesores = pS.getAll();
		rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_PROFESORES);
		req.setAttribute(Constantes.ATT_LISTADO_PROFESORES, profesores);
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Profesor profesor = null;
		String mensaje = "";
		try {
			profesor = recogerParametros(req);

			// procesaremos UPDATE or INSERT
			if (profesor.getCodigo() > Profesor.CODIGO_NULO) {// update
				pS.update(profesor);
				mensaje = "El profesor ha sido actualizado correctamente";
			} else {// create
				pS.create(profesor);
				mensaje = "El profesor ha sido creado correctamente";
			}
			cargarListaProfesores(req);
		} catch (Exception e) {
			// redirigir al formulario
			rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
			mensaje = e.getMessage();
			System.out.println(mensaje);
		}
		req.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(req, resp);
	}

	private Profesor recogerParametros(HttpServletRequest req) throws Exception {
		Profesor profesor = new Profesor();
		try {
			int codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
			profesor.setCodigo(codigo);
			profesor.setNombre(req.getParameter(Constantes.PAR_NOMBRE));
			profesor.setApellidos(req.getParameter(Constantes.PAR_APELLIDOS));
			profesor.setDireccion(req.getParameter(Constantes.PAR_DIRECCION));
			profesor.setDni(req.getParameter(Constantes.PAR_DNI));
			profesor.setEmail(req.getParameter(Constantes.PAR_EMAIL));
			
			String nSS_s= req.getParameter(Constantes.PAR_NSS);
			if(nSS_s != null && !"".equals(nSS_s)){
				profesor.setnSS(Integer.parseInt(req.getParameter(Constantes.PAR_NSS)));
			}
			
			String date = req.getParameter(Constantes.PAR_FNACIMIENTO);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			profesor.setfNacimiento(dateFormat.parse(date));
		} catch (Exception e) {
			throw new Exception("Los datos no son validos: " + e.getMessage());
		}
		return profesor;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		pS = new ProfesorServiceImp();
		super.init();
	}
       
    

}
