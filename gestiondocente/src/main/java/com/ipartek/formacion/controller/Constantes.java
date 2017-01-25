package com.ipartek.formacion.controller;

public class Constantes {

	private Constantes(){
		//con esto nadie me puede instanciar un objeto llamado constantes
	}

	//POJOS
	public static final String ATT_PROFESOR = "profesor";
	public static final String ATT_ALUMNO = "alumno";
	public static final String ATT_CURSO  = "curso";
	
	//SERVLETS
	public static final String SERVLET_PROFESOR = "profesor.do";
	public static final String SERVLET_ALUMNO = "alumno.do";
	public static final String SERVLET_CURSO = "curso.do";

	//JSP_LISTADOS	
	public static final String JSP_LISTADO_PROFESORES="profesores/listado.jsp";
	public static final String JSP_LISTADO_ALUMNOS = "alumnos/listado.jsp";
	public static final String JSP_LISTADO_CURSO   = "curso/listado.jsp";

	//JSP_FORMS
	public static final String JSP_FORMULARIO_PROFESOR = "profesores/profesor.jsp";
	public static final String JSP_FORMULARIO_ALUMNO = "alumnos/alumno.jsp";
	public static final String JSP_FORMULARIO_CURSO = "cursos/curso.jsp";

	//VARIOS
	public static final String JSP_HOME = "index.jsp";
	public static final String ATT_MENSAJE = "mensaje";
	public static final String ATT_LISTADO_PROFESORES = "listado-profesores";
	public static final String ATT_LISTADO_ALUMNOS = "listado-alumnos";
	public static final String ATT_LISTADO_CURSOS =  "listado-cursos";
	

	//OPERACIONES DEL SERVLET
	public static final String PAR_OPERACION = "op";
	
	public static final int OP_CREATE = 1;
	public static final int OP_READ = 2;
	public static final int OP_UPDATE = 3;
	public static final int OP_DELETE = 4;

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
	

}
