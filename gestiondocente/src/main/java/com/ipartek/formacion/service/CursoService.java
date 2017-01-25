package com.ipartek.formacion.service;

import java.util.List;
import com.ipartek.formacion.dbms.pojo.Curso;

/**
 * Interfaz que define los metodos de operaciones de la clase.
 * Con esta clase se realizaran operaciones de Create Read Update y Delete (CRUD).
 * @author Curso
 */

public interface CursoService {
	/**
	 * Metodo qie dara de alta objetos de tipo <code>Curso</code>
	 * 
	 * @param curso
	 * 				<code>Curso</code>
	 * @return <code>Curso</code>
	 */

	public Curso create(Curso curso);
	public Curso getById (int codigo);
	public List<Curso> getAll();
	public Curso update(Curso curso);
	public void delete(int codigo);
}
