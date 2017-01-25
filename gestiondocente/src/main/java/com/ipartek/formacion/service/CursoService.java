package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.dbms.pojo.Curso;

/**
 * Este un interfaz que define los metodos de operaciones de la clase
 * <code>Curso</code>. Con esta clase se realizaran operaciones de Create, Read,
 * Update y Delete (CRUD).
 * 
 * @author Urko Villanueva.
 *
 */
public interface CursoService {
	/**
	 * Metodo que dará de alta objetos de tipo <code>Curso</code>.
	 * 
	 * @param curso
	 *            <code>Curso</code>
	 * @return <code>Curso</code>
	 */
	public Curso create(Curso curso);

	public Curso update(Curso curso);

	public Curso getById(int codigo);

	public List<Curso> getAll();

	public void delete(int codigo);
}
