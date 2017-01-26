package com.ipartek.formacion.dbms.pojo.exceptions;

public class CursoException extends Exception {
	
	public final static int COD_NOMBRE_ERROR = 600;
	public final static int COD_DURACION_ERROR = 605;
	public static final int COD_FFINAL_ERROR = 610;
	
	public static final String MSG_NOMBRE_ERROR = "El nombre es mayor de ocho caracteres";
	public static final String MSG_DURACION_ERROR ="La duracion debe ser mayor de 0 horas";
	public static final String MSG_FFINAL_ERROR = "La fecha final debe ser superior a la fecha de hoy";
	
	private int codigo;
	private String mensaje;
	
	public CursoException(int codigo, String mensaje) {
		super(mensaje);
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public CursoException(String message) {
		super(message);
		this.mensaje = message;
	}

	public int getCodigo() {
		return codigo;
	}
	

}
