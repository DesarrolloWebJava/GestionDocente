package com.ipartek.formacion.service;

import java.util.Map;

import com.ipartek.formacion.dbms.pojo.Profesor;

/**
 * 
 * @author Raúl de Roba 17/01/17 
 * 
 * Interface de los servicios de la clase de la entidad profesor.
 *
 */
public interface ProfesorService {


	public Profesor create(Profesor profesor);

	public Map<Integer, Profesor> getAll();

	public Profesor getById(int codigo);

	public Profesor update(Profesor profesor);

	public void delete(int codigo);
}
