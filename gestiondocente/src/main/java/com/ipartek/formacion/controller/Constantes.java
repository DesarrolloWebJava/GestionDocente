package com.ipartek.formacion.controller;
/**
 * Como no queremos que nos inicien esta clase, ponemos private el contructor
 * @author Curso
 *
 */
public class Constantes {

	private Constantes() {
	}
	//Asi en lugar de poner las cadenas, que podemos equivocarnos, llamamos a la constante
	public static final String ATT_LISTADO_PROFESORES = "listado-profesores";
	public static final String ATT_LISTADO_ALUMNOS = "listado-alumnos";
	
	//llamadas de los servlets
	public static final String SERVLET_ALUMNO = "alumno.do";
	public static final String SERVLET_PROFESOR = "profesor.do";
	
	//JSP
	public static final String JSP_LISTADO_ALUMNOS = "alumnos/listado.jsp";
	public static final String JSP_LISTADO_PROFESORES = "profesores/listado.jsp";
	public static final String JSP_HOME = "index.jsp";
	
	
	//Parametros (como se van a pasar los datos al JSP)
	public static final String PAR_OPERACION = "op";
	//Nombres de paramentros de la clase alumnos
		public static final String PAR_CODIGO = "codigo";
		public static final String PAR_NOMBRE = "nombre";
		public static final String PAR_APELLIDOS = "apellidos";
		public static final String PAR_DNI = "dni";
		public static final String PAR_EMAIL = "email";
		public static final String PAR_DIRECCION = "direccion";
		public static final String PAR_FNACIMIENTO = "fNacimiento";
		public static final String PAR_NHERMANOS = "nHermanos";
		public static final String PAR_ACTIVO = "activo";
		
	//Constantes de operaciones de CRUD
	public static final int OP_CREATE = 1;
	public static final int OP_READ = 2;
	public static final int OP_UPDATE = 3;
	public static final int OP_DELETE = 4;
	
	
}
