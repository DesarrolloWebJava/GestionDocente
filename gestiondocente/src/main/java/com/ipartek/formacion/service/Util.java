package com.ipartek.formacion.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase util de metodos que se usan en todo el proyecto
 */
public final class Util {

	private Util() {
	}
	/**
	 * Validar si es Dni.
	 * @param le pasamos un strin dni.
	 * @return
	 * 			boolean true.
	 */
	public static boolean validarDni(String dni) {
		return true;
	}
	
	/**
	 * metodo para parsear las fechas de string a date
	 * @param date
	 * @return devuelve una fecha con el formato que queramos
	 * @throws ParseException
	 */
	
	public static Date parseLatinDate(String date) throws ParseException {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.parse(date);
	}
	
}
