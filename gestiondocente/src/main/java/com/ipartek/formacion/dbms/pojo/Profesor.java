package com.ipartek.formacion.dbms.pojo;

public class Profesor extends Persona implements Comparable<Profesor> {
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
		return "Profesor [ Codigo=" + getCodigo() + ", Nombre=" + getNombre()+ ", DNI=" + getDni() 
				+  ", Apellidos=" + getApellidos() + ", Fecha Nacimiento=" + getfNacimiento() 
				+ ", Email=" + getEmail()+ ", Direccion=" + getDireccion() +", Número SS=" + getnSS() + "]";
	}

	@Override
	public int compareTo(Profesor o) {
		return this.getApellidos().compareToIgnoreCase(o.getApellidos());
	}

	
}
