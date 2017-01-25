package com.ipartek.formacion.dbms.pojo;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

public class Curso implements Comparable<Curso>{
private int codigo;
private String nombre;
private int duracion;
private Date fInicio;
private Date fFin;
private List<Alumno> alumnos;
private Map<Integer,Profesor> profesores;
private String pattern = "dd/MM/yyyy";
private SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
public static final int CODIGO_NULO = -1;

public Curso() {
	super();
	this.codigo=-1;
	this.nombre="";
	this.duracion=0;
	this.fInicio=new Date();
	this.fFin=new Date();
	this.alumnos=null;
	this.profesores=null;
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

public void setNombre(String nombre) throws Exception {
	if(nombre.length()<8){
		throw new Exception("El nombre debe tener al menos 8 caracteres");
	}
		
	this.nombre = nombre;
}

public int getDuracion() {

	return duracion;
}

public void setDuracion(int duracion) throws Exception {
	if(duracion<0){
		throw new Exception("El número de horas debe ser mayor que 0");
	}
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

public void setfFin(Date fFin) throws Exception {
	GregorianCalendar gc = new GregorianCalendar();
	gc.setTime(new Date());
	
	if(!fFin.after(gc.getTime())){
		throw new Exception("La fecha fin debe ser posterior a la fecha de hoy.");
	}
	this.fFin = fFin;
}

public List<Alumno> getAlumnos() {
	return alumnos;
}

public void setAlumnos(List<Alumno> alumnos) {
	this.alumnos = alumnos;
}

public Map<Integer, Profesor> getProfesores() {
	return profesores;
}

public void setProfesores(Map<Integer, Profesor> profesores) {
	this.profesores = profesores;
}

@Override
public String toString() {
	return "Curso [codigo=" + codigo+ ", nombre=" + nombre + ", duracion=" + duracion + ", fInicio=" + dateFormat.format(fInicio)
			+ ", fFin=" + dateFormat.format(fFin) + "]";
}

@Override
public boolean equals(Object obj) {
	boolean iguales=false;
	if(obj instanceof Curso ){
		Curso curso=(Curso) obj;
		if(this.codigo==curso.getCodigo()){
			iguales=true;
		}
	}
	return iguales;
}

@Override
public int compareTo(Curso o) {//este se ejecuta con sort(null)
	
	return this.nombre.compareToIgnoreCase(o.getNombre());
}




}
