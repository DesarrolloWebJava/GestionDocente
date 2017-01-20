<%@page import="com.ipartek.formacion.controller.Constantes"%>

<!DOCTYPE html>
<html>
<head>
	<!-- Se le informa al navegador que le enviamos codigo en codificaci�n UTF-8. -->
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"> 
	<!-- Se le informa al navegador el autor de la p�gina. -->
	<meta name="author" content="Ra�l de Roba">  		
	<!-- Se le informa al navegador la fecha de la �ltima versi�n. -->
	<meta name="revised" content="Ra�l de Roba,18/01/17">  	
	<!-- Se informa al navegador la descripci�n y/o objetivo de la pagina.-->
	<meta name="description" content="Gesti�n Docente.Ipartek.">
	<!-- Se le informa al navegador con que software se ha realizado el c�digo. -->
	<meta name="generator" content="Eclipse Mars"> 
	<!-- Se le informa al buscador de las palbras claves. -->
	<meta name="keywords" content="Gesti�n Docente.Ipartek.Ra�l de Roba.Raul de Roba.">  				
	<!-- Con rel indicamos la relaci�n con el documento,en este caso se le indica que es 
	       una hoja de estilo.
		 type indica el tipo de documento,en este caso texto que contiene css.
		 href indica la ruta fisica del documento.
		 media indica la salida (screen o print) de esa manera el explorador usa una u 
		   otra en funci�n de la salida.-->
	<link rel="stylesheet" type="text/css" href="gestiondocente.css" media="screen">		
	<!-- Es la primera etiqueta que busca los buscadores,por lo que conviene que sea descriptivo. -->
	<title> Gesti�n Docente.Ipartek. (En Construcci�n)</title>
</head>

<!-- Informaci�n para el usuario (contenido)	-->	
<body>
	Bienvenidos a Gesti�n Alumnos


	<!-- Cabecera de la p�gina.	-->	
	<header>
		<!-- Titulo de la p�gina.	-->	
		<h1>Gesti�n Docente</h1>
		<!-- Caja que indica que su contenido es de navegaci�n.	-->	
		<nav>
			<!-- Lista no numerada.	-->	
			<ul>
				<!-- L�nea de la lista.	Gesti�n de Alumno.-->	
				<li>
					<!-- Enlace a la gesti�n de Alumno. Se llama al servlet 'Alumno' -->	
					<a href="<%=Constantes.SERVLET_ALUMNO%>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_READ%>">
						G. Alumnos
					</a>
				</li>
				<!-- L�nea de la lista.	Gesti�n de Profesores.-->
				<li>
					<!-- Enlace a la gesti�n de Profesor. Se llama al servlet 'Profesor' -->	
					<a href="<%=Constantes.SERVLET_PROFESOR%>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_READ%>">
						G. Profesores</a>
				</li>
				<!-- L�nea de la lista.	Gesti�n de Curso.-->
				<li>
					<!-- Enlace a la gesti�n de Curso.	-->	
					<a href="#">G. Cursos</a>
				</li>			
			</ul>
		</nav>
		<!-- Include de mensanjes.	
		  Se llama al include dimanico 
		  (posee codigo java que cambie en funci�n de alguna variable).-->	
		<jsp:include page="mensajes.jsp"/>
	</header>
