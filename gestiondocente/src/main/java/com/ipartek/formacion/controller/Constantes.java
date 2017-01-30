/**
 * 
 */
package com.ipartek.formacion.controller;

import java.util.Date;
import java.util.List;

import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.dbms.pojo.Profesor;

/**
 * @author Raúl de Roba 18/01/17
 * 
 * Constantes para gestionar en los JSP.
 * 
 */
/* Si una clase tiene el constructor privado la clase será final.*/
public final class  Constantes {
	/* Constantes de página inicial.*/
	public static final String JSP_HOME = "index.jsp";
	
	/* Constantes de variables de sesión.*/
	public static final String SESION_IDIOMA = "language";
	public static final String SESION_PERSONA= "persona";
	
	/* Constantes de atributos de Servlets.*/
	public static final String ATT_LISTADO_ALUMNOS = "listadoAlumnos";
	public static final String ATT_LISTADO_PROFESORES = "listadoProfesores";
	public static final String ATT_LISTADO_CURSOS = "listadoCursos";
	public static final String ATT_MENSAJES = "mensajes";
	public static final String ATT_ALUMNOS = "alumno";
	public static final String ATT_CURSOS = "curso";	
	public static final String ATT_IDIOMA = "idioma";
	public static final String ATT_USUARIO = "usuario";
	public static final String ATT_PASSWORD = "password";
	
	/* Constantes de redireccionamiento de Servlets.*/
	public static final String SERVLET_ALUMNO = "alumno.do";
	public static final String SERVLET_PROFESOR = "profesor.do";
	public static final String SERVLET_CURSO = "curso.do";
	public static final String SERVLET_IDIOMA = "idioma.do";
	public static final String SERVLET_LOGIN = "login.do";	
	
	/* Constantes de listados de Servlets.*/
	public static final String JSP_LISTADO_ALUMNO = "alumnos/listado.jsp";
	public static final String JSP_LISTADO_PROFESOR = "profesores/listado.jsp";	
	public static final String JSP_LISTADO_CURSO = "cursos/listado.jsp";	
	
	/* Constantes de formularios de Servlets.*/
	public static final String JSP_FORMULARIO_ALUMNO = "alumnos/alumno.jsp";
	public static final String JSP_FORMULARIO_PROFESOR = "profesores/profesor.jsp";
	public static final String JSP_FORMULARIO_CURSO = "cursos/curso.jsp";	
	
	
	/* Constantes para identificar los parametros de operaciones de Servlets.*/
	public static final String PAR_OPERACION = "op";
	public static final String PAR_IDIOMA = "idioma";
	/* Constantes para identificar los parametros de ogins.*/
	public static final String PAR_USUARIO = "usuario";
	public static final String PAR_PASSWORD = "password";
	
	/* Constantes para identificar el valor de los parametros de
	 *  operaciones de Servlets.*/
	public static final int OP_CREATE = 1;	
	public static final int OP_READ = 2;	
	public static final int OP_UPDATE = 3;
	public static final int OP_DELETE = 4;		
	
	/* Constantes para identificar los parametros de los atributos de los idiomas.*/
	public static final int IDIOMA_EUSKERA = 1;
	public static final int IDIOMA_CASTELLANO = 2;
	public static final int IDIOMA_INGLES = 3;	
	
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
	/* Constantes para identificar los parametros de los atributos de la clase curso.*/
	public static final String PAR_DURACION = "duracion";	
	public static final String PAR_FINICIO = "finicio";
	public static final String PAR_FFIN = "ffin";
	
	/* */
	
	private Constantes(){
		
	}

}
