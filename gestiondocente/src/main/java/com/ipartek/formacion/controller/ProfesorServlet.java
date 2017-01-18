package com.ipartek.formacion.controller;

import java.io.IOException;
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

		//4 Pasos: 
		//1. Obtener lista de datos:
		Map<Integer, Profesor> profesores = pS.getAll();
		//2. Fijamos la página de destino:
		rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_PROFESORES);
		//3. Añadimos el atributo a request.
		req.setAttribute("listado-profesores", profesores);
		//4. Hace la redirección:
		rd.forward(req, resp);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
