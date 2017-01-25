package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.dbms.pojo.Curso;
/**
 * 
 * Este un interfaz que define los metodos de operaciones de la clase...
 * <code>Curso</code> Con esta clase se realizan operaciones (CRUD).
 * @author Alberto Fernandez.
 *
 */

public interface CursoService {
	
	/**
	 * Metodo que dará de alta de tipo <code>Curso</code>.
	 * @param curso
	 * @return <code>Curso</code>
	 */

	public Curso create(Curso curso);
	
	public Curso getById(int codigo);
	
	public List<Curso> getAll();
	
	public Curso update(Curso curso);
	
	public void delete(int codigo);
	
}
