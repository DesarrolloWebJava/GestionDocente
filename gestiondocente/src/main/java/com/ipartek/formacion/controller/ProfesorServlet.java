/* Paquete donde se guardan los servlets que gestionan el trafico de la web.
 * (Get y Post) */
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
 * @author Raúl de Roba 18/01/17 
 * 
 * <p>Clase Servlet profesorServet para la gestión de profesores.</p>
 * 
 */
public class ProfesorServlet extends HttpServlet {
	/* Constante de serialición. */
	private static final long serialVersionUID = 1L;
	/* Se declara el servicio que gestiona Profesores.*/
	private ProfesorService aP;
       
	@Override
    /* Metodo que que se ejecuta crear la pagina.
     * Solo se ejecuta 1 vez,al acceder más veces no se crea.*/
	public void init() throws ServletException {
    	/* Se instancia la clase que gestiona los Profesores. */
    	aP = new ProfesorServiceImp();
		/* Se llama al init del padre.En los servlets tiene que ser la última línea,
		 * porque al llamar al padre se sale del metodo.*/
		super.init();
	}
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ProfesorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /* Metodo a ejecutar al recibir una petición Get. */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			                                         throws ServletException, IOException {
		/* Sde declara la lista de alumnos 
		 * donde se recorre la lista de los alumnos.*/
    	Map<Integer,Profesor> profesores = aP.getAll();
    	/* Se declara una RequestDispatcher para redireccionar a la url indicada.
		 * No es una redireccion limpia,con lo que envia parametros.
		 * En este caso request y response.*/
		RequestDispatcher rd = request.getRequestDispatcher("profesores/listado.jsp");
		/* Se crea un atributo en la request y se le asigna la lista de profesores.*/
		request.setAttribute("listado-profesores", profesores);
		/* Se redirecciona enviando por parametro los request y response 
		 * recibidos por parametro.*/
		rd.forward(request, response);
	}

	/* Metodo a ejecutar al recibir una petición Post. */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			                                        throws ServletException, IOException {
		
	}

}
