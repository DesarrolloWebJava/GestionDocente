/* Paquete donde se guardan las clase de las operaciones con entidades. */
package com.ipartek.formacion.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.dbms.pojo.exceptions.PersonaException;
import com.ipartek.formacion.service.exceptions.AlumnoServiceImpException;

/**
 *  @author Urko Villanueva
 *  @author Raúl de Roba 17/01/17 (Añadido de comentarios.)
 *  
 * <div>
 * <p>
 * Esta clase se va encargar de gestionar las operaciones de CRUD de Alumno
 * </p>
 * <ul>
 * <li>C: Create</li>
 * <li>R: Read</li>
 * <li>D: Delete</li>
 * <li>U: Update</li>
 * </ul>
 * </div>
 */
public class AlumnoServiceImp implements AlumnoService{

	/* Se declara la lista para trabajar con la relación de alumnos.*/
	private List<Alumno> alumnos;
	/* Se declara la variable estatica autoincremental 
	 * para asignar los codigos de Alumnos. */
	private static int i = 0;
	
	
	/* Constructor sin parametros.*/
	public AlumnoServiceImp() {
		/* Se llama al constructor del padre.*/
		super();
		/* Se crea una instancia de lsita para guardar (y trabajar) con los alumnos.*/
		alumnos = new ArrayList<Alumno>();
		/* Metodo que inicializa los valores de prueba de los alumnos.*/
		init();
	}

	/* Metodo que inicializa los valores de prueba de los alumnos.*/
	private void init() {
		/* Se instancia un objeto 'Alumno' con el que trabajar.*/
		Alumno alumno = new Alumno();
		/* Se crea una estructura que capturara los errores en la creación del alumno.*/
		try {
			/* Se asignan los atributos nombre,apellido y D.N.i.*/
			alumno.setNombre("Sergio");
			alumno.setApellidos("Aparicio Vegas");
			alumno.setDni("44974398z");
			/* Se llama al metodo que crea el alumno.*/	
			create(alumno);
		/* Se captura la posible excepción en la asignación de los atributos del alumno. */
		} catch (PersonaException e) {
			/* Se muestra por consola el error generado.*/
			System.out.println(e.getMessage());
		}

		/* Se vuelve a instanciar el alumno.
		 * (De esta manera se limpian sus atributos al estado inicial).*/
		alumno = new Alumno();
		/* Se crea una estructura que capturara los errores en la creación del alumno.*/
		try {
			/* Se asignan los atributos nombre,apellido, D.N.I.*/
			alumno.setNombre("Maite");
			alumno.setApellidos("Monasterio Herrero");
			alumno.setDni("16071559x");
			/* Se declara una variable fecha con el valor 19/11/200. */
			String date = "19/11/2000";
			/* Se declara la expresión regular para dar formato a la fecha.*/
			String pattern = "dd/MM/yyyy";
			/* Se guarda la fecha formateada en una variable.*/
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			/* Se asigna la fecha formateada a la fecha de nacimiento de la clase.*/
			alumno.setfNacimiento(dateFormat.parse(date));
			/* Se llama al metodo que crea el alumno.*/	
			create(alumno);
		/* Se captura las excepciones de tipo 'PersonaException'.*/	
		} catch (PersonaException e) {
			/* Se muestra por consola el error generado.*/
			System.out.println(e.getMessage());
			//TODO
			e.printStackTrace();
		/*//TODO Se captura las excepciones de tipo 'ParseException'.*/	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			/* Se muestra por consola el error generado.*/
			System.out.println("Error no controlado" + e.getMessage());
		}

		/* Se vuelve a instgaciar el alumno.
		 * (De esta manera se limpian sus atributos al estado inicial).*/
		alumno = new Alumno();
		/* Se crea una estructura que capturara los errores en la creación del alumno.*/
		try {
			/* Se asignan los atributos nombre,apellido, D.N.I.*/
			alumno.setNombre("Jon");
			alumno.setApellidos("Martinez Perez");
			alumno.setDni("16071559x");
			/* Se llama al metodo que crea el alumno.*/	
			create(alumno);
		} catch (PersonaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* Metodo que crea los Alumnos.*/
	public Alumno create(Alumno alumno) {
		/* Se asigna el codigo de la variable autoincremental al 
		 * alumno pasado por parametros.*/
		alumno.setCodigo(i);
		/* Se incrementa el contador autoincremental.*/
		i++;
		/* Se añade el alumno pasado por parametro a la lista de alumnos.*/
		alumnos.add(alumno);
		/* Se devuelve el alumno recibido por parametro.*/
		return alumno;
	}

	/* Metodo que devuelve la lista de los alumnos.*/
	public List<Alumno> getAll() {
		/* Devuelve la lista de alumnos de la clase.*/
		return alumnos;
	}

	/* Metodo que devuelve un Alumno en base al codigo pasado por parametro.*/
	public Alumno getById(int codigo) {
		/* Se declara e inicializa un objeto Alumnos.*/
		Alumno alumno = null;
		/* Se inicializa la posicion del alumno a localizar.*/
		int posicion = -1;
		/* Se crea una estructura que capturara los errores en la captura del alumno.*/
		try {
			/* Se llama al metodo que busca el alumno en la lista,
			 * devolviendo su posicion.*/
			posicion = buscarAlumno(codigo);
			/* Se recoge el alumno situado en la posición obtenida.*/
			alumno = alumnos.get(posicion);
		/* Se captura las excepciones de tipo 'AlumnoServiceImpException'.*/		
		} catch (AlumnoServiceImpException e) {
			/* Se muestra por consola el error capturado.*/
			System.out.println(e.getMessage());
			/* Se instancia el alumno para inicianizarlo.*/
			alumno = new Alumno();
		}
		/* Se devuelve el alumno obtenido.*/
		return alumno;
	}
	
	/* Metodo que borra un alumno de la lista.*/
	public void delete(int codigo) {
		/* Se inicializa la posicion del alumno a localizar.*/
		int posicion = -1;
		/* Se crea una estructura que capturara los errores en la captura del alumno.*/
		try {
			/* Se llama al metodo que busca el alumno en la lista,
			 * devolviendo su posicion.*/			
			posicion = buscarAlumno(codigo);
			/* Se borra el alumno situado en la posición obtenida.*/
			alumnos.remove(posicion);
		} catch (AlumnoServiceImpException e) {
			/* Se muestra por consola el error capturado.*/
			System.out.println(e.getMessage());
		}
	}
	
	/* Metodo que actualiza los valores de un alumno.*/
	public Alumno update(Alumno alumno) {
		/* Se inicializa la posicion del alumno a localizar.*/
		int posicion = -1;
		/* Se crea una estructura que capturara los errores en la captura del alumno.*/
		try {
			/* Se llama al metodo que busca el alumno en la lista,
			 * devolviendo su posicion.*/	
			posicion = buscarAlumno(alumno.getCodigo());
			/* Se actuliza el alumno situado en la posición obtenida.*/
			alumnos.set(posicion, alumno);
		} catch (AlumnoServiceImpException e) {
			/* Se muestra por consola el error capturado.*/
			System.out.println(e.getMessage());
		}
		/* Se devuelve el alumno actualizado.*/
		return alumno;

	}

	/* Metodo que busca un alumno en la lista en base a un codigo pasado por parametro.*/
	private int buscarAlumno(int codigo) throws AlumnoServiceImpException {
		/* Se inicializan los contadores a utilizar para recorrer la lista.*/
		int i = 0, posicion = -1;
		/* Se inicializa la variable (flag) que controla si se ha encontrado el alumno.*/
		boolean encontrado = false;
		/* Bucle que comprueba si se ha encontrado el alumno o 
		 * se ha recorrido toda la lista.*/
		while (encontrado == false && i < alumnos.size()) {
			/* Se compara el codigo del alumno actual de la lista con 
			 * el codigo pasado porparametro. */
			if (alumnos.get(i).getCodigo() == codigo) {
				/* Se activa la variable (flag) que controla si se ha encontrado 
				 * el alumno. */
				encontrado = true;
				/* Se recoge la posicion del alumno encontrado.*/
				posicion = i;
			}
			/* Se incrementa el contador que recorre la lista.*/
			i++;
		}

		/* Se comprueba si la posicion es el -1 inicial,
		 * señal de que no se ha encontrado el alumno. */
		if (posicion == -1) {
			/* Se lanza una excepcion AlumnoServiceImpException. */
			throw new AlumnoServiceImpException(
						AlumnoServiceImpException.COD_ALUMNO_NO_ENCONTRADO,
						AlumnoServiceImpException.MSG_ALUMNO_NO_ENCONTRADO);
		}
		/* Se devuelve la posicion del alumno encontrado.*/
		return posicion;
	}

	
}
