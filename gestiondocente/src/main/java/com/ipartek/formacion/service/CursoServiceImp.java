package com.ipartek.formacion.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.service.exceptions.CursoServiceImpException;
/**
 * <div>
 * <p>
 * Esta clase se va encargar de gestionar las operaciones de CRUD de Curso
 * </p>
 * <ul>
 * <li>C: Create</li>
 * <li>R: Read</li>
 * <li>D: Delete</li>
 * <li>U: Update</li>
 * </ul>
 * </div>
 **/

public class CursoServiceImp implements CursoService {

	private List<Curso> cursos;
	private static int i=0;
	
	
	public CursoServiceImp() {
		super();
		cursos=new ArrayList<Curso>();
		init();
	}

	private void init() {
		Curso curso= new Curso();
		try{
			curso.setNombre("Curso de Java");
			curso.setDuracion(500);
			String dateIni="23/01/2017";
			String dateFin="23/05/2017";
			String pattern="dd/MM/yyyy";
			SimpleDateFormat dateformat= new SimpleDateFormat(pattern);
			curso.setfInicio(dateformat.parse(dateIni));
			curso.setfFin(dateformat.parse(dateFin));
			create(curso);
		}catch (ParseException e) {
			e.printStackTrace();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		curso=new Curso();
		try{
			curso.setNombre("Curso de HTML5");
			curso.setDuracion(50);
			String dateIni="25/02/2017";
			String dateFin="01/03/2017";
			String pattern="dd/MM/yyyy";
			SimpleDateFormat dateformat= new SimpleDateFormat(pattern);
			curso.setfInicio(dateformat.parse(dateIni));
			curso.setfFin(dateformat.parse(dateFin));
			create(curso);
		}catch (ParseException e) {
			e.printStackTrace();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		Collections.sort(cursos);
	}

	@Override
	public Curso create(Curso curso) {
		
		i++;
		curso.setCodigo(i);
		cursos.add(curso);
		return curso;
	}

	@Override
	public Curso update(Curso curso) {
		int posicion=-1;
		try{
			posicion=buscarCurso(curso.getCodigo());
			cursos.set(posicion,curso);
		}catch(CursoServiceImpException e){
			System.out.println(e.getMessage());
		}
		return curso;
	}

	@Override
	public Curso getById(int codigo) {
		int posicion=-1;
		Curso curso=null;
		try{
			posicion=buscarCurso(codigo);
			curso=cursos.get(posicion);
		}catch(CursoServiceImpException e){
			System.out.println(e.getMessage());
			curso=new Curso();
		}
		return curso;
	}

	@Override
	public List<Curso> getAll() {
		return cursos;
	}

	@Override
	public void delete(int codigo) {
		int posicion=-1;
		try{
			posicion=buscarCurso(codigo);
			cursos.remove(posicion);
		}catch(CursoServiceImpException e){
			System.out.println(e.getMessage());
		}
	}

	private int buscarCurso(int codigo) throws CursoServiceImpException {
		int i = 0, posicion = -1;
		boolean encontrado = false;

		while (encontrado == false && i < cursos.size()) {
			Curso aux = cursos.get(i);
			if (aux.getCodigo() == codigo) {
				encontrado = true;
				posicion = i;
			}
			i++;
		}

		if (posicion == -1) {
			throw new CursoServiceImpException(CursoServiceImpException.COD_CURSO_NO_ENCONTRADO,
					CursoServiceImpException.MSG_CURSO_NO_ENCONTRADO);
		}
		return posicion;
	}

}
