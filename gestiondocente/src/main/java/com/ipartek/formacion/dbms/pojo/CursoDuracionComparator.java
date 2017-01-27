package com.ipartek.formacion.dbms.pojo;

import java.util.Comparator;

public class CursoDuracionComparator implements Comparator<Curso> {

	@Override
	public int compare(Curso o1, Curso o2) {
		Integer dur1=o1.getCodigo();
		Integer dur2=o2.getCodigo();
		int num=dur1.compareTo(dur2);
		
		return num;
	}

}
