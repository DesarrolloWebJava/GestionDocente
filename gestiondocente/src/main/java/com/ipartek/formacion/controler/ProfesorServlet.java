package com.ipartek.formacion.controler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.dbms.pojo.Profesor;
import com.ipartek.formacion.service.ProfesorService;
import com.ipartek.formacion.service.ProfesorServiceImp;

/**
 * Servlet implementation class ProfesorServlet
 */
public class ProfesorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProfesorService aP;   
    private RequestDispatcher rd;
  

	@Override
	public void init() throws ServletException {
		aP = new ProfesorServiceImp();
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String opercacion = request.getParameter(Constantes.PAR_OPERACION);
		//siempre q recogamos parametros de una url o de un formulario ->try catch
		
		
		//tengo que hacer un parse, pq el parametro me viene en string y yo quiero un -integer
		int op = -1;
		try{
			
			op = Integer.parseInt(opercacion);
			switch(op){
			 case Constantes.OP_CREATE:
				 //se va redirigir a la pagina profesores/profesor.jsp
				 rd=  request.getRequestDispatcher(Constantes.JSP_CREAR_PROFESOR);
				 
				 break;
			 case Constantes.OP_READ:
				 cargarListaProfesor(request);
				 break;
			 case Constantes.OP_UPDATE:
				 //tendriamos q recibir como parametro el codigo del profesor a modificar
				 break;
			default:
				cargarListaProfesor(request);
				break;
				 
			}
			
		}
		catch (Exception e){
			response.sendRedirect(Constantes.JSP_HOME);
			return;
			
		}
		
		//hace la redireccion
		rd.forward(request,response); 
		
	}
	/**
	 * Metodo para cargar la lista de profesores
	 * @param request
	 */

	private void cargarListaProfesor(HttpServletRequest request) {
		
		Map<Integer, Profesor> profesores = aP.getAll();
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_PROFESORES);
		request.setAttribute(Constantes.ATT_LISTADO_PROFESORES, profesores);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Profesor profesor = null;
		String mensaje="";
		try {
			profesor = recogerParametros(request);
			
			if(profesor.getCodigo() > Profesor.CODIGO_NULO){
				aP.update(profesor);
				mensaje ="El Profesor ha sido actualizado correctamente";
			}else{
				aP.create(profesor);
				mensaje ="El Profesor correctamente creado";
			}
			cargarListaProfesor(request);
			//procesaremos update or insert
		} catch (Exception e) {
			//redirigir al formulario
			rd = request.getRequestDispatcher(Constantes.JSP_CREAR_PROFESOR);
			//mensaje de error
			mensaje= e.getMessage();
			
		}
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(request,response); 
	}
private Profesor recogerParametros(HttpServletRequest request) throws Exception {
		
		Profesor profesor = new Profesor();
		try{
			
			int codigo =Integer.parseInt( request.getParameter(Constantes.PAR_CODIGO));
			profesor.setCodigo(codigo);
			profesor.setNombre( request.getParameter(Constantes.PAR_NOMBRE));
			profesor.setApellidos( request.getParameter(Constantes.PAR_APELLIDO));
			profesor.setDni( request.getParameter(Constantes.PAR_DNI));
			
			String date= request.getParameter(Constantes.PAR_FNACIMIENTO);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			profesor.setfNacimiento( dateFormat.parse(date));
			
			profesor.setDireccion( request.getParameter(Constantes.PAR_DIRECCION));
			profesor.setEmail( request.getParameter(Constantes.PAR_EMAIL));
			long nSS=Long.parseLong(request.getParameter(Constantes.PAR_nSS));
			profesor.setnSS(nSS);
		}catch(Exception e){
			
			//throw new Exception(e.getMessage());
			throw new Exception("Los datos del profesor no son validos"+e.getMessage());
		}
		
		
		//String nombre = request.getParameter("<%Constantes.PAR_NOMBRE%>");
		
		return profesor;
	}
	

}
