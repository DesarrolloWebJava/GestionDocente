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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CursoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String operacion =req.getParameter(Constantes.PAR_OPERACION);
		int op = -1;
		
		try{
			op=Integer.parseInt(operacion);
			switch (op){
			case Constantes.OP_CREATE:
			rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
				break;
			case Constantes.OP_READ:
				cargarListaCursos(req);
				break;
			case Constantes.OP_UPDATE:
			{
				int codigo = -1;
				codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
				Curso curso = cS.getById(codigo);
				
				rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
				req.setAttribute(Constantes.ATT_CURSO, curso);
			}
			break;
			case Constantes.OP_DELETE:
			{
				int codigo =-1;
				codigo=Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
				cS.delete(codigo);
				req.setAttribute(Constantes.ATT_MENSAJE, "El curso ha sido eliminado correctamente");
				cargarListaCursos(req);
			}
			break;
			default:
				cargarListaCursos(req);
				break;
			}
			
		}catch(Exception e){
			cargarListaCursos(req);
		}
		
		rd.forward(req, resp);
		
	}

	private void cargarListaCursos(HttpServletRequest req) {
		List<Curso> curso = cS.getAll();
		rd=req.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);
		req.setAttribute(Constantes.ATT_LISTADO_CURSOS, curso);
	}
	
	
	
	

	@Override
	public void destroy() {
		cS = null;
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		cS=new CursoServiceImp();
		super.init();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Curso curso = null;
		String mensaje ="";
		int codigo =-1;
		
		try{
			
			codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
			curso = recogerParametros(req);
			curso.setCodigo(codigo);
			
			if(curso.getCodigo()>Curso.CODIGO_NULO){
				cS.update(curso);
				mensaje="El curso ha sido actualizado correctamente";
			}else{
				cS.create(curso);
				mensaje = "El cusro ha sido creado correctamente";
			}
			cargarListaCursos(req);
		}catch (Exception e){
			
			if (codigo ==-1){
				cargarListaCursos(req);
				mensaje ="Se ha producido una operacion inesperada";
			}else{
				curso = cS.getById(codigo);
				req.setAttribute(Constantes.ATT_CURSO, curso);
				rd=req.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
				mensaje=e.getMessage();
			}
			
			System.out.println(mensaje);	
		}
		req.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(req, resp);
		
	}

	private Curso recogerParametros(HttpServletRequest req) throws Exception {
		
		Curso curso = new Curso();
		try{
			
			curso.setCodigo(Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO)));
			curso.setNombre(req.getParameter(Constantes.PAR_NOMBRE));
			curso.setDuracion(Integer.parseInt(req.getParameter(Constantes.PAR_DURACION)));
			
			String date = req.getParameter(Constantes.PAR_FINICIO);
			String pattern="dd/MM/yyyy";
			SimpleDateFormat dateformat = new SimpleDateFormat(pattern);
			curso.setfInicio(dateformat.parse(date));
			
			String datefinal = req.getParameter(Constantes.PAR_FFINAL);
			String patternfinal = "dd/MM/yyyy";
			SimpleDateFormat dateformatfinal = new SimpleDateFormat(patternfinal);
			curso.setfFinal(dateformatfinal.parse(datefinal));
			
		} catch(Exception e){
			throw new Exception("Los datos introducidos no son validos");
		}
			
		return curso;
		
	}

}
