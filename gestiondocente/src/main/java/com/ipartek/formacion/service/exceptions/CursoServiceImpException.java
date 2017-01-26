package com.ipartek.formacion.service.exceptions;

/**
 * Esta excepcion va a controlar:
 * <ul>
 * <li>Duracion > 0.</li>
 * <li>Fecha inicio y fin > hoy.</li>
 * <li>nombre curso >= 8.</li>
 * </ul>
 * 
 * @author va00
 *
 */

public class CursoServiceImpException extends Exception{

	public final static int COD_DURACION_ERROR = 500;
	public final static int COD_FECHA_INICIO_ERROR = 510;
	public final static int COD_FECHA_FIN_ERROR = 520;
	public final static int COD_LONGITUD_NOMBRE_CUROS = 530;

	public final static String MSG_DURACION_ERROR = "Duracion menor a la requerida";
	public final static String MSG_FECHA_INICIO_ERROR = "Debe de ser mayor a la fecha de hoy";
	public final static String MSG_FECHA_FIN_ERROR = "Debe de ser mayor a la fecha de hoy";
	public final static String MSG_LONGITUD_NOMBRE_CUROS = "El nombre introducido debe de tener ocho letras";
	private int codigo;
	
	
	public int getCodigo() {
		return codigo;
	}



	
	
	

}
