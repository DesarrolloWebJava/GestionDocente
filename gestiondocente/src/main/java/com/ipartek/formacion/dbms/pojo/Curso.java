package com.ipartek.formacion.dbms.pojo;

import java.util.Date;

import com.ipartek.formacion.dbms.pojo.exceptions.CursoException;

public class Curso implements Comparable<Curso> {
	
	public static final int CODIGO_NULO = -1;	
	private int codigo;
	private String nombre;
	private int duracion;
	private Date fechaInicio;
	private Date fechaFin;
	
	public Curso() {
		super();
		this.codigo = CODIGO_NULO; 
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

	public void setNombre(String nombre) throws CursoException {
		
		if(nombre.length()<8){
			throw new CursoException(CursoException.COD_NOMBRECURSO_ERROR, CursoException.MSG_NOMBRECURSO_ERROR);
		}
		
		this.nombre = nombre;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) throws CursoException {
		if(duracion<0){
			throw new CursoException(CursoException.COD_DURACION_ERROR, CursoException.MSG_DURACION_ERROR);
		}
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

	public void setFechaFin(Date fechaFin) throws CursoException {
		Date aux = new Date();
		long fecha1=fechaFin.getTime();
		long fecha2=aux.getTime();
				
		if(fecha1<fecha2){
			throw new CursoException(CursoException.COD_FECHAFIN_ERROR, CursoException.MSG_FECHAFIN_ERROR);			
		}
		this.fechaFin = fechaFin;
	}

	@Override
	public String toString() {
		return "Codigo: " + codigo + ", Nombre: " + nombre + ", Duracion: " + duracion + ", Fecha de inicio: "
				+ fechaInicio + ", Fecha de fin: " + fechaFin;
	}

	@Override
	public int compareTo(Curso o) {
		return this.nombre.compareToIgnoreCase(o.nombre);
	}


	
}
