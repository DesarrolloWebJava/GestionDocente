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
  
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		pS=new ProfesorServiceImp();
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse resp)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//recogemos el indicativo de la operacion y lo tratamos en el switch con los case
	//Crear, leer y por defecto
	String operacion=req.getParameter(Constantes.PAR_OPERACION);
	int op=-1;
	try{
		op= Integer.parseInt(operacion);
		
		switch (op){
		case Constantes.OP_CREATE:
		//se manda al formulario para introducir un nuevo profesor
			rd=req.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
			break;
		case Constantes.OP_READ:
		//se manda al metodo de listar profesores
			cargarProfesores(req);
			break;
		case Constantes.OP_UPDATE:
		{
			int codigo=-1;
			codigo=Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
			Profesor profesor=pS.getById(codigo);
			rd=req.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
			req.setAttribute(Constantes.ATT_PROFESOR, profesor);
		}
			break;
		case Constantes.OP_DELETE:
		{
			int codigo=-1;
			codigo=Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
			pS.delete(codigo);
			req.setAttribute(Constantes.ATT_MENSAJE, "El profesor ha sido borrado correctamente");
			cargarProfesores(req);
		}
			break;
		default:
		//mandamos por defecto que muestre la lista de profesores
			cargarProfesores(req);
			break;
		}
	}catch(Exception e){
	//si salta la excepcion lo redireccionamos a la pagina inicial
		resp.sendRedirect(Constantes.JSP_HOME);
		return;
	}
	
	// hace la redirección
	rd.forward(req, resp);
	}

	private void cargarProfesores(HttpServletRequest req) {
		Map<Integer,Profesor> profesores= pS.getAll();
		// fijamos la página de destino
		rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_PROFESORES);
		// añadimos el atributo a la request
		req.setAttribute(Constantes.ATT_LISTADO_PROFESORES, profesores);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Profesor profesor=null;
		String mensaje="";
		try{
			profesor=recogerParametrosProfesor(request);
			if(profesor.getCodigo()>profesor.CODIGO_NULO){
				//realizamos un update
				pS.update(profesor);
				mensaje="El profesor ha sido actualizado correctamente";
			}else{
				//introducimos uno nuevo
				pS.create(profesor);
				mensaje="El profesor ha sido introducido con exito";	
			}
			//muestra el listado de profesores
			cargarProfesores(request);
		}catch(NumberFormatException e){
			response.sendRedirect(Constantes.JSP_HOME);
			return;
		}catch(Exception e){
			rd=request.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
			mensaje=e.getMessage();
			//escribir el error en un fichero de trazas
			e.printStackTrace();
		}
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(request, response);
	}

	private Profesor recogerParametrosProfesor(HttpServletRequest request) throws Exception {
	Profesor profesor=new Profesor();
		try{
			//request.getParameter recoge la información introducida
			int codigo=Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
			profesor.setCodigo(codigo);
			//intoduzco una validacion en el campo nss ya que es int y 
			// si no se introduce nada en el campo el parseint falla
			String codNss=request.getParameter(Constantes.PAR_NSS);
			/*if("".equalsIgnoreCase(codNss)){
				codNss="11111111";
			}*/
			if (codNss!=null && !"".equals(codNss)){
				int codNss2=Integer.parseInt(codNss);
				profesor.setnSS(codNss2);
			}
				
		
			profesor.setNombre(request.getParameter(Constantes.PAR_NOMBRE));
			profesor.setApellidos(request.getParameter(Constantes.PAR_APELLIDOS));
			profesor.setDireccion(request.getParameter(Constantes.PAR_DIRECCION));
			profesor.setEmail(request.getParameter(Constantes.PAR_EMAIL));
			profesor.setDni(request.getParameter(Constantes.PAR_DNI));
		
			String date =request.getParameter(Constantes.PAR_FNACIMIENTO);
			if(date !=null && !"".equals(date)){
				String pattern = "dd/MM/yyyy";
				SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
				profesor.setfNacimiento(dateFormat.parse(date));
			}
		}catch(NumberFormatException e){
			throw new Exception("Alguien esta tocando el codigo");
		}catch(Exception e){
			throw new Exception("Los datos introducidos no son validos"+e.getMessage());
		}
		return profesor;
	}
}
