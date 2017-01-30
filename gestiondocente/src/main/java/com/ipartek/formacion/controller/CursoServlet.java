/* Paquete donde se guardan los servlets que gestionan el trafico de la web.*/
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

import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.dbms.pojo.exceptions.CursoException;
import com.ipartek.formacion.service.CursoService;
import com.ipartek.formacion.service.CursoServiceImp;

/**
 * @author Raúl de Roba 18/01/17 
 * 
 * <p>Clase Servlet CursoServet para la gestión de cursos.</p>
 * 
 */
public class CursoServlet extends HttpServlet {
	/* Constante de serialición. */
	private static final long serialVersionUID = 1L;
	/* Se declara el servicio que gestiona Cursos.*/
	private CursoService cS;
	/* Se declara una RequestDispatcher para redireccionar una url indicada. */
	RequestDispatcher rd;
	
	/* Se recoge la instacia del log pasando como parametro la clase actual.*/
	private static final Logger LOG = Logger.getLogger(CursoServlet.class);	
       
       
	 @Override
    /* Metodo que que se ejecuta crear la pagina.
     * Solo se ejecuta 1 vez,al acceder más veces no se crea.*/
	public void init() throws ServletException {
    	/* Se instancia la clase que gestiona los cursos. */
    	cS = new CursoServiceImp();
		/* Se llama al init del padre.En los servlets tiene que ser la última línea,
		 * porque al llamar al padre se sale del metodo.*/
		super.init();
	}

	 /* Metodo a ejecutar al recibir una petición Get. */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			             throws ServletException, IOException {
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
					rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO );
					/* Se sale de la estructura 'Swicth'*/
					break;
				/* Se comprueba si se ha recibido la operación de modificar.
				 * Para que la variable 'codigo' se limite al ambito creamos este con {... }.*/
				case Constantes.OP_UPDATE :{
					/* Se declara la variable para recoger el codigo de curso.*/
					int codigo=-1;
					/* Se recoge el codigo del curso pasada por el request.*/
					codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
					/* Se recoge el curso recibido cuyo codigo se ha recogido del request.*/
					Curso curso = cS.getById(codigo);
					/* Se redirecciona a la url del formulario del curso.*/
					rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
					/* Se asigna el Curso resultante al request.*/
					req.setAttribute(Constantes.ATT_CURSOS, curso);
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
					Curso curso = cS.getById(codigo);
					/* Se borra el curso.*/
					cS.delete(codigo);
					/* Se llama al metodo que cargar la lista de los cursos 
					 * en el request. */
					cargarListaCursos(req);
					/* Se envia al request el mensaje del borrado satisfactorio.*/
					req.setAttribute(Constantes.ATT_MENSAJES, 
							                       "El curso ha sido borrado correctamente.");
					/* Se sale de la estructura 'Swicth'*/
					break;
				}					
				/* Se comprueba si se ha recibido la operación de modificar.*/
				case Constantes.OP_READ :	
					/* Se llama al metodo que cargar la lista de los cursos 
					 * en el request. */
					cargarListaCursos(req);
					/* Se sale de la estructura 'Swicth'*/
					break;
			default:
				/* Se llama al metodo que cargar la lista de los cursos en el request. */
				cargarListaCursos(req);
				/* Se sale de la estructura 'Swicth'*/
				break;
			}
		/* Se captura la excepción.*/	
		}catch (Exception e){
			/* Se lanza la traza del error. */
			LOG.error(e.getMessage());		
			/* En caso que la operacion recibida no sea entero,
			 * se llama al manda a la página inicial. */
			resp.sendRedirect(Constantes.JSP_HOME);			
		}
		
		
		/* Se redirecciona enviando por parametro los request y response 
		 * recibidos por parametro.*/
		rd.forward(req, resp);
	}
	
	/* Metodo que carga la lista de los cursos en el request pasada por parametro. */
	private void cargarListaCursos(HttpServletRequest req) {
		/* Se declara la lista de cursos 
		 * donde se recorre la lista de los cursos.*/
    	List<Curso> cursos = cS.getAll();
    	/* Sobre el RequestDispatcher se indica una url pra redireccionar.
		 * No es una redireccion limpia,con lo que envia parametros.
		 * En este caso request y response.*/
		 rd =  req.getRequestDispatcher(Constantes.JSP_LISTADO_CURSO);
		/* Se crea un atributo en la request y se le asigna la lista de cursos.*/
		 req.setAttribute(Constantes.ATT_LISTADO_CURSOS, cursos);
	}

	/* Metodo a ejecutar al recibir una petición Post. */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			                                        throws ServletException, IOException {
		/* Se declara y se instancia el objeto donde trabajar con el curso.*/
		Curso curso = new Curso();
		/* Se declara la variable para transmitir el mensaje auditoria 
		 * de la operacion.*/
		String mensaje ="";
		/* Se monta estructura para la captura de excepciones.*/
		try {
			/* Se llama al metodo que devuelve el curso 
			 * pasado por los parametros de la request.*/
			curso =recogerParametros(req);			
			/* Se comprueba si se pretende añadir curso o modificarlo en función si
			 * el codigo del curso es mayor que la cosntante de 
			 * codigo nulo(codigo inicial). */
			if(curso.getCodigo()>curso.CODIGO_NULO){
				/* Se actualiza el curso.*/
				cS.update(curso);
				/* Se asigna el mensaje de actualización satisfactoria.*/
				mensaje = "El curso ha sido actualizado correctamente.";
			} else{
				/* Se crea el curso.*/
				cS.create(curso);
				/* Se asigna el mensaje de creación satisfactoria.*/
				mensaje = "El curso ha sido creado correctamente.";				
			}
		/* Se carga la lista de los cursos para actualizar los datos introducidos.*/	
		cargarListaCursos(req);
		/* Se captura la excepción.*/	
		} catch (Exception e) {
			/* Se lanza la traza del error. */
			LOG.error(e.getMessage());		
			/* Se asigna la url del formulario 'curso.jsp' 
			 * para redireccionar tras el error.*/
			rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
			/* Se asigna el atributo del error del mensaje para pasarlo al request.*/
			mensaje = e.getMessage();
		}
		req.setAttribute(Constantes.ATT_MENSAJES,mensaje);
		/* Se redirije a la url que se haya indicado.*/
		rd.forward(req, resp);		
	}
	
	/* Metodo que devuelve el curso pasado por los parametros de la request.*/
	private Curso recogerParametros(HttpServletRequest req) throws Exception {
		/* Se declara y se instancia el objeto donde trabajar con el alumno.*/
		Curso curso = new Curso();
		/* Se monta estructura para la captura de excepciones.*/
		try{
			/* Se declara y recoge el codigo recogido de la request.*/
			int codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
			/* Se asigna el codigo al curso.*/
			curso.setCodigo(codigo);
			/* Se asigna el nombre al curso con el nombre recogido por parametro.*/
			curso.setNombre(req.getParameter(Constantes.PAR_NOMBRE));	
			/* Se asigna la duración al curso con la dureación recogida por parametro.*/
			curso.setDuracion(
					         Integer.parseInt(req.getParameter(Constantes.PAR_DURACION)));		
			
			/* Se declara y asigna el pattern de fecha para la fecha de inicio y fin.*/
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			
			/* Se asigna la fecha de inicio al curso con los fecha de inicio 
			 * recogidos por parametro.*/			
			String date = req.getParameter(Constantes.PAR_FINICIO);			
			curso.setFinicio(dateFormat.parse(date));
			
			/* Se asigna la fecha de inicio al curso con los fecha de inicio 
			 * recogidos por parametro.*/			
			date = req.getParameter(Constantes.PAR_FFIN);
			curso.setFfin(dateFormat.parse(date));
						
			
		/* Se captura la excepción de tipo Curso.*/	
		}catch (CursoException e){
			/* Se lanza la traza del error. */
			LOG.error(e.getMessage());		
			/* Se trasfiere la excepcion a la clase padre.*/
			throw new CursoException(e.getMessage());
		/* Se captura la excepción.*/	
		}catch (Exception e){
			/* Se lanza la traza del error. */
			LOG.error(e.getMessage());		
			/* Se trasfiere la excepcion a la clase padre.*/
			throw new Exception("Los datos del curso contienen un error: "+
			                                                              e.getMessage());		
		}
		/* Se devuelve el alumno creado.*/
		return curso;
	}

	/* Metodo que se ejecuta al cerrar el Servlet. */
	@Override
	public void destroy() {
		/* Se anula el servicio.*/
		cS = null;
		/* Se llama al destructor del padre.*/
		super.destroy();
	}


}
