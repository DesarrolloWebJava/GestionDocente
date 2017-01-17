/* Paquete donde se guardan lasexcepciones de las clases pojo. */
package com.ipartek.formacion.dbms.pojo.exceptions;

/**
 * @author Urko Villanueva
 * @author Raúl de Roba 17/01/17 (Añadido de comentarios.)
 * 
 * Esta excepcion va a controlar:
 * <ul>
 * <li>cuando no se introduzca un DNI con el formato adecuado.</li>
 * <li>Que el alumno tenga al menos 18 años</li>
 * <li>El nombre >= 3.</li>
 * </ul> 
 *
 */
public class PersonaException extends Exception {

	/* Constante serial de la clase.*/
	private static final long serialVersionUID = 3758419752344728600L;
	/* Se declaran las constantes de codigo de error para la excepción. */
	public final static int COD_DNI_ERROR = 500;
	public final static int COD_EDAD_ERROR = 510;
	public final static int COD_LONGITUD_NOMBRE = 520;
	
	/* Se declaran las constantes de mensajes de error para la excepción. */
	public final static String MSG_DNI_ERROR = "El DNI introducido no es valido";
	public final static String MSG_EDAD_ERROR = "Debe de ser mayor de 18 años";
	public final static String MSG_LONGITUD_NOMBRE = 
			                     "El nombre introducido debe de tener tres letras o mas";
	
	/* Atributo 'codigo' con el que trabajar el codigo de error.*/
	private int codigo;
	/* Atributo 'mensaje' de error.*/
	private String mensaje;
	
	
	/* Constructor con el codigo y mensaje de error como parametro. */
	public PersonaException(int codigo, String mensaje) {
		/* Se llama al constructor de la clase pasandole el mensaje de error.*/
		super(mensaje);
		/* Se asigna el codigo de error de la clase.*/
		this.codigo = codigo;
		/* Se asigna el mensaje de error de la clase.*/
		this.mensaje = mensaje;
	}
	
	/* Constructor con el mensaje de error como parametro. */
	public PersonaException(String message) {
		/* Se llama al constructor de la clase pasandole el mensaje de error.*/
		super(message);
		/* Se asigna el mensaje de error de la clase.*/
		this.mensaje = message;
	}

	/* Getter del atributo 'codigo'.*/
	public int getCodigo() {
		/* Se devuelve el codigo.*/
		return codigo;
	}

}
