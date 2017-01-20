package com.ipartek.formacion.service.exceptions;

public class ProfesorServiceImpExcepciones extends Exception{

	


	private static final long serialVersionUID = 1L;
	public static final int COD_PROFESOR_NO_ENCONTRADO = 500;
	public static final String MSG_PROFESOR_NO_ENCONTRADO = "No se encuentra el profesor solicitado";
	
	private int codigo;
	private String mensaje;
	
	
	public ProfesorServiceImpExcepciones(String message) {
		super(message);
		this.mensaje = mensaje; // Por que se pone aqui?
	}
	
	public ProfesorServiceImpExcepciones(int codigo, String mensaje) {
		super(mensaje);
		this.codigo = codigo;
		this.mensaje = mensaje;
	}
	
	public int getCodigo() {
		return codigo;
	}

}
