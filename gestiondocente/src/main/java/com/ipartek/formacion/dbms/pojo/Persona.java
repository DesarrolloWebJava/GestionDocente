package com.ipartek.formacion.dbms.pojo;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ipartek.formacion.dbms.pojo.exceptions.PersonaException;
import com.ipartek.formacion.service.Util;

public class Persona {

	protected int codigo;
	private String dni;
	private String nombre;
	private String apellidos;
	private Date fNacimiento;
	private String email;
	private String direccion;

	public Persona() {
		super();
		this.email = "";
		this.direccion = "";
		this.nombre = "";
		this.apellidos = "";
		this.email = "";
		this.direccion = "";
		this.fNacimiento = new Date();
	}

	public Persona(String email, String direccion) {
		super();
		this.email = email;
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) throws PersonaException {
		final String regex = "\\d{8}[A-Za-z]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(dni);
		if (!matcher.find() && !Util.validarDni(dni)) {
			throw new PersonaException(PersonaException.COD_DNI_ERROR, PersonaException.MSG_DNI_ERROR);
		}
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) throws PersonaException {
		if (nombre.length() < 3) {
			throw new PersonaException(PersonaException.COD_LONGITUD_NOMBRE, PersonaException.MSG_LONGITUD_NOMBRE);
		}
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getfNacimiento() {
		return fNacimiento;
	}

	public void setfNacimiento(Date fNacimiento) throws PersonaException {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		int anyo18ago = gc.get(GregorianCalendar.YEAR) - 18;// 1999
		gc.set(GregorianCalendar.YEAR, anyo18ago);

		if (gc.getTime().before(fNacimiento)) {
			throw new PersonaException(PersonaException.COD_EDAD_ERROR, PersonaException.MSG_EDAD_ERROR);
		}

		this.fNacimiento = fNacimiento;
	}

}
