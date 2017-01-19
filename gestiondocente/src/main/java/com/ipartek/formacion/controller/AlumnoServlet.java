package com.ipartek.formacion.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
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
       

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		
		//para init() el atributo aS va antes de la llamada a la clase padre (super.init())
		aS= new AlumnoServiceImp();
		super.init();
		//aS= new AlumnoServiceImp(); --> esto aquí no se ejecute
	}



	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String operacion = req.getParameter(Constantes.PAR_OPERACION);
		int op = -1;
		try {
			op = Integer.parseInt(operacion);
			
			switch(op){
				case Constantes.OP_CREATE:{
					// se va redirigir a la página alumnos/alumno.jsp
					rd = req.getRequestDispatcher("alumnos/alumno.jsp");
				}break;
				case Constantes.OP_READ:{
					cargarListaAlumnos(req);
				}break;
				case Constantes.OP_UPDATE:{
					//aS.getById(codigo);
					// se va redirigir a la página alumnos/alumno.jsp
					rd = req.getRequestDispatcher("alumnos/alumno.jsp");
					//req.setAttribute(name, o);
				}break;
				case Constantes.OP_DELETE:{
					
				}break;
			default:{
				//cargarListaAlumnos(req);
				resp.sendRedirect(Constantes.JSP_HOME);
			}
			
			}
		} catch (Exception e) {
			//cargarListaAlumnos(req);
			resp.sendRedirect(Constantes.JSP_HOME);
			return;
		}
	
		
		//hace la redirección
		rd.forward(req, resp);
	}



	/**
	 * @param req
	 */
	private void cargarListaAlumnos(HttpServletRequest req) {
		//resp.sendRedirect("alumnos/listado.jsp")--> hace una redirección;
		//limpia.
		//obtenemos la lista de datos.
		List<Alumno>alumnos = aS.getAll();
		//RequestDispatcher permite enlazar atributos.
		//fijamos la página de destino.
		rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
		//añadimos el atributo a la request.
		req.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, alumnos);
	}



	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Alumno alumno = null;
		try {
			alumno= recogerParametros(req);
			String mensaje = "";
			//procesaremos UPDATE or INSERT
			if(alumno.getCodigo()>Alumno.CODIGO_NULO){//update
				aS.update(alumno);
				mensaje = "el alumno ha sido actualizado correctamente";
			}else{//create
				aS.create(alumno);
				mensaje = "el alumno ha sido creado correctamente";
			}
		} catch (Exception e) {
			rd = req.getRequestDispatcher("alumnos/alumno.jsp");
			req.setAttribute("mensaje", e.getMessage());
		}
		rd.forward(req, resp);
	}



	private Alumno recogerParametros(HttpServletRequest req) throws Exception {
		Alumno alumno = new Alumno();
		
		try{
			int codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
			String date = req.getParameter(Constantes.PAR_FNACIMIENTO);
			String pattern= "dd/MM/yyyy";
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);			
			
			alumno.setCodigo(codigo);
			alumno.setNombre(req.getParameter(Constantes.PAR_NOMBRE));
			alumno.setApellidos(req.getParameter(Constantes.PAR_APELLIDOS));
			alumno.setDni(req.getParameter(Constantes.PAR_DNI));
			alumno.setDireccion(req.getParameter(Constantes.PAR_DIRECCION));
			alumno.setEmail(req.getParameter(Constantes.PAR_EMAIL));
			alumno.setfNacimiento(sdf.parse(date));
			alumno.setnHermanos(Integer.parseInt(req.getParameter(Constantes.PAR_NHERMANOS)));
			alumno.setActivo(Boolean.parseBoolean(req.getParameter(Constantes.PAR_ACTIVO)));
		}catch(PersonaException e){
			throw new PersonaException(e.getMessage());
		}
		catch(Exception e){
			throw new Exception("Los datos son no validos");
		}
		return alumno;
	}



	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	@Override
	public void destroy() {
		aS = null;
		super.destroy();
	}

	

}
