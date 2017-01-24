package com.ipartek.formacion.dbms.pojo.exceptions;

public class CursoException extends Exception {

	public final static int COD_ERROR_FECHAS = 510;
	public final static int COD_LONGITUD_NOMBRE = 520;

	
	public final static String MSGERROR_FECHAS = "Las fecha de inicio debe de ser superior a la del fin del curso";
	public final static String MSG_LONGITUD_NOMBRE = "El nombre introducido debe de tener tres letras o mas";
	private int codigo;
	private String mensaje;
	
	public CursoException(int codigo, String mensaje) {
		super(mensaje);
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
