package com.ipartek.formacion.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.dbms.pojo.CursoDuracionComparator;

/**
 * 
 * @author Urko Villanueva.
 *
 */
public class CursoServiceImp implements CursoService {

	private List<Curso> cursos;
	private static int codigo = 0;

	public CursoServiceImp() {
		super();
		cursos = new ArrayList<Curso>();
		init();
	}

	private final void init() {
		Curso curso = new Curso();

		try {
			curso.setDuracion(510);
			curso.setNombre("IFCD0210 - Desarrollo de aplicaciones con Tecnologias Web");
			curso.setfInicio(Util.parseLatinDate("09/01/2017"));
			curso.setfFin(Util.parseLatinDate("13/06/2017"));
			create(curso);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
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
		cursos.set(buscarCurso(codigo), curso);
		return curso;
	}

	@Override
	public Curso getById(int codigo) {
		Curso curso = null;
		int pos = buscarCurso(codigo);
		curso = cursos.get(pos);
		return curso;
	}

	private int buscarCurso(int codigo) {
		int posicion = -1, i = 0;

		boolean encontrado = false;
		while (encontrado == false && i < cursos.size()) {
			if (codigo == cursos.get(i).getCodigo()) {
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
		cursos.remove(buscarCurso(codigo));
	}

}
