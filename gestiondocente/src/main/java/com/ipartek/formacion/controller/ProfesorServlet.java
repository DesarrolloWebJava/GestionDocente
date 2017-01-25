/* Paquete donde se guardan los servlets que gestionan el trafico de la web.
 * (Get y Post) */
package com.ipartek.formacion.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.dbms.pojo.Profesor;
import com.ipartek.formacion.dbms.pojo.exceptions.PersonaException;
import com.ipartek.formacion.service.ProfesorService;
import com.ipartek.formacion.service.ProfesorServiceImp;

/**
 * @author Raúl de Roba 18/01/17 
 * 
 * <p>Clase Servlet profesorServet para la gestión de profesores.</p>
 * 
 */
public class ProfesorServlet extends HttpServlet {
	/* Constante de serialición. */
	private static final long serialVersionUID = 1L;
	/* Se declara el servicio que gestiona Profesores.*/
	private ProfesorService pS;
	/* Se declara una RequestDispatcher para redireccionar una url indicada. */
	RequestDispatcher rd;
       
	@Override
    /* Metodo que que se ejecuta crear la pagina.
     * Solo se ejecuta 1 vez,al acceder más veces no se crea.*/
	public void init() throws ServletException {
    	/* Se instancia la clase que gestiona los Profesores. */
		pS = new ProfesorServiceImp();
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
					/* Se redirecciona a la url del formulario del profesor.*/
					rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR );
					/* Se sale de la estructura 'Swicth'*/
					break;
				/* Se comprueba si se ha recibido la operación de modificar.
				 * Para que la variable 'codigo' se limite al ambito creamos este con {... }.*/
				case Constantes.OP_UPDATE :{
					/* Se declara la variable para recoger el codigo de profesor.*/
					int codigo=-1;
					/* Se recoge el codigo del profesor pasada por el request.*/
					codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
					/* Se recoge el profesor recibido cuyo codigo se ha recogido del request.*/
					Profesor profesor = pS.getById(codigo);
					/* Se redirecciona a la url del formulario del profesor.*/
					rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
					/* Se asigna el profesor resultante al request.*/
					req.setAttribute(Constantes.ATT_LISTADO_PROFESORES, profesor);
					/* Se sale de la estructura 'Swicth'*/
					break;
				}
				/* Se comprueba si se ha recibido la operación de modificar.
				 * Para que la variable 'codigo' se limite al ambito creamos este con {... }.*/
				case Constantes.OP_DELETE :{
					/* Se declara la variable para recoger el codigo de profesor.*/
					int codigo=-1;
					/* Se recoge el codigo del profesor pasada por el request.*/
					codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
					/* Se recoge el profesor recibido cuyo codigo se ha recogido del request.*/
					Profesor profesor = pS.getById(codigo);
					/* Se borra el alumno.*/
					pS.delete(codigo);
					/* Se llama al metodo que cargar la lista de los profesores 
					 * en el request. */
					cargaListaProfesores(req);
					/* Se envia al request el mensaje del borrado satisfactorio.*/
					req.setAttribute(Constantes.ATT_MENSAJES, 
							                       "El profesor ha sido borrado correctamente.");
					/* Se sale de la estructura 'Swicth'*/
					break;
				}	
				/* Se comprueba si se ha recibido la operación de modificar.*/
				case Constantes.OP_READ :	
					/* Se llama al metodo que cargar la lista de los profesores 
					 * en el request. */
					cargaListaProfesores(req);
					/* Se sale de la estructura 'Swicth'*/
					break;
			default:
				/* Se llama al metodo que cargar la lista de los profesores en el request. */
				cargaListaProfesores(req);
				/* Se sale de la estructura 'Swicth'*/
				break;
			}
		/* Se captura la excepción.*/	
		}catch (Exception e){
			/* En caso que la operacion recibida no sea entero,
			 * se llama al manda a la página inicial. */
			resp.sendRedirect(Constantes.JSP_HOME);			
		}
		/* Se redirecciona enviando por parametro los request y response 
		 * recibidos por parametro.*/
		rd.forward(req, resp);
	}

	/* Metodo que carga la lista de los prodfesores en el request pasada por parametro. */
	private void cargaListaProfesores(HttpServletRequest request) {
		/* Sde declara la lista de profesores donde se recorre la lista de los profesores.*/
    	Map<Integer,Profesor> profesores = pS.getAll();
    	/* Se declara una RequestDispatcher para redireccionar a la url indicada.
		 * No es una redireccion limpia,con lo que envia parametros.
		 * En este caso request y response.*/    	
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_PROFESOR);
		/* Se crea un atributo en la request y se le asigna la lista de profesores.*/
		request.setAttribute(Constantes.ATT_LISTADO_PROFESORES, profesores);
	}

	/* Metodo a ejecutar al recibir una petición Post. */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			                                        throws ServletException, IOException {
		/* Se declara y se instancia el objeto donde trabajar con el profesor.*/
		Profesor profesor = new Profesor();
		/* Se declara la variable para transmitir el mensaje auditoria 
		 * de la operacion.*/
		String mensaje ="";
		/* Se monta estructura para la captura de excepciones.*/
		try {
			/* Se llama al metodo que devuelve el alumno 
			 * pasado por los parametros de la request.*/
			profesor =recogerParametros(req);
			
			
			/* Se comprueba si se pretende añadir alumno o modificarlo en función si
			 * el codigo del alumno es mayor que la cosntante de 
			 * codigo nulo(codigo inicial). */
			if(profesor.getCodigo()>profesor.CODIGO_NULO){
				/* Se actualiza el alumno.*/
				pS.update(profesor);
				/* Se asigna el mensaje de actualización satisfactoria.*/
				mensaje = "El alumno ha sido actualizado correctamente.";
			} else{
				/* Se crea el alumno.*/
				pS.create(profesor);
				/* Se asigna el mensaje de creación satisfactoria.*/
				mensaje = "El alumno ha sido creado correctamente.";				
			}
		/* Se carga la lista de los profesores para actualizar los datos introducidos.*/	
		cargaListaProfesores(req);
		/* Se captura la excepción.*/	
		} catch (Exception e) {
			/* Se asigna la url del formulario 'profesor.jsp' 
			 * para redireccionar tras el error.*/
			rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
			/* Se asigna el atributo del error del mensaje para pasarlo al request.*/
			mensaje = e.getMessage();
		}
		req.setAttribute(Constantes.ATT_MENSAJES,mensaje);
		/* Se redirije a la url que se haya indicado.*/
		rd.forward(req, resp);
		
	}

	/* Metodo que devuelve el alumno pasado por los parametros de la request.*/
	private Profesor recogerParametros(HttpServletRequest req) throws Exception {
		/* Se declara y se instancia el objeto donde trabajar con el profesor.*/
		Profesor profesor = new Profesor();
		/* Se monta estructura para la captura de excepciones.*/
		try{
			/* Se declara y recoge el codigo recogido de la request.*/
			int codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
			/* Se asigna el codigo al profesor.*/
			profesor.setCodigo(codigo);
			/* Se asigna el nombre al profesor con el nombre recogido por parametro.*/
			profesor.setNombre(req.getParameter(Constantes.PAR_NOMBRE));	
			/* Se asigna los apellidos al profesor con los apellidos 
			 * recogidos por parametro.*/
			profesor.setApellidos(req.getParameter(Constantes.PAR_APELLIDOS));
			/* Se asigna el dni al profesor con el dni recogido por parametro.*/
			profesor.setDni(req.getParameter(Constantes.PAR_DNI));
			/* Se asigna la fecha de nacimiento al profesor con los fecha de nacimiento 
			 * recogidos por parametro.*/			
			String date = req.getParameter(Constantes.PAR_FNACIMIENTO);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			profesor.setfNacimiento(dateFormat.parse(date));
			/* Se asigna los email al profesor con los email 
			 * recogidos por parametro.*/
			profesor.setEmail(req.getParameter(Constantes.PAR_EMAIL));
			/* Se asigna el nº S.S. al profesor el nº de la S.S. 
			 * recogido por parametro.*/
			profesor.setnSS(Integer.parseInt(req.getParameter(Constantes.PAR_NSS)));
			
		/* Se captura la excepción de tipo persona.*/	
		}catch (PersonaException e){
			/* Se trasfiere la excepcion a la clase padre.*/
			throw new PersonaException(e.getMessage());
		/* Se captura la excepción.*/	
		}catch (Exception e){
			/* Se trasfiere la excepcion a la clase padre.*/
			throw new Exception("Los datos del profesor contienen un error: "+e.getMessage());		
		}
		/* Se devuelve el alumno creado.*/
		return profesor;		
	}
}
