package com.ipartek.formacion.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
    
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		aS = new AlumnoServiceImp();
		super.init();
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String operacion = req.getParameter(Constantes.PAR_OPERACION);
		int op = -1;
		try{
			op = Integer.parseInt(operacion);
			switch (op){
				case Constantes.OP_CREATE:
					rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNOS);
					break;
				case Constantes.OP_READ:
					cargarListaAlumnos(req);
					break;
				case Constantes.OP_UPDATE:
					//aS.getById(codigo);
					rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNOS);
					//request.setAttribute(arg0, arg1);
					break;
				case Constantes.OP_DELETE:
					break;
				default:
					cargarListaAlumnos(req);
					break;
			}
		} catch(Exception e){
			//cargarListaAlumnos(req);
			resp.sendRedirect(Constantes.JSP_HOME);
			return;
		}
		rd.forward(req, resp);
	}
	
	private void cargarListaAlumnos(HttpServletRequest req) {
		List<Alumno> alumnos = aS.getAll();
		rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
		req.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, alumnos);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Alumno alumno = null; 
		String mensaje = "";
		try{
			alumno = recogerParametros(req);
			
			if(alumno.getCodigo() > Alumno.CODIGO_NULO){
				aS.update(alumno);
				mensaje = "El alumno a sido actualizado correctamente";
			}else{
				aS.create(alumno);
				mensaje = "El alumno a sido creado correctamente";
			}
			cargarListaAlumnos(req);
		} catch (Exception e){
			rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNOS);
			mensaje= e.getMessage();
			
		}
		req.setAttribute(Constantes.ATT_MENSAJE, mensaje);
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
			alumno.setActivo(Boolean.parseBoolean(req.getParameter(Constantes.PAR_ACTIVO)));
			
			
			String nHermanos = req.getParameter(Constantes.PAR_NHERMANOS);
			 			if (!"".equalsIgnoreCase(nHermanos)) {
			 				alumno.setnHermanos(Integer.parseInt(nHermanos));
			 			}
			
			String date = req.getParameter(Constantes.PAR_FNACIMIENTO);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			alumno.setfNacimiento(dateFormat.parse(date));
			
		} catch(Exception e){
			throw new Exception("Los datos son incorrectos: " + e.getMessage());
			
		}
		
		return alumno;
	}
	@Override
	public void destroy() {
		aS = null;
		super.destroy();
	}

	
}