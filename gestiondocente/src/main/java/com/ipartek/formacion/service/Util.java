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
	public static Date parseLatinDate(String date) throws ParseException {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.parse(date);
	}
}
