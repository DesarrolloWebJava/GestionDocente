package com.ipartek.formacion.dbms.pojo;

import java.util.Date;
import java.util.GregorianCalendar;

import com.ipartek.formacion.dbms.pojo.exceptions.CursoException;

public class Curso {

	protected int codigo;
	private String nombre;
	private int duracion;
	private Date fechaInicio;
	private Date fechaFin;
	public static final int CODIGO_NULO = -1;
	
	public Curso(){
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
		//if(nombre.length() < 8){
		//	throw new CursoException(CursoException.COD_LONGITUD_NOMBRE,CursoException.MSG_LONGITUD_NOMBRE);
		//}
		this.nombre = nombre;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) throws CursoException {
		//if(duracion<1){
			//throw new CursoException(CursoException.COD_DURACION_ERROR,CursoException.MSG_DURACION_ERROR);
		//}
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
		//GregorianCalendar gc = new GregorianCalendar();
		//gc.setTime(new Date());
		//int fechoy = gc.get(GregorianCalendar.DATE);
		///gc.set(GregorianCalendar.DATE, fechoy);
		//if(gc.getTime().after(fechaFin)){
		//	throw new CursoException(CursoException.COD_FECFIN_ERROR,CursoException.MSG_FECFIN_ERROR);
		//}
		this.fechaFin = fechaFin;
	}

	
	
}
