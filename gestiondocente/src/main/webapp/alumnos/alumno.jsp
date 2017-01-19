<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- Se le informa al navegador que le enviamos codigo en codificación UTF-8. -->
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"> 
	<!-- Se le informa al navegador el autor de la página. -->
	<meta name="author" content="Raúl de Roba">  		
	<!-- Se le informa al navegador la fecha de la última versión. -->
	<meta name="revised" content="Raúl de Roba,18/01/17">  	
	<!-- Se informa al navegador la descripción y/o objetivo de la pagina.-->
	<meta name="description" content="Gestión Docente.Ipartek.Gestión de Alumnos.">
	<!-- Se le informa al navegador con que software se ha realizado el código. -->
	<meta name="generator" content="Eclipse Mars"> 
	<!-- Se le informa al buscador de las palbras claves. -->
	<meta name="keywords" content="Gestión Docente.Ipartek.Raúl de Roba.
								   Raul de Roba.Gestión de Alumnos.">  				
	<!-- Con rel indicamos la relación con el documento,en este caso se le indica que es 
	       una hoja de estilo.
		 type indica el tipo de documento,en este caso texto que contiene css.
		 href indica la ruta fisica del documento.
		 media indica la salida (screen o print) de esa manera el explorador usa una u 
		   otra en función de la salida.-->
	<link rel="stylesheet" type="text/css" href="gestiondocente.css" media="screen">		
	<!-- Es la primera etiqueta que busca los buscadores,por lo que conviene que sea descriptivo. -->
	<title>Gestión Docente - Crear Alumno</title>
</head>
<body>
	<!--  Formulario de Alumnos. Se enlazará con la Servlet de Alumno. -->
	<form action="<%=Constantes.SERVLET_ALUMNO %>" method ="post">
		<!--  Campo de entrada ocupto para el dato alumno.codigo. -->
		<input type="hidden" name="<%=Constantes.PAR_CODIGO %>" 
		       id="<%=Constantes.PAR_CODIGO %>" value ="-1">
		<!-- Caja donde contener los campos con el dato el dato alumno.nombre. -->
		<div>
			<!--  Etiqueta y Campo de entrada para el dato alumno.nombre. -->
			<label for ="<%=Constantes.PAR_NOMBRE %>">Nombre : </label>
			<input type="text" name="<%=Constantes.PAR_NOMBRE %>" 
			       id="<%=Constantes.PAR_NOMBRE %>" value ="-1">
	    </div>
	    <!-- Caja donde contener los campos con el dato el dato alumno.apellido. -->
		<div>
			<!--  Etiqueta y campo de entrada para el dato alumno.apellido. -->
			<label for ="<%=Constantes.PAR_APELLIDOS %>">Apellidos : </label>
			<input type="text" placeholder="Introduzca su nombre" 
			       name="<%=Constantes.PAR_APELLIDOS %>" 
			       id="<%=Constantes.PAR_APELLIDOS %>" value ="-1">		
		</div>
	    <!-- Caja donde contener los campos con el dato el dato alumno.dni. -->
		<div>
			<!--  Etiqueta y campo de entrada para el dato alumno.dni. -->
			<label for ="<%=Constantes.PAR_DNI %>">D.N.I. : </label>
			<input type="text" placeholder="Introduzca su apellido" 
			       name="<%=Constantes.PAR_DNI %>" 
			       id="<%=Constantes.PAR_DNI %>" value ="-1">
	    </div>
	    <!-- Caja donde contener los campos con el dato el dato alumno.fnacimiento. -->
		<div>	
			<!--  Etiqueta y campo de entrada para el dato alumno.fnacimiento. -->
			<label for ="<%=Constantes.PAR_FNACIMIENTO %>">F. Nacimiento : </label>
			<input type="text" placeholder="Introduzca su fecha de nacimiento." 
			       name="<%=Constantes.PAR_FNACIMIENTO %>" 
			       id="<%=Constantes.PAR_FNACIMIENTO %>" value ="-1">
	    </div>
	    <!-- Caja donde contener los campos con el dato el dato alumno.dirección. -->
		<div>	
			<!--  Etiqueta y campo de entrada para el dato alumno.dirección. -->
			<label for ="<%=Constantes.PAR_DIRECCION %>">Dirección : </label>
			<input type="text" placeholder="Introduzca su dirección ." 
			       name="<%=Constantes.PAR_DIRECCION %>" 
			       id="<%=Constantes.PAR_DIRECCION %>" value ="-1">	
	    </div>
	    <!-- Caja donde contener los campos con el dato el dato alumno.email. -->
		<div>
			<!--  Etiqueta y campo de entrada para el dato alumno.email. -->
			<label for ="<%=Constantes.PAR_EMAIL %>">Email : </label>
			<input type="text" placeholder="Introduzca su email ." 
			       name="<%=Constantes.PAR_EMAIL %>" 
			       id="<%=Constantes.PAR_EMAIL %>" value ="-1">	
		</div>
	    <!-- Caja donde contener los campos con el dato el dato alumno.nhermanos. -->
		<div>
			<!--  Etiqueta y campo de entrada para el dato alumno.nhermanos. -->
			<label for ="<%=Constantes.PAR_NHERMANOS %>">Nº Hermanos : </label>
			<input type="text" placeholder="Introduzca el número de hermanos ." 
			       name="<%=Constantes.PAR_NHERMANOS %>" 
			       id="<%=Constantes.PAR_NHERMANOS %>" value ="-1">	
		 </div>
		 <!-- Caja donde contener los campos con el dato el dato alumno.activo. -->
		 <div>
			<!--  Selección de entrada para el dato alumno.activo. -->
			<label for ="<%=Constantes.PAR_ACTIVO %>">Activo : </label>
			<select name="<%=Constantes.PAR_NHERMANOS %>"> 
				<option value="1">Activo</option>
				<option value="2">Desactivo</option>	
			</select>
		</div>
		<!--  Botón para el envio de los datos del formulario (alumno.*). -->
		<input type="submit" value="Enviar"/>
	</form>
</body>
</html>