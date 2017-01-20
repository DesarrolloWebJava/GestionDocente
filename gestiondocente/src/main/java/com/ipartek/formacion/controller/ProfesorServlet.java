package com.ipartek.formacion.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
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

       
 	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		Map<String,Profesor> profesores = new HashMap<String,Profesor>();
		pS = new ProfesorServiceImp();
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int op = -1;
		String operacion = req.getParameter(Constantes.PAR_OPERACION);
		try {
			op = Integer.parseInt(operacion);
		
			switch (op)
			{
			case Constantes.OP_CREATE:
				rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
				break;
		
			case Constantes.OP_READ:
				cargarListaProfesores(req);
				
			break;
		
			case Constantes.OP_UPDATE:
				rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
			break;
			case Constantes.OP_DELETE:
			default:
				cargarListaProfesores(req);
				
			break;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resp.sendRedirect(Constantes.JSP_HOME);
			return;
		}
		
		
		
		// Hace la redireccion
		rd.forward(req, resp);
	}

	private void cargarListaProfesores(HttpServletRequest req) {
		Map<Integer,Profesor> profesores = pS.getAll();
		
		// Añadimos el atributo a la request de la manera clave-valor
		req.setAttribute(Constantes.ATT_LISTADO_PROFESORES,profesores);
		rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_PROFESORES);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Profesor profesor = null;
		String mensaje="";
		try {
			profesor = recogerParametros(request);
			
			if (profesor.getCodigo()> Profesor.CODIGO_NULO)
			{
				pS.update(profesor);
				mensaje = "El profesor ha sido actualizado";
			}
			else
			{
				pS.create(profesor);
				mensaje = "El profesor ha sido creado correctamente";
				
			}
			
			
			cargarListaProfesores(request);
		} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			mensaje = e.getMessage();
			rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
			e.printStackTrace();
		}
		
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(request, response);
	}
 private Profesor recogerParametros(HttpServletRequest req) throws Exception
 {
	 Profesor profesor = new Profesor();
	 
	 try {
		profesor.setCodigo(Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO)));
		 profesor.setNombre(req.getParameter(Constantes.PAR_NOMBRE));
		 profesor.setApellidos(req.getParameter(Constantes.PAR_APELLIDOS));
		 profesor.setDni(req.getParameter(Constantes.PAR_DNI));
		 profesor.setEmail(req.getParameter(Constantes.PAR_EMAIL));
		 profesor.setEmail(req.getParameter(Constantes.PAR_EMAIL));
			
		 String date = req.getParameter(Constantes.PAR_FNACIMIENTO);
		 String pattern = "dd/MM/yyyy";
		 SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		 profesor.setfNacimiento(dateFormat.parse(date));
		 
		 profesor.setnSS(Integer.parseInt(req.getParameter(Constantes.PAR_NSS)));
		 
	} catch (Exception e) {
		
		e.printStackTrace();
		throw new Exception("Los datos no son validos " + e.getMessage());
	}
	 
	 
	 return profesor;
 }
}
