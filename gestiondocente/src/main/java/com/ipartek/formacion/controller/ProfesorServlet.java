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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<Integer,Profesor> profesores = pS.getAll();
		//Fijamos la pagina de destino
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_PROFESORES);
		//Añadimos la lista y una key para identificarla al request
		request.setAttribute(Constantes.ATT_LISTADO_PROFESORES, profesores);
		//Hace la redireccion
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
