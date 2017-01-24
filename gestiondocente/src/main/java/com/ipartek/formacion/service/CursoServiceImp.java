package com.ipartek.formacion.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.dbms.pojo.exceptions.*;

/**
 * Desarrollamos los metodos declarados en la Interfaz Cursos
 * Create
 * Read
 * Delete
 * Update
 * @author Curso
 *
 */
public class CursoServiceImp implements CursoService {
	
	private List<Curso> cursos;
	private static int i = 0;


	public CursoServiceImp() {
		super();
		cursos = new ArrayList<Curso>();
		init();
	}

	private void init() {
		
		Curso curso = new Curso();
		
		curso.setNombre("POO-Programacion orientada a objetos");
		curso.setDuracion(100);
		String dateinicio = "20/1/2017";
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		try {
			curso.setfInicio(dateFormat.parse(dateinicio));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String datefin = "20/3/2017";
		String patternfin = "dd/MM/yyyy";
		SimpleDateFormat dateFormatfin = new SimpleDateFormat(patternfin);
		try {
			curso.setfFin(dateFormat.parse(datefin));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		create(curso);
		
		curso= new Curso();
		curso.setNombre("JAVA J2EE-Programacion web java");
		curso.setDuracion(100);
		String dateinicio1 = "10/2/2017";
		String pattern1 = "dd/MM/yyyy";
		SimpleDateFormat dateFormat1 = new SimpleDateFormat(pattern1);
		try {
			curso.setfInicio(dateFormat1.parse(dateinicio1));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String datefin2 = "10/6/2017";
		String patternfin2 = "dd/MM/yyyy";
		SimpleDateFormat dateFormatfin2 = new SimpleDateFormat(patternfin2);
		try {
			curso.setfFin(dateFormatfin2.parse(datefin2));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		create(curso);
	}

	@Override
	public Curso create(Curso curso) {
		curso.setCodigo(i);
		i++;
		cursos.add(curso);
		return curso;
	}

	@Override
	public List<Curso> getAll() {
		
		return cursos;
	}

	@Override
	public Curso getById(int codigo)  {
		Curso curso = null;
		int posicion = -1;
		
		try {
			posicion = buscarCurso(codigo);
			curso = cursos.get(posicion);
		} catch (CursoServiceImpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*try{
		posicion = buscarCurso(codigo);
		curso = cursos.get(posicion);
		}catch(CursoServiceImpException e){
			System.out.println(e.getMessage());
		}
		*/
		return curso;
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
			throw new CursoServiceImpException(CursoServiceImpException.COD_CURSO_NO_ENCONTRADO, CursoServiceImpException.MSG_CURSO_NO_ENCONTRADO);
		}	
		return posicion;
	}

	@Override
	public Curso update(Curso curso) {
		int posicion = -1;
		try {
			posicion = buscarCurso(curso.getCodigo());
			cursos.set(posicion, curso);
		} catch (CursoServiceImpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*try {
			posicion = buscarCurso(curso.getCodigo());
			cursos.set(posicion, curso);
			
		} catch (CursoServiceImpException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}*/
		return curso;
	}

	@Override
	public void delete(int codigo) {
		int posicion = -1;
		try {
			posicion = buscarCurso(codigo);
			cursos.remove(posicion);
		} catch (CursoServiceImpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*try {
			posicion = buscarCurso(codigo);
			cursos.remove(posicion);
		} catch (CursoServiceImpException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}*/
		
	}

}
