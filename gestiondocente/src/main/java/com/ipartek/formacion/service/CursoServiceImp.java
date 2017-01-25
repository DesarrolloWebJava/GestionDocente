/* Paquete donde se guardan las clase de las operaciones con entidades. */
package com.ipartek.formacion.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.dbms.pojo.exceptions.CursoException;
import com.ipartek.formacion.service.exceptions.CursoServiceImpException;

/**
 *  @author Raúl de Roba 24/01/17 (Añadido de comentarios).
 *  
 * <div>
 * 	<p> * Esta clase se va encargar de gestionar las operaciones de CRUD de Alumno.</p>
 * 	<ul>
	 * <li>C: Create : Crea un Alumno.</li>
	 * <li>R: Read : Lee un Alumno. Con los metodos GetAll y GetById. </li>
	 * <li>D: Delete : Borra un Alumno.</li>
	 * <li>U: Update : Modifica un Alumno.</li>
 * 	</ul>
 * </div>
 */
public class CursoServiceImp implements CursoService{

	/* Se declara la lista para trabajar con la relación de cursos.*/
	private List<Curso> cursos;
	/* Se declara la variable estatica autoincremental 
	 * para asignar los codigos de curso. */
	private static int i = 0;
	
	
	/* Constructor sin parametros.*/
	public CursoServiceImp() {
		/* Se llama al constructor del padre.*/
		super();
		/* Se crea una instancia de lsita para guardar (y trabajar) con los cursos.*/
		cursos = new ArrayList<Curso>();
		/* Metodo que inicializa los valores de prueba de los curso.  
	     * (Cualquier metodo llamado desde un constructor ha de ser final).*/
		init();
	}

	
	/* Metodo que inicializa los valores de prueba de los alumnos.
	 * (Cualquier metodo llamado desde un constructor ha de ser final).*/
	private final void init() {
		/* Se instancia un objeto 'Alumno' con el que trabajar.*/
		Curso curso = new Curso();
		/* Se crea una estructura que capturara los errores en la creación del alumno.*/
		try {
			/* Se asignan los atributos del curso.*/
			curso.setNombre("Curso Inicial");
			curso.setDuracion(500);
			
			/* Se declara un calendario gregoriano para sumar un día a la fecha actual.
			 * (Asignar la fecha actual provocaría una excepción de curso).*/
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(new Date());
			/* Se asigna un día a la fecha actual para esquivar la excepción de curso.*/
			gc.set(GregorianCalendar.DAY_OF_MONTH,gc.get(GregorianCalendar.DAY_OF_MONTH)+1);
			/* Se asign la fecha con un día mayor a la fecha de sistema.*/
			curso.setFinicio(gc.getTime());
			curso.setFfin(gc.getTime());
			/* Se llama al metodo que crea el curso.*/	
			create(curso);
		/* Se captura la posible excepción en la asignación de los atributos del curso. */
		} catch (CursoException e) {
		}
	}

	/* Metodo que crea los Cursos.	  
    * (Cualquier metodo llamado desde un constructor o metodo que se llama desde un metodo del constructor ha de ser final).*/
	public final Curso create(Curso curso) {
		/* Se asigna el codigo de la variable autoincremental al 
		 * curso pasado por parametros.*/
		curso.setCodigo(i);
		/* Se incrementa el contador autoincremental.*/
		i++;
		/* Se añade el curso pasado por parametro a la lista de cursos.*/
		cursos.add(curso);
		/* Se devuelve el curso recibido por parametro.*/
		return curso;
	}

	/* Metodo que devuelve la lista de los cursos.*/
	public List<Curso> getAll() {
		/* Devuelve la lista de cursos de la clase.*/
		return cursos;
	}

	/* Metodo que devuelve un Curso en base al codigo pasado por parametro.*/
	public Curso getById(int codigo) {
		/* Se declara e inicializa un objeto Curso.*/
		Curso curso = null;
		/* Se inicializa la posicion del curso a localizar.*/
		int posicion = -1;
		/* Se crea una estructura que capturara los errores en la captura del curso.*/
		try {
			/* Se llama al metodo que busca el curso en la lista,
			 * devolviendo su posicion.*/
			posicion = buscarCurso(codigo);
			/* Se recoge el curso situado en la posición obtenida.*/
			curso = cursos.get(posicion);
		/* Se captura las excepciones de tipo 'CursoServiceImpException'.*/		
		} catch (CursoServiceImpException e) {
			/* Se instancia el curso para inicianizarlo.*/
			curso = new Curso();
		}
		/* Se devuelve el curso obtenido.*/
		return curso;
	}
	
	/* Metodo que borra un curso de la lista.*/
	public void delete(int codigo) {
		/* Se inicializa la posicion del curso a localizar.*/
		int posicion = -1;
		/* Se crea una estructura que capturara los errores en la captura del curso.*/
		try {
			/* Se llama al metodo que busca el curso en la lista,
			 * devolviendo su posicion.*/			
			posicion = buscarCurso(codigo);
			/* Se borra el curso situado en la posición obtenida.*/
			cursos.remove(posicion);
		} catch (CursoServiceImpException e) {
		}
	}
	
	/* Metodo que actualiza los valores de un curso.*/
	public Curso update(Curso curso) {
		/* Se inicializa la posicion del curso a localizar.*/
		int posicion = -1;
		/* Se crea una estructura que capturara los errores en la captura del curso.*/
		try {
			/* Se llama al metodo que busca el curso en la lista,
			 * devolviendo su posicion.*/	
			posicion = buscarCurso(curso.getCodigo());
			/* Se actuliza el curso situado en la posición obtenida.*/
			cursos.set(posicion, curso);
		} catch (CursoServiceImpException e) {
		}
		/* Se devuelve el curso actualizado.*/
		return curso;

	}

	/* Metodo que busca un curso en la lista en base a un codigo pasado por parametro.*/
	private int buscarCurso(int codigo) throws CursoServiceImpException {
		/* Se inicializan los contadores a utilizar para recorrer la lista.*/
		int i = 0, posicion = -1;
		/* Se inicializa la variable (flag) que controla si se ha encontrado el curso.*/
		boolean encontrado = false;
		/* Bucle que comprueba si se ha encontrado el curso o 
		 * se ha recorrido toda la lista.*/
		while (encontrado == false && i < cursos.size()) {
			/* Se compara el codigo del curso actual de la lista con 
			 * el codigo pasado por parametro. */
			if (cursos.get(i).getCodigo() == codigo) {
				/* Se activa la variable (flag) que controla si se ha encontrado 
				 * el curso. */
				encontrado = true;
				/* Se recoge la posicion del curso encontrado.*/
				posicion = i;
			}
			/* Se incrementa el contador que recorre la lista.*/
			i++;
		}

		/* Se comprueba si la posicion es el -1 inicial,
		 * señal de que no se ha encontrado el curso. */
		if (posicion == -1) {
			/* Se lanza una excepcion AlumnoServiceImpException. */
			throw new CursoServiceImpException(
					            CursoServiceImpException.COD_CURSO_NO_ENCONTRADO,
					            CursoServiceImpException.MSG_CURSO_NO_ENCONTRADO);
		}
		/* Se devuelve la posicion del curso encontrado.*/
		return posicion;
	}

	
}