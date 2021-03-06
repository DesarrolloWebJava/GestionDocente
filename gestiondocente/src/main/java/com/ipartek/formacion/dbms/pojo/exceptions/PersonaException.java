package com.ipartek.formacion.dbms.pojo.exceptions;

/**
 * Esta excepcion va a controlar:
 * <ul>
 * <li>cuando no se introduzca un DNI con el formato adecuado.</li>
 * <li>Que el alumno tenga al menos 18 años</li>
 * <li>El nombre >= 3.</li>
 * </ul>
 * 
 * @author va00
 *
 */
public class PersonaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static int COD_DNI_ERROR = 500;
	public final static int COD_EDAD_ERROR = 510;
	public final static int COD_LONGITUD_NOMBRE = 520;

	public final static String MSG_DNI_ERROR = "El DNI introducido no es valido";
	public final static String MSG_EDAD_ERROR = "Debe de ser mayor de 18 años";
	public final static String MSG_LONGITUD_NOMBRE = "El nombre introducido debe de tener tres letras o mas";
	private int codigo;

	public PersonaException(int codigo, String mensaje) {
		super(mensaje);
		this.codigo = codigo;
	}

	public PersonaException(String message) {
		super(message);
	}

	public int getCodigo() {
		return codigo;
	}

}
