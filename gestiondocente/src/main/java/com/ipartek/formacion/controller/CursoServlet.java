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
import com.ipartek.formacion.service.Util;

/**
 * Servlet implementation class CursoServlet
 */
public class CursoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CursoService cS;
	private RequestDispatcher rd;
       

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		cS = new CursoServiceImp();
		super.init();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Curso> cursos = cS.getAll();
		
		String operacion = request.getParameter(Constantes.PAR_OPERACION);
		int op = -1;
		op = Integer.parseInt(operacion);
		
		switch(op){
			case Constantes.OP_CREATE:{
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
			}break;
			case Constantes.OP_READ:{
				cargarListaCursos(request);
				
			}break;
			case Constantes.OP_UPDATE:{
				int codigo = -1;
				codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
				Curso curso= cS.getById(codigo);
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
				request.setAttribute(Constantes.ATT_CURSO, curso);
			}break;
			case Constantes.OP_DELETE:{
				int codigo = -1;
				codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
				cS.delete(codigo);
				request.setAttribute(Constantes.ATT_MENSAJE, "El curso ha sido borrado correctamente");
				cargarListaCursos(request);
			}break;
			default:{
				cargarListaCursos(request);
			}
		}
		rd.forward(request, response);
	}

	private void cargarListaCursos(HttpServletRequest request) {
		List<Curso> cursos = cS.getAll();
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);
		request.setAttribute(Constantes.ATT_LISTADO_CURSOS, cursos);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Curso curso = null;
		String mensaje = "";
		int codigo = -1;
		
		try {
			codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
			curso = recogerParametros(request);
			curso.setCodigo(codigo);
			
			if(curso.getCodigo()>Curso.CODIGO_NULO){
				cS.update(curso);
				mensaje = "El curso ha sido actualizado correctamente";
			}else{
				cS.create(curso);
				mensaje = "El curso ha sido creado correctamente";
			}
			cargarListaCursos(request);
		} catch (Exception e) {
			
			if(codigo == -1){
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
				request.setAttribute(Constantes.ATT_CURSO, curso);
				mensaje = "Se ha producido una operacion inesperada contacte con el adminstrador";
			}else{
				curso = cS.getById(codigo);
				request.setAttribute(Constantes.ATT_CURSO, curso);
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
				mensaje = e.getMessage();
			}
			System.out.println(mensaje);
		}
		
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(request, response);
		
	}
	private Curso recogerParametros(HttpServletRequest request) throws Exception {
		Curso curso = new Curso();		
		int duracion = -1;
		try {
			
			curso.setNombre(request.getParameter(Constantes.PAR_NOMBRE));
			duracion = Integer.parseInt(request.getParameter(Constantes.PAR_DURACION));
			curso.setDuracion(duracion);
			
			String dateInicio = request.getParameter(Constantes.PAR_FINICIO);
			/*String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);*/
			
			curso.setfInicio(Util.parseLatinDate(dateInicio));
			
			String dateFin = request.getParameter(Constantes.PAR_FFIN);
			curso.setfFin(Util.parseLatinDate(dateFin));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Los datos no son validos: " + e.getMessage());
		}
		
		return curso;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	@Override
	public void destroy() {
		this.cS = null;
		super.destroy();
	}

}
