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
	private static final Logger LOG = Logger.getLogger(AlumnoServlet.class);
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
		LOG.trace("Metodo doGet de AlumnoServlet");
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
					LOG.trace("CREATE");
					//Se va a redirigir a la pagina alumnos/alumno.jsp
					rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
				break;
				case Constantes.OP_READ:
					LOG.trace("READ");
					//Carga la lista de los alumnos
					cargarListaAlumnos(request);
				break;
				case Constantes.OP_UPDATE:
				{
					LOG.trace("UPDATE");
					int codigo = -1;
					//Recogemos el parametro(codigo)
					codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
					//Recogemos el alumno que buscamos mediante el codigo y lo guardamos en la variable alumno
					Alumno alumno = aS.getByID(codigo);
					//Metemos el nuevo alumno en el request
					request.setAttribute(Constantes.ATT_ALUMNO, alumno);
					//Se prepara la redireccion a la pagina alumnos/alumno.jsp
					rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
				}
				break;
				case Constantes.OP_DELETE:
				{
					LOG.trace("DELETE");
					int codigo = -1;
					//Recogemos el parametro(codigo)
					codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
					//Ahora borramos 
					aS.delete(codigo);
					//Mesanje
					request.setAttribute(Constantes.ATT_MENSAJE, "El alumno a sido borrado correctamente");
					//Muestra la lista de alumnos
					cargarListaAlumnos(request);
				}
				break;
				default:
					LOG.trace("DEFAULT");
				break;
			}
		}catch(Exception e){
			//cargarListaAlumnos(request)
			LOG.error(e.getMessage()+ "Valor del codigo de alumno: "+ request.getParameter(Constantes.PAR_CODIGO));
			//En caso de excepcion prepara la redireccion a index.jsp
			response.sendRedirect(Constantes.JSP_HOME);
			return;
		}
		// Hace la redireccion
		rd.forward(request, response);
	}

	private void cargarListaAlumnos(HttpServletRequest request) {
		LOG.trace("Metodo cargarAlumnos de AlumnoServlet");
		// Obtenemos los objetos alumnos y los cargamos en la lista alumnos
		List<Alumno> alumnos = aS.getAll();
		// Fijamos la pagina de destino
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
		//Añadimos la lista y una key para identificarla al request
		request.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, alumnos);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.trace("Metodo doPost de AlumnoServlet");
		//Creamos una variable alumno nula
		Alumno alumno = null;
		//Variable que contiene el mensaje
		String mensaje ="";
		int codigo = -1;
		try {
			//Intentamos hacer parse a el codigo. Por si intentan meter un valor no integer
			codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
			//Cargamos la variable con los parametros
			alumno = recogerParametros(request);
			//Si existe el codigo...
			if(alumno.getCodigo() > alumno.CODIGO_NULO){
				//UPDATE aS con alumno
				aS.update(alumno);
				mensaje = "El alumno ha sido actualizado correctamente";
			}else{ 
				//CREATE aS con alumno
				aS.create(alumno);
				mensaje = "El alumno ha sido creado correctamente";
			}
			cargarListaAlumnos(request);
		}catch(NumberFormatException e){
			//Si el codigo no es numero...
			mensaje = "Se ha producido un error inesperado. \nContacte con el administrador";
			//Otro tipo de log
			LOG.error(e.getMessage());
			response.sendRedirect(Constantes.JSP_HOME);
		}catch (NullPointerException e){
			LOG.error(e.getMessage()+" Valor de la variable: "+request.getParameter(Constantes.PAR_CODIGO));
		} catch (Exception e) {
			LOG.error(e.getMessage());
			//Si el codigo es diferente de -1...
			mensaje = "Se ha producido un error inesperado. \nContacte con el administrador";
			response.sendRedirect(Constantes.JSP_HOME);
			if(codigo == -1){
				rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
				request.setAttribute(Constantes.ATT_ALUMNO, alumno);
				mensaje = "Se ha introducido una operacion inesperada."+ 
				"\nContacte con el administrador del sistema";
			//Si no	
			}else{
				alumno = aS.getByID(codigo);
				//Prepara la redireccion. Envia alumno
				request.setAttribute(Constantes.ATT_ALUMNO, alumno);
				//Prepara la redireccion
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
				//Guarda la excepcion como mensaje
				mensaje = e.getMessage();
			}
			
		}
		//Prepara la redireccion. Envia el mensaje
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		//Realiza la redireccion
		rd.forward(request, response);
	}
	
	private Alumno recogerParametros(HttpServletRequest request) throws Exception {
		LOG.trace("Metodo recogerParametro de AlumnoServlet");
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
			alumno.setnHermanos(Integer.parseInt(nHermanos));
			alumno.setActivo(Boolean.parseBoolean(request.getParameter(Constantes.PAR_ACTIVO)));
			String date = request.getParameter(Constantes.PAR_FNACIMIENTO);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			alumno.setfNacimiento(dateFormat.parse(date));
		}catch(Exception e){
			LOG.error(e.getMessage());
			throw new Exception("Los datos no son validos: "+e.getMessage());
		}
		return alumno;
	}

	//Solo para peticiones de formularios
	@Override
	public void destroy() {
		LOG.trace("AlumnoServlet destruido");
		this.aS = null;
		super.destroy();
	}
}
