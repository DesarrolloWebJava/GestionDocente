package com.ipartek.formacion.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.dbms.pojo.exceptions.CursoException;

public class CursoServiceImp implements CursoService {

	private List<Curso> cursos;
	private static int i = 0;

	public CursoServiceImp() {
		super();
		cursos = new ArrayList<Curso>();
		init();
	}

	private void init() {

		/*
		 * AlumnoService alumno = new AlumnoServiceImp(); List<Alumno> alumnos;
		 * alumnos = alumno.getAll();
		 * 
		 * ProfesorService profesor = new ProfesorServiceImp(); List<Profesor>
		 * profesores; profesores = (List<Profesor>)profesor.getAll();
		 */

		try {
			Curso curso = new Curso();
			curso.setNombre("Curso Primero");
			curso.setDuracion(80);
			String date = "20/11/2017";
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			curso.setfInicio(dateFormat.parse(date));
			String date2 = "25/11/2017";
			String pattern2 = "dd/MM/yyyy";
			SimpleDateFormat dateFormat2 = new SimpleDateFormat(pattern2);
			curso.setfFinal(dateFormat2.parse(date2));
			create(curso);

			curso = new Curso();
			curso.setNombre("Curso Segundo");
			curso.setDuracion(150);
			String date3 = "01/07/2017";
			String pattern3 = "dd/MM/yyyy";
			SimpleDateFormat dateFormat3 = new SimpleDateFormat(pattern3);
			curso.setfInicio(dateFormat3.parse(date3));
			String date4 = "17/07/2017";
			String pattern4 = "dd/MM/yyyy";
			SimpleDateFormat dateFormat4 = new SimpleDateFormat(pattern4);
			curso.setfFinal(dateFormat4.parse(date4));
			create(curso);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CursoException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public Curso create(Curso curso) {
		curso.setCodigo(i);
		i++;
		cursos.add(curso);
		return curso;
	}

	@Override
	public Curso getById(int codigo) {
		Curso curso = null;
		curso = cursos.get(codigo);

		return curso;
	}

	@Override
	public List<Curso> getAll() {

		return cursos;
	}

	@Override
	public Curso update(Curso curso) {

		cursos.set(curso.getCodigo(), curso);
		return curso;
	}

	@Override
	public void delete(int codigo) {

		cursos.remove(codigo);

	}

}
