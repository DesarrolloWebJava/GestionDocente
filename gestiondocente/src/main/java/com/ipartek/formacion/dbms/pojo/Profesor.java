package com.ipartek.formacion.dbms.pojo;

import java.io.Serializable;

public class Profesor extends Persona implements Comparable<Profesor>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		return this.getCodigo() + " " + this.getApellidos() + ", " + this.getNombre() + " " + this.getDni()+" "+this.getEmail()+" "+this.getDireccion()+" "+this.getnSS();
	}

	@Override
	public int compareTo(Profesor o) {
		// TODO Auto-generated method stub
		return this.getApellidos().compareToIgnoreCase(o.getApellidos());
	}

}
