package com.ipartek.formacion.service;
/**
 * este interfaz define los metodos de operaciones de la clase curso.
 *  con esta clase se realizan las operaciones de CRUD.
 *  create.
 *  update.
 *  read.
 *  delete.
 */
import java.util.List;

import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.service.exceptions.CursoServiceImpException;
/**
 * metodo que dara de alta objetos de tipo <code>curso</code>.
 * @author Curso.
 * <code>Curso</code>.
 * @return <code>curso</code>.
 *
 */
public interface CursoService{

	public Curso create(Curso curso);
	public Curso update(Curso curso);
	public Curso getById(int codigo);
	public List<Curso> getAll();
	public void delete(int codigo) throws CursoServiceImpException;	
	
}
