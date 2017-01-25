package com.ipartek.formacion.service;

import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.dbms.pojo.Curso;

public class CursoServiceImp implements CursoService {
	
private List<Curso> cursos;
private static int i=0;

	public CursoServiceImp()  {
		super();
		cursos=new ArrayList<Curso>();
		init();
	}

	private void init(){
		Curso curso=new Curso();
		try{
		curso.setNombre("Aplicaciones Web JEE");
		curso.setDuracion(500);
		create(curso);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
		
	}

	@Override
	public Curso create(Curso curso) {
		i++;
		curso.setCodigo(i);
		cursos.add(curso);
		return curso;
	}

	@Override
	public List<Curso> getAll() {
		return this.cursos;
	}

	@Override
	public Curso getById(int codigo) {
		Curso curso=null;
		int posicion=-1;
		posicion=buscarCurso(codigo);
		curso=cursos.get(posicion);
		return curso;
	}

	private int buscarCurso(int codigo) {
		int posicion=-1,i=0;
		boolean encontrado=false;
		while(!encontrado&&i<cursos.size()){
			Curso aux=cursos.get(i);
			if (aux.getCodigo()==codigo){
				encontrado=true;
				posicion=i;
			}
			i++;
		}
		return posicion;
	}

	@Override
	public void update(Curso curso) throws Exception {
		int posicion=-1;
		try{
			posicion=buscarCurso(curso.getCodigo());
			cursos.set(posicion, curso);
			
		}catch(Exception e){
			throw new Exception("Error al actualizar: "+e.getMessage());
		}

	}

	@Override
	public void delete(int codigo) throws Exception {
		int posicion=-1;
		try{
			posicion=buscarCurso(codigo);
			cursos.remove(posicion);
		}catch(Exception e){
			throw new Exception("Error al eliminar: "+e.getMessage());
		}
		

	}

}
