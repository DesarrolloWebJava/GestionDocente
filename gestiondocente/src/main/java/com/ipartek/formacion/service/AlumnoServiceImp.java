package com.ipartek.formacion.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.dbms.pojo.exceptions.*;

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
public class AlumnoServiceImp implements AlumnoService {

	private List<Alumno> alumnos;
	private static int i = 0;

	public AlumnoServiceImp() {
		super();
		alumnos = new ArrayList<Alumno>();
		init();
	}

	private final void init() {
		Alumno alumno = new Alumno();
		try {
			alumno.setNombre("Sergio");
			alumno.setApellidos("Aparicio Vegas");
			alumno.setDni("44974398z");
			create(alumno);
		} catch (PersonaException e) {
			// TODO Auto-generated catch block
			//System.out.println(e.getMessage());
		}

		alumno = new Alumno();
		try {
			alumno.setNombre("Maite");
			alumno.setApellidos("Monasterio Herrero");
			alumno.setDni("16071559x");
			String date = "19/11/1978";
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			alumno.setfNacimiento(dateFormat.parse(date));
			create(alumno);
		} catch (PersonaException e) {
			//System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			//System.out.println("Error no controlado" + e.getMessage());
		}

		alumno = new Alumno();
		try {
			alumno.setNombre("Jon");
			alumno.setApellidos("Martinez Perez");
			alumno.setDni("16071559x");
			create(alumno);
		} catch (PersonaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Alumno create(Alumno alumno) {
		alumno.setCodigo(i);
		i++;
		alumnos.add(alumno);
		return alumno;
	}

	public List<Alumno> getAll() {
		return alumnos;
	}

	public Alumno getById(int codigo) {
		Alumno alumno = null;
		int posicion = -1;
		try {
			posicion = buscarAlumno(codigo);
			alumno = alumnos.get(posicion);
		} catch (AlumnoServiceImpException e) {
			//System.out.println(e.getMessage());
			alumno = new Alumno();
		}

		return alumno;
	}

	public void delete(int codigo) {
		int posicion = -1;
		try {
			posicion = buscarAlumno(codigo);
			alumnos.remove(posicion);
		} catch (AlumnoServiceImpException e) {
			// TODO Auto-generated catch block
			//System.out.println(e.getMessage());
		}

	}

	private int buscarAlumno(int codigo) throws AlumnoServiceImpException {

		int i = 0, posicion = -1;
		boolean encontrado = false;

		while (encontrado == false && i < alumnos.size()) {
			Alumno aux = alumnos.get(i);
			if (aux.getCodigo() == codigo) {
				encontrado = true;
				posicion = i;
			}
			i++;
		}

		if (posicion == -1) {
			throw new AlumnoServiceImpException(AlumnoServiceImpException.COD_ALUMNO_NO_ENCONTRADO,
					AlumnoServiceImpException.MSG_ALUMNO_NO_ENCONTRADO);
		}
		return posicion;
	}

	public Alumno update(Alumno alumno) {
		int posicion = -1;
		try {
			posicion = buscarAlumno(alumno.getCodigo());
			alumnos.set(posicion, alumno);
			
		} catch (AlumnoServiceImpException e) {
			// TODO Auto-generated catch block
			//System.out.println(e.getMessage());
		}
		return alumno;
	}
}
