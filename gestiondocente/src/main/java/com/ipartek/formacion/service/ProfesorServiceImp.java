package com.ipartek.formacion.service;

import java.util.HashMap;
import java.util.Map;

import com.ipartek.formacion.dbms.pojo.Profesor;
import com.ipartek.formacion.dbms.pojo.exceptions.PersonaException;

public class ProfesorServiceImp implements ProfesorService {

	Map<Integer, Profesor> profesores;
	private static int contador;

	public ProfesorServiceImp() {
		super();
		profesores = new HashMap<Integer, Profesor>();
		init();
	}

	private void init() {
		contador = 0;
		Profesor profesor = new Profesor();
		try {
			profesor.setNombre("Urko");
			profesor.setEmail("uvillanueva@ipartek.com");
			create(profesor);
		} catch (PersonaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		profesor = new Profesor();
		try {
			profesor.setNombre("Ander");
			profesor.setEmail("auraga@ipartek.com");
			create(profesor);
		} catch (PersonaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Profesor create(Profesor profesor) {
		contador++;
		profesor.setCodigo(contador);
		profesores.put(profesor.getCodigo(), profesor);

		return profesor;
	}

	@Override
	public Map<Integer, Profesor> getAll() {

		return profesores;
	}

	@Override
	public Profesor getById(int codigo) {
		Profesor profesor = null;
		profesor = profesores.get(codigo);
		return profesor;
	}

	@Override
	public Profesor update(Profesor profesor) {
		profesores.put(profesor.getCodigo(), profesor);
		return profesor;
	}

	@Override
	public void delete(int codigo) {
		profesores.remove(codigo);
	}

}
