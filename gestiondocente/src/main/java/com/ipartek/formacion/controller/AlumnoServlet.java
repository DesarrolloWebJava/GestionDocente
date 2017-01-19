package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

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
    	aS= new AlumnoServiceImp();
    	super.init();
    	// aS= new AlumnoServiceImp(); esto aqui no se ejecuta. 
    	// El flujo tiene que ir antes quel el super)
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {	
		//recoger el parametro "op"
		String operacion = request.getParameter(Constantes.PAR_OPERACION);
		int op= -1;
		try{
			op= Integer.parseInt(operacion);
			switch (op){
				case Constantes.OP_CREATE:
					//se va a redirigir a la pagina alumnos/alumno.jsp
					rd = request.getRequestDispatcher("alumnos/alumno.jsp");
					break;
				case Constantes.OP_READE:
					cargarListaAlumnos(request);
					break;
				case Constantes.OP_UPDATE:
					// falta de hacer TODO aS.getById(codigo)
					//se va a redirigir a la pagina alumnos/alumno.jsp
					rd = request.getRequestDispatcher("alumnos/alumno.jsp"); //programar el formulario
					// falta de hacer TODO request.setAttribute(arg0, arg1);
					break;
				default:
					cargarListaAlumnos(request);
					break;
			}
			
		} catch (Exception e){
			resp.sendRedirect(Constantes.JSP_LISTADO_ALUMNOS);
			return;
			//cargarListaAlumnos(request);

		}
		// hace la redireccion.
		rd.forward(request, resp);	
	}

	private void cargarListaAlumnos(HttpServletRequest request) {
		//resp.sendRedirect("alumnos/listado.jsp"); //limpia
		// obtenemos la lista de datos.
		List<Alumno> alumnos = aS.getAll();
		// fijamos la página destino.
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
		// añadimos el atributo a la request.
		request.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, alumnos);
		// hace la redirección.
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
	
	@Override
	public void destroy() {
		aS = null;
		super.destroy();
	}
	
}
