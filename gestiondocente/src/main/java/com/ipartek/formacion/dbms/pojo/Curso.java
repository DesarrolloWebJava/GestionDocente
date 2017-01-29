package com.ipartek.formacion.dbms.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Curso implements Serializable, Comparable<Curso> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int CODIGO_NULO = -1;
	private int codigo;
	private String nombre;
	private int horasDuracionCurso;
	private Date finicio;
	private Date ffinal;
	private List<Alumno> alumnos;
	private Profesor profesor;

	public Curso() {
		super();
		this.nombre = "";
		this.horasDuracionCurso = 0;
		this.ffinal = new Date();
		this.finicio = new Date();
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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the horasDuracionCurso
	 */
	public int getDuracion() {
		return horasDuracionCurso;
	}

	/**
	 * @param horasDuracionCurso
	 *            the horasDuracionCurso to set
	 */
	public void setDuracion(int horasDuracionCurso) {
		this.horasDuracionCurso = horasDuracionCurso;
	}

	/**
	 * @return the finicio
	 */
	public Date getFinicio() {
		return finicio;
	}

	/**
	 * @param finicio
	 *            the finicio to set
	 */
	public void setFinicio(Date finicio) {
		this.finicio = finicio;
	}

	/**
	 * @return the ffinal
	 */
	public Date getFFinal() {
		return ffinal;
	}

	/**
	 * @param ffinal
	 *            the ffinal to set
	 */
	public void setFFinal(Date ffinal) {
		this.ffinal = ffinal;
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
		return this.nombre.compareToIgnoreCase(o.nombre);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nombre + ", " + this.horasDuracionCurso;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		boolean iguales = false;
		if (obj instanceof Curso) {
			Curso curso = (Curso) obj;
			if (curso.getCodigo() == this.codigo) {
				iguales = true;
			}
		}
		return iguales;
	}

}