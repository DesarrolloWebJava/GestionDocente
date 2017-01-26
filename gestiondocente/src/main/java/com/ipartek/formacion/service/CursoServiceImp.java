package com.ipartek.formacion.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.dbms.pojo.CursoDuracionComparator;
import com.ipartek.formacion.dbms.pojo.exceptions.CursoException;


/**
 * <div>
 * <p>
 * Esta clase se va encargar de gestionar las operaciones de CRUD del Curso.
 * </p>
 * <ul>
 * <li>C: Create</li>
 * <li>R: Read</li>
 * <li>D: Delete</li>
 * <li>U: Update</li>
 * </ul>
 * </div>
 * 
 * @author Sergio aparicio.
 *
 */
public class CursoServiceImp implements CursoService {

  private static int contador = 0;	
  private List<Curso> cursos=null;
  

public CursoServiceImp(){
	super();
	cursos = new ArrayList<Curso>();
	init();
}


private void init() {

	String date = "";
	String pattern = "dd/MM/yyyy";
	SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
	
	
  Curso curso = new Curso();
  
  try {
	
	curso.setDuracion(80);
	curso.setNombre("Java Web");
	date = "24/01/2017";
	curso.setfInicio(dateFormat.parse(date));
	date = "30/01/2017";
	curso.setfFin(dateFormat.parse(date));
	create(curso);
   } catch (CursoException e) {
	
	e.printStackTrace();
   }catch(Exception e){
	   e.printStackTrace();
   }
  
  
  try {
	curso = new Curso();
	
	curso.setDuracion(90);
	curso.setNombre("JavaScript");
	date = "27/03/2017";
	curso.setfInicio(dateFormat.parse(date));
	date = "10/05/2017";
	curso.setfFin(dateFormat.parse(date));
	create(curso);
   } catch (CursoException e) {
	
	e.printStackTrace();
   }catch(Exception e){
	   e.printStackTrace();
  
   }

  try {
		curso = new Curso();
		
		curso.setDuracion(75);
		curso.setNombre("Programacion PHP");
		date = "19/02/2017";
		curso.setfInicio(dateFormat.parse(date));
		date = "14/03/2017";
		curso.setfFin(dateFormat.parse(date));
		
		create(curso);
	   } catch (CursoException e) {
		
		e.printStackTrace();
	   }catch(Exception e){
		   e.printStackTrace();
	   }

  Collections.sort(cursos, new CursoDuracionComparator());
  Collections.reverse(cursos);
  
}// fin del init


@Override
public Curso create(Curso curso) {
	curso.setCodigo(++contador);
	cursos.add(curso);
	return curso;
}


@Override
public List<Curso> getAll() {
	
	return cursos;
}


@Override
public Curso getById(int codigo) {
	Curso curso=null;

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





@Override
public Curso update(Curso curso) {
	int posicion = -1;
	try {
		posicion = buscarCurso(curso.getCodigo());
		cursos.set(posicion, curso);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
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
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
	}
	
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



  
  
}
