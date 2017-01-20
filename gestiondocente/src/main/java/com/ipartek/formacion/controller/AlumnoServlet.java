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
	
	//En un Servlet los métodos init y destroy SÓLO se ejecutan UNA VEZ.
	//Cuando se llama por primera vez, cuando se sale?
	//Por eso cuando se llama una app por primera vez tarda un poco
	//porque se están genrando los servlets.
	//técnica autorobots para acelerar...
       
    @Override
	public void init() throws ServletException {
		aS = new AlumnoServiceImp(); //esto es para que no
		//se creen nuevas instancias del servlet en cada
		//doGet o doPost.
    	super.init();
    	//OJO: en el constructor se define 1º la funcionalidad
    	//i.e. crear la instancia, y después el método del padre.
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String operacion = req.getParameter(Constantes.PAR_OPERACION);
		//no podemos castear porque de un obj a un elemental así como así, no se puede.
		//NO: int op = Integer.parseInt(operacion);
		int op = -1;
		try {
			//Este try es para PESCAR que el ususario
			//no haya toqueteado la URL.
			op = Integer.parseInt(operacion);
			switch (op) {
			case Constantes.OP_CREATE:
				//redirigimos a la página que crea los alumnos: 
				//alumnos/alumno.jsp
				rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
				break;
			case Constantes.OP_READ:
				cargarListaAlumnos(req);
				break;
			case Constantes.OP_UPDATE:
				//Diff entre create/update es que el 2º se
				//hace sobre un registro que ya existe: 
				//update tiene que recibir un parámetro: el 
				//código, que lo pasamos al rd como ATRIBUTO.
				//TODO: aS.getById(codigo)
				rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
				//TODO: req.setAttribute(arg0, arg1);
				break;
				default:
					cargarListaAlumnos(req);
					break;
			}
		} catch (Exception e) {
			//el cargarListaAlumnos se puede ejecutae en 3 casos:
			//pero aquí solo en dos, porque eln el cacth mandamos a jasp_home
			//- default
			//- porque algo casque (catch)
			//- porque lo pidas
			//cargarListaAlumnos(req); //Incluye puntos del 1 al 3.
			resp.sendRedirect(Constantes.JSP_HOME);
			return;
		}
		
		//4* Hace la redirección
		rd.forward(req, resp);
		//Nosotros vamos a usar el RequestDispatcher porque nos permite añadirle aributos.
	}
	
	//Abro el siguiente javadoc automáticamente.
	/**
	 * 
	 * @param req
	 */

	private void cargarListaAlumnos(HttpServletRequest req) {
		// * PUNTOS: 
		//1* Obtenemos la lista de datos.
		List<Alumno> alumnos = aS.getAll();
		//Esto no va a mostrar nada. Se pierde porque no está 
		//dentro de una variable. Pero eso lo hacemos en
		//la view.
		
			//vamos a hacer una red de dirección
			//Ojo el flujo de la navegación.
			//REDIRECCION LIMPIA (que no engancha datos):
			//resp.sendRedirect("alumnos/listadoAl.jsp");
			//MÉTODO DE REDIRECCIÓN NO LIMPIA:
			//La dif entre los dos: redirección LIMPIA (sin enganchar datos de la petición anterior) O NO.
			//con RequestDispatcher cambia la URL mostrada en el navegador: aparece el .do, que es la información que está en
			//la request: la response mantiene esta info porque no es limpia.
		
		//2* Fijamos la página de destino
		rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
		//el setAttr por debajo es un mapa, con k y v.
		
		//3* Añadimos el atribuo a la request.
		req.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, alumnos);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Alumno alumno = null;
		String mensaje = "";
		try {
			alumno = recogerParametros(req);
			//procesamos update o insert
			
			if(alumno.getCodigo() > Alumno.CODIGO_NULO) {//update
				aS.update(alumno);
				mensaje = "El alumno ha sido actualizado correctamente.";
			} else {//create
				aS.create(alumno);
				mensaje = "El alumno ha sido creado correctamente.";
			}
			cargarListaAlumnos(req);
		} catch (Exception e) {
			// redirigir al formulario
			rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO); //alumno.jsp
			mensaje =e.getMessage();
		}
		req.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(req,  resp);
	}

	private Alumno recogerParametros(HttpServletRequest req) throws Exception {
		Alumno alumno = new Alumno();
		try {
			alumno.setCodigo(Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO)));
			alumno.setNombre(req.getParameter(Constantes.PAR_NOMBRE));
			alumno.setApellidos(req.getParameter(Constantes.PAR_APELLIDOS));
			alumno.setDireccion(req.getParameter(Constantes.PAR_DIRECCION));
			alumno.setDni(req.getParameter(Constantes.PAR_DNI));
			alumno.setEmail(req.getParameter(Constantes.PAR_EMAIL));
			
			//evitar error si no meten ningún número de hermanos
			String nHermanos = req.getParameter(Constantes.PAR_NHERMANOS);
			if("".equalsIgnoreCase(nHermanos)) {
				nHermanos = "0";
			}
			alumno.setnHermanos(Integer.parseInt(nHermanos));
			
			alumno.setActivo(Boolean.parseBoolean(req.getParameter(Constantes.PAR_ACTIVO)));
			
			String date = req.getParameter(Constantes.PAR_FNACIMIENTO);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			alumno.setfNacimiento(dateFormat.parse(date));
	
			//	} catch (PersonaException e) {
	//		throw new PersonaException(e.getMessage());
			} catch (Exception e) {
				throw new Exception("Los datos no son válidos: "+e.getMessage());
		}
		return alumno;
	}

	@Override
	public void destroy() {
		aS = null; //Caso de 1º funcionalidad, después método del padre.
		super.destroy();
	}

	
	
}