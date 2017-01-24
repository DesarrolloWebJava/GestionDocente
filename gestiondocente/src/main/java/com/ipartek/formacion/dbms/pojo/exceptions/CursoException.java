package com.ipartek.formacion.dbms.pojo.exceptions;

public class CursoException extends Exception {
	public final static int COD_LONGITUD_NOMBRE = 500;
	public final static int COD_DURACION_ERROR = 510;
	public final static int COD_FECFIN_ERROR = 510;
	
	public final static String MSG_LONGITUD_NOMBRE = "El nombre introducido debe de tener ocho letras o mas";
	public final static String MSG_DURACION_ERROR = "La duracion del curso tiene que ser mayor a 0";
	public final static String MSG_FECFIN_ERROR = "La fecha de fin de curso tiene que ser mayor a la de hoy";
	
	private int codigo;
	private String mensaje;
	
	public CursoException(int codigo, String mensaje){
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
