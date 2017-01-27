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
     * @throws ServletException 
     * @see HttpServlet#HttpServlet()
     */
    public CursoServlet() throws ServletException {
    	cS=new CursoServiceImp();
        super.init();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operacion=request.getParameter(Constantes.PAR_OPERACION);
		int op=-1;
		try{
			op=Integer.parseInt(operacion);
			switch(op){
			
			case Constantes.OP_CREATE:
				rd=request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
				break;
			case Constantes.OP_DELETE:
				{
					int codigo=-1;
					codigo=Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
					cS.delete(codigo);
					request.setAttribute(Constantes.ATT_MENSAJE, "El curso ha sido borrado con exito");
					cargarListaCursos(request);
				}
				break;
			case Constantes.OP_READ:
				cargarListaCursos(request);
				break;
			case Constantes.OP_UPDATE:
				{
					int codigo=-1;
					codigo=Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
					Curso curso=cS.getById(codigo);
					rd=request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
					request.setAttribute(Constantes.ATT_CURSO, curso);
				}
				break;
			default:
				cargarListaCursos(request);
				break;
			}	
		}catch(Exception e){
			response.sendRedirect(Constantes.JSP_HOME);
			return;
		}
		
		rd.forward(request, response);
	}

	private void cargarListaCursos(HttpServletRequest request) {
		List<Curso> cursos=cS.getAll();
		
		rd=request.getRequestDispatcher(Constantes.JSP_LISTADO_CURSO);
		request.setAttribute(Constantes.ATT_LISTADO_CURSOS, cursos);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Curso curso=null;
		String mensaje="";
		int codigo=-1;
		try{
			codigo=Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
			curso=recogerParametros(request);
			curso.setCodigo(codigo);
			
			if(curso.getCodigo()>Curso.CODIGO_NULO){
				cS.update(curso);
				mensaje="El curso ha sido actualizado con exito";
			}else{
				cS.create(curso);
				mensaje="El curso ha sido creado con exito";
			}
			cargarListaCursos(request);
		}catch(Exception e){
			if(codigo==-1){
				rd=request.getRequestDispatcher(Constantes.JSP_LISTADO_CURSO);
				mensaje="Se ha producido una operacion inesperada contacte con el administracion del sistema";
			}else{
				curso=cS.getById(codigo);
				rd=request.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
				request.setAttribute(Constantes.ATT_CURSO, curso);
				mensaje=e.getMessage();
			}
			System.out.println(mensaje);
		}
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(request, response);
	}
	private Curso recogerParametros(HttpServletRequest request) throws Exception {
		Curso curso=new Curso();
		try{
			curso.setNombre(request.getParameter(Constantes.PAR_NOMBRE));
			String nDuracion=request.getParameter(Constantes.PAR_DURACION);
			if("".equalsIgnoreCase(nDuracion)){
				nDuracion="0";
			}
			int nDuracion2=Integer.parseInt(nDuracion);
			curso.setDuracion(nDuracion2);
			String dateIni =request.getParameter(Constantes.PAR_FINI);
			String dateFin=request.getParameter(Constantes.PAR_FFIN);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			//el profe ha echo un paso intermedio mirar y en util.java para el formato
			curso.setfInicio(dateFormat.parse(dateIni));
			curso.setfFin(dateFormat.parse(dateFin));	
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Los datos introducidos no son validos"+e.getMessage());
		}
		
		return curso;
	}

	public void destroy() {
		//cuando se destruye el servlet
		this.cS=null;
		super.destroy();
	}

}
