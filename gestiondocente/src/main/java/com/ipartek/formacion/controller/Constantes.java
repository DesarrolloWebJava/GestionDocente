/**
 * 
 */
package com.ipartek.formacion.controller;

import com.ipartek.formacion.dbms.pojo.Alumno;

/**
 * @author Raúl de Roba 18/01/17
 *
 */
public class Constantes {
	/* Constantes de página inicial.*/
	public static final String JSP_HOME = "index.jsp";
	
	/* Constantes de atributos de Servlets.*/
	public static final String ATT_LISTADO_ALUMNOS = "listado-alumnos";
	public static final String ATT_LISTADO_PROFESORES = "listado-profesores";
	public static final String ATT_MENSAJES = "mensajes";
	public static final String ATT_ALUMNOS = "alumno";
	
	/* Constantes de redireccionamiento de Servlets.*/
	public static final String SERVLET_ALUMNO = "alumno.do";
	public static final String SERVLET_PROFESOR = "profesor.do";
	
	/* Constantes de listados de Servlets.*/
	public static final String JSP_LISTADO_ALUMNO = "alumnos/listado.jsp";
	public static final String JSP_LISTADO_PROFESOR = "profesores/listado.jsp";	
	
	/* Constantes de formularios de Servlets.*/
	public static final String JSP_FORMULARIO_ALUMNO = "alumnos/alumno.jsp";
	public static final String JSP_FORMULARIO_PROFESOR = "profesores/profesor.jsp";
	
	
	
	/* Constantes para identificar los parametros de operaciones de Servlets.*/
	public static final String PAR_OPERACION = "op";
	
	/* Constantes para identificar el valor de los parametros de
	 *  operaciones de Servlets.*/
	public static final int OP_CREATE = 1;	
	public static final int OP_READ = 2;	
	public static final int OP_UPDATE = 3;
	public static final int OP_DELETE = 4;		
	
	/* Constantes para identificar los parametros de los atributos de la clase Persona.*/
	public static final String PAR_CODIGO = "codigo";
	public static final String PAR_NOMBRE = "nombre";
	public static final String PAR_APELLIDOS = "apellidos";
	public static final String PAR_DNI = "dni";
	public static final String PAR_FNACIMIENTO = "fnacimiento";
	public static final String PAR_DIRECCION = "direccion";
	public static final String PAR_EMAIL = "email";
	/* Constantes para identificar los parametros de los atributos de la clase alumno.*/
	public static final String PAR_NHERMANOS = "hermanos";
	public static final String PAR_ACTIVO = "activo";
	/* Constantes para identificar los parametros de los atributos de la clase profesor.*/
	public static final String PAR_NSS = "N.S.S.";
	
	private Constantes(){
		
	}

}
