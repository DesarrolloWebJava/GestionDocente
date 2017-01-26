package com.ipartek.formacion.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Util {

	private Util() {
	}

	public static boolean validarDni(String dni) {
		return true;
	}
	
	/**
	 * Cambia la fecha de String a Date
	 * @param fecha Recoge una fecha formateada dd/MM/yyyy 
	 * @return Devuelve una Date
	 * @throws ParseException
	 */
	public static Date parseLatinDate(String fecha) throws ParseException{
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat fechaFormato = new SimpleDateFormat(pattern);
		return fechaFormato.parse(fecha);
	}
	
	/**
	 * Cambia la fecha de Date a String
	 * @param fechaSinFormato Introduce una fecha Date
	 * @return Devuelve una fecha formateada dd/MM/yyyy
	 */
	public static String formatoFecha(Date fechaSinFormato){
		String fecha ="";
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(fechaSinFormato);
		fecha = gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
		return fecha;
	}
}
