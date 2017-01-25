/* Paquete donde se guardan las entidades. */
package com.ipartek.formacion.dbms.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ipartek.formacion.dbms.pojo.exceptions.CursoException;
import com.ipartek.formacion.dbms.pojo.exceptions.PersonaException;

/**
 * 
 * @author Raúl de Roba 24/01/17.
 * 
 * <p>Clase de la entidad Curso. </p>
 *
 */
public class Curso implements Comparable<Curso>,Serializable{
	/* Constante serial de la clase.*/
	private static final long serialVersionUID = 3145347907233739262L;
	/* Constate para valores nulos. Se asigna -1 asumiendo que no se ha introducido 
	 * valor reseñable.ningún*/
	public static final int CODIGO_NULO = -1;
	/* Atributo identificativo de la 'Curso'. */
	private int codigo;
	/* Atributos de la clase. */
	private String nombre;
	private int duracion;
	private Date finicio;
	private Date ffin;
	private List<Alumno> alumnos;
	private Profesor profesor;
	
	
	/* Constructor sin parametros. */ 
	public Curso() {
		/* Se llama al constructor del padre.*/
		super();
		/* Se inicializan el codigo de la clase según la constante CODIGO_NULO. */
		this.codigo = CODIGO_NULO;
		/* Se inicializan el atributo nombre a blanco. */
		this.nombre = "";
		/* Se inicializan el duracion a 0 horas.*/
		this.duracion = 0;
		/* Se inicializan las fechas según la fecha actual.*/
		this.finicio = new Date();
		this.ffin = new Date();
		/* Se instancia los objetos para los atributos Alumno y profesor. */
		this.alumnos = new ArrayList<Alumno>();
		this.profesor = new Profesor();
	}
	
	public Curso(int codigo, String nombre, int duracion, Date finicio, Date ffin, 
			     List<Alumno> alumnos, Profesor profesor) {
		/* Se llama al constructor del padre.*/
		super();
		/* Se asignan los atributos de la clase con los parametros recibidos.*/
		this.codigo = codigo;
		this.nombre = nombre;
		this.duracion = duracion;
		this.finicio = finicio;
		this.ffin = ffin;
		this.alumnos = alumnos;
		this.profesor = profesor;
	}

	/**
	 * @return codigo 
	 * Se devuelve el atributo codigo. 
	 */
	public int getCodigo() {
		/* Se devuelve el atributo codigo.*/
		return this.codigo;
	}

	/**
	 * @param codigo 
	 * Se asigna el atributo codigo pasado por parametro.
	 */
	public void setCodigo(int codigo) {
		/* Se asigna el atributo codigo pasado por parametro.*/
		this.codigo = codigo;
	}

	/**
	 * @return nombre 
	 * Se devuelve el atributo nombre. 
	 */
	public String getNombre() {
		/* Se devuelve el atributo nombre.*/
		return this.nombre;
	}

	/**
	 * @param nombre
	 * @throws CursoException 
	 * Se asigna el atributo nombre pasado por parametro.
	 */
	public void setNombre(String nombre) throws CursoException {
		/* Se comprueba que el nombre recibido tenga más de 8 caracteres.*/
		if (nombre.length()>8){
			/* Se asigna el atributo nombre pasado por parametro.*/
			this.nombre = nombre;
		} else {
			/* Se lanza una excepcion de tipo curso.*/
			throw new CursoException(CursoException.COD_NOMBRE_ERROR, 
					                 CursoException.MSG_NOMBRE_ERROR);
		}

	}

	/**
	 * @return duracion 
	 * Se devuelve el atributo duracion. 
	 */
	public int getDuracion() {
		/* Se devuelve el atributo duracion.*/
		return this.duracion;
	}

	/**
	 * @param duracion 
	 * @throws CursoException 
	 * Se asigna el atributo duracion pasado por parametro.
	 */
	public void setDuracion(int duracion) throws CursoException {
		/* Se comprueba que la duración del curso sea superior a 0 horas.*/
		if (duracion > 0){
			/* Se asigna el atributo duracion pasado por parametro.*/
			this.duracion = duracion;			
		} else {
			/* Se lanza una excepcion de tipo curso.*/
			throw new CursoException(CursoException.COD_DURACION_ERROR, 
					                 CursoException.MSG_DURACION_ERROR);
		}

	}

	/**
	 * @return finicio 
	 * Se devuelve el atributo finicio. 
	 */
	public Date getFinicio() {
		/* Se devuelve el atributo finicio.*/
		return this.finicio;
	}

	/**
	 * @param finicio 
	 * @throws CursoException 
	 * Se asigna el atributo finicio pasado por parametro.	 * 
	 */
	public void setFinicio(Date finicio) throws CursoException {
		/* Se crean las clases gregorianas que se usaran para comparar la fecha 
		 * pasada por parametro y la fecha actual.*/
		GregorianCalendar gc=null,hoy=null;
		/* Se instancia el calendario gregoriano que contendrá la fecha 
		 * pasada por parametro. */
		gc = new GregorianCalendar();
		/* Se asigna la calendario gregoriano */
		gc.setTime(finicio);

		/* Se comprueba si la fecha es mayor que la fecha actual.*/
		if (gc.getTime().after(new Date())){			
			/* Se asigna el atributo finicio pasado por parametro.*/
			this.finicio = finicio;			
		}else{
			/* Se lanza una excepcion de tipo curso.*/
			throw new CursoException(CursoException.COD_FECHAINICIO_HOY_ERROR, 
					                 CursoException.MSG_FECHAINICIO_HOY_ERROR);	
		} 

	}

	/**
	 * @return ffin 
	 * Se devuelve el atributo ffin. 
	 */
	public Date getFfin() {
		/* Se devuelve el atributo ffin.*/
		return this.ffin;
	}

	/**
	 * @param ffin 
	 * @throws CursoException 
	 * Se asigna el atributo ffin pasado por parametro.
	 */
	public void setFfin(Date ffin) throws CursoException {
		/* Se crean las clases gregorianas que se usaran para comparar la fecha 
		 * pasada por parametro y la fecha actual.*/
		GregorianCalendar gc=null,hoy=null;
		/* Se instancia el calendario gregoriano que contendrá la fecha 
		 * pasada por parametro. */
		gc = new GregorianCalendar();
		/* Se asigna la calendario gregoriano */
		gc.setTime(ffin);

		/* Se comprueba si la fecha es mayor que la fecha actual.*/
		if (gc.getTime().after(new Date())){			
			/* Se asigna el atributo finicio pasado por parametro.*/
			this.ffin = ffin;			
		}else{
			/* Se lanza una excepcion de tipo curso.*/
			throw new CursoException(CursoException.COD_FECHAINICIO_HOY_ERROR, 
					                 CursoException.MSG_FECHAINICIO_HOY_ERROR);	
		} 

	}

	/**
	 * @return alumnos 
	 * Se devuelve el atributo alumnos. 
	 */
	public List<Alumno> getAlumnos() {
		/* Se devuelve el atributo alumnos.*/
		return this.alumnos;
	}

	/**
	 * @param alumnos 
	 * Se asigna el atributo alumnos pasado por parametro.
	 */
	public void setAlumnos(List<Alumno> alumnos) {
		/* Se asigna el atributo alumnos pasado por parametro.*/
		this.alumnos = alumnos;
	}

	/**
	 * @return profesor 
	 * Se devuelve el atributo profesor. 
	 */
	public Profesor getProfesor() {
		/* Se devuelve el atributo profesor.*/
		return this.profesor;
	}

	/**
	 * @param profesor 
	 * Se asigna el atributo profesor pasado por parametro.
	 */
	public void setProfesor(Profesor profesor) {
		/* Se asigna el atributo profesor pasado por parametro.*/
		this.profesor = profesor;
	}

	/* Metodo que devuelve la clase convertida en un String.  */
	@Override
	public String toString() {
		/* Se devuelve un String con los atributos de la clase.*/
		//TODO Devolver fechas.
		return this.nombre + " " + this.duracion;
	}
	
	/* Metodo que es requerido en el ordenamiento de List o Array 
	 * para la ordenación natural implementado por Comparable. */
	@Override
	public int compareTo(Curso o) {
		/* Devuelve la comparación entre el objeto pasado por parametro
		 * y la propia clase.
		 * Devolverá -1 si es menor,0 si es igual o 1 si es mayor.*/
		return this.getNombre().compareToIgnoreCase(o.getNombre());
	}
	
	/* Metodo que compara la igualdad de la clase y un objeto pasado por parametro. */
	@Override
	public boolean equals(Object obj) {
		/* Se inicializa el reultado a 'falso'.*/
		boolean iguales = false;
		/* Se comprueba que el objeto pasado por parametro sea un 'Alumno'*/
		if (obj instanceof Curso) {
			/* Se compara los codigos del objeto pasado por parametro y 
			 * la clase actual.*/
			if (this.codigo == ((Curso) obj).getCodigo()) {
				/* Se asigna un 'true' al resultado.*/
				iguales = true;
			}
		}
		/* Se devuelve el resultado.*/
		return iguales;
	}

}
