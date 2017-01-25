/* Paquete donde se guardan lasexcepciones de las clases pojo. */
package com.ipartek.formacion.dbms.pojo.exceptions;

/**
 * @author Raúl de Roba 24/01/17
 * 
 * <p>Esta excepcion va a controlar:</p>
 * <ul>
	 * <li>Cuando el nombre tenga menos de 8 caracteres. </li>
	 * <li>Cuando la duración sea igual o menor a 0.</li>
	 * <li>Cuando las fechas sean menor a la fecha actual.</li>
 * </ul> 
 *
 */
public class CursoException extends Exception {

	/* Se declaran las constantes de codigo de error para la excepción. */
	public final static int COD_NOMBRE_ERROR = 500;
	public final static int COD_DURACION_ERROR = 510;
	public final static int COD_FECHAINICIO_HOY_ERROR = 520;
	public final static int COD_FECHAFIN_HOY_ERROR = 530;
	public final static int COD_FECHAREGEX_ERROR = 540;
	
	/* Se declaran las constantes de mensajes de error para la excepción. */
	public final static String MSG_NOMBRE_ERROR = 
		     							  "El nombre ha de tener una longitud de 8 caracteres.";
	public final static String MSG_DURACION_ERROR = 
			                               "La duración del curso ha de ser mayor a 0 horas.";
	public final static String MSG_FECHAINICIO_HOY_ERROR = "La fecha no puede ser menor a hoy.";
	public final static String MSG_FECHAFIN_HOY_ERROR = "La fecha no puede ser menor a hoy.";
	public final static String MSG_FECHAREGEX_ERROR =  
			                                      "La fecha ha de ser en formato dd/mm/aaaa.";
	
	/* Atributo 'codigo' con el que trabajar el codigo de error.*/
	private int codigo;
	/* Atributo 'mensaje' de error.*/
	private String mensaje;
	
	/* Constructor con el mensaje de error como parametro. */
	public CursoException(int codigo,String message) {
		/* Se llama al constructor de la clase pasandole el mensaje de error.*/
		super(message);
		/* Se asigna el codigo de error de la clase.*/
		this.codigo = codigo;
		/* Se asigna el mensaje de error de la clase.*/
		this.mensaje = message;
	}
	
	/* Constructor con el mensaje de error como parametro. */
	public CursoException(String message) {
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
