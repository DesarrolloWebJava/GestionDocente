/* Paquete de excepciones de las clases del paquete Service.*/
package com.ipartek.formacion.service.exceptions;

/**
 * 
 * @author Raúl de Roba 18/01/17.
 * 
 * <p>Clase que gestiona las excepciones de las operaciones en la clase Alumno.</p>
 *
 */

public class AlumnoServiceImpException extends Exception {

	/* Constante serial de la clase.*/
	private static final long serialVersionUID = 1L;
	/* Se declaran las constantes de codigo de error para la excepción. */
	public static final int COD_ALUMNO_NO_ENCONTRADO = 500;
	/* Se declaran las constantes de mensajes de error para la excepción. */
	public static final String MSG_ALUMNO_NO_ENCONTRADO = 
			                                       "No se encuentra el alumno solicitado";

	/* Atributo 'codigo' con el que trabajar el codigo de error.*/
	private int codigo;
	/* Atributo 'mensaje' de error.*/
	private String mensaje;
	
	/* Constructor con el mensaje de error como parametro. */
	public AlumnoServiceImpException(String message) {		
		/* Se llama al constructor de la clase pasandole el mensaje de error.*/
		super(message);
		/* Se asigna el mensaje de error de la clase.*/
		this.mensaje = message;
	}

	/* Constructor con el codigo y mensaje de error como parametro. */
	public AlumnoServiceImpException(int codigo, String mensaje) {
		/* Se llama al constructor de la clase pasandole el mensaje de error.*/
		super(mensaje);
		/* Se asigna el codigo de error de la clase.*/
		this.codigo = codigo;
		/* Se asigna el mensaje de error de la clase.*/
		this.mensaje = mensaje;
	}

	/* Getter del atributo 'codigo'.*/
	public int getCodigo() {
		/* Se devuelve el codigo.*/
		return codigo;
	}

}
