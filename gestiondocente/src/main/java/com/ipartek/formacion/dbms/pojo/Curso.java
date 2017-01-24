package com.ipartek.formacion.dbms.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;





public class Curso implements Comparable<Curso>, Serializable{
	
	
	private static final long serialVersionUID = -6698866485450376235L;
	private  int codigo;
	public static final int CODIGO_NULO = -1;
	private String nombre;
	private int duracion;
	private Date fInicio;
	private Date fFin;
	private List<Alumno> alumnos;
	private Profesor profesor;
	
	
	public Curso() {
		super();
		this.codigo=CODIGO_NULO;
		this.nombre="";
		this.duracion= 0;
		this.fInicio =new Date();
		this.fFin =new Date();
		alumnos = new ArrayList<Alumno>();
		profesor = new Profesor();
		
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


	public void setNombre(String nombre) {
		
		this.nombre = nombre;
	}


	public int getDuracion() {
		return duracion;
	}




	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}


	public Date getfInicio() {
		return fInicio;
		
	}


	public void setfInicio(Date fInicio) {
		this.fInicio = fInicio;
	}


	public Date getfFin() {
		return fFin;
	}


	public void setfFin(Date fFin) {
		this.fFin = fFin;
	}


	public List<Alumno> getAlumnos() {
		return alumnos;
	}


	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}


	public Profesor getProfesor() {
		return profesor;
	}


	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}


	@Override
	public String toString() {
		return "Codigo:" + codigo + "  Nombre:" + nombre + "  Duracion:" + duracion +" horas";
	}

	/**
	 * Se usa para ordenar Listas
	 */
	@Override
	public int compareTo(Curso o) {
		return this.getNombre().compareToIgnoreCase(o.getNombre());
	}

	/**
	 * pàra evaluar si 2 objetos son iguales
	 */
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
