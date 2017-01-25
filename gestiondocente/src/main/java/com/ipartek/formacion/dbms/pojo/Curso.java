package com.ipartek.formacion.dbms.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.ipartek.formacion.dbms.pojo.exceptions.CursoException;

@SuppressWarnings("serial")
public class Curso implements Serializable, Comparable {

	public static final int CODIGO_NULO = -1;
	private int codigo;
	private String nombre;
	private int duracion;
	private Date fInicio;
	private Date fFinal;

	private List<Alumno> alumnos;
	private List<Profesor> profesores;

	public Curso() {
		super();
		this.codigo = CODIGO_NULO;
		this.nombre = "";
		this.duracion = 0;
		this.fInicio = new Date();
		this.fFinal = new Date();
		alumnos = new ArrayList<Alumno>();
		profesores = new ArrayList<Profesor>();
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) throws CursoException {

		if (nombre.length() < 8) {
			throw new CursoException(CursoException.COD_LONGITUD_NOMBRE, CursoException.MSG_LONGITUD_NOMBRE);
		}
		this.nombre = nombre;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) throws CursoException {
		if (duracion <= 0) {

			throw new CursoException(CursoException.COD_DURACION_ERROR, CursoException.MSG_DURACION_ERROR);
		}
		this.duracion = duracion;
	}

	public Date getfInicio() {
		return fInicio;
	}

	public void setfInicio(Date fInicio) {

		this.fInicio = fInicio;
	}

	public Date getfFinal() {
		return fFinal;
	}

	public void setfFinal(Date fFinal) throws CursoException {

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		if (gc.getTime().after(fFinal)) {

			throw new CursoException(CursoException.COD_FFIN_ERROR, CursoException.MSG_FFIN_ERROR);
		}

		this.fFinal = fFinal;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public List<Profesor> getProfesores() {
		return profesores;
	}

	public void setProfesores(List<Profesor> profesores) {
		this.profesores = profesores;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getCodigo() + ", " + this.getNombre() + ", " + this.getDuracion() + ", " + this.getfInicio() + ", "
				+ this.getfFinal();
	}

	public int compareTo(Curso c) {
		return this.nombre.compareToIgnoreCase(c.nombre);
	}

	@Override
	public boolean equals(Object obj) {
		boolean iguales = false;
		if (obj instanceof Curso) {
			Curso c = (Curso) obj;
			if (this.codigo == c.getCodigo()) {
				iguales = true;
			}
		}
		return iguales;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
