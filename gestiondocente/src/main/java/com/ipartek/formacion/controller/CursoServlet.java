package com.ipartek.formacion.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.dbms.pojo.CursoFechaInicioComparator;
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


	@Override
	public void init() throws ServletException {
		cS=new CursoServiceImp();
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int operacion=0;
		int codigo=-1;
		Curso curso=null;
		
		try{
			operacion=Integer.parseInt(request.getParameter(Constantes.PAR_OPERACION));

		switch(operacion){
		case Constantes.OP_READ:
			cargarListadoCursos(request);

			break;
		case Constantes.OP_CREATE:
			
			rd=request.getRequestDispatcher(Constantes.JSP_FORM_CURSO);
			break;
		case Constantes.OP_UPDATE:
			codigo=Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
			curso=cS.getById(codigo);
			request.setAttribute(Constantes.ATT_CURSO, curso);
			rd=request.getRequestDispatcher(Constantes.JSP_FORM_CURSO);
			
			break;
		case Constantes.OP_DELETE:
			codigo=Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
			cS.delete(codigo);
			request.setAttribute(Constantes.ATT_MENSAJE, "El curso ha sido eliminado.");
			cargarListadoCursos(request);
			break;
			default:
				cargarListadoCursos(request);
				break;
				
		}
		}catch(Exception e){
			response.sendRedirect(Constantes.JSP_HOME);
			return;
			
		}
		rd.forward(request, response);

	}

	private void cargarListadoCursos(HttpServletRequest request) {
		List<Curso> cursos=null;
		cursos=cS.getAll();
		//cursos.sort(null);//utiliza compareTo d la clse Curso
		cursos.sort(new CursoFechaInicioComparator());
		//ó Collections.sort(cursos,new CursoFechaInicioComparator())
		Collections.reverse(cursos);
		request.setAttribute(Constantes.ATT_LISTADO_CURSOS,cursos);
		rd=request.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int codigo=-1;
		Curso curso=null;
		String mensaje="";

		try{
			codigo=Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
			curso=recogerParametros(request);
			if(curso.getCodigo()>Curso.CODIGO_NULO){
				cS.update(curso);
				mensaje="Los datos se han actualizado correctamente";
			}else{
				cS.create(curso);
				mensaje="Los datos se han registrado correctamente";
			}
			cargarListadoCursos(request);
		}catch(NumberFormatException e){
			rd=request.getRequestDispatcher(Constantes.JSP_HOME);
			mensaje="Error: operación inesperada";
			
		}
		catch (Exception e){
			mensaje="Error: "+e.getMessage();
			if(codigo>Curso.CODIGO_NULO){
				curso=cS.getById(codigo);
				request.setAttribute(Constantes.ATT_CURSO, curso);
				rd=request.getRequestDispatcher(Constantes.JSP_FORM_CURSO);
				
			}
			else{
				//rd=request.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);
				cargarListadoCursos(request);
			}
			
		}
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(request, response);


	}

	private Curso recogerParametros(HttpServletRequest request) throws Exception {
		Curso curso=new Curso();
		try{
			curso.setCodigo(Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO)));
			curso.setNombre(request.getParameter(Constantes.PAR_NOMBRE));
			curso.setDuracion(Integer.parseInt(request.getParameter(Constantes.PAR_DURACION)));
			
			String date=request.getParameter(Constantes.PAR_FINICIO);		
			if(date!=null&&!"".equals(date)){
				curso.setfInicio(Util.parseLatinDate(date));
			}
			date=request.getParameter(Constantes.PAR_FFIN);
			if(date!=null&&!"".equals(date)){
				curso.setfFin(Util.parseLatinDate(date));
			}
					
		}catch(Exception e){
			e.printStackTrace();//para el desarrollador
			throw new Exception("Los datos no son válidos: "+e.getMessage());//para el usuario
		}
		return curso;
	}

}
