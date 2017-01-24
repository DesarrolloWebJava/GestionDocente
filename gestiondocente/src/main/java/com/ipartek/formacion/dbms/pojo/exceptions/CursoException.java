package com.ipartek.formacion.dbms.pojo.exceptions;

public class CursoException extends Exception {

	public final static int COD_LONGITUD_CURSO_NOMBRE = 520;
	
	public final static String MSG_DURACION_ERROR = "Debe tener mas horas";
	public final static String MSG_LONGITUD_CURSO_NOMBRE = "El curso introducido debe de tener al menos ocho letras o mas";
	
	
	private int codigo;
	
	
	
	public CursoException(int codigo, String mensaje) {
		super(mensaje);

	}

	public CursoException(String message) {
		super(message);
	
	}

	public int getCodigo() {
		return codigo;
	}
}
