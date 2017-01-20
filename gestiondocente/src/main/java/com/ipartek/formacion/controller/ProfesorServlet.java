package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.dbms.pojo.Profesor;
import com.ipartek.formacion.service.ProfesorService;
import com.ipartek.formacion.service.ProfesorServiceImp;

/**
 * Servlet implementation class ProfesorServlet
 */
public class ProfesorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfesorService pS;
	private RequestDispatcher rd;
	
	
	@Override
	public void init() throws ServletException {
		pS=new ProfesorServiceImp();
		super.init();
	}
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operacion=request.getParameter(Constantes.PAR_OPERACION);
		
		int op=0;
		try{
			op=Integer.parseInt(operacion);
			switch(op){
			case Constantes.OP_READ:
				cargarListaProfesores(request);
				break;
			case Constantes.OP_CREATE:
				rd=request.getRequestDispatcher(Constantes.JSP_FORM_PROFESOR);
				break;
			case Constantes.OP_UPDATE:
				
				rd=request.getRequestDispatcher(Constantes.JSP_FORM_ALUMNO);
				break;
				default:
					cargarListaProfesores(request);
					break;
				
			}
		}catch(Exception e){
			response.sendRedirect(Constantes.JSP_HOME);
			return;
		}

		rd.forward(request, response);

	}


	private void cargarListaProfesores(HttpServletRequest request) {
	
		Map<Integer,Profesor> profesores=(Map<Integer,Profesor>)pS.getAll();
		rd=request.getRequestDispatcher(Constantes.JSP_LISTADO_PROFESORES);
		request.setAttribute(Constantes.ATT_LISTADO_PROFESORES, profesores);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mensaje="";
		Profesor profesor=null;
		try{
			profesor=recogerParametros(request);
			if(profesor.getCodigo()>Profesor.CODIGO_NULO){
				pS.update(profesor);
				mensaje="El profesor ha sido actualizado correctamente";

				
			}else{
				pS.create(profesor);
				mensaje="El profesor ha sido creado correctamente";


			}
			
							
		}catch(Exception e){
			rd=request.getRequestDispatcher(Constantes.JSP_FORM_PROFESOR);
			mensaje=e.getMessage();
		}

		
		request.setAttribute(Constantes.ATT_MENSAJE,mensaje);
		rd.forward(request, response);

	}


	private Profesor recogerParametros(HttpServletRequest request) throws Exception {
		Profesor profesor=new Profesor();
		try{
			profesor.setCodigo(Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO)));
			profesor.setApellidos(request.getParameter(Constantes.PAR_APELLIDOS));
			profesor.setNombre(request.getParameter(Constantes.PAR_NOMBRE));
			profesor.setEmail(request.getParameter(Constantes.PAR_EMAIL));
		}catch(Exception e)
		{
			throw new Exception("Se ha producido un error "+e.getMessage());
		}
		return profesor;
	}





}
