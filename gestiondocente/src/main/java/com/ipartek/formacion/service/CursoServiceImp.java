package com.ipartek.formacion.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.dbms.pojo.Profesor;
import com.ipartek.formacion.service.exceptions.CursoServiceImpException;

public class CursoServiceImp implements CursoService {

	//Reservamos espacio para una lista de cursos
	private List<Curso> cursos;
	//Reservamos espacio para una lista de alumnos
	private List<Alumno> alumnos;
	//Reservamos espacio para un objeto profesor
	private Profesor profesor;
	//Inicializa el codigo del curso
		private static int codigoCurso = 0;
	
	public CursoServiceImp() {
		super();
		//Inicializamos la lista
		cursos = new ArrayList<Curso>();
		//Creamos una lista de alumnos
		alumnos = new ArrayList<Alumno>();
		//Creamos un nuevo profesor
		profesor = new Profesor();
		//Llamamos al metodo init()
		init();
	}

	/*
	 * En este metodo vamos a iniciualizar valores para pruebas
	 */
	private void init(){
		//Creamos un nuevo curso
		Curso nuevoCurso = new Curso();
		try{
			nuevoCurso.setNombre("Programacion Java");
			nuevoCurso.setDuracion(500);
			String fechaIni = "09/01/2017";
			String fechaFin = "09/06/2017";
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat formatoFecha = new SimpleDateFormat(pattern);
			nuevoCurso.setfInicio(formatoFecha.parse(fechaIni));
			nuevoCurso.setfFin(formatoFecha.parse(fechaFin));
			//Creo 2 alumnos
			Alumno alumno = new Alumno();
			Alumno alumno2 = new Alumno();
			//Añado los alumnos a la lista
			alumnos.add(alumno);
			alumnos.add(alumno2);
			//Cargo la lista
			nuevoCurso.setAlumnos(alumnos);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	
	@Override
	public Curso create(Curso curso) {
		//Incrementamos la variable
		codigoCurso++;
		//Le asignamos el valor de la variable estatica al codigo del curso
		curso.setCodigo(codigoCurso);
		//Añadimos el curso a la lista de cursos
		cursos.add(curso);
		//Devuelve el curso
		return curso;
	}

	@Override
	public Curso getById(int codigo) {
		//Creamos un nuevo curso nulo para cargar sus valores
		Curso curso = null;
		int posicion = -1;
		try{
			//Buscamos el curso mediante el codigo
			posicion = buscarCurso(codigo);
			//Cargamos el objeto curso vacio con el objeto de esa posicion en la lista
			curso = cursos.get(posicion);
		}catch(CursoServiceImpException e){
			System.out.println(e.getMessage());
			//Creamos un nuevo alumno para evitar nullPointerException
			curso = new Curso();
		}
		return curso;
	}

	@Override
	public List<Curso> getAll() {
		return cursos;
	}

	@Override
	public Curso update(Curso curso) {
		int posicion = -1;
		try{
			//Buscamos la posicion del alumno buscado (si existe)
			posicion = buscarCurso(curso.getCodigo());
			//Actualizamos el objeto dandole la posicion y el nuevo objeto
			cursos.set(posicion, curso);
		}catch(CursoServiceImpException e){
			System.out.println(e.getMessage());
		}
		return curso;
	}

	private int buscarCurso(int codigo) throws CursoServiceImpException {
		int i = 0, posicion = -1;
		boolean encontrado = false;
		while(encontrado == false && i < cursos.size()){
			//Guarda el objeto i de la lista en el objeto aux
			Curso aux = cursos.get(i);
			//Si el codigo del objeto aux es igual que el codigo...
			if(aux.getCodigo() == codigo){
				encontrado = true;
				posicion = i;
			}
			i++;
		}
		//Si no se han encontrado coincidencias...
		if(posicion == -1){
			/*
			 * Lanza una excepcion a la clase CursoServiceImpException que hemos creado
			 * pasandole como paramentro el codigo de curso y mensaje
			 */
			throw new CursoServiceImpException(CursoServiceImpException.COD_CURSO_NO_ENCONTRADO, 
					CursoServiceImpException.MSG_CURSO_NO_ENCONTRADO);
		}
		return posicion;
	}

	@Override
	public void delete(int codigo) {
		int posicion = -1;
		try{
			//Buscamos la posicion del curso por la posicion
			posicion = buscarCurso(codigo);
			//Eliminamos el curso de esa posicion
			cursos.remove(posicion);
		}catch(CursoServiceImpException e){
			System.out.println(e.getMessage());
		}
	}

}
