package com.ipartek.formacion.dbms.pojo;

import java.util.Date;
import java.util.List;

public class Curso {
	public static final int CODIGO_NULO = -1;
	
	private List<Alumno> listaAlumno;
	private Profesor profe;
	private int codigo;
	private String nombre;
	public List<Alumno> getListaAlumno() {
		return listaAlumno;
	}

	public void setListaAlumno(List<Alumno> listaAlumno) {
		this.listaAlumno = listaAlumno;
	}

	public Profesor getProfe() {
		return profe;
	}

	public void setProfe(Profesor profe) {
		this.profe = profe;
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

	private int duracion;
	private Date fInicio;
	private Date fFin;
	
	public Curso(List<Alumno> listaAlumno, Profesor profe, int codigo, String nombre, int duracion, Date fInicio,  Date fFin){
		this.listaAlumno=listaAlumno;
		this.profe=profe;
		this.codigo=codigo;
		this.nombre=nombre;
		this.duracion=duracion;
		this.fInicio=fInicio;
		this.fFin=fFin;
	}

	public Curso() {
		this.listaAlumno=null;
		this.profe=null;
		this.codigo=-1;
		this.nombre="";
		this.duracion=0;
		this.fInicio=null;
		this.fFin=null;
	}
	
	@Override
	public String toString() {
		return this.getCodigo() + " " + this.getNombre() + " " + this.getDuracion()+ " " + this.getfInicio()+ " " + this.getfFin();
	}

}
