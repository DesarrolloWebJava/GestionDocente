/**
 * 
 */
package com.ipartek.formacion.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.dbms.pojo.CursoDuracionComparator;
import com.ipartek.formacion.dbms.pojo.Profesor;

/**
 * <div>
 * <p>
 * Esta clase se va encargar de gestionar las operaciones de CRUD de Alumno
 * </p>
 * <ul>
 * <li>C: Create</li>
 * <li>R: Read</li>
 * <li>D: Delete</li>
 * <li>U: Update</li>
 * </ul>
 * </div>
 * 
 * @author Mikel Bruce
 *
 */
public class CursoServiceImp implements CursoService{

	private List<Curso> cursos;
	private static int contador = 0;
	
	public CursoServiceImp() {
		super();
		cursos = new ArrayList<Curso>();
		init();
	}
	
	private void init(){
		Curso curso = new Curso();
		
		try {
			curso.setNombre("Java");
			curso.setDuracion(80);
			String dateInicio = "24/01/2017";
			String dateFin = "23/07/2017";
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			curso.setfInicio(dateFormat.parse(dateInicio));
			curso.setfFin(dateFormat.parse(dateFin));
			create(curso);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		curso = new Curso();
		try {
			curso.setNombre("Excel");
			curso.setDuracion(50);
			String dateInicio = "20/01/2017";
			String dateFin = "03/05/2017";
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			curso.setfInicio(dateFormat.parse(dateInicio));
			curso.setfFin(dateFormat.parse(dateFin));
			create(curso);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		curso = new Curso();
		try {
			curso.setNombre("SQL");
			curso.setDuracion(110);
			String dateInicio = "28/01/2017";
			String dateFin = "09/08/2017";
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			curso.setfInicio(dateFormat.parse(dateInicio));
			curso.setfFin(dateFormat.parse(dateFin));
			create(curso);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		Collections.sort(cursos, new CursoDuracionComparator());
		Collections.reverse(cursos);
		
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
		
		try {
			posicion = buscarCurso(codigo);
			curso = cursos.get(posicion);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			curso = new Curso();
		}
			
		return curso;
	}

	private int buscarCurso(int codigo) throws Exception {
		
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
			throw new Exception("Error al buscar la posicion en la lista cursos");
		}
		return posicion;
		
	}

	@Override
	public Curso update(Curso curso) {
		int posicion = -1;
		
		try {
			posicion = buscarCurso(curso.getCodigo());
			cursos.set(posicion, curso);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return curso;
	}

	@Override
	public void delete(int codigo) {
		int posicion = -1;
		
		try {
			posicion = buscarCurso(codigo);
			cursos.remove(posicion);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

}
