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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Botones, hipervinculos... son get
		
		String operacion = req.getParameter(Constantes.PAR_OPERACION);
		int op = -1;
		
		try {
			op = Integer.parseInt(operacion);
			switch (op){
			case Constantes.OP_CREATE:
			//Se va redirigir a la pagina alumnos/alumno.jsp
			rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
				break;
			case Constantes.OP_READ:
				cargarListaAlumnos(req);
				break;
			case Constantes.OP_UPDATE:
				//aS.getById(codigo)
				//Se va redirigir a la pagina alumnos/alumno.jsp
				rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
				//req.setAttribute(arg0, arg1);
				
			default:
				cargarListaAlumnos(req);
				break;
			
			}
		} catch(Exception e){
			cargarListaAlumnos(req);//Añadimos los atributos a la request
			//resp.sendRedirect(Constantes.JSP_HOME);
			
			
		}
		
		
		rd.forward(req, resp);//hace la redireccion
		//otra opcion
		//resp.sendRedirect("alumnos/listado.jsp");
		
	}

	private void cargarListaAlumnos(HttpServletRequest req) {
		List<Alumno> alumno = aS.getAll();//cargamos la lista de datos
		 rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);//fijamos la pagina de destino
		req.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, alumno);
	}

	@Override
	public void init() throws ServletException {
		aS=new AlumnoServiceImp();
		super.init();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Los formularios son post
		Alumno alumno = null;
		String mensaje="";
		
		try{
			alumno = recogerParametros(request);
			
			//Procesaremos UPDATE o INSERT
			if (alumno.getCodigo()>Alumno.CODIGO_NULO){
			aS.update(alumno);
			mensaje="El alumno ha sido actualizado correctamente";
			} else {//create
			aS.create(alumno);
			mensaje="El alumno ha sido creado correctamente";
			}
			cargarListaAlumnos(request);
		} catch (Exception e){
			
			// redirigir al formulario
			rd=request.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
			mensaje = e.getMessage();
		}
		request.setAttribute("mensaje", mensaje);
		rd.forward(request,response);
	}

	private Alumno recogerParametros(HttpServletRequest request) throws Exception {
		Alumno alumno = new Alumno();
		try{
			
			alumno.setCodigo(Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO)));
			alumno.setNombre(request.getParameter(Constantes.PAR_NOMBRE));
			alumno.setApellidos(request.getParameter(Constantes.PAR_APELLIDOS));
			alumno.setDireccion(request.getParameter(Constantes.PAR_DIRECCION));
			alumno.setDni(request.getParameter(Constantes.PAR_DNI));
			alumno.setEmail(request.getParameter(Constantes.PAR_EMAIL));
			
			String nhermanos = request.getParameter(Constantes.PAR_NHERMANOS);
			if("".equalsIgnoreCase(nhermanos)){
				nhermanos="0";
			}
			
			alumno.setnHermanos(Integer.parseInt(request.getParameter(Constantes.PAR_NHERMANOS)));
			alumno.setActivo(Boolean.parseBoolean(request.getParameter(Constantes.PAR_ACTIVO)));
			
			String date = request.getParameter(Constantes.PAR_FNACIMIENTO);
			String pattern="dd/MM/yyyy";
			SimpleDateFormat dateformat = new SimpleDateFormat(pattern); 
			alumno.setfNacimiento(dateformat.parse(date));
		
		}catch(Exception e){
			throw new Exception("Los datos introducidos no son validos");
		}
		return alumno;
	}

	@Override
	public void destroy() {
		aS = null;
		super.destroy();
	}
	
	

}
