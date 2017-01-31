package com.ipartek.formacion.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.service.AlumnoService;
import com.ipartek.formacion.service.AlumnoServiceImp;

/**
 * Servlet implementation class AlumnoServlet
 */
public class AlumnoServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger(AlumnoServlet.class);
	
	private static final long serialVersionUID = 1L;
    private AlumnoService aS;
    private RequestDispatcher rd;
    
    @Override
	public void init() throws ServletException {
    	aS= new AlumnoServiceImp();
    	super.init();
    	// aS= new AlumnoServiceImp(); esto aqui no se ejecuta. 
    	// El flujo tiene que ir antes quel el super)
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {	
		//recoger el parametro "op"
		String operacion = request.getParameter(Constantes.PAR_OPERACION);
		int op= -1;
		try{
			op= Integer.parseInt(operacion);
			switch (op){
				case Constantes.OP_CREATE:
					//se va a redirigir a la pagina alumnos/alumno.jsp
					rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
					break;
				case Constantes.OP_READ:
					cargarListaAlumnos(request);
					break;
				case Constantes.OP_UPDATE:{
					// como recoger el codigo de una peticion get
					int codigo = -1;
					codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
					
					// falta de hacer aS.getById(codigo)
					Alumno alumno = aS.getById(codigo); 
					//se va a redirigir a la pagina alumnos/alumno.jsp
					rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO); //programar el formulario
					// falta de hacer request.setAttribute(arg0, arg1);
					request.setAttribute(Constantes.ATT_ALUMNO, alumno); //la redireccion
				}	
					break;
				case Constantes.OP_DELETE:	{
					int codigo = -1;
					codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
					aS.delete(codigo);
					request.setAttribute(Constantes.ATT_MENSAJE, "El alumno ha sido borrado correctamente.");
					cargarListaAlumnos(request);
				}
					break;
				default:
					cargarListaAlumnos(request);
					break;
			}
			
		} catch (Exception e){
			log.error(e.getMessage());
			resp.sendRedirect(Constantes.JSP_HOME);
			return;
			//cargarListaAlumnos(request);

		}
		// hace la redireccion.
		rd.forward(request, resp);	
	}

	private void cargarListaAlumnos(HttpServletRequest request) {
		//resp.sendRedirect("alumnos/listado.jsp"); //limpia
		// obtenemos la lista de datos.
		List<Alumno> alumnos = aS.getAll();
		// fijamos la página destino.
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
		// añadimos el atributo a la request.
		request.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, alumnos);
		// hace la redirección.
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Alumno alumno = null;
		String mensaje = "";
		
		
		try {
				alumno = recogerParametros(req);
				// procesamos UPDATE or INSERT
				if(alumno.getCodigo() > Alumno.CODIGO_NULO){ //UPDATE
					aS.update(alumno);
					mensaje = "El alumno ha sido actualizado correctamente";
				}else { //CREATE
					aS.create(alumno);
					mensaje = "El alumno ha sido creado correctamente";
					//mensaje = e.getMessage();
				}
			cargarListaAlumnos(req);
		} catch (NumberFormatException e){
			resp.sendRedirect(Constantes.JSP_HOME);
			return;		
		} catch (Exception e){
			rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
			mensaje = "Se ha producido un error inesperado.";
			
			//req.setAttribute("mensaje", mensaje);
		}
		req.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(req, resp);
	}
	
	private Alumno recogerParametros(HttpServletRequest req) throws Exception { //este throws se lo lanzamos del catch de la excepcion
		Alumno alumno = new Alumno();
		
		try {
			int codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
			alumno.setCodigo(codigo);
			alumno.setNombre(req.getParameter(Constantes.PAR_NOMBRE));
			alumno.setApellidos(req.getParameter(Constantes.PAR_APELLIDOS));
			alumno.setDireccion(req.getParameter(Constantes.PAR_DIRECCION));
			alumno.setDni(req.getParameter(Constantes.PAR_DNI));
			alumno.setEmail(req.getParameter(Constantes.PAR_EMAIL));
			
			String nHermanos = req.getParameter(Constantes.PAR_NHERMANOS);
			if(!"".equalsIgnoreCase(nHermanos)){
				//nHermanos = "0"; //es lo mismo la linea de abajo con las dos
				alumno.setnHermanos(Integer.parseInt(nHermanos));
			}
			//alumno.setnHermanos(Integer.parseInt(nHermanos));
			
			alumno.setActivo(Boolean.parseBoolean(req.getParameter(Constantes.PAR_ACTIVO)));
			String date = req.getParameter(Constantes.PAR_FNACIMIENTO);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			alumno.setfNacimiento(dateFormat.parse(date));
			//alumno.setCodigo(Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO)));
	
		} catch (Exception e) {
			throw new Exception("Los datos no son validos." + e.getMessage()); // desde aquí
		}
		return alumno;
		
	}

	@Override
	public void destroy() {
		aS = null;
		super.destroy();
	}
	
}
