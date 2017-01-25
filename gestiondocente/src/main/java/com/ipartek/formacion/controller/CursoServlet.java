package com.ipartek.formacion.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.dbms.pojo.exceptions.CursoException;
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int opcion = -1;
		String operacion = req.getParameter(Constantes.PAR_OPERACION);
		try{
			opcion = Integer.parseInt(operacion);
			switch (opcion)
			{
			case Constantes.OP_CREATE:
			{
				rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
			}
			break;
			case Constantes.OP_READ:
			{
			  cargarListaCursos(req);	
			}
			break;
			case Constantes.OP_UPDATE:
			{
			   Curso curso = null;
			   int codigo = -1;
			   codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
			   curso = cS.getById(codigo);
			   req.setAttribute(Constantes.ATT_CURSO, curso);
			   rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
			}
			break;
			case Constantes.OP_DELETE:
			{
				int codigo=-1;
				codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
				cS.delete(codigo);
				req.setAttribute(Constantes.ATT_MENSAJE,"El curso ha sido eliminado correctamente");
				cargarListaCursos(req);
			}
			break;
			default:
			{
			 cargarListaCursos(req);	
			}
			break;
			}// fin del switch
		}catch (Exception e)
		{
		  e.printStackTrace();	
		  resp.sendRedirect(Constantes.JSP_HOME);	
		  return;
		}
		
		rd.forward(req, resp);
	}

	private void cargarListaCursos(HttpServletRequest req) {
		List<Curso>cursos = cS.getAll();
		req.setAttribute(Constantes.ATT_LISTADO_CURSOS,cursos);
		rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String mensaje="";
		Curso curso=null;
		int codigo = -1;
		codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
		try {
			
			 curso = recogerParametros(req);
			 if (curso.getCodigo()> Curso.CODIGO_NULO){
				 // Actualizar
				 cS.update(curso);
				 mensaje = "El curso ha sido actualizado correctamente";
			 }
			 else
			 {
				 // crear
				 cS.create(curso);
				 mensaje = "el curso ha sido creado correctamente";
				 
			 }
			cargarListaCursos(req);
		} catch (NumberFormatException e){
			e.printStackTrace();
			mensaje = "Se ha producido un error inesperado, contacta con el admin que la llevas clara majo";
			rd = req.getRequestDispatcher(Constantes.JSP_HOME);
		
		} catch (Exception e) {
				e.printStackTrace();
				if (codigo == -1){ // el error es en el create
				   mensaje = e.getMessage();
				   req.setAttribute(Constantes.ATT_CURSO, curso);
				   rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
				}
				else
				{ // el error es al actualizar
					mensaje = e.getMessage();
					curso = cS.getById(codigo);
					req.setAttribute(Constantes.ATT_CURSO, curso);
					rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
					
				}
						   
		}// fin del cacth
		
		
	 req.setAttribute(Constantes.ATT_MENSAJE, mensaje);
	 rd.forward(req, resp);
	 
	}

	
	
	private Curso recogerParametros(HttpServletRequest req) throws Exception {
		
		Curso curso = new Curso();
		Date fecha;
		SimpleDateFormat dfmt = new SimpleDateFormat(Constantes.FECHA_PATRON);
		
		try {
			curso.setCodigo(Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO)));
			curso.setNombre(req.getParameter(Constantes.PAR_NOMBRE));
			curso.setDuracion(Integer.parseInt(req.getParameter(Constantes.PAR_DURACION)));
			String fInicio = req.getParameter(Constantes.PAR_FECHA_INI);
			String fFin    = req.getParameter(Constantes.PAR_FECHA_FIN);
			fecha = dfmt.parse(fInicio);
			curso.setfInicio(fecha);
			fecha = dfmt.parse(fFin);
			curso.setfFin(fecha);
		
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Los datos no son validos ->" + e.getMessage());
		}
		
		return curso;
		
	}

	@Override
	public void init() throws ServletException {
		// Nuestro codigo de la muerte
		cS = new CursoServiceImp();
		super.init();
	}

	@Override
	public void destroy() {
		cS=null;
		super.destroy();
	}
	

}
