package com.ipartek.formacion.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	private Util() {
	}

	public static boolean validarDni(String dni) {
		return true;
	}
	public static Date parseLatinDate(String fecha) throws Exception{
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date dFecha=null;
		try {
			dFecha = dateFormat.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
		return dFecha;

	}
}
