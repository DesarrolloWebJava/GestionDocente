/* Paquete donde se guardan las clase de las operaciones con entidades. */
package com.ipartek.formacion.service;

import java.util.HashMap;

import java.util.Map;


import com.ipartek.formacion.dbms.pojo.Profesor;

/** 
 * @author Urko Villanueva
 * @author Raúl de Roba 17/01/17 (Añadido de comentarios.)
 * 
 * <div>
 * 	<p> * Esta clase se va encargar de gestionar las operaciones de CRUD de Profesor.</p>
 * 	<ul>
	 * <li>C: Create : Crea un Profesor.</li>
	 * <li>R: Read : Lee un Profesor. Con los metodos GetAll y GetById. </li>
	 * <li>D: Delete : Borra un Profesor.</li>
	 * <li>U: Update : Modifica un Profesor.</li>
 * 	</ul>
 * 
 */ 
public class ProfesorServiceImp implements ProfesorService {

	/* Se declara la lista para trabajar con la relación de alumnos.*/
	Map<Integer, Profesor> profesores;
	/* Se declara la variable para asignar los codigos de Alumnos. */
	private static int contador;

	/* Constructor sin parametros.*/
	public ProfesorServiceImp() {
		/* Se llama al constructor del padre.*/
		super();
		/* Se crea una instancia de lsita para guardar (y trabajar) con los alumnos.*/
		profesores = new HashMap<Integer, Profesor>();
		/* Metodo que inicializa los valores de prueba de los alumnos.*/
		init();
	}
	
	/* Metodo que inicializa los valores de prueba de los alumnos.*/
	private void init() {
		/* Se inicizaliza el contador autoincremental que asigna el codigo de profesor.*/
		contador = 0;
		/* Se instancia un objeto 'Profesor' con el que trabajar.*/
		Profesor profesor = new Profesor();
		/* Se asigna el email del profesor.*/
		profesor.setEmail("uvillanueva@ipartek.com");
		/* Se llama al metodo que crea el profesor.*/	
		create(profesor);
		
		/* Se vuelve a instanciar el profesor.
		 * (De esta manera se limpian sus atributos al estado inicial).*/
		profesor = new Profesor();
		/* Se asigna el email del profesor.*/
		profesor.setEmail("auraga@ipartek.com");
		/* Se llama al metodo que crea el profesor.*/	
		create(profesor);
	}

	@Override
	/* Metodo que crea e introduce en la lista un profesor pasado por parametro.*/
	public Profesor create(Profesor profesor) {
		/* Se incrementa el contador de profesores.*/
		contador++;
		/* Se asigna el codigo autoincremental. */
		profesor.setCodigo(contador);
		/* Se añade el profesor a la lista.*/
		profesores.put(profesor.getCodigo(), profesor);
		/* Se devuelve el profesor pasado por parametro. */
		return profesor;
	}

	@Override
	/* Metodo que devuelve la lista de la clase.*/
	public Map<Integer, Profesor> getAll() {
		/* Se devuelve la lista de la clase. */
		return profesores;
	}

	@Override
	/* Metodo que devuelve un profesor en base al codigo pasado por parametro.*/
	public Profesor getById(int codigo) {
		/* Se declara e inicializa un objeto profesor.*/
		Profesor profesor = null;
		/* Se recoge el Profesor cuyo codigo sea el pasado por parametro.*/
		profesor = profesores.get(codigo);
		/* Se devuelve el profesor recogido.*/
		return profesor;
	}

	@Override
	/* Metodo que actualiza un profesor pasado por parametro.*/
	public Profesor update(Profesor profesor) {
		/* Se actualiza el profesor pasado por parametro.*/
		profesores.put(profesor.getCodigo(), profesor);
		/* Se devuelve el profesor.*/
		return profesor;
	}

	@Override
	/* Metodo que borra un profesor pasado por parametro.*/
	public void delete(int codigo) {
		/* Se borra el profesor pasado por parametro.*/
		profesores.remove(codigo);
	}

}
