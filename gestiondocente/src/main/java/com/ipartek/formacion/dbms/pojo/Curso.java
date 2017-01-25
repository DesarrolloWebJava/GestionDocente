package com.ipartek.formacion.dbms.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.FormAction;

import com.ipartek.formacion.service.exceptions.CursoServiceImpException;

/**
 * Clase Curso.
 * En ella definimos los campos que formaran los objetos de la clase.
 * @author Jon Ander Ochoa
 *
 */
public class Curso implements Comparable<Curso>, Serializable {

	//Serializable
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String nombre;
	private int duracion;
	private Date fInicio;
	private Date fFin;
	private List<Alumno> alumnos;
	private Profesor profesor;
	public static final int CODIGO_NULO = -1;

	/**
	 * Constructor de la clase curso
	 * Inicializa las variables
	 */
	public Curso() {
		super();
		this.codigo = CODIGO_NULO;
		this.nombre = "";
		this.duracion = 0;
		this.fInicio = new Date();
		this.fFin = new Date();
		alumnos = new ArrayList<Alumno>();
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

	public void setNombre(String nombre) throws CursoServiceImpException {
		if(nombre.length() > 8){
				this.nombre = nombre;
		}else{
			throw new CursoServiceImpException(
					CursoServiceImpException.COD_NOMBRE_CURSO_CORTO, 
					CursoServiceImpException.MSG_NOMBRE_CURSO_CORTO);
		}
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

	/*public List<Alumno> getAlumnos() {
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
	}*/
	
	@Override
	public boolean equals(Object obj) {
		boolean iguales = false;
		//Si obj es del tipo Curso...
		if(obj instanceof Curso){
			//Transforma el objeto a tipo curso y lo guarda en la variable
			Curso curso = (Curso) obj;
			//Si el codigo de esta clase es igual que el codigo del objeto...
			if(this.codigo == curso.getCodigo()){
				iguales = true;
			}
		}
		return iguales;
	}

	/**
	 * Que los cursos se ordenen por orden alfabetico
	 * Por que lo he decidido yo
	 */
	@Override
	public int compareTo(Curso o) {
		return this.nombre.compareToIgnoreCase(o.nombre);
	}

	@Override
	public String toString() {
		return "Curso [ codigo = " + codigo + ", nombre = " + nombre + ", duracion = " + duracion 
				+ ", Fecha Inicio = " + formatoFecha(fInicio) + ", Fecha Fin = " + formatoFecha(fFin) + " ]";
	}
	
	private String formatoFecha(Date fechaSinFormato){
		String fecha ="";
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(fechaSinFormato);
		fecha = gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
		return fecha;
	}
}
