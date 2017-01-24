package com.ipartek.formacion.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.service.exceptions.CursoServiceImpException;


public class CursoServiceImp implements CursoService {
	
	private List<Curso> listaCurso;
	private static int codi = 0;

	public CursoServiceImp() {
		super();
		listaCurso = new ArrayList<Curso>();
		init();	
	}
	
	
	private void init() {
		Curso curso = new Curso();
		try {
			curso.setNombre("Programación Web");
			curso.setDuracion(500);
			
			String fInicio = "30/12/2016";
			String fFinal = "13/06/2017";
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			curso.setfInicio(dateFormat.parse(fInicio));
			curso.setfFin(dateFormat.parse(fFinal));
			create(curso);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error no controlado" + e.getMessage());
		}
		
		
		Curso curso2 = new Curso();
		try {
			curso2.setNombre("Contablilidad");
			curso2.setDuracion(300);
			
			String fInicio = "23/11/2016";
			String fFinal = "06/02/2017";
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			curso2.setfInicio(dateFormat.parse(fInicio));
			curso2.setfFin(dateFormat.parse(fFinal));
			create(curso2);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error no controlado" + e.getMessage());
		}
		
		
		Curso curso3 = new Curso();
		try {
			curso3.setNombre("Inglés Técnico");
			curso3.setDuracion(250);
			
			String fInicio = "15/02/2017";
			String fFinal = "05/05/2017";
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			curso3.setfInicio(dateFormat.parse(fInicio));
			curso3.setfFin(dateFormat.parse(fFinal));
			create(curso3);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error no controlado" + e.getMessage());
		}
	}

	@Override
	public Curso create(Curso curso) {
		curso.setCodigo(codi);
		codi++;
		listaCurso.add(curso);
		return curso;
	}

	@Override
	public Curso getById(int codigo) {
		// TODO Auto-generated method stub
		Curso curso = null;
		int posicion = -1;
		try {
			posicion = buscarCurso(codigo);
			curso = listaCurso.get(posicion);
		} catch (CursoServiceImpException e) {
			System.out.println(e.getMessage());
			curso = new Curso();
		}

		return curso;
	}

	@Override
	public List<Curso> getAll() {
		// TODO Auto-generated method stub
		return listaCurso;
	}

	public void delete(int codigo) {
		int posicion = -1;
		try {
			posicion = buscarCurso(codigo);
			listaCurso.remove(posicion);
		} catch (CursoServiceImpException e) {

			System.out.println(e.getMessage());
		}

	}

	private int buscarCurso(int codigo) throws CursoServiceImpException {

		int i = 0, posicion = -1;
		boolean encontrado = false;

		while (encontrado == false && i < listaCurso.size()) {
			Curso aux = listaCurso.get(i);
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

	public Curso update(Curso curso) {
		int posicion = -1;
		try {
			posicion = buscarCurso(curso.getCodigo());
			listaCurso.set(posicion, curso);
		} catch (CursoServiceImpException e) {
			System.out.println(e.getMessage());
		}
		return curso;
	}
}
