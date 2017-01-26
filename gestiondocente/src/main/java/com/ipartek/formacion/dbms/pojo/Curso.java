package com.ipartek.formacion.dbms.pojo;

import java.util.Date;
import java.util.GregorianCalendar;

import com.ipartek.formacion.dbms.pojo.exceptions.CursoException;

public class Curso {
	
	/**
	 * 
	 */
	public static final int CODIGO_NULO = -1;
	private int codigo;
	private String nombre;
	private int duracion;
	private Date fInicio;
	private Date fFinal;
	
	public Curso(){
		
		this.codigo = CODIGO_NULO;
		this.nombre = "";
		this.duracion = 0;
		this.fInicio = new Date();
		this.fFinal = new Date();
		
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
		if (nombre.length()>8){
			throw new CursoException(CursoException.COD_NOMBRE_ERROR, CursoException.MSG_NOMBRE_ERROR);
		}
		
		this.nombre=nombre;
	}

	public int getDuracion() {
	
		return duracion;
		
	}

	public void setDuracion(int duracion) throws CursoException {
		if (duracion < 1){
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
		int fechahoy = gc.get(GregorianCalendar.DATE);
		gc.set(GregorianCalendar.DATE, fechahoy);
		
		if (gc.getTime().before(fFinal)){
			
			throw new CursoException(CursoException.COD_FFINAL_ERROR, CursoException.MSG_FFINAL_ERROR);
			
		}
		
		
		this.fFinal = fFinal;
	}

	@Override
	public String toString() {
		return "Curso: " + nombre + ", " + duracion + ", " + fInicio
				+ ", " + fFinal + "]";
	}


	
	
	
}
