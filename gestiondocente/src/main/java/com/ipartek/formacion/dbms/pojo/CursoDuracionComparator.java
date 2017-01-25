package com.ipartek.formacion.dbms.pojo;

import java.util.Comparator;

public class CursoDuracionComparator implements Comparator<Curso>{


	@Override
	public int compare(Curso o1, Curso o2) {
		Integer dur1 = o1.getDuracion();
		Integer dur2 = o2.getDuracion();
		int number = dur1.compareTo(dur2);
		
		return number;
	}

}
