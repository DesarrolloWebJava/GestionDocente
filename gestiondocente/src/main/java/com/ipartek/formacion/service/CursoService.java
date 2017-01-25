package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.dbms.pojo.Curso;

/**
 * Creamos la interfaz sobre la que implementaremos las clases de control del CRUD
 * @author Sergio Aparicio
 *
 */
public interface CursoService {

 public Curso create(Curso curso);
 
 public List<Curso>getAll();
 
 public Curso getById(int codigo);
 
 public Curso update(Curso curso);
 
 public void delete(int codigo);
 
 
 
	
	
}
