/* Paquete donde se guardan las entidades. */
package com.ipartek.formacion.dbms.pojo;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ipartek.formacion.dbms.pojo.exceptions.PersonaException;
import com.ipartek.formacion.service.Util;
/**
 * 
 * @author Urko Villanueva
 * @author Raúl de Roba 17/01/17 (Añadido de comentarios.)
 * 
 * <p>Clase de la entidad Alumno (Heredada de la clase persona).</p>
 *
 */
public class Persona {
    /* Atributo identificativo de la 'Persona'. */
	protected int codigo;
	/* Atributos personales de la clase Persona. */
	private String dni;
	private String nombre;
	private String apellidos;
	private Date fNacimiento;
	private String email;
	private String direccion;

	/* Constructor sin parametro de la clase 'Pesona'.*/
	public Persona() {
		/* Se llama al constructor padre.*/
		super();
		/* Se inicializa el atributo 'email'. */
		this.email = "";
		/* Se inicializa el atributo 'dirección'. */
		this.direccion = "";
	}

	/* Constructor con los parametros email y dirección.*/
	public Persona(String email, String direccion) {
		/* Se llama al constructor padre.*/
		super();
		/* Se asigna el atributo 'email' pasado por parametro. */
		this.email = "";
		/* Se asigna el atributo 'dirección' pasado por parametro. */
		this.direccion = "";
	}

	/* Getter del atributo 'email'.*/
	public String getEmail() {
		/* Se devuelve el atributo 'email'.*/
		return email;
	}

	/* Setter del atributo 'email'.*/
	public void setEmail(String email) {
		/* Se asigna el 'email' pasado por parametro.*/
		this.email = email;
	}
    
	/* Getter del atributo 'direccion'.*/
	public String getDireccion() {
		/* Se devuelve el atributo 'direccion'.*/
		return direccion;
	}

	/* Setter del atributo 'direccion'.*/
	public void setDireccion(String direccion) {
		/* Se asigna el 'direccion' pasado por parametro.*/
		this.direccion = direccion;
	}
	
	/* Getter del atributo 'codigo'.*/
	public int getCodigo() {
		/* Se devuelve el atributo 'codigo'.*/
		return codigo;
	}

	/* Setter del atributo 'codigo'.*/
	public void setCodigo(int codigo) {
		/* Se asigna el 'codigo' pasado por parametro.*/
		this.codigo = codigo;
	}

	/* Getter del atributo 'dni'.*/
	public String getDni() {
		/* Se devuelve el atributo 'dni'.*/
		return dni;
	}

	/* Setter del atributo 'dni'.
	 * Se comprueba que el D.N.I. tenga un formato valido.*/
	public void setDni(String dni) throws PersonaException {
		/* Se declara la constante con la expresión regular que valida el D.N.I. */
		final String regex = "\\d{8}[A-Za-z]";
		/* Se declara un objeto Pattern asignandole la expresión regular sobre la que
		 * se volcará el D.N.I. a validar.*/
		Pattern pattern = Pattern.compile(regex);
		/* Se asigna el objeto Matcher asignandole la expresión regular con el D.N.I.*/
		Matcher matcher = pattern.matcher(dni);
		/* Se comprueba si la expresión regular sobre el D.N.I. es incorrecta.
		 * //TODO util*/
		if (!matcher.find() && !Util.validarDni(dni)) {
			/* Se envia una excepcion de tipo Persona.*/
			throw new PersonaException(PersonaException.COD_DNI_ERROR, 
					  PersonaException.MSG_DNI_ERROR);
		}
		/* Se asigna el D.N.I.*/
		this.dni = dni;
	}

	/* Getter del artibuto 'nombre'. */
	public String getNombre() {
		/* Se devuelve el atributo 'nombre'. */
		return nombre;
	}

	/* Setter del atributo 'nombre'. */
	public void setNombre(String nombre) throws PersonaException {
		/* Se comprueba si se ha pasado por parametro un nombre con 3 o más caracteres.
		 * No vamos a dejar introducir nombres de menos de 3 caracteres.*/
		if (nombre.length() < 3) {
			/* Se envia una excepcion de tipo Persona.*/
			throw new PersonaException(PersonaException.COD_LONGITUD_NOMBRE, 
					  PersonaException.MSG_LONGITUD_NOMBRE);
		}
		/* Se asigna el atributo 'nombre'.*/
		this.nombre = nombre;
	}

	/* Getter del atributo 'apellido'.*/
	public String getApellidos() {
		/* Se devuelve el atributo 'apellido'. */
		return apellidos;
	}

	/* Setter del atributo 'apellido'. */
	public void setApellidos(String apellidos) {
		/* Se asigna el atributo 'apellido'. */
		this.apellidos = apellidos;
	}

	/* Getter del atributo fecha de nacimiento. */
	public Date getfNacimiento() {
		/* Se devueve el atributo 'fnacimiento'.*/
		return fNacimiento;
	}

	/* Setter del atributo 'fnacimiento'. */
	public void setfNacimiento(Date fNacimiento) throws PersonaException {
		/* Se guarda una instancia del calendario gregoriano.*/
		GregorianCalendar gc = new GregorianCalendar();
		/* Se asigna la fecha actual en el calendario gregoriano guardado.*/
		gc.setTime(new Date());
		/* Se calcula el año restandole al año actual 18 años y guarda en una variable. */
		int anyo18ago = gc.get(GregorianCalendar.YEAR) - 18;
		/* Se asigna a la fecha actual (donde se había guardado la fecha actual), el año
		 * de hace 18 años.*/
		gc.set(GregorianCalendar.YEAR, anyo18ago);

		/* Se compara (con la sentencia 'before') si la fecha de hace 18 años es menor a la 
		 * fecha de nacimiento de la clase.En tal caso será una Alumno menor de edad. */
		if (gc.getTime().before(fNacimiento)) {
			/* Se envia una excepcion de tipo Persona.*/
			throw new PersonaException(PersonaException.COD_EDAD_ERROR, 
					  PersonaException.MSG_EDAD_ERROR);
		}
        /* Se asigna la fecha de naciemiento.*/
		this.fNacimiento = fNacimiento;
	}

}
