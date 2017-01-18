package com.ipartek.formacion.controler;
/**
 * Cuando es un metodo statico el constructor es private,
 * nadie me puede instanciar un objeto de tipo constantes
 * @author Mai
 *
 */
public class Constantes {

	private Constantes() {
		
	}
	public static final String ATT_LISTADO_PROFESORES = "listado-profesores";
	public static final String ATT_LISTADO_ALUMNOS = "listado-alunmos";
	
	public static final String SERVLET_ALUMNO ="Alumno.do";
	public static final String SERVLET_PROFESOR ="profesor.do";
	
	public static final String JSP_LISTADO_ALUMNOS="alumnos/listado.jsp";
	public static final String JSP_LISTADO_PROFESORES="profesores/listado.jsp";
	public static final String JSP_HOME = "index.jsp";
	
}
