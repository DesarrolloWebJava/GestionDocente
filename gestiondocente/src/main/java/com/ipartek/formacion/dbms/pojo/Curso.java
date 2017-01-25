package com.ipartek.formacion.dbms.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class Curso implements Serializable, Comparable<Curso>{ 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int CODIGO_NULO = -1;
	private int codigo;
	private String nombreCurso;
	private int duracion;
	private Date fechaInicio;
	private Date fechaFin;
	private List<Alumno> alumnos;
	private Profesor profesor;
	
	

	public Curso() {
		super();
		this.nombreCurso = "";
		this.duracion= 0;
		this.fechaInicio= new Date();
		this.fechaFin= new Date();
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
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(int codigo) {
			this.codigo = codigo;
	}
	
	/**
	 * @return the nombreCurso
	 */
	public String getNombreCurso() {
			return nombreCurso;
	}
	
	/**
	 * @param nombre
	 *            the nombreCurso to set
	 */
	public void setNombreCurso(String nombreCurso) {
			this.nombreCurso = nombreCurso;
	}
	
	
	/**
	 * @return the duracion
	 */
	public int getDuracion() {
			return duracion;
	}
	
	/**
	 * @param nombre
	 *            the nombreCurso to set
	 */
	public void setDuracion(int duracion) {
			this.duracion = duracion;
	}
	
	
	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
			return fechaInicio;
	}
	
	/**
	 * @param fechaInicio
	 *            the fechaInicio to set
	 */	
	public void setFechaInicio(Date fechaInicio) {
			this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechafin
	 */
	public Date getFechaFin() {
			return fechaFin;
	}
	
	/**
	 * @param fechafin
	 *            the fechafin to set
	 */	
	public void setFechaFin(Date fechaFin) {
			this.fechaFin = fechaFin;
	}

	/**
	 * @return the alumnos
	 */
	public List<Alumno> getAlumnos() {
			return alumnos;
	}
	
	/**
	 * @param alumnos
	 *            the alumnos to set
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
	 * @param profesor
	 *            the profesor to set
	 */
	public void setProfesor(Profesor profesor) {
			this.profesor = profesor;
	}
	
	@Override
	public int compareTo(Curso o) {
		return this.nombreCurso.compareToIgnoreCase(o.nombreCurso);
	}
	
	@Override
	public String toString() {
		return this.nombreCurso+ ", " + this.duracion;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		boolean iguales = false;
		if (obj instanceof Curso) {
			Curso cur = (Curso) obj;
			if (this.codigo == cur.getCodigo()) {
				iguales = true;
			}
		}
		return iguales;
	}

	
}
