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
				case Constantes.OP_UPDATE:{
					int codigo = -1;
					codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
					Profesor profesor =pS.getById(codigo);
					rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESORES);
					req.setAttribute(Constantes.ATT_PROFESOR, profesor);
				}
					break;
				case Constantes.OP_DELETE:{
					int codigo = -1;
					codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
					pS.delete(codigo);
					req.setAttribute(Constantes.ATT_MENSAJE, "el porfesor a sido borrado Correctamente");
					cargarListaProfesores(req);
				}
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
		int codigo = -1;
		try{
			profesor = recogerParametros(req);
			codigo= Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
			if(profesor.getCodigo() > Profesor.CODIGO_NULO){
				pS.update(profesor);
				mensaje = "El profesor a sido actualizado correctamente";
			}else{
				pS.create(profesor);
				mensaje = "El profesor a sido creado correctamente";
			}
			cargarListaProfesores(req);
		}catch(NumberFormatException e) {
			resp.sendRedirect(Constantes.JSP_HOME);
		}catch(Exception e){
			if (codigo == -1){
				cargarListaProfesores(req);
				mensaje="Se ha producido un error inesperado";
			}else{
				profesor = pS.getById(codigo);
				req.setAttribute(Constantes.ATT_PROFESOR, profesor);
				rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESORES);
				mensaje= e.getMessage();
			}
			System.out.println(mensaje);
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
