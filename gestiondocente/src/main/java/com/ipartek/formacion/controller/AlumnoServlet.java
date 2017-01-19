package com.ipartek.formacion.controller;

import java.io.IOException;
import java.text.DateFormat;
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
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#destroy()
	 */


	private static final long serialVersionUID = 1L;
	private AlumnoService aS;
	private RequestDispatcher rd;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		aS = new AlumnoServiceImp();
		super.init();
//aS = new AlumnoServiceImp();-->colocando esta sentencia despues de la llamada del padre no se ejecuta nunca
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String operacion=req.getParameter(Constantes.PAR_OPERACION);
		int op=-1;
		
		try{
			op= Integer.parseInt(operacion);
			
			switch(op){
				case Constantes.OP_CREATE:
					//redirigiremos a la pagina alumnos.alumno.jsp
					rd = req.getRequestDispatcher("alumnos/alumno.jsp");
					break;
				case Constantes.OP_READ:
					cargarListaAlumnos(req);
					break;
				case Constantes.OP_UPDATE:
					//falta el getbyid
					rd = req.getRequestDispatcher("alumnos/alumno.jsp");
					//req.setAttribute();
					break;
				default:
					cargarListaAlumnos(req);
					break;
			}
			
			
		}catch(Exception e){
			//cargarListaAlumnos(req);
			resp.sendRedirect(Constantes.JSP_HOME);
			return;
		}
	
			
		// hace la redirección
		rd.forward(req, resp);

	}

	private void cargarListaAlumnos(HttpServletRequest req) {
		// resp.sendRedirect("alumnos/listado.jsp"); --> hace una redireccion
		// limpia.
		// obtenemos la lista de datos.
		List<Alumno> alumnos = aS.getAll();
		// fijamos la página de destino
		rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
		// añadimos el atributo a la request
		req.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, alumnos);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Alumno alumno=null;
		String mensaje="";
		try {
			alumno=recogerParametros(req);
			//procesaremos UPDATE or INSERT
			if(alumno.getCodigo()>Alumno.CODIGO_NULO){
				//update
				aS.update(alumno);
				mensaje="El alumno ha sido actualizado correctamente";
			}else{
				//create
				aS.create(alumno);
				mensaje="El alumno ha sido creado correctamente";
			}
		} catch (Exception e) {
			//redirigir al formulario
			rd=req.getRequestDispatcher("alumnos/alumno.jsp");
			mensaje=e.getMessage();
		}
		req.setAttribute("mensaje", mensaje);
		rd.forward(req, resp);
		
	}
	private Alumno recogerParametros(HttpServletRequest req) throws Exception {
		Alumno alumno=new Alumno();
		try{
			int codigo=Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
			alumno.setCodigo(codigo);
			alumno.setNombre(req.getParameter(Constantes.PAR_NOMBRE));
			alumno.setApellidos(req.getParameter(Constantes.PAR_APELLIDOS));
			alumno.setDireccion(req.getParameter(Constantes.PAR_DIRECCION));
			alumno.setEmail(req.getParameter(Constantes.PAR_EMAIL));
			alumno.setDni(req.getParameter(Constantes.PAR_DNI));
			int nHermanos=Integer.parseInt(req.getParameter(Constantes.PAR_NHERMANOS));
			alumno.setnHermanos(nHermanos);
			alumno.setActivo(Boolean.parseBoolean(req.getParameter(Constantes.PAR_ACTIVO)));
			
			String date =req.getParameter(Constantes.PAR_FNACIMIENTO);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			alumno.setfNacimiento(dateFormat.parse(date));
		
		}catch(Exception e){
			
			throw new Exception("Los datos introducidos no son validos");
			
		}
		
		return alumno;
	}

	@Override
	public void destroy() {
		//cuando se destruye el servlet
		aS=null;
		super.destroy();
	}
}
