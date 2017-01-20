package com.ipartek.formacion.controller;

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
		aS=new AlumnoServiceImp();

		super.init();
	}
       
    
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operacion=request.getParameter(Constantes.PAR_OPERACION);
		int op=-1;
		try{
			op=Integer.parseInt(operacion);
			switch (op){
			case Constantes.OP_CREATE:
				rd=request.getRequestDispatcher(Constantes.JSP_FORM_ALUMNO);
				break;
			case Constantes.OP_READ:
				cargarListaAlumnos(request);
				break;
			case Constantes.OP_UPDATE:

				rd=request.getRequestDispatcher(Constantes.JSP_FORM_ALUMNO);
				break;
				default:
					cargarListaAlumnos(request);
					break;
					

			}
		}catch(Exception e){
			response.sendRedirect(Constantes.JSP_HOME);
			return;

		}

		rd.forward(request, response);
		//http://localhost:8080/gestiondocente/alumno.do
		

	}



	private void cargarListaAlumnos(HttpServletRequest request) {
		//response.sendRedirect("alumnos/listado.jsp");
		//http://localhost:8080/gestiondocente/alumnos/listado.jsp
		
	
		List<Alumno> alumnos=aS.getAll();
		rd=request.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
		request.setAttribute(Constantes.ATT_LISTADO_ALUMNOS,alumnos);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Alumno alumno=null;
		String mensaje="";
		try{
		alumno=recogerParametros(req);
		
		if(alumno.getCodigo()>Alumno.CODIGO_NULO){
			aS.update(alumno);
			mensaje="El alumno ha sido actualizado correctamente";
			
		}else{
			aS.create(alumno);
			mensaje="El alumno ha sido creado correctamente";
		}
		
		}catch(Exception e){
			rd=req.getRequestDispatcher(Constantes.JSP_FORM_ALUMNO);
			mensaje=e.getMessage();
		}
		req.setAttribute("mensaje",mensaje);
		rd.forward(req, resp);

	}



	private Alumno recogerParametros(HttpServletRequest req) throws Exception {
		Alumno alumno=new Alumno();
		try{
			alumno.setCodigo(Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO)));
			alumno.setNombre(req.getParameter(Constantes.PAR_NOMBRE));
			alumno.setApellidos(req.getParameter(Constantes.PAR_APELLIDOS));
			alumno.setDireccion(req.getParameter(Constantes.PAR_DIRECCION));
			alumno.setDni(req.getParameter(Constantes.PAR_DNI));
			alumno.setEmail(req.getParameter(Constantes.PAR_EMAIL));
			if(!"".equalsIgnoreCase(req.getParameter(Constantes.PAR_NHERMANOS))){
				alumno.setnHermanos(Integer.parseInt(req.getParameter(Constantes.PAR_NHERMANOS)));			
			}		
			alumno.setActivo(Boolean.parseBoolean(req.getParameter(Constantes.PAR_ACTIVO)));
			String date=req.getParameter(Constantes.PAR_FNACIMIENTO);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			alumno.setfNacimiento(dateFormat.parse(date));
			
		}catch(Exception e){
			
			throw new Exception("Se ha producido un error: "+ e.getMessage());
		}

		return alumno;
	}
	

}
