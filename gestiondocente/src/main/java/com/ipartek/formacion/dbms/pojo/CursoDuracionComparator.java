/* Paquete donde se guardan las entidades. */
package com.ipartek.formacion.dbms.pojo;

import java.util.Comparator;

/**
 * @author Raúl de Roba
 * 
 * Clase que implementa el metodo 'compare' ordenado por curso.
 *
 */
public class CursoDuracionComparator implements Comparator<Curso>{
	@Override
	/* Comparado por duración.*/
	public int compare(Curso o1, Curso o2) {
		/* Se devuelve la comparación de las duraciones de los objetos recibidos.
		 * El metodo compareTo solo está disponible para clases y no tipo primitivos.*/		
		return ((Integer) o1.getDuracion()).compareTo((Integer) o2.getDuracion());
	}

}
