package com.ipartek.formacion.dbms.pojo.exceptions;
/**
 * Esta clase va a controlar los errores de la calse Curso
 * 1.nombre menor de 8 caracteres
 * 2.duracion menos de 0 horas
 * 3.Fecha de fin menor que fecha actual
 * @author Curso
 *
 */
public class CursoException extends Exception {
	
	public final static int COD_NOMBRE_ERROR = 600;
	public final static int COD_DURACION_ERROR = 610;
	public final static int COD_FFIN_NOMBRE = 620;

	public final static String MSG_NOMBRE_ERROR = "El Nombre debe  tener minimo 8 caracteres";
	public final static String MSG_DURACION_ERROR = "La duración ";
	public final static String MSG_FFIN_NOMBRE = "El nombre introducido debe de tener tres letras o mas";
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
