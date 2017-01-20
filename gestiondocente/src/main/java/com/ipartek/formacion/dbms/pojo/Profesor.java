package com.ipartek.formacion.dbms.pojo;

public class Profesor extends Persona {
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.codigo+" " + this.getEmail();
	}

	public static final int CODIGO_NULO = -1;
	private long nSS;
	private int codigo;

	public Profesor() {
		super();
		this.codigo = CODIGO_NULO;
		this.nSS = 0;
	}

	public long getnSS() {
		return nSS;
	}

	public void setnSS(long nSS) {
		this.nSS = nSS;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

}
