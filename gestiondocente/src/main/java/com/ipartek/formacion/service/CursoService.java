package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.dbms.pojo.Curso;

public interface CursoService {

	//CRUD
	public Curso create(Curso curso);
	public Curso getById(int codigo);
	public List<Curso> getAll();
	public Curso update(Curso curso);
	public void delete(int codigo);
	
}
