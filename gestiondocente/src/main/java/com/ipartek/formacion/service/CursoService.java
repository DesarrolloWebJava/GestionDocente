/* Paquete donde se guardan las clase de las operaciones con entidades. */
package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.dbms.pojo.Curso;



/**
 * 
 * @author Raúl de Roba 24/01/17 
 * 
 * Interface de los servicios de la clase de la entidad Curso.
 *
 */
public interface CursoService {
	public Curso create(Curso curso);
	public Curso getById(int codigo);
	public List<Curso> getAll();
	public Curso update(Curso curso);
	public void delete(int codigo);
	}

