/* Paquete donde se guardan los servlets que gestionan el trafico de la web.
 * (Get y Post) */
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

import com.ipartek.formacion.controller.listeners.InitListener;
import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.dbms.pojo.exceptions.PersonaException;
import com.ipartek.formacion.service.AlumnoService;
import com.ipartek.formacion.service.AlumnoServiceImp;

/**
 * @author Raúl de Roba 18/01/17 
 * 
 * <p>Clase Servlet AlumnoServet para la gestión de alumnos.</p>
 * 
 */
public class AlumnoServet extends HttpServlet {
	/* Constante de serialición. */
	private static final long serialVersionUID = 1L;
	/* Se declara el servicio que gestiona Alumnos.*/
	private AlumnoService aS;
	/* Se declara una RequestDispatcher para redireccionar una url indicada. */
	RequestDispatcher rd;
	
	/* Se recoge la instacia del log pasando como parametro la clase actual.*/
	private static final Logger LOG = Logger.getLogger(AlumnoServet.class);	
       
    @Override
    /* Metodo que que se ejecuta crear la pagina.
     * Solo se ejecuta 1 vez,al acceder más veces no se crea.*/
	public void init() throws ServletException {
    	/* Se instancia la clase que gestiona los Alumnos. */
    	aS = new AlumnoServiceImp();
		/* Se llama al init del padre.En los servlets tiene que ser la última línea,
		 * porque al llamar al padre se sale del metodo.*/
		super.init();
	}

	/* Metodo a ejecutar al recibir una petición Get. */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			             throws ServletException, IOException {
		/* Se redirecciona a la url : 'alumno/listado.jsp' 	
		 * Es una redirección limpia,es decir sin parametros.*/
		//response.sendRedirect("alumnos/listado.jsp");
		
		/* Se declara una variable para recoger el numero de operación.*/
		int op = -1;		
		/* Se recoge la operación a realizar. (En String)*/
		String operacion = req.getParameter(Constantes.PAR_OPERACION);		
		/* Se monta estructura para la captura de excepciones.*/
		try{
			/* Se castea la operacion a un entero.*/
			op = Integer.parseInt(operacion);
			/* Se compreuba la operación recibida.*/
			switch (op) {
				/* Se comprueba si se ha recibido la operación de crear.*/
				case Constantes.OP_CREATE :
					/* Se redirecciona a la url del formulario del alumno.*/
					rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO );
					/* Se sale de la estructura 'Swicth'*/
					break;
				/* Se comprueba si se ha recibido la operación de modificar.
				 * Para que la variable 'codigo' se limite al ambito creamos este con {... }.*/
				case Constantes.OP_UPDATE :{
					/* Se declara la variable para recoger el codigo de alumno.*/
					int codigo=-1;
					/* Se recoge el codigo del alumno pasada por el request.*/
					codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
					/* Se recoge el alumno recibido cuyo codigo se ha recogido del request.*/
					Alumno alumno = aS.getById(codigo);
					/* Se redirecciona a la url del formulario del alumno.*/
					rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
					/* Se asigna el Alumno resultante al request.*/
					req.setAttribute(Constantes.ATT_ALUMNOS, alumno);
					/* Se sale de la estructura 'Swicth'*/
					break;
				}
				/* Se comprueba si se ha recibido la operación de modificar.
				 * Para que la variable 'codigo' se limite al ambito creamos este con {... }.*/
				case Constantes.OP_DELETE :{
					/* Se declara la variable para recoger el codigo de alumno.*/
					int codigo=-1;
					/* Se recoge el codigo del alumno pasada por el request.*/
					codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
					/* Se recoge el alumno recibido cuyo codigo se ha recogido del request.*/
					Alumno alumno = aS.getById(codigo);
					/* Se borra el alumno.*/
					aS.delete(codigo);
					/* Se llama al metodo que cargar la lista de los alumnos 
					 * en el request. */
					cargarListaAlumnos(req);
					/* Se envia al request el mensaje del borrado satisfactorio.*/
					req.setAttribute(Constantes.ATT_MENSAJES, 
							                       "El alumno ha sido borrado correctamente.");
					/* Se sale de la estructura 'Swicth'*/
					break;
				}					
				/* Se comprueba si se ha recibido la operación de modificar.*/
				case Constantes.OP_READ :	
					/* Se llama al metodo que cargar la lista de los alumnos 
					 * en el request. */
					cargarListaAlumnos(req);
					/* Se sale de la estructura 'Swicth'*/
					break;
			default:
				/* Se llama al metodo que cargar la lista de los alumnos en el request. */
				cargarListaAlumnos(req);
				/* Se sale de la estructura 'Swicth'*/
				break;
			}
		/* Se captura la excepción.*/	
		}catch (Exception e){
			/* En caso que la operacion recibida no sea entero,
			 * se llama al manda a la página inicial. */
			resp.sendRedirect(Constantes.JSP_HOME);	
			/* Se lanza la traza del error. */
			LOG.error(e.getMessage());
		}
		
		
		/* Se redirecciona enviando por parametro los request y response 
		 * recibidos por parametro.*/
		rd.forward(req, resp);
	}

	/* Metodo que carga la lista de los alumnos en el request pasada por parametro. */
	private void cargarListaAlumnos(HttpServletRequest req) {
		/* Se declara la lista de alumnos 
		 * donde se recorre la lista de los alumnos.*/
    	List<Alumno> alumnos = aS.getAll();
    	/* Sobre el RequestDispatcher se indica una url pra redireccionar.
		 * No es una redireccion limpia,con lo que envia parametros.
		 * En este caso request y response.*/
		 rd =  req.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNO);
		/* Se crea un atributo en la request y se le asigna la lista de alumnos.*/
		 req.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, alumnos);
	}

	/* Metodo a ejecutar al recibir una petición Post. */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			                                        throws ServletException, IOException {
		/* Se declara y se instancia el objeto donde trabajar con el alumno.*/
		Alumno alumno = new Alumno();
		/* Se declara la variable para transmitir el mensaje auditoria 
		 * de la operacion.*/
		String mensaje ="";
		/* Se monta estructura para la captura de excepciones.*/
		try {
			/* Se llama al metodo que devuelve el alumno 
			 * pasado por los parametros de la request.*/
			alumno =recogerParametros(req);			
			/* Se comprueba si se pretende añadir alumno o modificarlo en función si
			 * el codigo del alumno es mayor que la cosntante de 
			 * codigo nulo(codigo inicial). */
			if(alumno.getCodigo()>alumno.CODIGO_NULO){
				/* Se actualiza el alumno.*/
				aS.update(alumno);
				/* Se asigna el mensaje de actualización satisfactoria.*/
				mensaje = "El alumno ha sido actualizado correctamente.";
			} else{
				/* Se crea el alumno.*/
				aS.create(alumno);
				/* Se asigna el mensaje de creación satisfactoria.*/
				mensaje = "El alumno ha sido creado correctamente.";				
			}
		/* Se carga la lista de los alumnos para actualizar los datos introducidos.*/	
		cargarListaAlumnos(req);
		/* Se captura la excepción.*/	
		} catch (Exception e) {
			/* Se asigna la url del formulario 'alumno.jsp' 
			 * para redireccionar tras el error.*/
			rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
			/* Se asigna el atributo del error del mensaje para pasarlo al request.*/
			mensaje = e.getMessage();
			/* Se lanza la traza del error. */
			LOG.error(e.getMessage());		
		}
		req.setAttribute(Constantes.ATT_MENSAJES,mensaje);
		/* Se redirije a la url que se haya indicado.*/
		rd.forward(req, resp);
		
	}

	/* Metodo que devuelve el alumno pasado por los parametros de la request.*/
	private Alumno recogerParametros(HttpServletRequest req) throws Exception {
		/* Se declara y se instancia el objeto donde trabajar con el alumno.*/
		Alumno alumno = new Alumno();
		/* Se monta estructura para la captura de excepciones.*/
		try{
			/* Se declara y recoge el codigo recogido de la request.*/
			int codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
			/* Se asigna el codigo al alumno.*/
			alumno.setCodigo(codigo);
			/* Se asigna el nombre al alumno con el nombre recogido por parametro.*/
			alumno.setNombre(req.getParameter(Constantes.PAR_NOMBRE));	
			/* Se asigna los apellidos al alumno con los apellidos 
			 * recogidos por parametro.*/
			alumno.setApellidos(req.getParameter(Constantes.PAR_APELLIDOS));
			/* Se asigna el dni al alumno con el dni recogido por parametro.*/
			alumno.setDni(req.getParameter(Constantes.PAR_DNI));
			/* Se asigna la fecha de nacimiento al alumno con los fecha de nacimiento 
			 * recogidos por parametro.*/			
			String date = req.getParameter(Constantes.PAR_FNACIMIENTO);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			alumno.setfNacimiento(dateFormat.parse(date));
			/* Se asigna los email al alumno con los email 
			 * recogidos por parametro.*/
			alumno.setEmail(req.getParameter(Constantes.PAR_EMAIL));
			
			/* Se declara una variable String para comprobar si el valor 
			 * recibido es un entero,asignado a dicha variable el valor de nHermano
			 * recibido de la request.*/
			String nHermanos = req.getParameter(Constantes.PAR_NHERMANOS);
			/* Se compara el valor recibido con vacio.*/
			if (!"".equalsIgnoreCase(nHermanos)) {
				/* Se asigna el numero de hermanos al alumno con el numero de hermanos 
				 * recogidos por parametro.*/
				alumno.setnHermanos(Integer.parseInt(nHermanos));
			}
			
			/* Se asigna el estado activo al alumno con el estado activo
			 * recogidos por parametro.*/
			//TODO quitar casteo automatico del booleano.
			alumno.setActivo(
					      Boolean.parseBoolean(req.getParameter(Constantes.PAR_ACTIVO)));			
			
		/* Se captura la excepción de tipo persona.*/	
		}catch (PersonaException e){
			/* Se lanza la traza del error. */
			LOG.error(e.getMessage());		
			/* Se trasfiere la excepcion a la clase padre.*/
			throw new PersonaException(e.getMessage());
		/* Se captura la excepción.*/	
		}catch (Exception e){
			/* Se lanza la traza del error. */
			LOG.error(e.getMessage());		
			/* Se trasfiere la excepcion a la clase padre.*/
			throw new Exception("Los datos del alumno contienen un error: "+e.getMessage());		
		}
		/* Se devuelve el alumno creado.*/
		return alumno;
	}
	
	/* Metodo que se ejecuta al cerrar el Servlet. */
	@Override
	public void destroy() {
		/* Se anula el servicio.*/
		aS = null;
		/* Se llama al destructor del padre.*/
		super.destroy();
	}
}
