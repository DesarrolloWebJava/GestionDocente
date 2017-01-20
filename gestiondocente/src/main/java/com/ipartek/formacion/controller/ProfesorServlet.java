package com.ipartek.formacion.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
			pS = new ProfesorServiceImp();
			super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
		//ESTE ES EL MÉTODO QUE SE ACTIVA CUANDO ACCEDEMOS A UNA URL 
		//VINCULADA A UN SERVLET
		
		//1º Recogemos la acción del servlet como parámetro
		String operacion = req.getParameter(Constantes.PAR_OPERACION);
		int op = -1;
		try {
			op = Integer.parseInt(operacion);
			switch (op) {
			case Constantes.OP_CREATE:
				rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
				break;
			case Constantes.OP_READ:
				//TODO: cargarListaProfesores(req);
				cargarListaProfesores(req);
				break;
			case Constantes.OP_UPDATE:
				//TODO: updateListaProfesores(req);
				//de momento redirect...
				rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
				break;
				default:
					//TODO: cargarListaProfesores(req);
					break;
			}
		} catch (Exception e) {
			resp.sendRedirect(Constantes.JSP_HOME);
			return;
		}
		
		//SIEMPRE FINISH DOGET CON FORWARD
		rd.forward(req, resp);
	}
		
	//DOING-> TODO: OP_READ
	private void cargarListaProfesores(HttpServletRequest req) {
		//1. Obtener lista de datos:
		Map<Integer, Profesor> profesores = pS.getAll();
		//2. Fijamos la página de destino:
		rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_PROFESORES);
		//3. Añadimos el atributo a request.
		req.setAttribute(Constantes.ATT_LISTADO_PROFESORES, profesores);
		//Los ATTS cq OBJ que se pasa del CONTR a la VTA
		//en realidad, los objs se pasan de la capa 
		//modelo: service+dbms, a/t de la capa controlador
		//aquí, profesores es un OJ de tipo ProfesorService que está en Service
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Profesor profesor = null;
		String mensaje = "";
		try {
			profesor = recogerParametros(req);
			//procesamos update o insert
			
			if(profesor.getCodigo() > profesor.CODIGO_NULO) {//update
				pS.update(profesor);
				mensaje = "El profesor ha sido actualizado correctamente.";
			} else {//create
				pS.create(profesor);
				mensaje = "El profesor ha sido creado correctamente.";
			}
			cargarListaProfesores(req);
		} catch (Exception e) {
			// redirigir al formulario
			rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR); //profesor.jsp
			mensaje =e.getMessage();
		}
		req.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(req,  resp);
	}		
		
	
	private Profesor recogerParametros(HttpServletRequest req) throws Exception {
		Profesor profesor = new Profesor();
		try {
			profesor.setCodigo(Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO)));
			profesor.setNombre(req.getParameter(Constantes.PAR_NOMBRE));
			profesor.setApellidos(req.getParameter(Constantes.PAR_APELLIDOS));
			profesor.setDireccion(req.getParameter(Constantes.PAR_DIRECCION));
			profesor.setDni(req.getParameter(Constantes.PAR_DNI));
			
			//¡MUY IMP!
			//Controlamos posibles fallos de input para que no casque el programa.			
			String nSS_s = req.getParameter(Constantes.PAR_nSS);
			if (nSS_s != null && !"".equals(nSS_s)) {
				int nSS = Integer.parseInt(nSS_s);
				profesor.setnSS(nSS);
			}
						
			profesor.setEmail(req.getParameter(Constantes.PAR_EMAIL));			
			
			String date = req.getParameter(Constantes.PAR_FNACIMIENTO);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			profesor.setfNacimiento(dateFormat.parse(date));
	
			//	} catch (NumberFormatException e) {
			//	throw new NumberFormatException("Alguien manipula el código: " + e.getMessage());
			//	response.sendRedirect(Constantes.JSP_HOME);
			//	return;
			
		} catch (Exception e) {
				throw new Exception("Los datos no son válidos: "+e.getMessage());
		}
		return profesor;
	}		
		
		
	@Override
	public void destroy() {
		pS = null; //Caso de 1º funcionalidad, después método del padre.
		super.destroy();
	}	
		


}
