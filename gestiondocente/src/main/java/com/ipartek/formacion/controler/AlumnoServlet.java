package com.ipartek.formacion.controler;

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
		
		aS = new AlumnoServiceImp();
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//obtenemos la lista de alumnos
		List<Alumno> alumnos = aS.getAll();
		//(Redireccion limpia)response.sendRedirect("alumnos/listado.jsp");-->hace una direccion limpia
		//(FOWARD)trabajar con objeto tipo requestDispacher--> te da la url del Servlet
		//fijamos la landing page
		RequestDispatcher rd = request.getRequestDispatcher("alumnos/listado.jsp");
		//añadimos el atributo a la request
		request.setAttribute("listado-alunmos", alumnos);
		//hace la redireccion
		rd.forward(request,response); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
