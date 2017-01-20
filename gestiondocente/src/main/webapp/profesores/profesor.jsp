<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<!-- Include de cabecera (header).	
	     Se llama al include dimanico 
	     (posee codigo java que cambie en función de alguna variable).
	     Contiene (<body>).-->	
     <jsp:include page="../includes/header.jsp"/>
	<main>
		<!--  Formulario de Profesor. Se enlazará con la Servlet de Profesor. -->
		<form action="<%=Constantes.SERVLET_PROFESOR %>" method ="post">
			<!--  Campo de entrada ocupto para el dato profesor.codigo. -->
			<input type="hidden" name="<%=Constantes.PAR_CODIGO %>" 
			       id="<%=Constantes.PAR_CODIGO %>" value ="-1">
			<!-- Caja donde contener los campos con el dato el dato profesor.nombre. -->
			<div>
				<!--  Etiqueta y Campo de entrada para el dato profesor.nombre. -->
				<label for ="<%=Constantes.PAR_NOMBRE %>">Nombre : </label>
				<input type="text" placeholder="Introduzca su nombre"
				       name="<%=Constantes.PAR_NOMBRE %>" 
				       id="<%=Constantes.PAR_NOMBRE %>">
		    </div>
		    <!-- Caja donde contener los campos con el dato el dato profesor.apellido. -->
			<div>
				<!--  Etiqueta y campo de entrada para el dato profesor.apellido. -->
				<label for ="<%=Constantes.PAR_APELLIDOS %>">Apellidos : </label>
				<input type="text" 
				       name="<%=Constantes.PAR_APELLIDOS %>" 
				       id="<%=Constantes.PAR_APELLIDOS %>">		
			</div>
		    <!-- Caja donde contener los campos con el dato el dato profesor.dni. -->
			<div>
				<!--  Etiqueta y campo de entrada para el dato profesor.dni. -->
				<label for ="<%=Constantes.PAR_DNI %>">D.N.I. : </label>
				<input type="text" placeholder="Introduzca su D.N.I."    
				       name="<%=Constantes.PAR_DNI %>" 
				       id="<%=Constantes.PAR_DNI %>">
		    </div>
		    <!-- Caja donde contener los campos con el dato el dato profesor.fnacimiento. -->
			<div>	
				<!--  Etiqueta y campo de entrada para el dato profesor.fnacimiento. -->
				<label for ="<%=Constantes.PAR_FNACIMIENTO %>">F. Nacimiento : </label>
				<input type="text" placeholder="Introduzca su fecha de nacimiento." 
				       name="<%=Constantes.PAR_FNACIMIENTO %>" 
				       id="<%=Constantes.PAR_FNACIMIENTO %>">
		    </div>
		    <!-- Caja donde contener los campos con el dato el dato profesor.dirección. -->
			<div>	
				<!--  Etiqueta y campo de entrada para el dato profesor.dirección. -->
				<label for ="<%=Constantes.PAR_DIRECCION %>">Dirección : </label>
				<input type="text" placeholder="Introduzca su dirección ." 
				       name="<%=Constantes.PAR_DIRECCION %>" 
				       id="<%=Constantes.PAR_DIRECCION %>">	
		    </div>
		    <!-- Caja donde contener los campos con el dato el dato profesor.email. -->
			<div>
				<!--  Etiqueta y campo de entrada para el dato profesor.email. -->
				<label for ="<%=Constantes.PAR_EMAIL %>">Email : </label>
				<input type="text" placeholder="Introduzca su email ."   
				       name="<%=Constantes.PAR_EMAIL %>" 
				       id="<%=Constantes.PAR_EMAIL %>">	
			</div>
			<!-- Caja donde contener los campos con el dato el dato profesor.nss. -->
			<div>
				<!--  Etiqueta y campo de entrada para el dato profesor.nss. -->
				<label for ="<%=Constantes.PAR_NSS %>">N.S.S. : </label>
				<input type="text" placeholder="Introduzca su nº de la S.S."   
				       name="<%=Constantes.PAR_NSS %>" 
				       id="<%=Constantes.PAR_NSS %>">	
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