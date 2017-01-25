/**
 * 
 */
package com.ipartek.formacion.dbms.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Mikel Bruce
 *
 */
public class Curso implements Serializable, Comparable<Curso>{

	private int codigo;
	private String nombre;
	private int duracion;
	private Date fInicio;
	private Date fFin;
	private List<Alumno> alumnos;
	private Profesor profesor;
	public static final int CODIGO_NULO = -1;
	/**
	 * 
	 */
	public Curso() {
		super();
		this.codigo = CODIGO_NULO;
		this.nombre = "";
		this.duracion = 0;
		this.fInicio = new Date();
		this.fFin = new Date();
		this.alumnos = new ArrayList<Alumno>();
		this.profesor = new Profesor();
	}
	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the duracion
	 */
	public int getDuracion() {
		return duracion;
	}
	/**
	 * @param duracion the duracion to set
	 */
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	/**
	 * @return the fInicio
	 */
	public Date getfInicio() {
		return fInicio;
	}
	/**
	 * @param fInicio the fInicio to set
	 */
	public void setfInicio(Date fInicio) {
		this.fInicio = fInicio;
	}
	/**
	 * @return the fFin
	 */
	public Date getfFin() {
		return fFin;
	}
	/**
	 * @param fFin the fFin to set
	 */
	public void setfFin(Date fFin) {
		this.fFin = fFin;
	}
	/**
	 * @return the alumnos
	 */
	public List<Alumno> getAlumnos() {
		return alumnos;
	}
	/**
	 * @param alumnos the alumnos to set
	 */
	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	/**
	 * @return the profesor
	 */
	public Profesor getProfesor() {
		return profesor;
	}
	/**
	 * @param profesor the profesor to set
	 */
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String fI, fF = null;
		GregorianCalendar  gc1 = new GregorianCalendar();
		GregorianCalendar  gc2 = new GregorianCalendar();
		gc1.setTime(fInicio);
		gc2.setTime(fFin);
		fI=gc1.get(GregorianCalendar.DAY_OF_MONTH)+"/"+((gc1.get(GregorianCalendar.MONTH))+1)+"/"+gc1.get(GregorianCalendar.YEAR);
		fF=gc2.get(GregorianCalendar.DAY_OF_MONTH)+"/"+((gc2.get(GregorianCalendar.MONTH))+1)+"/"+gc2.get(GregorianCalendar.YEAR);
		return "Curso: codigo: " + codigo + ", nombre: " + nombre + ", duracion: " + duracion + ", fInicio: " + fI
				+ ", fFin: " + fF + ", alumnos: " + alumnos + ", profesor: " + profesor;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		boolean iguales = false;
		if (obj instanceof Curso) {
			Curso curso = (Curso) obj;
			if (this.codigo == curso.getCodigo()) {
				iguales = true;
			}
		}
		return iguales;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Curso o) {
		return this.nombre.compareToIgnoreCase(o.nombre);
	}
		
}
