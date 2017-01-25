package com.ipartek.formacion.service.exceptions;

public class CursoServiceImpException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int COD_CURSO_NO_ENCONTRADO = 1000;
	public static final String MSG_CURSO_NO_ENCONTRADO = "No se encuentra el curso solicitado";
	public static final int COD_NOMBRE_CURSO_CORTO = 1001;
	public static final String MSG_NOMBRE_CURSO_CORTO = "El nombre del curso es demasiado corto";
	public static final int COD_FECHA_FIN_INCORRECTA = 1002;
	public static final String MSG_FECHA_FIN_INCORRECTA = "La fecha de finalizacion tiene que sea mayor que el dia de hoy";
	public static final int COD_DURACION_NEG = 1003;
	public static final String MSG_DURACION_NEG = "La duracion no puede ser 0 o menos";
	
	private int codigo;
	private String mensaje;
	
	
	public CursoServiceImpException(String mensaje) {
		super(mensaje);
		this.mensaje = mensaje;
	}


	public CursoServiceImpException(int codigo, String mensaje) {
		super(mensaje);
		this.codigo = codigo;
		this.mensaje = mensaje;
	}


	public int getCodigo() {
		return codigo;
	}
	
	
}
