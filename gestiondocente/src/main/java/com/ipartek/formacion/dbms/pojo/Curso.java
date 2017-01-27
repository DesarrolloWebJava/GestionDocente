package com.ipartek.formacion.dbms.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.ipartek.formacion.dbms.pojo.exceptions.CursoException;

public class Curso implements Comparable<Curso>, Serializable{

	private static final long serialVersionUID = -6698866485450376235L;
	private int codigo;
	private String nombre;
	private int duracion;
	private Date fInicio;
	private Date fFin;
	private List<Alumno> alumnos;
	private Profesor profesor;
	public static final int CODIGO_NULO = -1;
	
	public Curso() {
		super();
		this.codigo=CODIGO_NULO;
		this.nombre="";
		this.duracion=0;
		this.fInicio=new Date();
		this.fFin=new Date();
		this.alumnos=new ArrayList<Alumno>();
		this.profesor=new Profesor();
		
		
	}

	/**
	 * @return the alumnos
	 */
	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	/**
	 * @param alumnos the alumnos to set
	 */
	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	/**
	 * @return the profesor
	 */
	public Profesor getProfesor() {
		return profesor;
	}

	/**
	 * @param profesor the profesor to set
	 */
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 * @throws CursoException 
	 */
	public void setNombre(String nombre) throws CursoException {
		if(nombre.length() < 8){
			throw new CursoException(CursoException.COD_NOMBRE_ERROR,CursoException.MSG_NOMBRE_ERROR);
		}
		this.nombre = nombre;
	}

	/**
	 * @return the duracion
	 */
	public int getDuracion() {
		return duracion;
	}

	/**
	 * @param duracion the duracion to set
	 * @throws CursoException 
	 */
	public void setDuracion(int duracion) throws CursoException {
	//	if(this.getDuracion()<0){
	//		throw new CursoException(CursoException.COD_DURACION_ERROR,CursoException.MSG_DURACION_ERROR);
	//	}
		this.duracion = duracion;
	}

	/**
	 * @return the fInicio
	 */
	public Date getfInicio() {
		return fInicio;
	}

	/**
	 * @param fInicio the fInicio to set
	 */
	public void setfInicio(Date fInicio) {
		this.fInicio = fInicio;
	}

	/**
	 * @return the fFin
	 */
	public Date getfFin() {
		return fFin;
	}

	/**
	 * @param fFin the fFin to set
	 * @throws CursoException 
	 */
	public void setfFin(Date fFin) throws CursoException {

	//	if(this.getfFin().after(new Date())){
	//		throw new CursoException(CursoException.COD_FFIN_ERROR,CursoException.MSG_FFIN_ERROR);
	//    }
		this.fFin = fFin;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String date="";
		String date2="";
		GregorianCalendar gc=new GregorianCalendar();
		gc.setTime(this.getfInicio());
		date=gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
		gc.setTime(this.getfFin());
		date2=gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);

		return this.getNombre() + " duracion=" + this.getDuracion() 
		+" Fecha de inicio=" + date
				+ " Fecha de fin=" + date2;
	}

	@Override
	public int compareTo(Curso o) {
	//	return this.getNombre().compareToIgnoreCase(o.getNombre());
		return this.nombre.compareToIgnoreCase(o.nombre);
	}
	
	public boolean equals(Object obj){
		boolean iguales=false;
		if(obj instanceof Curso){
			Curso cur=(Curso) obj;
			if(this.codigo==cur.getCodigo()){
				iguales=true;
			}
		}
		return iguales;
	}


	
	
}
