package com.ipartek.formacion.dbms.pojo.exceptions;

public class CursoException extends Exception {

	public final static int COD_DURACION_ERROR = 550;
	public final static int COD_FFIN_ERROR = 560;
	public final static int COD_LONGITUD_NOMBRE = 570;

	public final static String MSG_DURACION_ERROR = "La duracion del curso no es correcta";
	public final static String MSG_FFIN_ERROR = "Fecha fin incorrecta, debe ser posterior al dia de hoy";
	public final static String MSG_LONGITUD_NOMBRE = "El nombre introducido debe de tener ocho letras o mas";
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
