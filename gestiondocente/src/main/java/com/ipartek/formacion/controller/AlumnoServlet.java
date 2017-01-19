package com.ipartek.formacion.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int op = -1;
		String operacion = req.getParameter(Constantes.PAR_OPERACION);
		try{
			op = Integer.parseInt(operacion);
			switch (op)
			{
			case Constantes.OP_CREATE:
				// Se va redirigir a la pagina alumnos/alumno.jsp
				rd = req.getRequestDispatcher( "alumnos/alumno.jsp");
			break;
			case Constantes.OP_READ:
				
				cargarListaAlumnos(req);
			break;
			case Constantes.OP_UPDATE:
				//aS.getById(codigo);
				//req.setAttribute(name, o);
				// Se va redirigir a la pagina alumnos/alumno.jsp
				rd = req.getRequestDispatcher("alumnos/alumno.jsp");
			break;
			default :
				cargarListaAlumnos(req);
			break;
			}
			
		}
		catch(NumberFormatException e){
			
			//cargarListaAlumnos(req);
			// Forma 1 -> Redireccion Limpia.
			resp.sendRedirect(Constantes.JSP_HOME);
			return;
		}
		
		// Hace la redireccion
		rd.forward(req, resp);
		
	}

	private void cargarListaAlumnos(HttpServletRequest req) {
		
		
		// Obtenemos la lista de datos
		List<Alumno> alumnos = aS.getAll();
		// Añadimos el atributo a la request de la manera clave-valor
		req.setAttribute(Constantes.ATT_LISTADO_ALUMNOS,alumnos);
		
		// Forma 2 -> Engancha la peticion original.
		// Fijamos la pagina de destino
		rd= req.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Procesamos la peticion de create
		
		Alumno alumno = null;
		String mensaje = "";
		try {
			// Recoger Parametros y generar objeto Alumno
			alumno = recogerParametros(req);
			// Llamar a la capa service y realizar el create/insert
			if (alumno.getCodigo()>Alumno.CODIGO_NULO){ // UPDATE
				aS.update(alumno);
				mensaje="El Alumno ha sido actualizado correctamente";
			}else{ // CREATE
				aS.create(alumno);
				mensaje="El Alumno ha sido creado correctamente";
			}
			
			cargarListaAlumnos(req);
			
		} catch (Exception e) {
		   // redirigir al formulario 	
			rd = req.getRequestDispatcher("alumnos/alumno.jsp");
			mensaje = e.getMessage();
		}
		
		req.setAttribute("mensaje",mensaje);
		// Si excepcion llamar de nuevo al formulario
		rd.forward(req, resp);
	}

	private Alumno recogerParametros(HttpServletRequest req) throws  Exception{
		Alumno alumno = new Alumno();
		try{
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat formato = new SimpleDateFormat(pattern);
			Date fecha = new Date();
			
			alumno.setCodigo(Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO)));
			alumno.setNombre(req.getParameter(Constantes.PAR_NOMBRE));
			alumno.setApellidos(req.getParameter(Constantes.PAR_APELLIDOS));
			alumno.setDni(req.getParameter(Constantes.PAR_DNI));
			alumno.setEmail(req.getParameter(Constantes.PAR_EMAIL));
			alumno.setEmail(req.getParameter(Constantes.PAR_EMAIL));
			formato.parse(req.getParameter(Constantes.PAR_FNACIMIENTO));
			alumno.setfNacimiento(fecha);
				
			alumno.setnHermanos(Integer.parseInt(req.getParameter(Constantes.PAR_NHERMANOS)));
			alumno.setActivo(Boolean.parseBoolean(req.getParameter(Constantes.PAR_ACTIVO)));
		}
		catch (Exception e){
			throw new Exception("Los datos No son validos");
		}
		
		return alumno;
		
		
	}

	@Override
	public void destroy() {
		aS=null;
		super.destroy();
	}

	
}
