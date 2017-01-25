package com.ipartek.formacion.dbms.pojo;

import java.util.Comparator;

public class CursoFechaInicioComparator implements Comparator<Curso> {
	@Override
	public int compare(Curso o1, Curso o2) {

		return o1.getfInicio().compareTo(o2.getfInicio());
	}

}
