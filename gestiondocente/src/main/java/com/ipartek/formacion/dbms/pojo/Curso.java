package com.ipartek.formacion.dbms.pojo;

import java.util.Date;
import java.util.GregorianCalendar;


public class Curso {




	public static final int CODIGO_NULO = -1;
	private int codigo;
	private String nombreCurso;
	private int duracion;
	private Date fechaInicio;
	private Date fechaFin;

	public Curso() {
		super();
		
		this.nombreCurso = "";
		this.codigo = CODIGO_NULO;
		this.fechaInicio= new Date();
		this.fechaFin= new Date();
		this.duracion= 0;	
	}

	public int getDuracion() {
		return duracion;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public void setFechaInicio(Date fechaInicio) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		int anyo18ago = gc.get(GregorianCalendar.YEAR);
		gc.set(GregorianCalendar.YEAR, anyo18ago);
		
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		int anyo18ago = gc.get(GregorianCalendar.YEAR);
		gc.set(GregorianCalendar.YEAR, anyo18ago);
		
		return fechaFin;
	}
	
	public String getNombreCurso() {
		return nombreCurso;
	}
	
	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	@Override
	public String toString() {
		return this.getCodigo()+ " " + this.getNombreCurso();
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
