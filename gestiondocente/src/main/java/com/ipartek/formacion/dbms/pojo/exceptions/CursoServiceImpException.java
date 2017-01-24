package com.ipartek.formacion.dbms.pojo.exceptions;
/**
 * clase que me controla la excepcion de la interfaz cuando un curso no se encuentre
 * @author Curso
 *
 */
public class CursoServiceImpException extends Exception {
	private static final long serialVersionUID = 1L;
	public static final int COD_CURSO_NO_ENCONTRADO = 600;
	public static final String MSG_CURSO_NO_ENCONTRADO = "No se encuentra el curso solicitado";

	private int codigo;
	private String mensaje;
	
	public CursoServiceImpException(String message) {
		super(message);
		this.mensaje = message;
	}
	
	
	public CursoServiceImpException(int codigo, String message) {
		super(message);
		this.codigo = codigo;
		this.mensaje = message;
	}

	public int getCodigo() {
		return codigo;
	}


	
	
	

}
