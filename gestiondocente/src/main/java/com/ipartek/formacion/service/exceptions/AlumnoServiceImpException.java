package com.ipartek.formacion.service.exceptions;

public class AlumnoServiceImpException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int COD_ALUMNO_NO_ENCONTRADO = 500;
	public static final String MSG_ALUMNO_NO_ENCONTRADO = "No se encuentra el alumno solicitado";

	private int codigo;
	private String mensaje;

	public AlumnoServiceImpException(String message) {
		super(message);
		this.mensaje = message;
	}

	public AlumnoServiceImpException(int codigo, String mensaje) {
		super(mensaje);
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public int getCodigo() {
		return codigo;
	}

}
