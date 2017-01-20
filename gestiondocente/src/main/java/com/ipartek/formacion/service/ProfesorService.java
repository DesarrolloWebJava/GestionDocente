package com.ipartek.formacion.service;

import java.util.Map;

import com.ipartek.formacion.dbms.pojo.Profesor;

public interface ProfesorService {

	/**
	 * 
	 * @param profesor
	 * @return
	 */
	public Profesor create(Profesor profesor);

	public Map<Integer, Profesor> getAll();

	public Profesor getById(int codigo);

	public Profesor update(Profesor profesor);

	public void delete(int codigo);

	public void delate(int codigo);
}
