package com.ipartek.formacion.dbms.pojo.exceptions;

public class CursoException extends Exception {
    
	public static final int COD_ERROR_LONGITUD=100;
	public static final int COD_ERROR_DURACION=101;
	public static final int COD_ERROR_FECHA_INI=102;
	public static final int COD_ERROR_FECHA_FIN=103;
	public static final int COD_ERROR_PARSEO_FECHA_INI=104;
	
	public static final String MSG_ERROR_LONGITUD="El numero minimo de caracteres para el nombre es de 8";
	public static final String MSG_ERROR_DURACION="La duracion debe ser mayor que 0";
	public static final String MSG_ERROR_FECHA_INI="La fecha de Inicio debe ser mayor o igual que la de hoy";
	public static final String MSG_ERROR_FECHA_FIN="La fecha de fin debe ser igual o mayor a la fecha fin";
	public static final String MSG_ERROR_PARSEO_FECHA_INI="Error al parsear la fecha de inicio";
	
	private int codigo;
	private String mensaje;

public CursoException(int codigo, String mensaje) {
	super(mensaje);
	this.codigo = codigo;
	this.mensaje = mensaje;
}

}
