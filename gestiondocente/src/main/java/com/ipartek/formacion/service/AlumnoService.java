/* Paquete donde se guardan las clase de las operaciones con entidades. */
package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.dbms.pojo.Alumno;
/**
 * 
 * @author Raúl de Roba 17/01/17 
 * 
 * Interface de los servicios de la clase de la entidad Alumno.
 *
 */

public interface AlumnoService {
	public Alumno create(Alumno alumno);
	public Alumno getById(int codigo);
	public List<Alumno> getAll();
	public Alumno update(Alumno alumno);
	public void delete(int codigo);
}
