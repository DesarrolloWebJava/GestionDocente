package com.ipartek.formacion.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.dbms.pojo.exceptions.CursoException;
import com.ipartek.formacion.service.exceptions.CursoServiceImpException;

public class CursoServiceImp implements CursoService {

	private List<Curso> cursos;
	public static int i = 0;
	
	public CursoServiceImp(){
		super();
		cursos= new ArrayList<Curso>();
		init();
	}
	
	private final void init() {
		Curso curso = new Curso();
		try {
			curso.setNombre("Sergio Aparicio");
			curso.setDuracion(10);
			String dateIni = "19/11/1995";
			String dateFin = "20/05/2017";
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			curso.setFechaInicio(dateFormat.parse(dateIni));
			curso.setFechaFin(dateFormat.parse(dateFin));
			create(curso);
		} catch (CursoException e) {
			
			e.printStackTrace();
		} catch (ParseException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
		}
		
	}
	
	public final Curso create(Curso curso){
		curso.setCodigo(i);
		i++;
		cursos.add(curso);
		return curso;
		
	}
	
	public List<Curso> getAll(){
		return cursos;
		
	}
	
	public Curso getById(int codigo){
		Curso curso = null;
		int posicion = -1;
		try{
		posicion = buscarCurso(codigo);
		curso = cursos.get(posicion);
		} catch(CursoServiceImpException e) {
			
			curso = new Curso(); 
		}
		return curso;
		
	}

	public void delete(int codigo){
		int posicion = -1;
		try{
			posicion = buscarCurso(codigo);
			cursos.remove(posicion);
		} catch (CursoServiceImpException e) {
			
		}
	}
	
	public Curso update(Curso curso){
		int posicion = -1;
		try{
			posicion=buscarCurso(curso.getCodigo());
			cursos.set(posicion, curso);
		}catch(CursoServiceImpException e){
			
		}
		return curso;
	}
	
	private int buscarCurso(int codigo) throws CursoServiceImpException {
		int i = 0, posicion = -1;
		boolean encontrado = false;
		
		while(encontrado == false && i< cursos.size()){
			Curso aux = cursos.get(i);
			if(aux.getCodigo()==codigo){
				encontrado = true;
				posicion = i;
			}
			i++;
		}
		if (posicion == -1){
			throw new CursoServiceImpException(CursoServiceImpException.COD_CURSO_NO_ENCONTRADO,
					CursoServiceImpException.MSG_CURSO_NO_ENCONTRADO );
		}
		return posicion;
	}
	
	
	
	
}
