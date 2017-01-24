package com.ipartek.formacion.dbms.pojo;

public class Profesor extends Persona {
	public static final int CODIGO_NULO = -1;
	private int nSS;
	private int codigo;

	public Profesor() {
		super();
		this.codigo = CODIGO_NULO;
		this.nSS = 0;
	}

	public int getnSS() {
		return nSS;
	}

	public void setnSS(int nSS) {
		this.nSS = nSS;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Profesor [getnSS()=" + getnSS() + ", getCodigo()=" + getCodigo() + ", getEmail()=" + getEmail()
				+ ", getDireccion()=" + getDireccion() + ", getDni()=" + getDni() + ", getNombre()=" + getNombre()
				+ ", getApellidos()=" + getApellidos() + ", getfNacimiento()=" + getfNacimiento() + "]";
	}

	
}
