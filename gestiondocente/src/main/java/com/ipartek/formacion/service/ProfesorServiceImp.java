package com.ipartek.formacion.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.pojo.Profesor;
import com.ipartek.formacion.dbms.pojo.exceptions.PersonaException;

public class ProfesorServiceImp implements ProfesorService {
	private static final Logger LOG = Logger.getLogger(ProfesorServiceImp.class);
	Map<Integer, Profesor> profesores;
	private static int contador;

	public ProfesorServiceImp() {
		super();
		LOG.trace("Constructor");
		profesores = new HashMap<Integer, Profesor>();
		init();
	}

	private void init() {
		LOG.trace("metodo init");
		contador = 0;
		Profesor profesor = new Profesor();
		try {
			profesor.setNombre("Urko");
			profesor.setDni("67876547u");
			profesor.setApellidos("Villanueva");
			profesor.setDireccion("Ipartek");
			String naci = "11/12/1980";
			profesor.setfNacimiento(Util.parseLatinDate(naci));
			profesor.setnSS(123456);
			profesor.setEmail("uvillanueva@ipartek.com");
			create(profesor);
			profesor = new Profesor();
			profesor.setNombre("Isaak");
			profesor.setDni("45634212t");
			profesor.setApellidos("Asimov");
			profesor.setDireccion("Bilbao centro");
			naci = "23/07/1970";
			profesor.setfNacimiento(Util.parseLatinDate(naci));
			profesor.setnSS(654321);
			profesor.setEmail("auraga@ipartek.com");
			create(profesor);
		} catch (PersonaException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		} catch (ParseException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public Profesor create(Profesor profesor) {
		LOG.trace("metodo create");
		contador++;
		profesor.setCodigo(contador);
		profesores.put(profesor.getCodigo(), profesor);
		return profesor;
	}

	@Override
	public Map<Integer, Profesor> getAll() {
		LOG.trace("metodo getAll");
		return profesores;
	}

	@Override
	public Profesor getById(int codigo) {
		LOG.trace("metodo getById");
		Profesor profesor = null;
		profesor = profesores.get(codigo);
		return profesor;
	}

	@Override
	public Profesor update(Profesor profesor) {
		LOG.trace("metodo update");
		profesores.put(profesor.getCodigo(), profesor);
		return profesor;
	}

	@Override
	public void delete(int codigo) {
		LOG.trace("metodo delete");
		profesores.remove(codigo);
	}
}
