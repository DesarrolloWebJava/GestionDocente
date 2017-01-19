package com.ipartek.formacion.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.SimpleFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

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
		//response.sendRedirect("alumnos/listado.jsp");
		//limpia.
		//
		String operacion = request.getParameter(Constantes.PAR_OPERACION);
		int op = -1;
		try{
			op = Integer.parseInt(operacion);
			switch (op){
				case Constantes.OP_CREATE:
					rd = request.getRequestDispatcher(Constantes.JSP_CREAR_ALUMNOS);
					break;
				case Constantes.OP_READ:
					cargarListaAlumnos(request);
					break;
				case Constantes.OP_UPDATE:
					//aS.getById(codigo);
					rd = request.getRequestDispatcher(Constantes.JSP_CREAR_ALUMNOS);
					//request.setAttribute(arg0, arg1);
					break;
				case Constantes.OP_DELETE:
					break;
				default:
					cargarListaAlumnos(request);
					break;
			}
		} catch(Exception e){
			//response.sendRedirect(Constantes.JSP_HOME);
			cargarListaAlumnos(request);
		}
		rd.forward(request, response);
	}
	
	private void cargarListaAlumnos(HttpServletRequest request) {
		List<Alumno> alumnos = aS.getAll();
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
		request.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, alumnos);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Alumno alumno = null; 
		String mensaje="";
		try{
			alumno = recogerParametros(req);
			
			if(alumno.getCodigo()>Alumno.CODIGO_NULO){
				aS.update(alumno);
				mensaje = "El alumno a sido actializado correctamente";
			}else{
				aS.create(alumno);
				mensaje = "El alumno a sido creado correctamente";
			}
		} catch (Exception e){
			rd = req.getRequestDispatcher(Constantes.JSP_CREAR_ALUMNOS);
			mensaje= e.getMessage();
		}
		req.setAttribute("mensaje", mensaje);
		rd.forward(req, resp);
		
	}
	private Alumno recogerParametros(HttpServletRequest req) throws Exception {
		Alumno alumno = new Alumno();
		try{
			alumno.setCodigo(Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO)));
			alumno.setNombre(req.getParameter(Constantes.PAR_NOMBRE));
			alumno.setApellidos(req.getParameter(Constantes.PAR_APELLIDOS));
			alumno.setDni(req.getParameter(Constantes.PAR_DNI));
			alumno.setEmail(req.getParameter(Constantes.PAR_EMAIL));
			alumno.setDireccion(req.getParameter(Constantes.PAR_DIRECCION));
			alumno.setnHermanos(Integer.parseInt(req.getParameter(Constantes.PAR_NHERMANOS)));
			alumno.setActivo(Boolean.parseBoolean(req.getParameter(Constantes.PAR_ACTIVO)));
			String date = req.getParameter(Constantes.PAR_FNACIMIENTO); 
			String pattern ="dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			alumno.setfNacimiento(dateFormat.parse(date));
			
		} catch(Exception e){
			throw new Exception("Los datos son incorrectos");
		}
		
		return alumno;
	}
	@Override
	public void destroy() {
		aS = null;
		super.destroy();
	}

	
}
