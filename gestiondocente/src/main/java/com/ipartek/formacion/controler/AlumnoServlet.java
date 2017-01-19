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
		//recoger parametro de la peticion del jsp al servlet
		String opercacion = request.getParameter(Constantes.PAR_OPERACION);
		//siempre q recogamos parametros de una url o de un formulario ->try catch
		
		
		//tengo que hacer un parse, pq el parametro me viene en string y yo quiero un -integer
		int op = -1;
		try{
			
			op = Integer.parseInt(opercacion);
			switch(op){
			 case Constantes.OP_CREATE:
				 //se va redirigir a la pagina alumnos/alumno.jsp
				 rd=  request.getRequestDispatcher("alumnos/alumno.jsp");
				 
				 break;
			 case Constantes.OP_READ:
				 cargarListaAlumnos(request);
				 break;
			 case Constantes.OP_UPDATE:
				 //tendriamos q recibir como parametro el codigo del alumno a modificar
				 break;
			default:
				cargarListaAlumnos(request);
				break;
				 
			}
			
		}
		catch (Exception e){
			response.sendRedirect(Constantes.JSP_HOME);
			return;
			//cargarListaAlumnos(request);
		}
		
		//hace la redireccion
		rd.forward(request,response); 
		
	}

	private void cargarListaAlumnos(HttpServletRequest request) {
		//obtenemos la lista de alumnos
		List<Alumno> alumnos = aS.getAll();
		//(Redireccion limpia)response.sendRedirect("alumnos/listado.jsp");-->hace una direccion limpia
		//(FOWARD)trabajar con objeto tipo requestDispacher--> te da la url del Servlet
		//fijamos la landing page
		rd = request.getRequestDispatcher("alumnos/listado.jsp");
		//añadimos el atributo a la request
		request.setAttribute("listado-alunmos", alumnos);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public void destroy() {
		aS = null;
		super.destroy();
	}

}
