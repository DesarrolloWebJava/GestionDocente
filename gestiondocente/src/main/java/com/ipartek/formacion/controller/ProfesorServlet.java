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

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String operacion = req.getParameter(Constantes.PAR_OPERACION);
		int op = -1;
		try{
			op = Integer.parseInt(operacion);
			switch (op){
				case Constantes.OP_CREATE:
					rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESORES);
					break;
				case Constantes.OP_READ:
					cargarListaProfesores(req);
					break;
				case Constantes.OP_UPDATE:
					//aS.getById(codigo);
					rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESORES);
					//request.setAttribute(arg0, arg1);
					break;
				case Constantes.OP_DELETE:
					break;
				default:
					cargarListaProfesores(req);
					break;
			}
		} catch(Exception e){
			resp.sendRedirect(Constantes.JSP_HOME);
			return;
		}
		
		rd.forward(req, resp);
	}
	
	
	private void cargarListaProfesores(HttpServletRequest req) {
		Map<Integer, Profesor> profesores = pS.getAll();
		rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_PROFESORES);
		req.setAttribute(Constantes.ATT_LISTADO_PROFESORES, profesores);
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Profesor profesor = null;
		String mensaje="";
		try{
			profesor = recogerParametros(req);
			
			if(profesor.getCodigo() > Profesor.CODIGO_NULO){
				pS.update(profesor);
				mensaje = "El profesor a sido actualizado correctamente";
			}else{
				pS.create(profesor);
				mensaje = "El profesort a sido creado correctamente";
			}
			cargarListaProfesores(req);
		}catch(NumberFormatException e) {
			resp.sendRedirect(Constantes.JSP_HOME);
		}catch(Exception e){
			rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESORES);
			mensaje= e.getMessage();
			e.printStackTrace();
		}
		req.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(req, resp);	
	}


	private Profesor recogerParametros(HttpServletRequest req) throws Exception {
		Profesor profesor = new Profesor();
		try{
			profesor.setCodigo(Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO)));
			profesor.setNombre(req.getParameter(Constantes.PAR_NOMBRE));
			profesor.setApellidos(req.getParameter(Constantes.PAR_APELLIDOS));
			profesor.setDni(req.getParameter(Constantes.PAR_DNI));
			profesor.setEmail(req.getParameter(Constantes.PAR_EMAIL));
			profesor.setDireccion(req.getParameter(Constantes.PAR_DIRECCION));
			
			
			String nSS = req.getParameter(Constantes.PAR_NSS);
 			if (!"".equalsIgnoreCase(nSS)) {
 				profesor.setnSS(Integer.parseInt(nSS));
 			}
 		
			
			String date = req.getParameter(Constantes.PAR_FNACIMIENTO);
			if (date != null && !"".equals(date)){
				String pattern = "dd/MM/yyyy";
				SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
				profesor.setfNacimiento(dateFormat.parse(date));
			}
			
		} catch(Exception e){
			throw new Exception("Los datos son incorrectos: " + e.getMessage());
			
		}
		
		return profesor;
	}
	
	@Override
	public void destroy() {
		pS = null;
		super.destroy();
	}

}
