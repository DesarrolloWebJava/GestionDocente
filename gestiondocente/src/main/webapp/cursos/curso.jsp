<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Curso"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<!-- Include de cabecera (header).	
	     Se llama al include dimanico 
	     (posee codigo java que cambie en función de alguna variable).
	     Contiene (<body>).-->	
	<jsp:include page="../includes/header.jsp"/>
	 <%
     	/* Se declara la variable que se va a utilizar para asignar código del curso.*/
     	String titulo = "";
     	/* Se recoge el curso que se ha asignado al request.*/
     	Curso curso = (Curso) request.getAttribute(Constantes.ATT_CURSOS);
     	/* Se comprueba si se ha recibido un curso. 
     	  (En caso de recibirlo se quiere modificar,de lo contrario es para modificar.)*/
	     if(curso == null){
	    	 /* Se asigna el titulo de la creación de curso.*/
	    	 titulo = "Crear Curso.";
	    	 /* Se crea el alumno*/
	    	 curso = new Curso();    	 
	     } else {
	    	 /* Se asigna el titulo de la modificación de alumno.*/
	    	 titulo = "Modificar Curso.";
	     }	    	 
	  %>
    
     
    
	<main>
		<header>
			<h2> <%=titulo %> </h2>
		</header>
		<!--  Formulario de Cursos. Se enlazará con la Servlet de Cursos. -->
		<!-- Se asigna el valor del codigo del curso recogido del request. -->
		<form action="<%=Constantes.SERVLET_CURSO %>" method ="post">
			<!--  Campo de entrada ocupto para el dato curso.codigo. -->
			<input type="hidden" name="<%=Constantes.PAR_CODIGO %>" 
			       id="<%=Constantes.PAR_CODIGO %>" value="<%=curso.getCodigo()%>">
			<!-- Caja donde contener los campos con el dato el dato curso.nombre. -->
			<div>
				<!-- Etiqueta y Campo de entrada para el dato curso.nombre. -->
				<!-- Se asigna el valor del nombre del curso recogido del request. -->
				<label for ="<%=Constantes.PAR_NOMBRE %>">Nombre : </label>
				<input type="text" placeholder="Introduzca el nombre del curso"
				       name="<%=Constantes.PAR_NOMBRE %>" 
				       id="<%=Constantes.PAR_NOMBRE %>"				       
				       value="<%=curso.getNombre()%>">		
		    </div>
		    <!-- Caja donde contener los campos con el dato el dato curso.duracion. -->
			<div>
				<!--  Etiqueta y campo de entrada para el dato curso.duracion. -->
				<!-- Se asigna valor de los apellidos del curso recogido del request. -->
				<label for ="<%=Constantes.PAR_DURACION %>">Duración (Horas) : </label>
				<input type="text" 
				       name="<%=Constantes.PAR_DURACION %>" 
				       id="<%=Constantes.PAR_DURACION %>"
				       value="<%=curso.getDuracion()%>">		
		    <!-- Caja donde contener los campos con el dato el dato curso.finicio. -->
			<div>	
				<%
				/* Se inicializa la variable donde se va a parsear la fecha a String.*/
				String date ="";
				/* Se llama a la instancia del calendario Gregoriano.*/
				GregorianCalendar gc = new GregorianCalendar();
				/* Se asigna la fecha de inicio del curso al gregoriano.*/
				gc.setTime(curso.getFinicio());
				/* Se construye la fecha sobre un String.
				   (Enero lo interpreta como mes 0,por lo que se ha de sumar 1).*/
				date = gc.get(GregorianCalendar.DAY_OF_MONTH) + "/" + 
					   (gc.get(GregorianCalendar.MONTH) + 1) + "/" + 
					   gc.get(GregorianCalendar.YEAR);
				%>
				<!--  Etiqueta y campo de entrada para el dato curso.finicio. -->
				<!-- Se asigna el valor de la fecha de inicio del alumno parseado. -->				
				<label for ="<%=Constantes.PAR_FINICIO %>">F. Inicio : </label>
				<input type="text" placeholder="Introduzca la fecha de inicio." 
				       name="<%=Constantes.PAR_FINICIO %>" 
				       value="<%=date%>">
		    </div>
		    
		    <!-- Caja donde contener los campos con el dato el dato curso.ffin. -->
			<div>	
				<%
				/* Se asigna la fecha de fin del curso al gregoriano.
				(No se declaran las variables porque ya han sido decraradas e instanciadas 
				 en la fecha de inicio).*/
				gc.setTime(curso.getFinicio());
				/* Se construye la fecha sobre un String.
				   (Enero lo interpreta como mes 0,por lo que se ha de sumar 1).*/
				date = gc.get(GregorianCalendar.DAY_OF_MONTH) + "/" + 
					   (gc.get(GregorianCalendar.MONTH) + 1) + "/" + 
					   gc.get(GregorianCalendar.YEAR);
				%>
				<!--  Etiqueta y campo de entrada para el dato curso.finicio. -->
				<!-- Se asigna el valor de la fecha de inicio del alumno parseado. -->				
				<label for ="<%=Constantes.PAR_FFIN %>">F. Fin : </label>
				<input type="text" placeholder="Introduzca la fecha de inicio." 
				       name="<%=Constantes.PAR_FFIN %>" 
				       value="<%=date%>">
		    </div>
		    
			<!--  Botón para el envio de los datos del formulario (curso.*). -->
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