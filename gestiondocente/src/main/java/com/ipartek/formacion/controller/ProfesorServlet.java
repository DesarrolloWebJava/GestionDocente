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
	private ProfesorService proSe;
	private RequestDispatcher rd;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		proSe = new ProfesorServiceImp();
		super.init();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		proSe=null;
		super.destroy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String operacion = req.getParameter(Constantes.PAR_OPERACION);
		int op = -1;
		try {
			op = Integer.parseInt(operacion);
			switch (op) {
				case Constantes.OP_CREATE:
					// se va redirigir a la pagina "profesores/profesor.jsp"
					rd = req.getRequestDispatcher("profesores/profesor.jsp");
					break;
				case Constantes.OP_READ:
					cargarListaProfesores(req);
					break;
				case Constantes.OP_UPDATE:
					// aS.getById(codigo)
					// se va redirigir a la pagina "profesores/profesor.jsp"
					rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
					// req.setAttribute(arg0, arg1);
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


	
	
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Profesor profe = null;
		String mensaje = "";
		try {
			profe = recogerParametros(req);

			// procesaremos UPDATE or INSERT
			if (profe.getCodigo() > profe.CODIGO_NULO) {// update
				proSe.update(profe);
				mensaje = "El profe ha sido actualizado correctamente";
			} else {// create
				proSe.create(profe);
				mensaje = "El profe ha sido creado correctamente";
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
		Profesor profe = new Profesor();
		try {
			int codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
			profe.setCodigo(codigo);
			profe.setNombre(req.getParameter(Constantes.PAR_NOMBRE));
			profe.setApellidos(req.getParameter(Constantes.PAR_APELLIDOS));
			profe.setDireccion(req.getParameter(Constantes.PAR_DIRECCION));
			profe.setDni(req.getParameter(Constantes.PAR_DNI));
			profe.setEmail(req.getParameter(Constantes.PAR_EMAIL));

			

			String date = req.getParameter(Constantes.PAR_FNACIMIENTO);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			profe.setfNacimiento(dateFormat.parse(date));
		} catch (Exception e) {
			throw new Exception("Los datos no son validos: " + e.getMessage());
		}
		return profe;
	}
	
	private void cargarListaProfesores(HttpServletRequest req) {

		
		Map<Integer, Profesor> profesores = proSe.getAll();
		// fijamos la página de destino
		 rd = req.getRequestDispatcher("profesores/listado.jsp");
		// añadimos el atributo a la request
		req.setAttribute("listado-profesores", profesores);
	}


}
