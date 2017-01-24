package com.ipartek.formacion.service;

import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.dbms.pojo.exceptions.CursoException;
import com.ipartek.formacion.service.exceptions.AlumnoServiceImpException;

public class CursoServiceImp implements CursoService {
	
	List<Curso> cursos;
	private static int contador = 0;

	
	public CursoServiceImp() {
		super();
		cursos = new ArrayList<Curso>();
		init();
	}

	private void init(){
		
		Curso curso = new Curso();
		create(curso);		
	}

	@Override
	public Curso create(Curso curso) {
		
		contador++;
		curso.setCodigo(contador);
		cursos.add(curso);

		return curso;
	}

	@Override
	public List<Curso> getAll() {
		return cursos;
	}

	@Override
	public Curso getById(int codigo) {
		Curso curso = null;
		int posicion = -1;
		
		posicion = buscarCurso(codigo);
		curso = cursos.get(posicion);

		return curso;
	}

	@Override
	public Curso update(Curso curso) {
		
		int posicion = -1;
		posicion=buscarCurso(curso.getCodigo());
		cursos.set(posicion, curso);
		
		return curso;
	}

	@Override
	public void delete(int codigo) {
		
		int posicion = -1;
		posicion = buscarCurso(codigo);
		cursos.remove(posicion);
		
	}
	
	private int buscarCurso(int codigo){
		int i=0;
		int posicion=-1;
		boolean encontrado = false;
		
		while (encontrado == false && i < cursos.size()) {
			Curso curso = cursos.get(i);
			if (curso.getCodigo() == codigo) {
				encontrado = true;
				posicion = i;
			}
			i++;
		}
		
		return posicion;
	}
}
