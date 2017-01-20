	package com.ipartek.formacion.dbms.pojo;


public class Profesor extends Persona {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3179760743540634720L;
	public static final int CODIGO_NULO = -1;
	private int nSS;
	private int codigo;
	private boolean activo;
	private int nHermanos;

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
	
	public int getnHermanos() {
		return nHermanos;
	}

	public void setnHermanos(int nHermanos) {
		this.nHermanos = nHermanos;
	}
	
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
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
			Profesor alum = (Profesor) obj;
			if (this.codigo == alum.getCodigo()) {
				iguales = true;
			}
		}
		return iguales;
	}
}
