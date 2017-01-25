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
   
	@Override
	public void init() throws ServletException {
		cS = new CursoServiceImp();
		super.init();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String operacion = req.getParameter(Constantes.PAR_OPERACION);
		int op = -1;
		try{
			op = Integer.parseInt(operacion);
			switch (op){
				case Constantes.OP_CREATE:
					rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSOS);
					break;
				case Constantes.OP_READ:
					cargarListaCursos(req);
					break;
				case Constantes.OP_UPDATE:{
					int codigo = -1;
					codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
					Curso curso = cS.getById(codigo);
					rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSOS);
					req.setAttribute(Constantes.ATT_CURSO, curso);
				}
					break;
				case Constantes.OP_DELETE:{
					int codigo = -1;
					codigo= Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
					cS.delete(codigo);
					req.setAttribute(Constantes.ATT_MENSAJE, "el curso a sido borrado");
					cargarListaCursos(req);
				}
					break;
				default:
					cargarListaCursos(req);
					break;		
			}
		}catch(Exception e){
			resp.sendRedirect(Constantes.JSP_HOME);
			return;
		}
		rd.forward(req, resp);
	}

	private void cargarListaCursos(HttpServletRequest req) {
		List<Curso> cursos = cS.getAll();
		rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);
		req.setAttribute(Constantes.ATT_LISTADO_CURSOS, cursos);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Curso curso = null;
		String mensaje = "";
		int codigo = -1;
		
		try{
			codigo= Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
			
			curso = recogerParamentros(request);
			curso.setCodigo(codigo);
			
			if(curso.getCodigo() > curso.CODIGO_NULO){
				cS.update(curso);
				mensaje = "El curso a sido actualizado correctamente";
			}else{
				cS.create(curso);
				mensaje = "El curso a sido creado";
			}
			
			cargarListaCursos(request);
		
		}catch(Exception e){
			if(codigo == -1){
				cargarListaCursos(request);
				mensaje="Se ha producido un error inesperado";
			}else{
				curso = cS.getById(codigo);
				request.setAttribute(Constantes.ATT_CURSO, curso);
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSOS);
				mensaje = e.getMessage();
			}
			//System.out.println(e.getMessage());
		}
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(request, response);
	}
	private Curso recogerParamentros(HttpServletRequest request) throws Exception {
		Curso curso = new Curso();
		try{
			curso.setCodigo(Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO)));
			curso.setNombre(request.getParameter(Constantes.PAR_NOMBRE));
			
			String duracion = request.getParameter(Constantes.PAR_DURACION);
			if (!"".equals(duracion)){
				curso.setDuracion(Integer.parseInt(duracion));
			}
			
			String dateini = request.getParameter(Constantes.PAR_FECHAINICIO);
			String patternini = "dd/MM/yyyy";
			SimpleDateFormat dateFormatini = new SimpleDateFormat(patternini);
			curso.setFechaInicio(dateFormatini.parse(dateini));
			
			String datefin = request.getParameter(Constantes.PAR_FECHAFIN);
			String patternfin = "dd/MM/yyyy";
			SimpleDateFormat dateFormatfin = new SimpleDateFormat(patternfin);
			curso.setFechaFin(dateFormatfin.parse(datefin));
			
			
		} catch (Exception e){
			throw new Exception("Los datos son incorrectos: " + e.getMessage());
		}
		return curso;
	}

	@Override
	public void destroy() {
		cS = null;
		super.destroy();
	}

}