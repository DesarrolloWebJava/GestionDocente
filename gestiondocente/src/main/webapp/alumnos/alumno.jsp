<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<!-- Include de cabecera (header).	
	     Se llama al include dimanico 
	     (posee codigo java que cambie en función de alguna variable).
	     Contiene (<body>).-->	
     <jsp:include page="../includes/header.jsp"/>
	<main>
		<!--  Formulario de Alumnos. Se enlazará con la Servlet de Alumno. -->
		<form action="<%=Constantes.SERVLET_ALUMNO %>" method ="post">
			<!--  Campo de entrada ocupto para el dato alumno.codigo. -->
			<input type="hidden" name="<%=Constantes.PAR_CODIGO %>" 
			       id="<%=Constantes.PAR_CODIGO %>" value ="-1">
			<!-- Caja donde contener los campos con el dato el dato alumno.nombre. -->
			<div>
				<!--  Etiqueta y Campo de entrada para el dato alumno.nombre. -->
				<label for ="<%=Constantes.PAR_NOMBRE %>">Nombre : </label>
				<input type="text" placeholder="Introduzca su nombre"
				       name="<%=Constantes.PAR_NOMBRE %>" 
				       id="<%=Constantes.PAR_NOMBRE %>">
		    </div>
		    <!-- Caja donde contener los campos con el dato el dato alumno.apellido. -->
			<div>
				<!--  Etiqueta y campo de entrada para el dato alumno.apellido. -->
				<label for ="<%=Constantes.PAR_APELLIDOS %>">Apellidos : </label>
				<input type="text" 
				       name="<%=Constantes.PAR_APELLIDOS %>" 
				       id="<%=Constantes.PAR_APELLIDOS %>">		
			</div>
		    <!-- Caja donde contener los campos con el dato el dato alumno.dni. -->
			<div>
				<!--  Etiqueta y campo de entrada para el dato alumno.dni. -->
				<label for ="<%=Constantes.PAR_DNI %>">D.N.I. : </label>
				<input type="text" placeholder="Introduzca su D.N.I."    
				       name="<%=Constantes.PAR_DNI %>" 
				       id="<%=Constantes.PAR_DNI %>">
		    </div>
		    <!-- Caja donde contener los campos con el dato el dato alumno.fnacimiento. -->
			<div>	
				<!--  Etiqueta y campo de entrada para el dato alumno.fnacimiento. -->
				<label for ="<%=Constantes.PAR_FNACIMIENTO %>">F. Nacimiento : </label>
				<input type="text" placeholder="Introduzca su fecha de nacimiento." 
				       name="<%=Constantes.PAR_FNACIMIENTO %>" 
				       id="<%=Constantes.PAR_FNACIMIENTO %>">
		    </div>
		    <!-- Caja donde contener los campos con el dato el dato alumno.dirección. -->
			<div>	
				<!--  Etiqueta y campo de entrada para el dato alumno.dirección. -->
				<label for ="<%=Constantes.PAR_DIRECCION %>">Dirección : </label>
				<input type="text" placeholder="Introduzca su dirección ." 
				       name="<%=Constantes.PAR_DIRECCION %>" 
				       id="<%=Constantes.PAR_DIRECCION %>">	
		    </div>
		    <!-- Caja donde contener los campos con el dato el dato alumno.email. -->
			<div>
				<!--  Etiqueta y campo de entrada para el dato alumno.email. -->
				<label for ="<%=Constantes.PAR_EMAIL %>">Email : </label>
				<input type="text" placeholder="Introduzca su email ."   
				       name="<%=Constantes.PAR_EMAIL %>" 
				       id="<%=Constantes.PAR_EMAIL %>">	
			</div>
		    <!-- Caja donde contener los campos con el dato el dato alumno.nhermanos. -->
			<div>
				<!--  Etiqueta y campo de entrada para el dato alumno.nhermanos. -->
				<label for ="<%=Constantes.PAR_NHERMANOS %>">Nº Hermanos : </label>
				<input type="text" placeholder="Introduzca el número de hermanos ." 
				       name="<%=Constantes.PAR_NHERMANOS %>" 
				       id="<%=Constantes.PAR_NHERMANOS %>">	
			 </div>
			 <!-- Caja donde contener los campos con el dato el dato alumno.activo. -->
			 <div>
				<!--  Selección de entrada para el dato alumno.activo. -->
				<label for ="<%=Constantes.PAR_ACTIVO %>">Activo : </label>
				<select name="<%=Constantes.PAR_NHERMANOS %>"> 
					<option value="1">Activo</option>
					<option value="0">Desactivo</option>	
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