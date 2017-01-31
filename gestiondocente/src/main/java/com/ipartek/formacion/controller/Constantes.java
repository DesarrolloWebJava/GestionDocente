package com.ipartek.formacion.controller;

public class Constantes {
	private Constantes() {
	}

	//ATRIBUTOS POJOS
	public static final String ATT_PROFESOR = "profesor";
	public static final String ATT_ALUMNO = "alumno";
	public static final String ATT_CURSO  = "curso";
	
	//ATRIBUTOS LISTADOS
	public static final String ATT_LISTADO_PROFESORES = "listadoProfesores";
	public static final String ATT_LISTADO_ALUMNOS = "listadoAlumnos";
	public static final String ATT_LISTADO_CURSOS =  "listadoCursos";
	public static final String ATT_LISTADO_USUARIOS = "listadoUsuarios";
	
	public static final String CTX_LISTADO_USUARIOS = "listadoUsuarios";
	
	//OTROS ATTS
	public static final String ATT_IDIOMA =  "idioma";
	public static final String ATT_MENSAJE = "mensaje";
	
	//SERVLETS
	public static final String SERVLET_PROFESOR = "profesor.do";
	public static final String SERVLET_ALUMNO = "alumno.do";
	public static final String SERVLET_CURSO = "curso.do";
	public static final String SERVLET_IDIOMA = "idioma.do";
	public static final String SERVLET_LOGIN = "login.do";
	public static final String SERVLET_USUARIO = "admin.do";
	
	//SESIONES
	public static final String SESSION_IDIOMA = "language";
	public static final String SESSION_PERSONA = "persona";
	
	//JSP_LISTADOS	
	public static final String JSP_LISTADO_PROFESORES="profesores/listado.jsp";
	public static final String JSP_LISTADO_ALUMNOS = "alumnos/listado.jsp";
	public static final String JSP_LISTADO_CURSOS   = "cursos/listado.jsp";
	public static final String JSP_LISTADO_USUARIOS = "usuarios/listado.jsp";

	//JSP_FORMS
	public static final String JSP_FORMULARIO_PROFESOR = "profesores/profesor.jsp";
	public static final String JSP_FORMULARIO_ALUMNO = "alumnos/alumno.jsp";
	public static final String JSP_FORMULARIO_CURSO = "cursos/curso.jsp";	

	//JSP BÁSICOS
	public static final String JSP_HOME = "index.jsp";

	//OPERACIONES DE SERVLETS CON CRUD
	public static final String PAR_OPERACION = "op";
	
	public static final int OP_CREATE = 1;
	public static final int OP_READ = 2;
	public static final int OP_UPDATE = 3;
	public static final int OP_DELETE = 4;
	
	//DEL SERVLET IDIOMA
	public static final int IDIOMA_CASTELLANO = 1;
	public static final int IDIOMA_EUSKERA = 2;
	public static final int IDIOMA_INGLES = 3;
	
	//PARÁMETROS:
	
	//DE VARIAS CLASES:
	//profesor, alumno,curso:
	public static final String PAR_CODIGO = "codigo";
	public static final String PAR_NOMBRE = "nombre";
	
	//DE LA CLASE PROFESOR:
	public static final String PAR_nSS = "nSS";
	//PAR_CODIGO
	//PARNOMBRE
	
	//DE LA CLASE ALUMNOS:
	public static final String PAR_APELLIDOS = "apellidos";
	public static final String PAR_DNI = "dni";
	//NOTA: en vista son todo texto, int. Pasa de fechas...
	public static final String PAR_FNACIMIENTO = "fNacimiento";
	public static final String PAR_DIRECCION = "direccion";
	public static final String PAR_EMAIL = "email";
	public static final String PAR_NHERMANOS = "nHermanos";
	public static final String PAR_ACTIVO = "activo";
	//PAR_CODIGO
	//PARNOMBRE
	
	//DE LA CLASE CURSO
	public static final String PAR_HORAS = "horasDuracionCurso";
	public static final String PAR_FINICIO = "finicio";
	public static final String PAR_FFINAL = "ffinal";
	//PAR_CODIGO
	//PARNOMBRE
//	public static final String PAR_ALUMNOS_CURSO;
//	public static final String PAR_PROFESOR_CURSO;
	
	//DE LA CLASE IDIOMA
	public static final String PAR_IDIOMA = "idioma";
	
	//DE LA CLASE LOGIN
	public static final String PAR_USUARIO =  "user";
	public static final String PAR_PASSWORD =  "password";
	
	//DE LA CLASE SESIÓN
	public static final String PAR_SESION = "sessionId";
	
	
}