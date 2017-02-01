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
	public static final String ATT_LISTADO_PROFESORES = "listadoProfesores";
	public static final String ATT_LISTADO_ALUMNOS = "listadoAlumnos";
	public static final String ATT_LISTADO_CURSOS = "listadoCursos";
	public static final String ATT_LISTADO_USUARIOS = "listadoUsuarios";
	public static final String ATT_MENSAJE = "mensaje";
	public static final String ATT_ALUMNO = "alumno";
	public static final String ATT_PROFESOR = "profesor";
	public static final String ATT_CURSO = "curso";
	
	//Constante de contexto
	public static final String CTX_LISTADO_USUARIOS = "listadoUsuarios";
	
	//Variables de sesion
	public static final String SESSION_IDIOMA = "language";
	public static final String SESSION_PERSONA = "persona";
	
	//llamadas de los servlets
	public static final String SERVLET_ALUMNO = "alumno.do";
	public static final String SERVLET_PROFESOR = "profesor.do";
	public static final String SERVLET_CURSO = "curso.do";
	public static final String SERVLET_IDIOMA = "idioma.do";
	public static final String SERVLET_LOGIN = "login.do";
	public static final String SERVLET_USUARIO = "usuarios.do";
	
	//JSP
	public static final String JSP_LISTADO_ALUMNOS = "alumnos/listado.jsp";
	public static final String JSP_LISTADO_PROFESORES = "profesores/listado.jsp";
	public static final String JSP_LISTADO_CURSOS = "cursos/listado.jsp";
	public static final String JSP_LISTADO_USUARIOS = "usuarios/listado.jsp";
	public static final String JSP_HOME = "index.jsp";
	public static final String JSP_FORMULARIO_ALUMNO = "alumnos/alumno.jsp";
	public static final String JSP_FORMULARIO_PROFESOR = "profesores/profesor.jsp";
	public static final String JSP_FORMULARIO_CURSO ="cursos/curso.jsp";
	
	//Parametros (como se van a pasar los datos al JSP)
	public static final String PAR_OPERACION = "op";
	//Nombres de paramentros de la clase persona
	public static final String PAR_CODIGO = "codigo";
	public static final String PAR_NOMBRE = "nombre";
	public static final String PAR_APELLIDOS = "apellidos";
	public static final String PAR_DNI = "dni";
	public static final String PAR_EMAIL = "email";
	public static final String PAR_DIRECCION = "direccion";
	public static final String PAR_FNACIMIENTO = "fNacimiento";
	//Nombres de paramentros de la clase alumnos
	public static final String PAR_NHERMANOS = "nHermanos";
	public static final String PAR_ACTIVO = "activo";
	//Nombres de parametros de la clase profesor
	public static final String PAR_NSS = "nSS";
	//Nombre de los parametro de la clase curso
	public static final String PAR_DURACION = "duracion";
	public static final String PAR_FINICIO = "fInicio";
	public static final String PAR_FFIN = "fFin";
	//Parametros de idioma
	public static final String PAR_IDIOMA = "idioma";
	//Parametro de login
	public static final String PAR_USUARIO = "user";
	public static final String PAR_PASSWORD = "password";
	
	public static final String PAR_SESSION_ID = "sessionID";
	//Parametros de cookies
	public static final String PAR_RECUERDAME = "recuerdame";

	
	//Constantes de operaciones de CRUD
	public static final int OP_CREATE = 1;
	public static final int OP_READ = 2;
	public static final int OP_UPDATE = 3;
	public static final int OP_DELETE = 4;
	
	//Idiomas
	public static final int IDIOMA_EUSKERA = 1;
	public static final int IDIOMA_CASTELLANO = 2;
	public static final int IDIOMA_INGLES = 3;
}
