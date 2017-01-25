package com.ipartek.formacion.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	private void init() {
		
		Curso curso = new Curso();
		try {
			curso.setNombre("Programación");
		} catch (CursoException e2) {
			e2.getMessage();
		}
		try {
			curso.setDuracion(510);
		} catch (CursoException e1) {
			e1.printStackTrace();
		}
		String date = "09/01/2017";
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		try {
			curso.setFechaInicio(dateFormat.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String date2 = "30/05/2017";
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			curso.setFechaInicio(df.parse(date2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		create(curso);		
	}

	@Override
	public Curso create(Curso curso) {
		
		curso.setCodigo(contador);
		contador++;
		
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
