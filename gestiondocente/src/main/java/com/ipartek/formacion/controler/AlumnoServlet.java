package com.ipartek.formacion.controler;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.dbms.pojo.exceptions.PersonaException;
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
	/**
	 * Funcion que me lista todos la lista de  alumnos
	 * @param request
	 */

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
		Alumno alumno = null;
		String mensaje="";
		try {
			alumno = recogerParametros(request);
			
			if(alumno.getCodigo()>Alumno.CODIGO_NULO){
				aS.update(alumno);
				mensaje ="El alumno ha sido actualizado correctamente";
			}else{
				aS.create(alumno);
				mensaje ="El alumno correctamente creado";
			}
			cargarListaAlumnos(request);
			//procesaremos update or insert
		} catch (Exception e) {
			//redirigir al formulario
			rd = request.getRequestDispatcher("alumnos/alumno.jsp");
			//mensaje de error
			mensaje= e.getMessage();
			
		}
		request.setAttribute("menseje", mensaje);
		rd.forward(request,response); 
	}

	private Alumno recogerParametros(HttpServletRequest request) throws Exception {
		
		Alumno alumno = new Alumno();
		try{
			
			int codigo =Integer.parseInt( request.getParameter(Constantes.PAR_CODIGO));
			alumno.setCodigo(codigo);
			alumno.setNombre( request.getParameter(Constantes.PAR_NOMBRE));
			alumno.setApellidos( request.getParameter(Constantes.PAR_APELLIDO));
			alumno.setDni( request.getParameter(Constantes.PAR_DNI));
			
			String date= request.getParameter(Constantes.PAR_FNACIMIENTO);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			alumno.setfNacimiento( dateFormat.parse(date));
			
			alumno.setDireccion( request.getParameter(Constantes.PAR_DIRECCION));
			alumno.setEmail( request.getParameter(Constantes.PAR_EMAIL));
			alumno.setnHermanos(Integer.parseInt(request.getParameter(Constantes.PAR_NHERMANOS)));
			alumno.setActivo(Boolean.parseBoolean( request.getParameter(Constantes.PAR_ACTIVO)));
		}catch(Exception e){
			
			//throw new Exception(e.getMessage());
			throw new Exception("los datos no son validos");
		}
		
		
		//String nombre = request.getParameter("<%Constantes.PAR_NOMBRE%>");
		
		return alumno;
	}

	@Override
	public void destroy() {
		aS = null;
		super.destroy();
	}

}
