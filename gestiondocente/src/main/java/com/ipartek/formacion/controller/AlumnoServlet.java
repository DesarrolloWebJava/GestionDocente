package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.service.AlumnoService;
import com.ipartek.formacion.service.AlumnoServiceImp;

/**
 * Servlet implementation class AlumnoServlet
 */
public class AlumnoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AlumnoService aS;  
    private RequestDispatcher rd;
   
	@Override
	public void init() throws ServletException {
		aS = new AlumnoServiceImp();
		super.init();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("alumnos/listado.jsp");
		//limpia.
		//
		String operacion = request.getParameter(Constantes.PAR_OPERACION);
		int op = -1;
		try{
			op = Integer.parseInt(operacion);
			switch (op){
				case Constantes.OP_CREATE:
					rd = request.getRequestDispatcher(Constantes.JSP_CREAR_ALUMNOS);
					break;
				case Constantes.OP_READ:
					cargarListaAlumnos(request);
					break;
				case Constantes.OP_UPDATE:
					//aS.getById(codigo);
					rd = request.getRequestDispatcher(Constantes.JSP_CREAR_ALUMNOS);
					//request.setAttribute(arg0, arg1);
					break;
				case Constantes.OP_DELETE:
					break;
				default:
					cargarListaAlumnos(request);
					break;
			}
		} catch(Exception e){
			//response.sendRedirect(Constantes.JSP_HOME);
			cargarListaAlumnos(request);
		}
		rd.forward(request, response);
	}
	
	private void cargarListaAlumnos(HttpServletRequest request) {
		List<Alumno> alumnos = aS.getAll();
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
		request.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, alumnos);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	@Override
	public void destroy() {
		aS = null;
		super.destroy();
	}

	
}
