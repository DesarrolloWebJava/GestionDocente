package com.ipartek.formacion.dbms.pojo;

import java.util.Comparator;

public class CursoDuracionComparator implements Comparator<Curso> {

	@Override
	public int compare(Curso o1, Curso o2) {
		Integer duracion1 = o1.getDuracion();
		Integer duracion2 = o2.getDuracion();
		int number = duracion1.compareTo(duracion2) ;
		/* Aqui iria la ordenacion de segundo nivel 
		if (number==0){		
		}*/
		return number;
	}

}
