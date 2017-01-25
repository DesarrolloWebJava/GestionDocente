package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.dbms.pojo.Curso;

/**
 * Es una interfaz que define los metodos de operaciones de la clase Curso.
 * 
 * @author Amaia Martija
 *
 */
public interface CursoService {
	/**
	 * 
	 * @param curso
	 * 
	 * 
	 * @return
	 */
	public Curso create(Curso curso);

	public Curso getById(int codigo);

	public List<Curso> getAll();

	public Curso update(Curso curso);

	public void delete(int codigo);

}
