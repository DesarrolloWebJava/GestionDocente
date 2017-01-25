package com.ipartek.formacion.dbms.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.ipartek.formacion.dbms.pojo.exceptions.CursoException;
import com.ipartek.formacion.dbms.pojo.exceptions.PersonaException;





public class Curso implements Comparable<Curso>, Serializable{
	
	
	private static final long serialVersionUID = -6698866455450376235L;
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
		this.alumnos = new ArrayList<Alumno>();
		this.profesor = new Profesor();
		
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
			throw new CursoException(CursoException.COD_NOMBRE_ERROR,CursoException.MSG_NOMBRE_ERROR);
		}
		
		this.nombre = nombre;
	}


	public int getDuracion() {
		return duracion;
	}




	public void setDuracion(int duracion) throws CursoException {
		
		if (duracion < 1) {
			throw new CursoException(CursoException.COD_DURACION_ERROR,CursoException.MSG_DURACION_ERROR);
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
	
	
	public void setfFin(Date fFin) throws CursoException {
		
		this.fFin = fFin;	
	}

/*
	public void setfFin(Date fFin) throws CursoException {

		GregorianCalendar gc = new GregorianCalendar();
		
		gc.setTime(new Date());
		//int anyo18ago = gc.get(GregorianCalendar.YEAR) - 18;// 1999
		
		if (gc.getTime().before(fFin)) {
			throw new CursoException(CursoException.COD_FFIN_NOMBRE, CursoException.MSG_FFIN_NOMBRE);
		}

		this.fFin = fFin;
	}

*/
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
		//return this.nombre.compareToIgnoreCase(o.nombre);
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
