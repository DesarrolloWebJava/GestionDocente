package com.ipartek.formacion.controller;

public class Constantes {
	private Constantes(){
	}
	
	// Nombres de atributos
	public static final String ATT_LISTADO_PROFESORES = "listadoProfesores";
	public static final String ATT_LISTADO_ALUMNOS = "listadoAlumnos";
	public static final String ATT_LISTADO_CURSOS = "listadoCursos";
	public static final String ATT_MENSAJE = "mensaje";
	public static final String ATT_ALUMNO = "alumno";
	public static final String ATT_PROFESOR = "profesor";
	public static final String ATT_CURSO = "curso";
	public static final String ATT_LISTADO_USUARIOS = "listadoUsuarios";
	
	// Variable de sesion 
	public static final String SESSION_IDIOMA = "language";
	public static final String SESSION_PERSONA = "persona";
	
	//Variable de contexto
	public static final String CTX_LISTADO_USUARIOS = "listadoUsuarios";
	
	// Nombres de los servlets
	public static final String SERVLET_ALUMNO = "alumno.do";
	public static final String SERVLET_PROFESOR = "profesor.do";
	public static final String SERVLET_CURSO = "curso.do";
	public static final String SERVLET_IDIOMA = "idioma.do";
	public static final String SERVLET_LOGIN = "login.do";
	public static final String SERVLET_ACTIVOS = "listaractivosservlet.do";
	
	// Para las cookies
	public static final String COOKIE_USERNAME = "username";
	public static final String COOKIE_PASSWORD = "password";
	public static final String COOKIE_IDIOMA = "idioma";
	
	// Rutas de las JSPs
	public static final String JSP_LISTADO_ALUMNOS = "alumnos/listado.jsp";
	public static final String JSP_LISTADO_PROFESORES = "profesores/listado.jsp";
	public static final String JSP_HOME = "index.jsp";
	public static final String JSP_FORMULARIO_ALUMNO = "alumnos/alumno.jsp";
	public static final String JSP_FORMULARIO_PROFESOR = "profesores/profesor.jsp";
	public static final String JSP_LISTADO_CURSOS = "cursos/listado.jsp";
	public static final String JSP_FORMULARIO_CURSO = "cursos/curso.jsp";
	public static final String JSP_LISTADO_ACTIVOS = "usuarios/listado.jsp"; 
	
	// Constantes para los parametros
	public static final String PAR_OPERACION  = "OP";
	public static final String PAR_CODIGO = "codigo";
	public static final String PAR_NOMBRE = "nombre";
	public static final String PAR_APELLIDOS = "apellidos";
	public static final String PAR_DNI = "dni";
	public static final String PAR_FNACIMIENTO = "fnacimiento";
	public static final String PAR_DIRECCION = "direccion";
	public static final String PAR_EMAIL = "email";
	public static final String PAR_NHERMANOS = "nHermanos";
	public static final String PAR_ACTIVO = "activo";
	public static final String PAR_NSS = "nss";
	public static final String PAR_DURACION = "duracion";
	public static final String PAR_FINICIO = "fInicio";
	public static final String PAR_FFIN = "fFinalizacion";
	public static final String PAR_LISTADO_ALUMNOS = "alumnos";
	public static final String PAR_PROFESOR = "alumnos";
	public static final String PAR_IDIOMA = "idioma";
	public static final String PAR_USUARIO = "usuario";
	public static final String PAR_PASSWORD = "password";
	public static final String PAR_LISTADO_USUARIOS = "listaUsuarios";
	public static final String PAR_SESSION_ID ="sessionID";
	public static final String PAR_RECUERDAME ="recuerdame";
	
	// Constantes para las operaciones
	public static final int OP_CREATE = 1;
	public static final int OP_READ = 2;
	public static final int OP_UPDATE = 3;
	public static final int OP_DELETE = 4;
	
	public static final int IDIOMA_CASTELLANO = 1;
	public static final int IDIOMA_EUSKERA = 2;
	public static final int IDIOMA_INGLES = 3;
}
