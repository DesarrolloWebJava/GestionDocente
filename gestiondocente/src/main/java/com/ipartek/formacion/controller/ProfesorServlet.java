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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recogemos el parametro de lo que queremos hacer: create, delete, update, ...
		String operacion = request.getParameter(Constantes.PAR_OPERACION);
		//Variable que controla si es correcto o no el parametro 
		int op = -1;
		try{
			//Cambiamos el valor del parametro de String a Integer
			op = Integer.parseInt(operacion);
			//Switch para gestionar a donde redirecciona en funcion del parametro
			switch(op){
				case Constantes.OP_CREATE:
					//Prepara la redireccion al formulario
					rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
				break;
				case Constantes.OP_READ:
					//Carga la lista de profesores
					cargarListaProfesores(request);
				break;
				case Constantes.OP_UPDATE:
					//Prepara la redireccion al formulario
					rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
				break;
				case Constantes.OP_DELETE:
					
				break;
				default:
					
				break;
			}
		}catch(Exception e){
			//En caso de excepcion prepara la redireccion a index.jsp
			response.sendRedirect(Constantes.JSP_HOME);
			return;
		}
		//Hace la redireccion
		rd.forward(request, response);
	}

	private void cargarListaProfesores(HttpServletRequest request) {
		//Obtenemos los datos de los profesores y los metemos en la variable profesores
		Map<Integer,Profesor> profesores = pS.getAll();
		//Prepara la redireccion al listado de profesores
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_PROFESORES);
		//Añadimos la lista y una key para identificarla al request
		request.setAttribute(Constantes.ATT_LISTADO_PROFESORES, profesores);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Creamos una variable profesor nula
		Profesor profesor = null;
		String mensaje = "";
		try{
			//Cargamos la variable con los parametros
			profesor = recogerParametros(request);
			//Si existe el codigo lo actualiza, sino lo crea
			if(profesor.getCodigo() > profesor.CODIGO_NULO){
				//actualiza pS con profesor
				pS.update(profesor);
				mensaje = "El profesor ha sido actualizado correctamente";
			}else{
				//Crea un nuevo pS profesor
				pS.create(profesor);
				mensaje = "El profesor ha sido creado correctamente";
			}
		}catch(NumberFormatException e){
			//En caso de excepcion prepara la redireccion a index.jsp
			response.sendRedirect(Constantes.JSP_HOME);
		}catch(Exception e){
			//Prepara la redireccion
			rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
			//Guarda la excepcion como mensaje
			mensaje = e.getMessage();
			//Genera un fichero de trazas para tener un log y debuggear
			e.printStackTrace();
		}
		//Prepara la redireccion. Envia el mensaje
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		//Realiza la redireccion
		rd.forward(request, response);
	}

	
	private Profesor recogerParametros(HttpServletRequest request) throws Exception {
		//Creamos una instancia de profesor
		Profesor profesor = new Profesor();
		try{
			profesor.setCodigo(Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO)));
			profesor.setNombre(request.getParameter(Constantes.PAR_NOMBRE));
			profesor.setApellidos(request.getParameter(Constantes.PAR_APELLIDOS));
			profesor.setDni(request.getParameter(Constantes.PAR_DNI));
			profesor.setEmail(request.getParameter(Constantes.PAR_EMAIL));
			profesor.setDireccion(request.getParameter(Constantes.PAR_DIRECCION));
			String date = request.getParameter(Constantes.PAR_FNACIMIENTO);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			profesor.setfNacimiento(dateFormat.parse(date));
			String nSS_s = request.getParameter(Constantes.PAR_NSS);
			if(nSS_s != null && !"".equals(nSS_s)){
				int nss = Integer.parseInt(nSS_s);
				profesor.setnSS(nss);
			}
		}catch(Exception e){
			//Lanzamos la excepcion
			throw new Exception("Los datos no son validos "+e.getMessage());
		}
		return profesor;
	}

	@Override
	public void destroy() {
		//Se carga la instancia
		pS = null;
		super.destroy();
	}

}
