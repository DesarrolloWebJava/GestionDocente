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

	@Override
	public void init() throws ServletException {
		pS = new ProfesorServiceImp();
		super.init();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operacion = request.getParameter(Constantes.PAR_OPERACION);
		int op= -1;
		try{
			op= Integer.parseInt(operacion);
			switch (op){
				case Constantes.OP_CREATE:
					//se va a redirigir a la pagina alumnos/alumno.jsp
					rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
					break;
				case Constantes.OP_READ:
					cargarListaProferos(request);
					break;
				case Constantes.OP_UPDATE:
					// falta de hacer TODO aS.getById(codigo)
					//se va a redirigir a la pagina alumnos/alumno.jsp
					rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR); //programar el formulario
					// falta de hacer TODO request.setAttribute(arg0, arg1);
					
					break;
				case Constantes.OP_DELATE:
					int codigo = -1;
					codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
					pS.delate(codigo);
					request.setAttribute(Constantes.ATT_MENSAJE, "El profesor a sido...");
					cargarListaProferos(request);
					break;
				
				default:
					cargarListaProferos(request);
					break;
			}
			
		} catch (Exception e){
			response.sendRedirect(Constantes.JSP_LISTADO_PROFESORES);
			return;
			//cargarListaAlumnos(request);

		}
		// hace la redireccion.
		rd.forward(request, response);	
	
		
		
		/*// obtenemos la lista de datos.		
		Map<Integer, Profesor> profesores = pS.getAll(); 
		// de la clase constantes del controller
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_PROFESORES); 
		request.setAttribute(Constantes.ATT_LISTADO_PROFESORES, profesores);
		rd.forward(request, response);*/
				
		// fijamos la página profesoresdestino.
		//RequestDispatcher rd = request.getRequestDispatcher("profesores/listado.jsp");
		// añadimos el atributo a la request.
		//request.setAttribute("listado-profesores", profesores);
		// hace la redirección.
		//rd.forward(request, resp);
	}

	private void cargarListaProferos(HttpServletRequest req) {
		Map<Integer, Profesor> profesores =  pS.getAll(); //cast de pS
		rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_PROFESORES);
		req.setAttribute(Constantes.ATT_LISTADO_PROFESORES, profesores);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Profesor profesor =null;
		String mensaje = "";
		try {
			profesor = recogerParametros(request);
			//String mensaje = "";
			// procesamos UPDATE or INTERT
			if(profesor.getCodigo()> Profesor.CODIGO_NULO){//UPDATE
			pS.update(profesor);
			mensaje = "El profesor ha sido actualizado correctamente";
			}else { //CREATE
			pS.create(profesor);
			mensaje = "El profesor ha sido creado correctamente";
			//mensaje = e.getMessage();
			}
			cargarListaProferos(request);
		} catch (NumberFormatException e){
			response.sendRedirect(Constantes.JSP_HOME);
			return;
		} catch (Exception e){
			rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
			//request.setAttribute("mensaje", e.getMessage());
			mensaje = e.getMessage();
			System.out.println(mensaje);
			//req.setAttribute("mensaje", mensaje);
		}
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(request, response);
	}
		
	
	private Profesor recogerParametros(HttpServletRequest req)throws Exception{
		Profesor profesor = new Profesor();
		try {
			int codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
			profesor.setCodigo(codigo);
			
			//int nSS = Integer.parseInt(req.getParameter(Constantes.PAR_NSS));
			//profesor.setnSS(nSS);
			String nSS_s = req.getParameter(Constantes.PAR_NSS);
			if(nSS_s != null && !"".equals(nSS_s)){
				int nss = Integer.parseInt(nSS_s);
				profesor.setnSS(nss);
			}
			
			String date = req.getParameter(Constantes.PAR_FNACIMIENTO);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			profesor.setfNacimiento(dateFormat.parse(date));
			
			String nHermanos = req.getParameter(Constantes.PAR_NHERMANOS);
			if("".equalsIgnoreCase(nHermanos)){
				//nHermanos = "0"; //es lo mismo la linea de abajo con las dos
				profesor.setnHermanos(Integer.parseInt(nHermanos));
			}
			
			profesor.setNombre(req.getParameter(Constantes.PAR_NOMBRE));
			profesor.setApellidos(req.getParameter(Constantes.PAR_APELLIDOS));
			profesor.setDni(req.getParameter(Constantes.PAR_DNI));
			profesor.setCodigo(Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO)));
			
		}catch (Exception e){
			throw new Exception("Los datos no son validos." +e.getMessage());
		}
		return profesor;
	}
}
