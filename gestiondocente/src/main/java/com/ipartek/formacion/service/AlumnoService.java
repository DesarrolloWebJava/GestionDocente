package com.ipartek.formacion.service;

import java.util.List;
import java.util.Map;

import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.dbms.pojo.Profesor;

/**
 * 
 * @author Maite Monasterio
 *
 */
public interface AlumnoService {

	public Alumno create(Alumno alumno);
	
	public List<Alumno> getAll();

	public Alumno getById(int codigo);

	public Alumno update(Alumno alumno);

	public void delete(int codigo);

}
