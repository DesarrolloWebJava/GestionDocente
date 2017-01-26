package com.ipartek.formacion.controller;

public class Constantes {

	private Constantes(){ //nadie podra instaciar Constantes
	}
	
	// nombres de parametros de la clase Alumno
	public static final String ATT_LISTADO_ALUMNOS = "listadoAlumnos";
	// nombres de parametros de la clase Profesor
	public static final String ATT_LISTADO_PROFESORES = "listadoProfesores";
	// nombres de parametros de la clase Curso
	public static final String ATT_LISTADO_CURSOS = "listadoCursos";
	
	// VARIABLES DE SESION
	public static final String SESSION_IDIOMA = "language";
	
	
	
	public static final String ATT_MENSAJE = "mensaje";
	// nombres de parametros de la clase Alumno
	public static final String ATT_ALUMNO = "alumno";
	// nombres de parametros de la clase Profesor
	public static final String ATT_PROFESOR = "profesor";
	// nombres de parametros de la clase Curso
	public static final String ATT_CURSO = "curso";
	
	
	
	
	// nombres de parametros de la clase Alumno
	public static final String SERVLET_ALUMNO = "alumno.do";
	// nombres de parametros de la clase Profesor
	public static final String SERVLET_PROFESOR = "profesor.do";
	// nombres de parametros de la clase Curso
	public static final String SERVLET_CURSO = "curso.do";
	
	public static final String SERVLET_IDIOMA = "idioma.do";
	
	
	
	public static final String JSP_HOME = "index.jsp";
	// nombres de parametros de la clase Alumno
	public static final String JSP_LISTADO_ALUMNOS = "alumnos/listado.jsp";
	public static final String JSP_FORMULARIO_ALUMNO = "alumnos/alumno.jsp";
	// nombres de parametros de la clase Profesor
	public static final String JSP_LISTADO_PROFESORES ="profesores/listado.jsp";
	public static final String JSP_FORMULARIO_PROFESOR = "profesores/profesor.jsp";
	// nombres de parametros de la clase Curso
	public static final String JSP_LISTADO_CURSOS ="cursos/listado.jsp";
	public static final String JSP_FORMULARIO_CURSO = "cursos/curso.jsp";
	
	
	
	public static final String PAR_OPERACION = "op";
	// nombres de parametros de la clase alumnos
	public static final String PAR_CODIGO = "codigo";
	public static final String PAR_NOMBRE = "nombre";
	public static final String PAR_APELLIDOS = "apellidos";
	public static final String PAR_DNI = "dni";
	public static final String PAR_EMAIL = "email";
	public static final String PAR_FNACIMIENTO = "fnacimiento";
	public static final String PAR_DIRECCION = "direccion";
	public static final String PAR_NHERMANOS = "nHermanos";
	public static final String PAR_ACTIVO = "activo";
	// nombres de parametros de la clase Profesor
	public static final String PAR_NSS = "nss";
	// nombres de parametros de la clase Curso
	public static final String PAR_NOMBRE_CURSO = "nCurso";
	public static final String PAR_DURACION = "duracion";
	public static final String PAR_FECHA_INICIO = "fInicio";
	public static final String PAR_FECHA_FIN = "fFin";
	public static final String PAR_IDIOMA = "idioma";
	
	
	
	public static final int OP_CREATE = 1;
	public static final int OP_READ = 2;
	public static final int OP_UPDATE = 3;
	public static final int OP_DELETE = 4;
	
	public static final int IDIOMA_EUSKERA = 1;
	public static final int IDIOMA_CASTELLANO = 2;
	public static final int IDIOMA_INGLES = 3;
	
}
