package com.ipartek.formacion.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ipartek.formacion.controller.Constantes;

public class Util {

	private Util() {
	}

	public static boolean validarDni(String dni) {
		return true;
	}
	
	public static final Date parseLatinDate(String date) throws ParseException
	{
		Date fecha;
		SimpleDateFormat dfmt = new SimpleDateFormat(Constantes.FECHA_PATRON);
		fecha = dfmt.parse(date);
		return fecha;
	}
}
