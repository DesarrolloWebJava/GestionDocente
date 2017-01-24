	package com.ipartek.formacion.dbms.pojo;


public class Profesor extends Persona {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3179760743540634720L;
	public static final int CODIGO_NULO = -1;
	private int nSS;
	private int codigo;

	public Profesor() {
		super();
		this.codigo = CODIGO_NULO;
		this.nSS = 0;
	}
	public Profesor(int nSS) {
		super();
		this.nSS = nSS;
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
		
		return this.getCodigo() + " " + this.getEmail();
	}
	@Override
	public int compareTo(Profesor o) {
		return this.getApellidos().compareToIgnoreCase(o.getApellidos());
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean iguales = false;
		if (obj instanceof Profesor) {
			Profesor prof = (Profesor) obj;
			if (this.codigo == prof.getCodigo()) {
				iguales = true;
			}
		}
		return iguales;
	}
}
