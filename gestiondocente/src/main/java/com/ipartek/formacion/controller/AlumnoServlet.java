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
	
	/**
	 * El servlet se ejecuta el solo por eso no se usa el constructor.
	 * El metodo init() le dice que hacer al iniciar el Servlet como si
	 * fuera un constructor
	 */
	@Override
	public void init() throws ServletException {
		/*
		 * En los servlet se programa antes de la llamada al padre porque 
		 * cuando se llama al padre se sale.
		 * Se inicializa AlumnoServiceImp que tiene los metodos CRUD
		 * Solo necesito un objeto aS para este servlet
		 */
		aS = new AlumnoServiceImp();
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Recogemos el parametro de lo que queremos hacer: create, delete, update, ...
		String operacion = request.getParameter(Constantes.PAR_OPERACION);
		//Variable que controla si es correcto o no el parametro 
		int op = -1;
		try{
			//Cambiamos el valor del parametro de String a Integer
			op = Integer.parseInt(operacion);
			//Switch para gestionar a donde redirecciona en funcion del parametro
			switch(op){
				case Constantes.OP_CREATE:
				//Se va a redirigir a la pagina alumnos/alumno.jsp
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
				break;
				case Constantes.OP_READ:
				cargarListaAlumnos(request);
				break;
				case Constantes.OP_UPDATE:
				//TODO recogemos el codigo para coger el getById
				//request.getParameter()
				//Se va a redirigir a la pagina alumnos/alumno.jsp
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
				//request.setAtribute(arg0, arg1);
				break;
				case Constantes.OP_DELETE:
					
				break;
				default:
					
				break;
			}
		}catch(Exception e){
			//En caso de excepcion prepara la redireccion a index.jsp
			response.sendRedirect(Constantes.JSP_HOME);
			return;
		}
		// Hace la redireccion
		rd.forward(request, response);
	}

	private void cargarListaAlumnos(HttpServletRequest request) {
		// Obtenemos los datos de los alumnos y los cargamos en la variable alumnos
		List<Alumno> alumnos = aS.getAll();
		// Fijamos la pagina de destino
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
		//Añadimos la lista y una key para identificarla al request
		request.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, alumnos);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Creamos una variable alumno nula
		Alumno alumno = null;
		//Recogemos los parametros
		String mensaje ="";
		try {
			//Cargamos la variable con los parametros
			alumno = recogerParametros(request);
			//Si existe el codigo lo actualiza, sino lo crea
			if(alumno.getCodigo() > Alumno.CODIGO_NULO){
				//actualiza aS con alumno
				aS.update(alumno);
				mensaje = "El alumno ha sido actualizado correctamente";
			}else{ 
				//Crea aS con alumno
				aS.create(alumno);
				mensaje = "El alumno ha sido creado correctamente";
			}
		} catch (Exception e) {
			//Prepara la redireccion
			rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
			//Guarda la excepcion como mensaje
			mensaje = e.getMessage();
		}
		//Prepara la redireccion. Envia el mensaje
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		//Realiza la redireccion
		rd.forward(request, response);
	}
	
	
	private Alumno recogerParametros(HttpServletRequest request) throws Exception {
		Alumno alumno = new Alumno();
		try{
			alumno.setCodigo(Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO)));
			alumno.setNombre(request.getParameter(Constantes.PAR_NOMBRE));
			alumno.setApellidos(request.getParameter(Constantes.PAR_APELLIDOS));
			alumno.setDni(request.getParameter(Constantes.PAR_DNI));
			alumno.setEmail(request.getParameter(Constantes.PAR_EMAIL));
			alumno.setDireccion(request.getParameter(Constantes.PAR_DIRECCION));
			String nHermanos = request.getParameter(Constantes.PAR_NHERMANOS);
			if("".equalsIgnoreCase(nHermanos)) {
				nHermanos = "0";
			}
			alumno.setActivo(Boolean.parseBoolean(request.getParameter(Constantes.PAR_ACTIVO)));
			String date = request.getParameter(Constantes.PAR_FNACIMIENTO);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			alumno.setfNacimiento(dateFormat.parse(date));
		}catch(Exception e){
			throw new Exception("Los datos no son validos: "+e.getMessage());
		}
		return alumno;
	}

	//Solo para peticiones de formularios
	@Override
	public void destroy() {
		aS = null;
		super.destroy();
	}
}
