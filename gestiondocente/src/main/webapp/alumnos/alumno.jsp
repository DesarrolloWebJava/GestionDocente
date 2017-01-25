<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<!-- Include de cabecera (header).	
	     Se llama al include dimanico 
	     (posee codigo java que cambie en función de alguna variable).
	     Contiene (<body>).-->	
     <jsp:include page="../includes/header.jsp"/>
     
     <%
     	/* Se declara la variable que se va a utilizar para asignar código del alumno.*/
     	String titulo = "";
     	/* Se recoge el alumno que se ha asignado al request.*/
     	Alumno alumno = (Alumno) request.getAttribute(Constantes.ATT_ALUMNOS);
     	/* Se comprueba si se ha recibido un alumno. 
     	  (En caso de recibirlo se quiere modificar,de lo contrario es para modificar.)*/
	     if(alumno == null){
	    	 /* Se asigna el titulo de la creación de alumno.*/
	    	 titulo = "Crear Alumno.";
	    	 /* Se crea el alumno*/
	    	 alumno = new Alumno();    	 
	     } else {
	    	 /* Se asigna el titulo de la modificación de alumno.*/
	    	 titulo = "Modificar Alumno.";
	     }	    	 
	  %>
	<main>
		<header>
			<h2> <%=titulo %> </h2>
		</header>
		
		
	
		<!--  Formulario de Alumnos. Se enlazará con la Servlet de Alumno. -->
		<!-- Se asigna el valor del codigo del alumno recogido del request. -->
		<form action="<%=Constantes.SERVLET_ALUMNO %>" method ="post">
			<!--  Campo de entrada ocupto para el dato alumno.codigo. -->
			<input type="hidden" name="<%=Constantes.PAR_CODIGO %>" 
			       id="<%=Constantes.PAR_CODIGO %>" value="<%=alumno.getCodigo()%>">
			<!-- Caja donde contener los campos con el dato el dato alumno.nombre. -->
			<div>
				<!-- Etiqueta y Campo de entrada para el dato alumno.nombre. -->
				<!-- Se asigna el valor del nombre del alumno recogido del request. -->
				<label for ="<%=Constantes.PAR_NOMBRE %>">Nombre : </label>
				<input type="text" placeholder="Introduzca su nombre"
				       name="<%=Constantes.PAR_NOMBRE %>" 
				       id="<%=Constantes.PAR_NOMBRE %>"				       
				       value="<%=alumno.getNombre()%>">		
		    </div>
		    <!-- Caja donde contener los campos con el dato el dato alumno.apellido. -->
			<div>
				<!--  Etiqueta y campo de entrada para el dato alumno.apellido. -->
				<!-- Se asigna valor de los apellidos del alumno recogido del request. -->
				<label for ="<%=Constantes.PAR_APELLIDOS %>">Apellidos : </label>
				<input type="text" 
				       name="<%=Constantes.PAR_APELLIDOS %>" 
				       id="<%=Constantes.PAR_APELLIDOS %>"
				       value="<%=alumno.getApellidos()%>">		
			</div>
		    <!-- Caja donde contener los campos con el dato el dato alumno.dni. -->
			<div>
				<!--  Etiqueta y campo de entrada para el dato alumno.dni. -->
				<!-- Se asigna el valor del dni del alumno recogido del request. -->
				<label for ="<%=Constantes.PAR_DNI %>">D.N.I. : </label>
				<input type="text" placeholder="Introduzca su D.N.I."    
				       name="<%=Constantes.PAR_DNI %>" 
				       value="<%=alumno.getDni()%>">	
		    </div>
		    <!-- Caja donde contener los campos con el dato el dato alumno.fnacimiento. -->
			<div>	
				<%
				/* Se inicializa la variable donde se va a parsear la fecha a String.*/
				String date ="";
				/* Se llama a la instancia del calendario Gregoriano.*/
				GregorianCalendar gc = new GregorianCalendar();
				/* Se asigna la fecha de nacimiento del alumno al gregoriano.*/
				gc.setTime(alumno.getfNacimiento());
				/* Se construye la fecha sobre un String.
				   (Enero lo interpreta como mes 0,por lo que se ha de sumar 1).*/
				date = gc.get(GregorianCalendar.DAY_OF_MONTH) + "/" + 
					   (gc.get(GregorianCalendar.MONTH) + 1) + "/" + 
					   gc.get(GregorianCalendar.YEAR);
				%>
				<!--  Etiqueta y campo de entrada para el dato alumno.fnacimiento. -->
				<!-- Se asigna el valor de la fecha de nacimiento del alumno parseado. -->				
				<label for ="<%=Constantes.PAR_FNACIMIENTO %>">F. Nacimiento : </label>
				<input type="text" placeholder="Introduzca su fecha de nacimiento." 
				       name="<%=Constantes.PAR_FNACIMIENTO %>" 
				       value="<%=date%>">
		    </div>
		    <!-- Caja donde contener los campos con el dato el dato alumno.dirección. -->
			<div>	
				<!--  Etiqueta y campo de entrada para el dato alumno.dirección. -->
				<!-- Se asigna el valor de la dirección del alumno recogido del request. -->
				<label for ="<%=Constantes.PAR_DIRECCION %>">Dirección : </label>
				<input type="text" placeholder="Introduzca su dirección ." 
				       name="<%=Constantes.PAR_DIRECCION %>" 
				       id="<%=Constantes.PAR_DIRECCION %>" 
				       value="<%=alumno.getDireccion()%>">	
		    </div>
		    <!-- Caja donde contener los campos con el dato el dato alumno.email. -->
			<div>
				<!--  Etiqueta y campo de entrada para el dato alumno.email. -->
				<!-- Se asigna el valor del email del alumno recogido del request. -->
				<label for ="<%=Constantes.PAR_EMAIL %>">Email : </label>
				<input type="text" placeholder="Introduzca su email ."   
				       name="<%=Constantes.PAR_EMAIL %>" 
				       id="<%=Constantes.PAR_EMAIL %>" 
				       value="<%=alumno.getEmail()%>">	
			</div>
		    <!-- Caja donde contener los campos con el dato el dato alumno.nhermanos. -->
			<div>
				<!--  Etiqueta y campo de entrada para el dato alumno.nhermanos. -->
				<!-- Se asigna el valor de los numeros de hermoanos del alumno 
				     recogido del request. -->
				<label for ="<%=Constantes.PAR_NHERMANOS %>">Nº Hermanos : </label>
				<input type="text" placeholder="Introduzca el número de hermanos ." 
				       name="<%=Constantes.PAR_NHERMANOS %>" 
				       id="<%=Constantes.PAR_NHERMANOS %>" 
				       value="<%=alumno.getnHermanos()%>">
			 </div>
			 <!-- Caja donde contener los campos con el dato el dato alumno.activo. -->
			 <div>
				<!--  Selección de entrada para el dato alumno.activo. -->
				<label for ="<%=Constantes.PAR_ACTIVO %>">Activo : </label>
				<select name="<%=Constantes.PAR_ACTIVO %>"> 
				<%
					/* Se comprueba si el alumno está activo.*/
					if (alumno.isActivo()){
				%>
						<!-- Se definen las opciones del control select con el true marcado. -->
						<option selected value="1">Activo</option>
						<option value="0">Desactivo</option>			
				<%
					}else{
				%>
						<!-- Se definen las opciones del control select con el false marcado. -->
						<option value="1">Activo</option>
						<option selected value="0">Desactivo</option>	
				<%
					}
				%>
				</select>
			</div>
			<!--  Botón para el envio de los datos del formulario (alumno.*). -->
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