package com.ipartek.formacion.controller;

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
	private ProfesorService pS;
	private RequestDispatcher rd;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Botones, hipervinculos... son get
		
				String operacion = req.getParameter(Constantes.PAR_OPERACION);
				int op = -1;
				
				try {
					op = Integer.parseInt(operacion);
					switch (op){
					case Constantes.OP_CREATE:
					//Se va redirigir a la pagina profesores/profesor.jsp
					rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
						break;
					case Constantes.OP_READ:
						cargarListaProfesores(req);
						break;
					case Constantes.OP_UPDATE:
						//aS.getById(codigo)
						//Se va redirigir a la pagina profesores/profesor.jsp
						rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
						//req.setAttribute(arg0, arg1);
						
					default:
						cargarListaProfesores(req);
						break;
					
					}
				} catch(Exception e){
					cargarListaProfesores(req);//Añadimos los atributos a la request
					//resp.sendRedirect(Constantes.JSP_HOME);
									
				}
								
				rd.forward(req, resp);//hace la redireccion
				//otra opcion
				//resp.sendRedirect("alumnos/listado.jsp");
				
	}
	
	private void cargarListaProfesores(HttpServletRequest req){
		Map<Integer,Profesor> profesores = pS.getAll();
		rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_PROFESORES);//fijamos la pagina de destino
		req.setAttribute(Constantes.ATT_LISTADO_PROFESORES, profesores);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Los formularios son post
				 Profesor profesor = null;
				String mensaje="";
				
				try{
					profesor = recogerParametros(request);
					
					//Procesaremos UPDATE o INSERT
					if (profesor.getCodigo()>Profesor.CODIGO_NULO){
					pS.update(profesor);
					mensaje="El profesor ha sido actualizado correctamente";
					} else {//create
					pS.create(profesor);
					mensaje="El profesor ha sido creado correctamente";
					}
					cargarListaProfesores(request);
				} catch (Exception e){
					
					// redirigir al formulario
					rd=request.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
					mensaje = e.getMessage();
				}
				request.setAttribute("mensaje", mensaje);
				rd.forward(request,response);
			}


private Profesor recogerParametros(HttpServletRequest request) throws Exception {
	Profesor profesor = new Profesor();
	try{
		
		profesor.setCodigo(Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO)));
		profesor.setNombre(request.getParameter(Constantes.PAR_NOMBRE));
		profesor.setApellidos(request.getParameter(Constantes.PAR_APELLIDOS));
		profesor.setDireccion(request.getParameter(Constantes.PAR_DIRECCION));
		profesor.setDni(request.getParameter(Constantes.PAR_DNI));
		profesor.setEmail(request.getParameter(Constantes.PAR_EMAIL));
		
		String nSS = request.getParameter(Constantes.PAR_NSS);
		if("".equalsIgnoreCase(nSS)){
			nSS="0";
		}
		
		String date = request.getParameter(Constantes.PAR_FNACIMIENTO);
		
		String pattern="dd/MM/yyyy";
		SimpleDateFormat dateformat = new SimpleDateFormat(pattern); 
		profesor.setfNacimiento(dateformat.parse(date));
	
	}catch(Exception e){
		throw new Exception("Los datos introducidos no son validos");
	}
	return profesor;
}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		pS = new ProfesorServiceImp();
		super.init();
	}

	public void destroy() {
		pS = null;
		super.destroy();
	}
	
	
}
