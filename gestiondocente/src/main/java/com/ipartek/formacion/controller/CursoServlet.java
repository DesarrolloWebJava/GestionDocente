package com.ipartek.formacion.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.service.CursoService;
import com.ipartek.formacion.service.CursoServiceImp;

/**
 * Servlet implementation class CursoServlet
 */
public class CursoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CursoService cS;
    private RequestDispatcher rd;
   
	public void init() throws ServletException{
    	//Se inicializa CursoServiceImp que contiene los metodos CRUD
    	cS = new CursoServiceImp();
    	super.init();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recogemos el parametro de lo que queremos hacer
		String operacion = request.getParameter(Constantes.PAR_OPERACION);
		int op = -1;
		try{
			//Cambiamos el valor de la variable de String a int
			op = Integer.parseInt(operacion);
			//Switch que gestiona la redireccion en funcion del parametro
			switch(op){
			case Constantes.OP_CREATE:
				//Se prepara la redireccion a la pagina cursos/curso.jsp
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
				break;
			case Constantes.OP_READ:
				cargarListaCursos(request);
				break;
			case Constantes.OP_UPDATE:
			{
				int codigo = -1;
				//Recogemos el parametro(codigo)
				codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
				//Recogemos el curso que buscamos mediante el codigo y lo guardamos en la variable curso
				Curso curso = cS.getById(codigo);
				//Se redirige a la pagina cursos/curso.jsp
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
				//Preparamos el redireccionamiento pasando el curso
				request.setAttribute(Constantes.ATT_CURSO, curso);
			}
				break;
			case Constantes.OP_DELETE:
			{
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
					
				break;
			}
		}catch(Exception e){
			//En caso de excepcion prepara la redireccion al index
			response.sendRedirect(Constantes.JSP_HOME);
			return;
		}
		//Hace la redireccion
		rd.forward(request, response);
	}

	private void cargarListaCursos(HttpServletRequest request) {
		//Obtenemos los objetos cursos y los metemos en la lista cursos
		List<Curso> cursos = cS.getAll();
		//Fijamos la pagina de destino
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);
		//Añadimos la lista al request
		request.setAttribute(Constantes.ATT_CURSO, cursos);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Creamos una variable cursos nula
		Curso curso = null;
		//Variable que contiene el mensaje
		String mensaje = "";
		int codigo = -1;
		try{
			//Convertimos el codigo a int, si lo han tocado se va al catch
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
			
		}catch(Exception e){
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
			}
		}
		//Prepara la redireccion. Envia el mensaje
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		//Realiza la redireccion
		rd.forward(request, response);
	}

    private Curso recogerParametros(HttpServletRequest request) throws Exception {
		//Creamos un nuevo curso
    	Curso curso = new Curso();
		try{
			curso.setCodigo(Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO)));
			curso.setNombre(request.getParameter(Constantes.PAR_NOMBRE));
			curso.setDuracion(Integer.parseInt(request.getParameter(Constantes.PAR_DURACION)));
			String fechaIni = request.getParameter(Constantes.PAR_FINICIO);
			String fechaFin = request.getParameter(Constantes.PAR_FFIN);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat fechaFormato = new SimpleDateFormat(pattern);
			curso.setfInicio(fechaFormato.parse(fechaIni));
			curso.setfFin(fechaFormato.parse(fechaFin));
			//TODO
			//curso.setAlumnos(alumnos);
			//curso.setProfesor(profesor);
		}catch(Exception e){
			throw new Exception("Los datos no son validos: "+e.getMessage());
		}
		return curso;
	}
    
	@Override
	public void destroy() {
    	//Le damos valor a nulo
		cS = null;
		super.destroy();
	}
}
