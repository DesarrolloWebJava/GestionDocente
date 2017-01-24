package com.ipartek.formacion.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.dbms.pojo.exceptions.PersonaException;
import com.ipartek.formacion.service.exceptions.AlumnoServiceImpException;

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
public class AlumnoServiceImp implements AlumnoService{

	//Reservamos espacio para una lista de alumnos
	private List<Alumno> alumnos;
	//Inicializa el codigo del alumno
	private static int i = 0;

	public AlumnoServiceImp() {
		super();
		//Inicializamos la lista
		alumnos = new ArrayList<Alumno>();
		//Llamamos al metodo init()
		init();
	}

	/*
	 * En este metodo vamos a iniciualizar valores para pruebas
	 */
	private void init() {
		//Creamos un nuevo alumno
		Alumno alumno = new Alumno();
		try {
			alumno.setNombre("Sergio");
			alumno.setApellidos("Aparicio Vegas");
			alumno.setDni("44974398z");
			create(alumno);
		} catch (PersonaException e) {
			System.out.println(e.getMessage());
		}

		alumno = new Alumno();
		try {
			alumno.setNombre("Maite");
			alumno.setApellidos("Monasterio Herrero");
			alumno.setDni("16071559x");
			String date = "19/11/1984";
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			alumno.setfNacimiento(dateFormat.parse(date));
			create(alumno);
		} catch (PersonaException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error no controlado" + e.getMessage());
		}

		alumno = new Alumno();
		try {
			alumno.setNombre("Jon");
			alumno.setApellidos("Martinez Perez");
			alumno.setDni("16071559x");
			create(alumno);
		} catch (PersonaException e) {
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

	public void delete(int codigo) {
		int posicion = -1;
		try {
			posicion = buscarAlumno(codigo);
			alumnos.remove(posicion);
		} catch (AlumnoServiceImpException e) {
			System.out.println(e.getMessage());
		}
	}

	private int buscarAlumno(int codigo) throws AlumnoServiceImpException {

		int i = 0, posicion = -1;
		boolean encontrado = false;
		while (encontrado == false && i < alumnos.size()) {
			//Guarda el objeto i de la lista en el objeto aux
			Alumno aux = alumnos.get(i);
			//Si el codigo del objeto aux es igual que el codigo...
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
			//Busca la posicion del alumno con ese codigo
			posicion = buscarAlumno(alumno.getCodigo());
			//Modifica la lista
			alumnos.set(posicion, alumno);
		} catch (AlumnoServiceImpException e) {
			System.out.println(e.getMessage());
		}
		return alumno;
	}

	@Override
	public Alumno getByID(int codigo) {
		Alumno alumno = null;
		int posicion = -1;
		try {
			//Busca la posicion del alumno con ese codigo
			posicion = buscarAlumno(codigo);
			//guarda en la variable alumno el objeto con esa posicion
			alumno = alumnos.get(posicion);
		} catch (AlumnoServiceImpException e) {
			System.out.println(e.getMessage());
			//Creamos un nuevo alumno para evitar NullpointerException
			alumno = new Alumno();
		}
		return alumno;
	}
}
