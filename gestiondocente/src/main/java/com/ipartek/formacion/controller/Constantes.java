package com.ipartek.formacion.controller;
/**
 * Esta clase va a contener los constantes
 * @author Mikel Bruce
 *
 */
public class Constantes {
	
	private Constantes(){
		
	}
	
	public static final String ATT_LISTADO_PROFESORES = "listado-profesores";
	public static final String ATT_LISTADO_ALUMNOS = "listado-alumnos";

	public static final String SERVLET_ALUMNO = "alumno.do";
	public static final String SERVLET_PROFESOR = "profesor.do";

	public static final String JSP_LISTADO_ALUMNOS = "alumnos/listado.jsp";
	public static final String JSP_LISTADO_PROFESORES = "profesores/listado.jsp";
	public static final String JSP_HOME = "index.jsp";
	
	public static final String PAR_OPERACION = "op";
	public static final String PAR_CODIGO = "codigo";
	public static final String PAR_NOMBRE = "nombre";
	public static final String PAR_APELLIDOS = "apellidos";
	public static final String PAR_DNI = "dni";
	public static final String PAR_FNACIMIENTO = "fNacimiento";
	public static final String PAR_EMAIL = "email";
	public static final String PAR_DIRECCION = "direccion";
	public static final String PAR_NHERMANOS = "nHermanos";
	public static final String PAR_ACTIVO = "activo";
	
	public static final int OP_CREATE =1;
	public static final int OP_READ=2;
	public static final int OP_UPDATE=3;
	public static final int OP_DELETE=4;
}
