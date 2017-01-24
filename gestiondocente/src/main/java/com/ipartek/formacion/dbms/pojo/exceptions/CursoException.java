package com.ipartek.formacion.dbms.pojo.exceptions;

public class CursoException extends Exception {
	
	public final static int COD_DURACION_ERROR=1;
	public final static int COD_NOMBRECURSO_ERROR=2;
	public final static int COD_FECHAINI_ERROR=3;
	public final static int COD_FECHAFIN_ERROR=4;
	private int codigo;
	
	public final static String MSG_DURACION_ERROR="La duración del curso no puede ser menor de 0 h.";
	public final static String MSG_NOMBRECURSO_ERROR= "El nombre del curso debe tener al menos 8 caracteres";
	public final static String MSG_FECHAINI_ERROR="El formato no es correcto (dd/MM/yyyy)";
	public final static String MSG_FECHAFIN_ERROR="La fecha final no puede ser menor al día de hoy";
	
	public CursoException(int codigo, String mensaje) {
		super(mensaje);
		this.codigo = codigo;
	}
	
	public CursoException(String message){
		super(message);
	}

	public int getCodigo() {
		return codigo;
	}
}
