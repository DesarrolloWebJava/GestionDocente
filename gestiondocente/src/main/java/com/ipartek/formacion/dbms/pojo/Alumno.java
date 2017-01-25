package com.ipartek.formacion.dbms.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * 
 * @author Urko Villanueva
 *
 */
public class Alumno extends Persona implements Comparable<Alumno>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6698866485450376235L;
	public static final int CODIGO_NULO = -1;
	private int codigo;
	private boolean activo;
	private int nHermanos;
	private String pattern = "dd/MM/yyyy";
	private SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

	public Alumno() {
		super();// constructor de la clase padre
		this.codigo = CODIGO_NULO;
		this.activo = true;
		this.nHermanos = 0;

	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return this.getCodigo() + " " + this.getApellidos() + ", " + this.getNombre() + " " + this.getDni()+" "+dateFormat.format(this.getfNacimiento())+" "+this.getEmail()+" "+this.getnHermanos();
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getnHermanos() {
		return nHermanos;
	}

	public void setnHermanos(int nHermanos) {
		this.nHermanos = nHermanos;
	}

	/**
	 * Se usa en el caso de ordenamiento de List o Array
	 * 
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(Alumno o) {
		 int numero = this.getApellidos().compareToIgnoreCase(o.getApellidos());
		 
		 return numero;
	}

	/**
	 * Para evaluar si los objetos son iguales
	 */

	@Override
	public boolean equals(Object obj) {
		boolean iguales = false;
		if (obj instanceof Alumno) {
			Alumno alum = (Alumno) obj;
			if (this.codigo == alum.getCodigo()) {
				iguales = true;
			}
		}
		return iguales;
	}

}
