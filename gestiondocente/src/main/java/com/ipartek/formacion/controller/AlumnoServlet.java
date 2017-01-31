package com.ipartek.formacion.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.service.AlumnoService;
import com.ipartek.formacion.service.AlumnoServiceImp;

/**
 * Servlet implementation class AlumnoServlet
 */
public class AlumnoServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(AlumnoServlet.class);
	private static final long serialVersionUID = 1L;
	private AlumnoService aS;
	private RequestDispatcher rd;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		aS = new AlumnoServiceImp();
		super.init();
		// aS = new AlumnoServiceImp(); --> esto aqui no se ejecuta
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String operacion = req.getParameter(Constantes.PAR_OPERACION);
		int op = -1;
		try {
			op = Integer.parseInt(operacion);
			switch (op) {
				case Constantes.OP_CREATE:
					// se va redirigir a la pagina alumnos/alumno.jsp
					rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
					break;
				case Constantes.OP_READ:
					cargarListaAlumnos(req);
					break;
				case Constantes.OP_UPDATE:{
					int codigo = -1;
					codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
					Alumno alumno = aS.getById(codigo);
					rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
					req.setAttribute(Constantes.ATT_ALUMNO, alumno);
				}
					break;
				case Constantes.OP_DELETE:{
					int codigo = -1;
					codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
					aS.delete(codigo);
					req.setAttribute(Constantes.ATT_MENSAJE, "El alumno ha sido borrado correctamente");
					cargarListaAlumnos(req);
				}
				default:
					
					break;
			}

		} catch (Exception e) {
			// cargarListaAlumnos(req);
			/*System.out.println(e.getMessage());*/
			LOG.error(e.getMessage()+" Valor del codigo del alumno: "+req.getParameter(Constantes.PAR_CODIGO));
			resp.sendRedirect(Constantes.JSP_HOME);			
			return;
		}

		// hace la redirección
		rd.forward(req, resp);

	}

	/**
	 * @param req
	 */
	private void cargarListaAlumnos(HttpServletRequest req) {
		// resp.sendRedirect("alumnos/listado.jsp"); --> hace una redireccion
		// limpia.
		// obtenemos la lista de datos.
		List<Alumno> alumnos = aS.getAll();
		// fijamos la página de destino
		rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
		// añadimos el atributo a la request
		req.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, alumnos);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Alumno alumno = null;
		String mensaje = "";
		int codigo = -1;
		LOG.trace("");
		try {
			codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
			
			alumno = recogerParametros(req);
			alumno.setCodigo(codigo);
			// procesaremos UPDATE or INSERT
			if (alumno.getCodigo() > Alumno.CODIGO_NULO) {// update
				aS.update(alumno);
				mensaje = "El alumno ha sido actualizado correctamente";
			} else {// create
				aS.create(alumno);
				mensaje = "El alumno ha sido creado correctamente";
			}
			cargarListaAlumnos(req);
		} catch (Exception e) {
			// redirigir al formulario
			
			if(codigo == -1){
				rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
				req.setAttribute(Constantes.ATT_ALUMNO, alumno);
				mensaje = "Se ha producido una operacion inesperada contacte con el adminstrador";
			}else{
				alumno = aS.getById(codigo);
				req.setAttribute(Constantes.ATT_ALUMNO, alumno);
				rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
				mensaje = e.getMessage();
			}
			System.out.println(mensaje);
		}
		req.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(req, resp);
	}

	private Alumno recogerParametros(HttpServletRequest req) throws Exception {
		Alumno alumno = new Alumno();
		try {
			alumno.setNombre(req.getParameter(Constantes.PAR_NOMBRE));
			alumno.setApellidos(req.getParameter(Constantes.PAR_APELLIDOS));
			alumno.setDireccion(req.getParameter(Constantes.PAR_DIRECCION));
			alumno.setDni(req.getParameter(Constantes.PAR_DNI));
			alumno.setEmail(req.getParameter(Constantes.PAR_EMAIL));

			String nHermanos = req.getParameter(Constantes.PAR_NHERMANOS);
			if (!"".equalsIgnoreCase(nHermanos)) {
				alumno.setnHermanos(Integer.parseInt(nHermanos));
			}

			alumno.setActivo(Boolean.parseBoolean(req.getParameter(Constantes.PAR_ACTIVO)));

			String date = req.getParameter(Constantes.PAR_FNACIMIENTO);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			alumno.setfNacimiento(dateFormat.parse(date));
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new Exception("Los datos no son validos: " + e.getMessage());
		}
		return alumno;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	@Override
	public void destroy() {
		aS = null;
		super.destroy();
	}

}
