package com.ipartek.formacion.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.dbms.pojo.CursoDuracionComparator;
/**
 * 
 * 
 * @author Alberto Fernandez.
 *
 */

public class CursoServiceImp implements CursoService{
	
		private List<Curso> cursos;
		private static int codigo = 0;
	
	
	public CursoServiceImp(){
			super();
			cursos = new ArrayList<Curso>();
			init();
	}
	
	private void init() {
		
		Curso curso = new Curso();
		try {
			curso.setDuracion(80);
			curso.setNombreCurso("Primer Curso");
			
			curso.setFechaInicio(Util.parseLatinDate("25/01/2017"));
			curso.setFechaFin(Util.parseLatinDate("25/06/2017"));
			create(curso);	
		} catch (ParseException e) {	
			e.printStackTrace();
		}
		curso = new Curso();
		try {
			curso.setNombreCurso("Segundo Curso");
			curso.setDuracion(80);
			
			curso.setFechaInicio(Util.parseLatinDate("25/06/2017"));
			curso.setFechaFin(Util.parseLatinDate("25/09/2017"));
			
			create(curso);
		} catch (ParseException e) {
			e.printStackTrace();
		//} catch (Exception e) {
			//System.out.println("Error no controlado" + e.getMessage());
		}
		
		curso = new Curso();
		try {
			curso.setNombreCurso("Tercer Curso");
			curso.setDuracion(80);
			
			curso.setFechaInicio(Util.parseLatinDate("25/09/2017"));
			curso.setFechaFin(Util.parseLatinDate("25/12/2017"));
		
			create(curso);	
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Collections.sort(cursos, new CursoDuracionComparator());
		Collections.reverse(cursos);
	}
	
	@Override
	public Curso create(Curso curso) {
		codigo++;
		curso.setCodigo(codigo);
		cursos.add(curso);
		return curso;
	}

	@Override
	public Curso update(Curso curso) {
			//int posicion = -1;
		 	//posicion = buscarCurso(curso.getCodigo());
		 	//cursos.set(posicion, curso);
		 	
		 	cursos.set(buscarCurso(codigo),curso);
		return curso;
	}
	
	@Override
	public Curso getById(int codigo) {

		Curso curso = null;
		int posicion = buscarCurso(codigo);
		curso = cursos.get(posicion);	
		return curso;
	}
	
	private int buscarCurso (int codigo){
		
		int posicion = -1;
		int i = 0;
		
		boolean encontrado = false;
		while (encontrado == false && i < cursos.size()) {
			//Curso aux = cursos.get(i);
			//if (aux.getCodigo() == codigo) {
			if (codigo == cursos.get(i).getCodigo()){
				posicion = i;
			 	encontrado = true;
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
	public void delete(int codigo) {
		
		//int posicion = -1;
		//posicion = buscarCurso(codigo);
		//cursos.remove(posicion);
		cursos.remove(buscarCurso(codigo));
		
	}	

}
