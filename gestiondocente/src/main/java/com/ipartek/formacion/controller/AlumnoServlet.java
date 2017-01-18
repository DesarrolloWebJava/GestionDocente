package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.service.AlumnoService;
import com.ipartek.formacion.service.AlumnoServiceImp;

/**
 * Servlet implementation class AlumnoServlet
 */
public class AlumnoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AlumnoService aS;
	
	//En un Servlet los métodos init y destroy SÓLO se ejecutan UNA VEZ.
	//Cuando se llama por primera vez, cuando se sale?
	//Por eso cuando se llama una app por primera vez tarda un poco
	//porque se están genrando los servlets.
	//técnica autorobots para acelerar...
       
    @Override
	public void init() throws ServletException {
		aS = new AlumnoServiceImp(); //esto es para que no
		//se creen nuevas instancias del servlet en cada
		//doGet o doPost.
    	super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// * PUNTOS: 
		//1* Obtenemos la lista de datos.
		List<Alumno> alumnos = aS.getAll();
		//Esto no va a mostrar nada. Se pierde porque no está 
		//dentro de una variable. Pero eso lo hacemos en
		//la view.
		
			//vamos a hacer una red de dirección
			//Ojo el flujo de la navegación.
			//REDIRECCION LIMPIA (que no engancha datos):
			//resp.sendRedirect("alumnos/listadoAl.jsp");
			//MÉTODO DE REDIRECCIÓN NO LIMPIA:
			//La dif entre los dos: redirección LIMPIA (sin enganchar datos de la petición anterior) O NO.
			//con RequestDispatcher cambia la URL mostrada en el navegador: aparece el .do, que es la información que está en
			//la request: la response mantiene esta info porque no es limpia.
		
		//2* Fijamos la página de destino
		RequestDispatcher rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
		//el setAttr por debajo es un mapa, con k y v.
		
		//3* Añadimos el atribuo a la request.
		req.setAttribute("listado-alumnos", alumnos);
		
		//4* Hace la redirección
		rd.forward(req, resp);
		//Nosotros vamos a usar el RequestDispatcher porque nos permite añadirle aributos.
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}