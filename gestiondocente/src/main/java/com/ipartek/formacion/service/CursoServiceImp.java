package com.ipartek.formacion.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.dbms.pojo.exceptions.CursoException;

public class CursoServiceImp implements CursoService{

	private List<Curso> cursos;
	private static int i = 0;
	
	
	public CursoServiceImp(){
		super();
		cursos = new ArrayList<Curso>();
		init();
	}
	
	private void init() {
		
		Curso curso = new Curso();
		try {
			curso.setNombreCurso("Primer Curso");
			curso.setDuracion(80);
			String pattern = "dd/MM/yyyy";
			String inicio = "24/01/2017";
			
			String fin = "24/06/2017";
			
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			curso.setFechaInicio(dateFormat.parse(inicio));
			curso.setFechaFin(dateFormat.parse(fin));
			create(curso);
			

		} catch (ParseException e) {

			e.printStackTrace();
		}
		
		curso = new Curso();
		try {
			curso.setNombreCurso("Segundo Curso");
			curso.setDuracion(80);
			String pattern = "dd/MM/yyyy";
			String inicio = "24/01/2017";
			
			String fin = "24/06/2017";

			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			curso.setFechaInicio(dateFormat.parse(inicio));
			create(curso);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		curso = new Curso();
		try {
			curso.setNombreCurso("Tercer Curso");
			curso.setDuracion(80);
			String pattern = "dd/MM/yyyy";
			String inicio = "24/01/2017";
			
			String fin = "24/06/2017";
			
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			curso.setFechaInicio(dateFormat.parse(inicio));
			create(curso);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public Curso create(Curso curso) {
		
		curso.setCodigo(i);
		i++;
		cursos.add(curso);
		return curso;
	}

	@Override
	public Curso getById(int codigo) {

		Curso curso = null;
		int posicion = -1;
		
		posicion =buscarCurso(codigo);
		curso = cursos.get(posicion);
		
		return curso;
	}
	
	private int buscarCurso (int codigo){
		
		int posicion = -1;
		int i = 0;
		
		boolean encontrado = false;
		while (encontrado == false && i < cursos.size()) {
			Curso aux = cursos.get(i);
			if (aux.getCodigo() == codigo) {
			 	encontrado = true;
			 	posicion = i;
			 }
		i++;
		}
		return posicion;
	}
	
	@Override
	public List<Curso> getAll() {
		return cursos;
	}
	
	@Override
	public Curso update(Curso curso) {
		
		int posicion = -1;
	
		 	posicion = buscarCurso(curso.getCodigo());
		 	cursos.set(posicion, curso);
		
		return curso;
	}

	@Override
	public void delete(int codigo) {
		
		int posicion = -1;
		
		posicion = buscarCurso(codigo);
		cursos.remove(posicion);
	}
	
	
	
	
	
	

	
	
	
	
	
}
