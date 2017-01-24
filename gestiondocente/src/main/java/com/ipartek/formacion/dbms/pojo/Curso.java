package com.ipartek.formacion.dbms.pojo;

import java.util.Date;

public class Curso {
	
	public static final int CODIGO_NULO = -1;	
	private int codigo;
	private String nombre;
	private int duracion;
	private Date fechaInicio;
	private Date fechaFin;
	
	public Curso() {
		super();
		this.codigo = 0;
		this.nombre = "";
		this.duracion = 0;
		this.fechaInicio = new Date();
		this.fechaFin = new Date();
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

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Override
	public String toString() {
		return "Curso [codigo=" + codigo + ", nombre=" + nombre + ", duracion=" + duracion + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + "]";
	}


	
}
