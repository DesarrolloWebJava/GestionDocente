package com.ipartek.formacion.service;

import java.util.List;
import com.ipartek.formacion.dbms.pojo.Alumno;
/**
 * 
 * @author Curso
 *
 */

public interface AlumnoService {
	public Alumno create(Alumno alumno);

	public List<Alumno> getAll();

	public Alumno getById(int codigo);

	public Alumno update(Alumno alumno);

	public void delete(int codigo);
	

}
