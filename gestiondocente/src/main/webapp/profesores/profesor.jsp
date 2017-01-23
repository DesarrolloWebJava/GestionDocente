<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<!-- Include de cabecera (header).	
	     Se llama al include dimanico 
	     (posee codigo java que cambie en función de alguna variable).
	     Contiene (<body>).-->	
     <jsp:include page="../includes/header.jsp"/>
     
      <%
     	/* Se declara la variable que se va a utilizar para asignar código del profesor.*/
     	String titulo = "";
     	/* Se recoge el profesor que se ha asignado al request.*/
     	Profesor profesor = (Profesor) request.getAttribute(Constantes.ATT_LISTADO_PROFESORES);
     	/* Se comprueba si se ha recibido un profesor. 
     	  (En caso de recibirlo se quiere modificar,de lo contrario es para modificar.)*/
	     if(profesor == null){
	    	 /* Se asigna el titulo de la creación de profesor.*/
	    	 titulo = "Crear Alumno.";
	    	 /* Se crea el profesor*/
	    	 profesor = new Profesor();    	 
	     } else {
	    	 /* Se asigna el titulo de la modificación de profesor.*/
	    	 titulo = "Crear Alumno.";
	     }	    	 
	  %>
	<main>
		<header>
			<h2> <%=titulo %> </h2>
		</header>
		<!--  Formulario de Profesor. Se enlazará con la Servlet de Profesor. -->
		<form action="<%=Constantes.SERVLET_PROFESOR %>" method ="post">
			<!--  Campo de entrada ocupto para el dato profesor.codigo. -->
			<input type="hidden" name="<%=Constantes.PAR_CODIGO %>" 
			       id="<%=Constantes.PAR_CODIGO %>" value="<%=profesor.getCodigo()%>">
			<!-- Caja donde contener los campos con el dato el dato profesor.nombre. -->
			<div>
				<!--  Etiqueta y Campo de entrada para el dato profesor.nombre. -->
				<!-- Se asigna el valor del nombre del profesor recogido del request. -->
				<label for ="<%=Constantes.PAR_NOMBRE %>">Nombre : </label>
				<input type="text" placeholder="Introduzca su nombre"
				       name="<%=Constantes.PAR_NOMBRE %>" 			       
				       value="<%=profesor.getNombre()%>">	
		    </div>
		    <!-- Caja donde contener los campos con el dato el dato profesor.apellido. -->
			<div>
				<!--  Etiqueta y campo de entrada para el dato profesor.apellido. -->
				<!-- Se asigna el valor de los apellidos del profesor recogido del request. -->
				<label for ="<%=Constantes.PAR_APELLIDOS %>">Apellidos : </label>
				<input type="text" 
				       name="<%=Constantes.PAR_APELLIDOS %>" 
				       id="<%=Constantes.PAR_APELLIDOS %>"			       
				       value="<%=profesor.getApellidos()%>">			
			</div>
		    <!-- Caja donde contener los campos con el dato el dato profesor.dni. -->
			<div>
				<!--  Etiqueta y campo de entrada para el dato profesor.dni. -->
				<!-- Se asigna el valor del dni del profesor recogido del request. -->
				<label for ="<%=Constantes.PAR_DNI %>">D.N.I. : </label>
				<input type="text" placeholder="Introduzca su D.N.I."    
				       name="<%=Constantes.PAR_DNI %>" 
				       id="<%=Constantes.PAR_DNI %>"			       
				       value="<%=profesor.getDni()%>">
		    </div>
		    <!-- Caja donde contener los campos con el dato el dato profesor.fnacimiento. -->
			<div>
				<%
				/* Se inicializa la variable donde se va a parsear la fecha a String.*/
				String date ="";
				/* Se llama a la instancia del calendario Gregoriano.*/
				GregorianCalendar gc = new GregorianCalendar();
				/* Se asigna la fecha de nacimiento del alumno al gregoriano.*/
				gc.setTime(profesor.getfNacimiento());
				/* Se construye la fecha sobre un String.
				   (Enero lo interpreta como mes 0,por lo que se ha de sumar 1).*/
				date = gc.get(GregorianCalendar.DAY_OF_MONTH) + "/" + 
					   gc.get(GregorianCalendar.MONTH+1) + "/" + 
					   gc.get(GregorianCalendar.YEAR);
				%>	
				<!--  Etiqueta y campo de entrada para el dato profesor.fnacimiento. -->
				<!-- Se asigna el valor de la fecha de nacimiento del alumno parseado. -->			
				<label for ="<%=Constantes.PAR_FNACIMIENTO %>">F. Nacimiento : </label>
				<input type="text" placeholder="Introduzca su fecha de nacimiento." 
				       name="<%=Constantes.PAR_FNACIMIENTO %>" 
				       id="<%=Constantes.PAR_FNACIMIENTO %>" 
				       value="<%=date%>">
		    </div>
		    <!-- Caja donde contener los campos con el dato el dato profesor.dirección. -->
			<div>	
				<!--  Etiqueta y campo de entrada para el dato profesor.dirección. -->
				<!-- Se asigna el valor del direccion del profesor recogido del request. -->
				<label for ="<%=Constantes.PAR_DIRECCION %>">Dirección : </label>
				<input type="text" placeholder="Introduzca su dirección ." 
				       name="<%=Constantes.PAR_DIRECCION %>" 			       
				       value="<%=profesor.getDireccion()%>">
		    </div>
		    <!-- Caja donde contener los campos con el dato el dato profesor.email. -->
			<div>
				<!--  Etiqueta y campo de entrada para el dato profesor.email. -->
				<!-- Se asigna el valor del email del profesor recogido del request. -->
				<label for ="<%=Constantes.PAR_EMAIL %>">Email : </label>
				<input type="text" placeholder="Introduzca su email ."   
				       name="<%=Constantes.PAR_EMAIL %>" 
				       id="<%=Constantes.PAR_EMAIL %>"			       
				       value="<%=profesor.getEmail()%>">	
			</div>
			<!-- Caja donde contener los campos con el dato el dato profesor.nss. -->
			<!-- Se asigna el valor del nombre del profesor recogido del request. -->
			<div>
				<!--  Etiqueta y campo de entrada para el dato profesor.nss. -->
				<label for ="<%=Constantes.PAR_NSS %>">N.S.S. : </label>
				<input type="text" placeholder="Introduzca su nº de la S.S."   
				       name="<%=Constantes.PAR_NSS %>" 
				       id="<%=Constantes.PAR_NSS %>"			       
				       value="<%=profesor.getnSS()%>">	
			</div>
		    
			<!--  Botón para el envio de los datos del formulario (profesor.*). -->
			<input type="submit" value="Enviar"/>
		</form>
	</main>
	<!-- Include del Pie de la página.	
		  Se llama al include estetico 
		  (no posee codigo java que cambie en función de alguna variable).
		  Contiene las etiquetas <header> y </header>.-->
	<%@ include file ="../includes/footer.html" %>	
</body>
</html>