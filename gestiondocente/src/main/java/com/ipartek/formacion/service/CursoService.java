package com.ipartek.formacion.service;

/**
 * Este un interfaz que define los metodos de operaciones de create, read, update, 
 * delete(CRUD).
 */
import java.util.List;

import com.ipartek.formacion.dbms.pojo.Curso;

public interface CursoService {
	/**
	 * Metodo que dará de alta objetos de tipo <code>Curso</code>.
	 * 
	 * @param curso <code>Curso</code>
	 * @return <code>Curso</code>
	 */
	public Curso create(Curso curso);
	
	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public Curso getById(int codigo);
	
	/**
	 * 
	 * @return
	 */
	public List<Curso> getAll();
	
	/**
	 * 
	 * @param curso
	 * @return
	 */
	public Curso update(Curso curso);
	
	/**
	 * 
	 * @param codigo
	 */
	public void delete(int codigo);
	
}
