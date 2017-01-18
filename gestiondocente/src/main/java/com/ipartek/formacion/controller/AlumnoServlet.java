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
	
	@Override
	public void init() throws ServletException {
		aS=new AlumnoServiceImp();

		super.init();
	}
       
    
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.sendRedirect("alumnos/listado.jsp");
		//http://localhost:8080/gestiondocente/alumnos/listado.jsp
		
	
		List<Alumno> alumnos=aS.getAll();
		RequestDispatcher rd=request.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
		request.setAttribute(Constantes.ATT_LISTADO_ALUMNOS,alumnos);
		rd.forward(request, response);
		//http://localhost:8080/gestiondocente/alumno.do
		
		

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
