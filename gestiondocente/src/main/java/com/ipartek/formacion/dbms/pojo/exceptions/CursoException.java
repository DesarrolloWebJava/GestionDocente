package com.ipartek.formacion.dbms.pojo.exceptions;
/**
 * Esta excepcion va a controlar:
 * <ul>
 * <li>Que el curso tenga al menos 8 caracteres</li>
 * <li>la duracion >0</li>
 * <li>fecha de fin > hoy</li>
 * </ul>
 * 
 * @author va00
 *
 */
public class CursoException extends Exception {
	private static final long serialVersionUID = 1L;
	public final static int COD_NOMBRE_ERROR = 100;
	public final static int COD_DURACION_ERROR=200;
	public final static int COD_FFIN_ERROR=300;

	public final static String MSG_NOMBRE_ERROR = "El nombre del curso tiene que tener mas de 8 caracteres";
	public final static String MSG_DURACION_ERROR="La duracion del curso no puede ser inferior o igual a 0";
	public final static String MSG_FFIN_ERROR="La fecha de fin del curso no puede ser anterior a hoy";
	
	private int codigo;

	public CursoException(int codigo, String mensaje) {
		super(mensaje);
		this.codigo = codigo;
	}

	public CursoException(String message) {
		super(message);
	}

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}
	

}
