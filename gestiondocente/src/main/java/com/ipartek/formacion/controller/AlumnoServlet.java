package com.ipartek.formacion.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.dbms.pojo.exceptions.PersonaException;
import com.ipartek.formacion.service.AlumnoService;
import com.ipartek.formacion.service.AlumnoServiceImp;

/**
 * Servlet implementation class AlumnoServlet
 */
public class AlumnoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(AlumnoServlet.class);
	
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
				rd = req.getRequestDispatcher( Constantes.JSP_FORMULARIO_ALUMNO);
			break;
			case Constantes.OP_READ:
				cargarListaAlumnos(req);
		
			break;
			case Constantes.OP_UPDATE:
			{
				int codigo = -1;
				codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
				Alumno alumno = aS.getById(codigo);
				req.setAttribute(Constantes.ATT_ALUMNO,alumno);
				// Se va redirigir a la pagina alumnos/alumno.jsp
				rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
			}
			break;
			case Constantes.OP_DELETE:
			{
				int codigo = -1;
				codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
				aS.delete(codigo);
				req.setAttribute(Constantes.ATT_MENSAJE,"El Alumno ha sido borrado correctamente");
				cargarListaAlumnos(req);
			}
			break;
			default :
				cargarListaAlumnos(req);
				
			break;
			}
			
		}
		catch(Exception e){
			
			//cargarListaAlumnos(req);
			// Forma 1 -> Redireccion Limpia.
			LOG.error(e.getMessage());
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
		int codigo = -1;
		String mensaje = "";
		try {
			codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
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
			
		} 
		catch (NumberFormatException e)
		{
			mensaje = "Se ha producido un error inesperado, contacte con el administrador";
			LOG.error(e.getMessage() + " Valor de la Variable: " + req.getParameter(Constantes.PAR_CODIGO) );
			rd = req.getRequestDispatcher(Constantes.JSP_HOME);
		}
		catch (Exception e) {
		   // redirigir al formulario 	
			LOG.error(e.getMessage());
			if (codigo == -1){
			 // rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
			 // req.setAttribute(Constantes.ATT_LISTADO_ALUMNO, alumno);
			 // mensaje = "Se ha producido una operacion inesperada contacte con el administrador";
			 //cargarListaAlumnos(req);
			 	 
			mensaje = e.getMessage();
	     	rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
		 	req.setAttribute(Constantes.ATT_ALUMNO, alumno);
			}
			else
			{
			 alumno = aS.getById(codigo);
			 rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
			 req.setAttribute(Constantes.ATT_ALUMNO, alumno);
			 mensaje = e.getMessage();
			
			}
		}
		
		req.setAttribute("mensaje",mensaje);
		// Si excepcion llamar de nuevo al formulario
		rd.forward(req, resp);
	}

	private Alumno recogerParametros(HttpServletRequest req) throws  Exception{
		Alumno alumno = new Alumno();
		try{
		
			alumno.setCodigo(Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO)));
			alumno.setNombre(req.getParameter(Constantes.PAR_NOMBRE));
			alumno.setApellidos(req.getParameter(Constantes.PAR_APELLIDOS));
			alumno.setDni(req.getParameter(Constantes.PAR_DNI));
			alumno.setEmail(req.getParameter(Constantes.PAR_EMAIL));
			alumno.setEmail(req.getParameter(Constantes.PAR_EMAIL));
			
			String date = req.getParameter(Constantes.PAR_FNACIMIENTO);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			alumno.setfNacimiento(dateFormat.parse(date));
			
		    String nHermanos = req.getParameter(Constantes.PAR_NHERMANOS);
		    if ("".equals(nHermanos))
		    { 
		    	nHermanos = "0";
		    
		    }
		    alumno.setnHermanos(Integer.parseInt(nHermanos));
		 	alumno.setActivo(Boolean.parseBoolean(req.getParameter(Constantes.PAR_ACTIVO)));
		}
		catch (Exception e){
			e.printStackTrace();
		    LOG.error(e.getMessage());
			throw new Exception("Los datos No son validos " + e.getMessage());
		//	LOG.error(e.getMessage(), new Exception("Los datos No son validos " + e.getMessage()));
			
		}
		
		return alumno;
		
		
	}

	@Override
	public void destroy() {
		aS=null;
		super.destroy();
	}

	
}
