package com.ipartek.formacion.controler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.service.AlumnoService;
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
		String opercacion = request.getParameter(Constantes.PAR_OPERACION);
		
		int op = -1;
		try{
				
				op = Integer.parseInt(opercacion);
				switch(op){
					case Constantes.OP_CREATE:
					rd=  request.getRequestDispatcher(Constantes.JSP_CREAR_CURSO);
					 
					break;
					case Constantes.OP_READ:
						cargarListaCursos(request);
					break;
					case Constantes.OP_UPDATE:
					{	
						 int codigo = -1;
						 codigo=Integer.parseInt( request.getParameter(Constantes.PAR_CODIGO));
						
						 Curso curso = cS.getById(codigo);
						 rd=  request.getRequestDispatcher(Constantes.JSP_CREAR_CURSO);
						 request.setAttribute(Constantes.ATT_CURSO, curso);
					}
					 break;
					 case Constantes.OP_DELETE:
					 {
						 int codigo = -1;
						 codigo=Integer.parseInt( request.getParameter(Constantes.PAR_CODIGO));
						// recoger variable alumno
						// Alumno alumno = aS.getById(codigo);
						 cS.delete(codigo);
						 //sacar un mensaje en la vista
						 request.setAttribute(Constantes.ATT_MENSAJE,"El Curso ha sido eliminado");
						 cargarListaCursos(request);
					 }
					 break;
					default:
						
					break;
						 
				}
			
		}catch (Exception e){
			response.sendRedirect(Constantes.JSP_HOME);
			return;
			
		}
		
		//hace la redireccion
		rd.forward(request,response);
			
	}
	
	
	private void cargarListaCursos(HttpServletRequest request) {

		List<Curso> cursos = cS.getAll();
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_CURSO);
		request.setAttribute(Constantes.ATT_CURSO, cursos);
		
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Curso curso = null;
		String mensaje="";
		int  codigo = -1;
		try {
			
			codigo =Integer.parseInt( request.getParameter(Constantes.PAR_CODIGO));
			curso = recogerParametros(request);
			curso.setCodigo(codigo);
			if(curso.getCodigo()>Curso.CODIGO_NULO){
				cS.update(curso);
				mensaje ="El Curso ha sido actualizado correctamente";
			}else{
				cS.create(curso);
				mensaje ="El Curso ha sido correctamente creado";
			}
			cargarListaCursos(request);
			//procesaremos update or insert
		} catch (Exception e) {
			
			if ( codigo == -1){
				rd = request.getRequestDispatcher(Constantes.JSP_CREAR_CURSO);
				
				mensaje= ("Se ha producido una operación inesperada"+e.getMessage());
			}else{
				//validamos datos
				curso = cS.getById(codigo);
				
				//redirigir al formulario
				rd = request.getRequestDispatcher(Constantes.JSP_CREAR_CURSO);
				request.setAttribute(Constantes.ATT_CURSO, curso);
				//mensaje de error
				mensaje= e.getMessage();
			}
			
			
		}
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(request,response); 
	}
	/**
	 * metado para recoger los datos del formulario
	 * @param request
	 * @return
	 * @throws Exception
	 */

	private Curso recogerParametros(HttpServletRequest request) throws Exception {
		
		Curso curso = new Curso();
		try{
			
			int codigo =Integer.parseInt( request.getParameter(Constantes.PAR_CODIGO));
			curso.setCodigo(codigo);
			
			curso.setNombre( request.getParameter(Constantes.PAR_NOMBRE));
			String duracion=request.getParameter(Constantes.PAR_DURACION);
			if(null!=duracion&&!"".equals(duracion))
			{
				curso.setDuracion(Integer.parseInt(request.getParameter(Constantes.PAR_DURACION)));
			}			
				
			
			{
				String date= request.getParameter(Constantes.PAR_FINICIO);
				String pattern = "dd/MM/yyyy";
				SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
				curso.setfInicio( dateFormat.parse(date));
			}
			{
				String date= request.getParameter(Constantes.PAR_FFIN);
				String pattern = "dd/MM/yyyy";
				SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
				curso.setfFin( dateFormat.parse(date));
			}
			
			
			//alumno.setActivo(Boolean.parseBoolean( request.getParameter(Constantes.PAR_ACTIVO)));
			}catch(Exception e){
				
				//throw new Exception(e.getMessage());
				throw new Exception("los datos no son validos"+e.getMessage());
			}
		
		
		//String nombre = request.getParameter("<%Constantes.PAR_NOMBRE%>");
		
		return curso;
	}



	@Override
	public void destroy() {
		cS = null;
		super.destroy();
	}

}
