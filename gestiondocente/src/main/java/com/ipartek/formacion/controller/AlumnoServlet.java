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
	 * El servlet se ejecuta el solo por eso no se usa el constructor.
	 * El metodo init() le dice que hacer al iniciar el Servlet como si
	 * fuera un constructor
	 */
	@Override
	public void init() throws ServletException {
		/*
		 * En los servlet se programa antes de la llamada al padre porque 
		 * cuando se llama al padre se sale.
		 * Se inicializa AlumnoServiceImp que tiene los metodos CRUD
		 */
		aS = new AlumnoServiceImp();
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Obtenemos los datos de los alumnos y los cargamos 
		 * en la variable alumnos
		 */
		List<Alumno> alumnos = aS.getAll();
		
		//Esto nos redirecciona
		//response.sendRedirect("Alumnos/listado.jsp");
		/*
		 * Fijamos la pagina de destino
		 */
		RequestDispatcher rd = request.getRequestDispatcher("Alumnos/listado.jsp");
		/*
		 * listado-alumno es una key
		 * Y alumno es el objeto lista
		 * request lo manda a la VISTA
		 *  Añadimos el atributo a la request(peticion)
		 */
		request.setAttribute("listado-alumnos", alumnos);
		/*
		 * Hace la redireccion
		 */
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
		//Solo para peticiones de formularios
}
