/**
 * 
 */
package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.dbms.pojo.Curso;

/**
 * Este es un interfaz que define los metodos de operaciones de  la clase Curso.
 * Con esta clase se realizaran operaciones de Create, Update, Delete y  Read.
 * @author Mikel Bruce.
 *
 */
public interface CursoService {
	
	/**
	 * Metodo que dará de alta objetos de tipo <code>Curso</code>
	 * @param curso
	 * 				<code> Curso </code>
	 * @return <code> Curso </code>
	 */
	public Curso create(Curso curso);
	
	public List<Curso> getAll();
	
	public Curso getById(int codigo);
	
	public Curso update(Curso curso);
	
	public void delete(int codigo);

}
