package com.ipartek.formacion.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.dbms.pojo.exceptions.CursoException;
import com.ipartek.formacion.dbms.pojo.exceptions.PersonaException;
import com.ipartek.formacion.service.exceptions.AlumnoServiceImpException;
import com.ipartek.formacion.service.exceptions.CursoServiceImpException;

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
 * @author va00
 *
 */

public class CursoServiceImp implements CursoService {

	private List<Curso> cursos;
	private static int i = 0;
	
	
	public CursoServiceImp(){
	
		super();
		cursos=new ArrayList<Curso>();
		init();
		
	}


	private void init() {
		// TODO Auto-generated method stub
		Curso curso = new Curso();
		try{
			curso.setNombre("Macrame");
			curso.setDuracion(250);
			
			create(curso);
						
		}catch (CursoException e){
			System.out.println(e.getMessage());	
		}
		
		curso =new Curso();
		try{
			
			curso.setNombre("Soldadura");
			curso.setDuracion(300);
			String date = "05/06/2015";
			String pattern = "dd/MM/yy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			curso.setfInicio(dateFormat.parse(date));
			
			create(curso);
			} catch (CursoException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("Error no controlado" + e.getMessage());
			}

	
	}


	public Curso create(Curso curso) {
		// TODO Auto-generated method stub
		curso.setCodigo(i);
		i++;
		cursos.add(curso);
		return curso;
		
	}
	
	public List <Curso> getAll(){
		return cursos;
		
	}
		
	public Curso getById(int codigo){
		Curso curso = null;
		int posicion = -1;
		try{
			posicion = buscarCurso(codigo);
			curso = cursos.get(posicion);
						
		}catch(CursoServiceImpException e){
			System.out.println(e.getMessage());
			curso = new Curso();
			
		}
		return curso;
		
	}


	private int buscarCurso(int codigo) throws CursoServiceImpException {
		
		int i =0, posicion =-1;
		boolean encontrado = false;
		
		while (encontrado == false && i<cursos.size()){
			
			Curso aux = cursos.get(i);
			if (aux.getCodigo()==codigo){
				encontrado = true;
				posicion = i;
			}
			i++;
		}
		if(posicion==-1){
			throw new CursoServiceImpException(CursoServiceImpException.COD_CURSO_NO_ENCONTRADO,
					CursoServiceImpException.MSG_CURSO_NO_ENCONTRADO);
			
		}
		return posicion;
	}
	
	public Curso update(Curso curso) {
		int posicion = -1;
		try {
			posicion = buscarCurso(curso.getCodigo());
			cursos.set(posicion, curso);
		} catch (CursoServiceImpException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
			return curso;
	}
	
	
	
	
	public void delete(int codigo) {
		int posicion = -1;
		try {
			posicion = buscarCurso(codigo);
			cursos.remove(posicion);
		} catch (CursoServiceImpException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}	
	
	
}
	
	
	

