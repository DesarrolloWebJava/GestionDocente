package com.ipartek.formacion.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class Util {

	private Util() {
	}

	public static boolean validarDni(String dni) {
		return true;
	}
	
	public static String fechaFormateada(Date fecha){
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    return sdf.format(fecha);
	} 
}
