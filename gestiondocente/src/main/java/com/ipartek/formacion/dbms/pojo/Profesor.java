/* Paquete donde se guardan las entidades. */
package com.ipartek.formacion.dbms.pojo;
/**
 * 
 * @author Urko Villanueva
 * @author Raúl de Roba 17/01/17 (Añadido de comentarios.)
 * 
 * <p>Clase de la entidad Profesor (Heredada de la clase persona).</p>
 *
 */
public class Profesor extends Persona {
	/* Constate para valores nulos. Se asigna -1 asumiendo que no se ha introducido 
	 * valor reseñable.ningún*/
	public static final int CODIGO_NULO = -1;
	/* Atributos personalizados de la clase 'Profesor'. */
	private int nSS;
	private int codigo;

	/* Constructor sin parametros de la clase 'Profesor'. */
	public Profesor() {
		/* Constructor de la clase padre. */
		super();
		/* Se asigna el valor nulo. (Recogido de la constante de clase.)*/
		this.codigo = CODIGO_NULO;
		/* Se inicializa el numero de la S.S.*/
		this.nSS = 0;
	}

	/* Getter del atributo 'NSS'.*/
	public int getnSS() {
		/* Se devuelve el numero de la seguridad social.*/
		return nSS;
	}

	/* Setter del atributo 'NSS'.*/
	public void setnSS(int nSS) {
		/* Se asigna la seguridad social.*/
		this.nSS = nSS;
	}

	/* Getter del atributo */
	public int getCodigo() {
		/* Se devuelde el codigo.*/
		return codigo;
	}
	
    /* Setter del atributo 'codigo'. */
	public void setCodigo(int codigo) {
		/* Se asigna el codigo. */
		this.codigo = codigo;
	}
	
	@Override
	/* Metodo que devuelve le objeto como cadena de caracteres.*/
	public String toString() {
		/* Se llama a la clase del padre que conviente la clase a cadena de caracteres.
		 * (Se devuelve un String con el codigo, apellido , el nombre y D.N.I.)
		 * y el nss de la clase.*/
		return super.toString() + " " + this.nSS;
		//return this.getNombre() + " " + this.getApellidos();
	}

}
