package com.ipartek.formacion.dbms.pojo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ipartek.formacion.dbms.pojo.exceptions.CursoException;
import static com.ipartek.formacion.dbms.pojo.exceptions.CursoException.*;

public class Curso implements Comparable<Curso>, Serializable{

public static final int CODIGO_NULO = -1;
public static final int NUM_MIN_CARACTERES = 8;
public static final String PATRON_FECHA="dd/MM/yyyy";

private int codigo;
private String nombre;
private int duracion;
private Date fInicio;
private Date fFin;
private List<Alumno> alumnos;
private Profesor profesor;

public Curso() {
	super();
	this.codigo = CODIGO_NULO;
	this.nombre="";
	this.duracion=0;
	this.fInicio = new Date();
	this.fFin  = new Date();
	this.alumnos = null;
	this.profesor = null;
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

public void setNombre(String nombre) throws CursoException{
	if (nombre.length()< NUM_MIN_CARACTERES){
		throw new CursoException(COD_ERROR_LONGITUD,MSG_ERROR_LONGITUD);
	}
	
	this.nombre = nombre;
}

public int getDuracion() {
	return duracion;
}

public void setDuracion(int duracion)throws CursoException {
	
	if (duracion > 0)
	this.duracion = duracion;
	else
		throw new CursoException(COD_ERROR_DURACION,MSG_ERROR_DURACION);
	
}

public Date getfInicio() {
	return fInicio;
}

public void setfInicio(Date fInicio) throws CursoException {
	String fechaPattern = PATRON_FECHA;
    SimpleDateFormat dfmt = new SimpleDateFormat(fechaPattern);
    String fechaInicio = dfmt.format(fInicio);
    String fechaHoy    = dfmt.format(new Date());
   try {
	   Date fHoy = dfmt.parse(fechaHoy);
	   fInicio=dfmt.parse(fechaInicio);
   } catch (ParseException e) {
	   e.printStackTrace();
	   throw new CursoException(COD_ERROR_PARSEO_FECHA_INI,MSG_ERROR_PARSEO_FECHA_INI + e.getMessage());
	
   }
	this.fInicio = fInicio;
}

public Date getfFin() {
	return fFin;
}

public void setfFin(Date fFin) throws CursoException {
	if (fInicio == null) 
		fInicio = new Date();
	
	if (fFin.getTime() < fInicio.getTime())
		throw new CursoException(COD_ERROR_FECHA_FIN,MSG_ERROR_FECHA_FIN);
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
	String fechaInicio="";
	String fechaFin="";
	fechaInicio = (new SimpleDateFormat(PATRON_FECHA)).format(this.getfInicio());
	fechaFin = (new SimpleDateFormat(PATRON_FECHA)).format(this.getfFin());
	return this.getCodigo() + " " +  this.getNombre() + " " + this.getDuracion()+ " " + fechaInicio + " " + fechaFin;
	
}

@Override
public int compareTo(Curso o) {
	return this.codigo - o.getCodigo();
}

@Override
public boolean equals(Object obj) {
boolean resultado = false;
if (obj != null)
{
	if (this == obj)
		resultado = true;
	else
	{
	  if (this.codigo == ((Curso)obj).getCodigo())
		  resultado = true;
	}
	
}
	return resultado;
}











}
