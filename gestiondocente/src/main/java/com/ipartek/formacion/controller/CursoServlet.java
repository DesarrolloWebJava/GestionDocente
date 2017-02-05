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
import com.ipartek.formacion.service.CursoService;
import com.ipartek.formacion.service.CursoServiceImp;
import com.ipartek.formacion.service.Util;
import com.ipartek.formacion.service.exceptions.CursoServiceImpException;

/**
 * Servlet implementation class CursoServlet
 */
public class CursoServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(CursoServlet.class);
	private static final long serialVersionUID = 1L;
    private CursoService cS;
    //Variable que almacena las peticiones
    private RequestDispatcher rd;
   
	public void init() throws ServletException{
    	//Se inicializa CursoServiceImp que contiene los metodos CRUD
    	cS = new CursoServiceImp();
    	//llama al metodo init del padre
    	super.init();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.trace("Metodo doGet de CursoServlet");
		//Recogemos el parametro de lo que queremos hacer
		String operacion = request.getParameter(Constantes.PAR_OPERACION);
		int op = -1;
		try{
			//Cambiamos el valor de la variable de String a int
			op = Integer.parseInt(operacion);
			//Switch que gestiona la redireccion en funcion del parametro
			switch(op){
			case Constantes.OP_CREATE:
				LOG.trace("CREATE");
				//Se prepara la redireccion a la pagina cursos/curso.jsp
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
				break;
			case Constantes.OP_READ:
				LOG.trace("READ");
				cargarListaCursos(request);
				break;
			case Constantes.OP_UPDATE:
			{
				LOG.trace("UPDATE");
				int codigo = -1;
				//Recogemos el parametro(codigo)
				codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
				//Recogemos el curso que buscamos mediante el codigo y lo guardamos en la variable curso
				Curso curso = cS.getById(codigo);
				//Metemos el nuevo curso en la request
				request.setAttribute(Constantes.ATT_CURSO, curso);
				//Se prepara la redireccion a la pagina cursos/curso.jsp
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
			}
				break;
			case Constantes.OP_DELETE:
			{
				LOG.trace("DELETE");
				int codigo = -1;
				//Recogemos el parametro(codigo)
				codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
				//Borramos el elemento
				cS.delete(codigo);
				//Mostramos mensaje
				request.setAttribute(Constantes.ATT_MENSAJE, "El curso ha sido borrado correctamente");
				//Mostrar lista
				cargarListaCursos(request);
			}	
				break;
			default:
				LOG.trace("DEFAULT");	
				break;
			}
		}catch(Exception e){
			LOG.error(e.getMessage());
			//En caso de excepcion prepara la redireccion al index
			response.sendRedirect(Constantes.JSP_HOME);
			return;
		}
		//Hace la redireccion
		rd.forward(request, response);
	}

	private void cargarListaCursos(HttpServletRequest request) {
		LOG.trace("Metodo cargarListaCursos de CursoServlet");
		//Obtenemos los objetos cursos y los metemos en la lista cursos
		List<Curso> cursos = cS.getAll();
		//Fijamos la pagina de destino
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);
		//Añadimos la lista al request
		request.setAttribute(Constantes.ATT_LISTADO_CURSOS, cursos);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.trace("Metodo doPost de CursoServlet");
		//Creamos una variable cursos nula
		Curso curso = null;
		//Variable que contiene el mensaje
		String mensaje = "";
		int codigo = -1;
		try{
			//Recogemos el parametro codigo del request y lo cambiamos a Integer
			codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
			//Cargamos la variable con los paramentros
			curso = recogerParametros(request);
			//Si existe el codigo...
			if(curso.getCodigo() > curso.CODIGO_NULO){
				//UPDATE cS con curso
				cS.update(curso);
				mensaje = "El curso ha sido actualizado correctamente";
			}else{
				//CREATE cS con curso
				cS.create(curso);
				mensaje = "El curso ha sido creado correctamente";
			}
			//Cargamos la lista de cursos
			cargarListaCursos(request);
		}catch(NullPointerException e){
			LOG.error(e.getMessage());
		}catch(Exception e){
			LOG.error(e.getMessage());
			mensaje = "Se ha producido un error inesperado. \nContacte con el administrador";
			response.sendRedirect(Constantes.JSP_HOME);
			if(codigo == -1){
				rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);
				request.setAttribute(Constantes.ATT_CURSO, curso);
				mensaje = "Se ha introducido una operacion inesperada."+ 
				"\nContacte con el administrador del sistema";
			//Si no	
			}else{
				curso = cS.getById(codigo);
				//Prepara la redireccion. Envia el curso
				request.setAttribute(Constantes.ATT_CURSO, curso);
				//Prepara la redireccion
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
				//Guarda la excepcion como mensaje
				mensaje = e.getMessage();
				//Mensaje para el programador
				System.out.println(mensaje);
			}
		}
		//Prepara la redireccion. Envia el mensaje
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		//Realiza la redireccion
		rd.forward(request, response);
	}

    private Curso recogerParametros(HttpServletRequest request) throws Exception {
    	LOG.trace("Metodo recogerParametros de CursoServlet");
		//Creamos un nuevo curso
    	Curso curso = new Curso();
		try{
			curso.setCodigo(Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO)));
			try{
				curso.setNombre(request.getParameter(Constantes.PAR_NOMBRE));
			}catch(CursoServiceImpException e){
				e.getMessage();
				LOG.error(e.getMessage());
			}
			curso.setDuracion(Integer.parseInt(request.getParameter(Constantes.PAR_DURACION)));
			String fechaIni = request.getParameter(Constantes.PAR_FINICIO);
			String fechaFin = request.getParameter(Constantes.PAR_FFIN);
			if(fechaIni!=null && !"".equalsIgnoreCase(fechaIni)){
				curso.setfInicio(Util.parseLatinDate(fechaIni));
			}
			if(fechaFin!=null && !"".equalsIgnoreCase(fechaFin)){
				curso.setfFin(Util.parseLatinDate(fechaFin));
			}
			//TODO
			//curso.setAlumnos(alumnos);
			//curso.setProfesor(profesor);
			//Controla todas las excepciones que se puedan crear al generar un curso
		}catch(Exception e){
			//Generamos una traza
			e.printStackTrace();
			LOG.error(e.getMessage());
			throw new Exception("Los datos no son validos: "+e.getMessage());
		}
		return curso;
	}
    
	@Override
	public void destroy() {
		LOG.trace("CursoServlet destruido");
    	//Le damos valor a nulo
		this.cS = null;
		super.destroy();
	}
}
