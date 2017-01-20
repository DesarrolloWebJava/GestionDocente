package com.ipartek.formacion.service;

import java.util.HashMap;
import java.util.Map;

import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.dbms.pojo.Profesor;
import com.ipartek.formacion.dbms.pojo.exceptions.PersonaException;
import com.ipartek.formacion.service.exceptions.AlumnoServiceImpException;
import com.ipartek.formacion.service.exceptions.ProfesorServiceImpExcepciones;

/**
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
 * 
 * @author va00
 *
 */

public class ProfesorServiceImp implements ProfesorService {

	Map<Integer, Profesor> profesores;
	private static int i = 0;

	public ProfesorServiceImp() {
		super();
		profesores = new HashMap<Integer, Profesor>();
		init();
	}

	private void init() {
		Profesor profesor = new Profesor();
		try {
			profesor.setNombre("Urko");
			profesor.setEmail("uvillanueva@ipartek.com");
			profesor.setDni("44974398z");
			create(profesor);	
		} catch (PersonaException e){
			e.printStackTrace();
		}
		
		profesor = new Profesor();
		try {
			profesor.setNombre("Alberto");
			profesor.setEmail("alberto.ie2cmfp@gmail.com");
			profesor.setDni("44974398z");
			create(profesor);	
		} catch (PersonaException e){
			e.printStackTrace();
		}
	}

	public Profesor create(Profesor profesor) {
		profesor.setCodigo(i);
		i++;
		profesores.put(profesor.getCodigo(), profesor);
		Integer numero = new Integer(5);
		int n = numero.intValue();
		return profesor;
	}

	public Map<Integer, Profesor> getAll() {
		return profesores;
	}

	public Profesor getById(int codigo) {
		Profesor profesor = null;
		int posicion = -1;
		try {
			posicion = buscarProfesor(codigo);
			profesor = profesores.get(codigo);	
		} catch (ProfesorServiceImpExcepciones e){
			System.out.println(e.getMessage());
			profesor = new Profesor();
		}
		return profesor;
	}


	public Profesor update(Profesor profesor) {
		//profesores.put(profesor.getCodigo(), profesor);
		
		int posicion = -1;
		try {
			posicion = buscarProfesor(profesor.getCodigo());
			profesores.put(profesor.getCodigo(), profesor);
		} catch (ProfesorServiceImpExcepciones e) {
			System.out.println(e.getMessage());
		}
		return profesor;
	}

	public void delete(int codigo) {
		int posicion = -1;
		try {
			posicion = buscarProfesor(codigo);
			profesores.remove(posicion);
		} catch (ProfesorServiceImpExcepciones e) {
			System.out.println(e.getMessage());
		}
	}
	
	private int buscarProfesor(int codigo) throws ProfesorServiceImpExcepciones {

		int i = 0, posicion = -1;
		boolean encontrado = false;

		while (encontrado == false && i < profesores.size()) {
			Profesor aux = profesores.get(i);
			if (aux.getCodigo() == codigo) {
				encontrado = true;
				posicion = i;
			}
			i++;
		}

		if (posicion == -1) {
			throw new ProfesorServiceImpExcepciones(ProfesorServiceImpExcepciones.COD_PROFESOR_NO_ENCONTRADO,
					ProfesorServiceImpExcepciones.MSG_PROFESOR_NO_ENCONTRADO);
		}
		return posicion;
	}
	
	@Override
	public void delate(int codigo) {
		
	}
}
