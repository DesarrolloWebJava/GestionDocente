package com.ipartek.formacion.dbms.pojo;

import java.io.Serializable;

public class Profesor extends Persona implements Serializable {
	@Override
	public boolean equals(Object obj) {
		boolean iguales=false;
		if(obj instanceof Profesor ){
			Profesor profesor=(Profesor) obj;
			if(this.codigo==profesor.getCodigo()){
				iguales=true;
			}
		}
		return iguales;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getCodigo()+" "+this.getApellidos()+" "+this.getNombre()+" "+this.getEmail();
	}


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

}
