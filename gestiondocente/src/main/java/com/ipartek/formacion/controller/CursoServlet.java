package com.ipartek.formacion.controller;

import java.io.IOException;
import java.text.ParseException;
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
   
	@Override
	public void init() throws ServletException {
	    	cS = new CursoServiceImp();
	    super.init();
	}
	    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * <a>
		 * <button>
		 * --> Operacion getAll()
		 * --> Update -->Formulario con el curso
		 * --> Create ---> Formulario
		 * --> Operacion Delete
		 * 1º Recoger OPCION (Constantes)
		 * 2º Switch para realizar la operacion demandada
		 */
		
		//Recoger el parametro "op"
		String operacion = request.getParameter(Constantes.PAR_OPERACION);
		int op= -1;
		op= Integer.parseInt(operacion);
		
		switch (op){
			case Constantes.OP_CREATE:
				// a la vista cursos/curso.jsp
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
				break;
			case Constantes.OP_READ:
				// REDIRECCIONA cursos/listado.jsp
				cargarListaCursos(request);
				break;

			case Constantes.OP_UPDATE:{
				int codigo = -1;
				
				codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
				Curso curso = cS.getById(codigo); 
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO); // redirigir a: /curso.jsp
				request.setAttribute(Constantes.ATT_CURSO, curso); 
				break;
			} //cerrar el ciclo de la variable codigo = -1.
			case Constantes.OP_DELETE:{
				int codigo = -1;
				
				codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
				request.setAttribute(Constantes.ATT_MENSAJE, "Curso borrado.");
				cS.delete(codigo);
				
				cargarListaCursos(request);
			}
				break;
				
				default:
					cargarListaCursos(request);
					break;	
		}	
		rd.forward(request, response);
	}
	
	private void cargarListaCursos(HttpServletRequest request) {
		
		List<Curso> cursos = cS.getAll(); // OPERACION getAll()
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS); // va a "cursos/listado.jsp"
		request.setAttribute(Constantes.ATT_LISTADO_CURSOS, cursos);  // atributo "listado-cursos"
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * <form>
		 * ---> Operacion de Crear
		 * ---> Operacion de Update
		 */
		
		Curso curso = null;
		String mensaje = "";
			
		try{
			curso = recogerParametros(request);
			if(curso.getCodigo()> Curso.CODIGO_NULO){ //update
				cS.update(curso);
				mensaje = "El curso ha sido actualizado";	
			}else {  // create
				cS.create(curso);
				mensaje= "El curso ha sido creado correctamente";
			}
			cargarListaCursos(request);
			
		}catch (Exception e) {
			
			//rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);
			//mensaje = "Se ha producido un error inesperado.";	
		}
		
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(request,response);
	}

	
	private Curso recogerParametros(HttpServletRequest request) throws ParseException {
		Curso curso = new Curso();
		
		try{
			int codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
			curso.setCodigo(codigo);
			curso.setNombreCurso(request.getParameter(Constantes.PAR_NOMBRE_CURSO));
			
			String duracion= request.getParameter(Constantes.PAR_DURACION);
			curso.setDuracion(Integer.parseInt(duracion));
			
			String inicio= request.getParameter(Constantes.PAR_FECHA_INICIO);
			String fin= request.getParameter(Constantes.PAR_FECHA_FIN);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			curso.setFechaFin(dateFormat.parse(inicio));
			curso.setFechaFin(dateFormat.parse(fin));
			
		}catch (Exception e) {
		}
		return curso;
	}

	@Override
	public void destroy() {
		cS = null;
		super.destroy();
	}
}
