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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Botones, hipervinculos... son get
		
		
		List<Alumno> alumno = aS.getAll();//cargamos la lista de datos
		RequestDispatcher rd = req.getRequestDispatcher("alumnos/listado.jsp");//fijamos la pagina de destino
		req.setAttribute("listado-alumnos", alumno);//Añadimos los atributos a la request
		rd.forward(req, resp);//hace la redireccion
		//otra opcion
		//resp.sendRedirect("alumnos/listado.jsp");
		
	}

	@Override
	public void init() throws ServletException {
		aS=new AlumnoServiceImp();
		super.init();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Los formularios son post
	}

}
