/* Paquete donde se guardan las entidades. */
package com.ipartek.formacion.dbms.pojo;

import java.io.Serializable;

/**
 * 
 * @author Urko Villanueva
 * @author Raúl de Roba 17/01/17 (Añadido de comentarios.)
 * 
 * <p>Clase de la entidad Alumno (Heredada de la clase persona).</p>
 *
 */
public class Alumno extends Persona implements Comparable<Alumno>, Serializable {

	/* Constante serial de la clase.*/
	private static final long serialVersionUID = -6698866485450376235L;
	/* Constate para valores nulos. Se asigna -1 asumiendo que no se ha introducido 
	 * valor reseñable.ningún*/
	public static final int CODIGO_NULO = -1;
	/* Atributo identificativo de la 'Alumno'. */
	private int codigo;
	/* Atributo Activo */
	private boolean activo;
	/* Atributo nº de Hermanos de la 'Alumno'.*/
	private int nHermanos;

	/* Constructor sin parametros. */ 
	public Alumno() {
		/* Se llama al constructor padre.*/
		super();
		/* Se asigna el valor nulo. (Recogido de la constante de clase.)*/
		this.codigo = CODIGO_NULO;
		/* Se activa el 'Alumno'. */
		this.activo = true;
		/* Se inicializa el numero de hermanos a 0.*/
		this.nHermanos = 0;

	}

	/* Getter de codigo.*/
	public int getCodigo() {
		/* Se devuelve el 'codigo' de la 'Alumno'.*/
		return codigo;
	}

	/* Setter del codigo. */
	public void setCodigo(int codigo) {
		/* Se asigna el 'codigo' a la clase.*/
		this.codigo = codigo;
	}

	@Override
	/* Metodo que devuelve una versión (personalizada) de la clase como String.*/
	public String toString() {
		/* Se llama a la clase del padre que conviente la clase a cadena de caracteres.
		 * (Se devuelve un String con el codigo, apellido , el nombre y D.N.I.)
		 * y el nhermanos de la clase.*/
		return super.toString() + this.nHermanos;
	}

	/**
	 * @return activo
	 * Metodo que devuelve si la persona está activa.
	 */
	public boolean isActivo() {
		/* Se devuelve el atributo 'activo' de la 'Alumno'.*/
		return activo;
	}

	/* Setter del atributo activo. */	
	public void setActivo(boolean activo) {
		/* Se asigna el atributo 'activo' de la clase.*/
		this.activo = activo;
	}

	/* getter del nº de hermanos. */
	public int getnHermanos() {
		/* Se devuelve el atributo 'nhermano' de la clase.*/
		return nHermanos;
	}

	/* setter del nº de hermanos. */
	public void setnHermanos(int nHermanos) {
		/* Se asigna el atributo 'nhermanos' de la clase.*/
		this.nHermanos = nHermanos;
	}

	/* Metodo que es requerido en el ordenamiento de List o Array. */
	@Override
	public int compareTo(Alumno o) {
		/* Devuelve la comparación entre el objeto pasado por parametro
		 * y la propia clase.
		 * Devolverá -1 si es menor,0 si es igual o 1 si es mayor.*/
		return this.getApellidos().compareToIgnoreCase(o.getApellidos());
	}

	/* Metodo que compara la igualdad de la clase y un objeto pasado por parametro. */
	@Override
	public boolean equals(Object obj) {
		/* Se inicializa el reultado a 'falso'.*/
		boolean iguales = false;
		/* Se comprueba que el objeto pasado por parametro sea un 'Alumno'*/
		if (obj instanceof Alumno) {
			/* Se compara los codigos del objeto pasado por parametro y 
			 * la clase actual.*/
			if (this.codigo == ((Alumno) obj).getCodigo()) {
				/* Se asigna un 'true' al resultado.*/
				iguales = true;
			}
		}
		/* Se devuelve el resultado.*/
		return iguales;
	}

}
