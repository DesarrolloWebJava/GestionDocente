package com.ipartek.formacion.dbms.pojo;

import java.util.Comparator;

public class CursoNombreComparator implements Comparator<Curso> {

	@Override
	public int compare(Curso o1, Curso o2) {
		// TODO Auto-generated method stub

		return o1.getNombre().compareTo(o2.getNombre());
	}

}
