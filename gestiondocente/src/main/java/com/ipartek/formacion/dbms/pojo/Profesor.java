package com.ipartek.formacion.dbms.pojo;
import com.ipartek.formacion.service.Util;


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
		return "Profesor :Email=" + getEmail() + ", Direccion=" + getDireccion() + ", Dni=" + getDni()
				+ ", Nombre=" + getNombre() + ", Apellidos=" + getApellidos() + ", fNacimiento="
				+ Util.fechaFormateada( getfNacimiento()) ;
	}

	
	
}
