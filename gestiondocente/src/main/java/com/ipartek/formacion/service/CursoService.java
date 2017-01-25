package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.dbms.pojo.Curso;

/**
 * Operaciones CRUD
 * @author Curso
 *
 */
public interface CursoService {
	public Curso create(Curso curso);
	public List<Curso> getAll();
	public Curso getById(int codigo);
	public void update(Curso curso) throws Exception;
	public void delete(int codigo) throws Exception;

}
