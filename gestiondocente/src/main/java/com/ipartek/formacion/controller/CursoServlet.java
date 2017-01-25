package com.ipartek.formacion.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.service.CursoService;
import com.ipartek.formacion.service.CursoServiceImp;
import com.ipartek.formacion.service.Util;



/**
 * Servlet implementation class CursoServlet
 */
public class CursoServlet extends HttpServlet {
	

	private static final long serialVersionUID = 1L;
	private CursoService cS;
	private RequestDispatcher rd;
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
	    cS = new CursoServiceImp();
	    super.init();
	}
	    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, 
	 * 		HttpServletResponse response)
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
			//String operacion = request.getParameter(Constantes.PAR_OPERACION);
			// op= -1;
		
			int operacion = -1;
			String mensaje = "";
			try{
				//op= Integer.parseInt(operacion);
				operacion= recogerOperacion(request);
		
				switch (operacion){
					case Constantes.OP_CREATE:{
						// a la vista cursos/curso.jsp
						//rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
						
						procesarCreateOrUpdate(request);
				}	
						break;
				case Constantes.OP_READ: {
					// REDIRECCIONA cursos/listado.jsp
					
					cargarListaCursos(request);
				}	
					break;
	
				case Constantes.OP_UPDATE:{
					//int codigo = -1;
					//codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
					//rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO); // redirigir a: /curso.jsp
					
					int codigo = cargarCodigo(request);
					Curso curso = cS.getById(codigo); 
					request.setAttribute(Constantes.ATT_CURSO,curso);
					
					procesarCreateOrUpdate(request);
					
					break;
				} //cerrar el ciclo de la variable codigo = -1.
				case Constantes.OP_DELETE:{
					//int codigo = -1;
					//codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
					//request.setAttribute(Constantes.ATT_MENSAJE, "Curso borrado.");
					
					int codigo = cargarCodigo(request);
					cS.delete(codigo);
					
					cargarListaCursos(request);
				}
					break;
					
					default:
					
				}
			} catch (Exception e){
				
				cargarListaCursos(request);
				e.printStackTrace();
	
			}
			rd.forward(request, response);
	}
	
	private void procesarCreateOrUpdate(HttpServletRequest request) {
		rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
		
	}
	
	private int cargarCodigo(HttpServletRequest request) {
		int codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
		return codigo;
	}

	private void cargarListaCursos(HttpServletRequest request) {
		
		//List<Curso> cursos = cS.getAll(); // OPERACION getAll()
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS); // va a "cursos/listado.jsp"
		request.setAttribute(Constantes.ATT_LISTADO_CURSOS, cS.getAll());  // atributo "listado-cursos"
		
	}
	
	private int recogerOperacion(HttpServletRequest request) {
		int op = Integer.parseInt(request.getParameter(Constantes.PAR_OPERACION));
		return op;
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
		int codigo = -1;
		try{
			codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));	
			curso = recogerParametros(request);
			curso.setCodigo(codigo);
			
			if(curso.getCodigo()> Curso.CODIGO_NULO){ //update
				cS.update(curso);
				mensaje = "El curso ha sido actualizado";	
			}else {  // create
				cS.create(curso);
				mensaje= "El curso ha sido creado correctamente";
			}
			cargarListaCursos(request);
			
		} catch (NumberFormatException e){
			mensaje = "Se ha producido una operacion inesperada contacte con el administrador del sistema.";
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);
			
		}catch (Exception e) {
			if (codigo == -1){ //Create
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);	
			}else{ // Update
				curso = cS.getById(codigo);
				request.setAttribute(Constantes.ATT_CURSO, curso);
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
			}
			mensaje = e.getMessage();
			System.out.println(mensaje);
		}
		
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(request,response);
	}

	
	private Curso recogerParametros(HttpServletRequest request) throws Exception {
		Curso curso = new Curso();
		
		try{
			
			String duracion = request.getParameter(Constantes.PAR_DURACION);
			int nDuracion = Integer.parseInt(duracion);
			curso.setDuracion(nDuracion);
		
			String inicio= request.getParameter(Constantes.PAR_FECHA_INICIO);
			String fin= request.getParameter(Constantes.PAR_FECHA_FIN);
			
			if (inicio != null && !"".equalsIgnoreCase(fin)){
				curso.setFechaInicio(Util.parseLatinDate(inicio));
			}
			if (fin != null && !"".equalsIgnoreCase(fin)){
				curso.setFechaFin(Util.parseLatinDate(fin));
			}
			//String duracion= request.getParameter(Constantes.PAR_DURACION);
			//curso.setDuracion(Integer.parseInt(duracion));
			//String pattern = "dd/MM/yyyy";
			//SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			//curso.setFechaFin(dateFormat.parse(inicio));
			//curso.setFechaFin(dateFormat.parse(fin));
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return curso;
	}

	@Override
	public void destroy() {
		this.cS = null;
		super.destroy();
	}
}
