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
	public static final String JSP_LISTADO_ALUMNOS = "Alumnos/listado.jsp";
	public static final String JSP_LISTADO_PROFESORES = "profesores/listado.jsp";
	public static final String JSP_HOME = "index.jsp";
}
