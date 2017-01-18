<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- Se le informa al navegador que le enviamos codigo en codificación UTF-8. -->
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"> 
	<!-- Se le informa al navegador el autor de la página. -->
	<meta name="author" content="Raúl de Roba">  		
	<!-- Se le informa al navegador la fecha de la última versión. -->
	<meta name="revised" content="Raúl de Roba,18/01/17">  	
	<!-- Se informa al navegador la descripción y/o objetivo de la pagina.-->
	<meta name="description" content="Gestión Docente.Ipartek.">
	<!-- Se le informa al navegador con que software se ha realizado el código. -->
	<meta name="generator" content="Eclipse Mars"> 
	<!-- Se le informa al buscador de las palbras claves. -->
	<meta name="keywords" content="Gestión Docente.Ipartek.Raúl de Roba.Raul de Roba.">  				
	<!-- Con rel indicamos la relación con el documento,en este caso se le indica que es 
	       una hoja de estilo.
		 type indica el tipo de documento,en este caso texto que contiene css.
		 href indica la ruta fisica del documento.
		 media indica la salida (screen o print) de esa manera el explorador usa una u 
		   otra en función de la salida.-->
	<link rel="stylesheet" type="text/css" href="gestiondocente.css" media="screen">		
	<!-- Es la primera etiqueta que busca los buscadores,por lo que conviene que sea descriptivo. -->
	<title> Gestión Docente.Ipartek. (En Construcción)</title>
</head>

<!-- Información para el usuario (contenido)	-->	
<body>
	Bienvenidos a Gestión Alumnos


	<!-- Cabecera de la página.	-->	
	<header>
		<!-- Titulo de la página.	-->	
		<h1>Gestión Docente</h1>
		<!-- Caja que indica que su contenido es de navegación.	-->	
		<nav>
			<!-- Lista no numerada.	-->	
			<ul>
				<!-- Línea de la lista.	Gestión de Alumno.-->	
				<li>
					<!-- Enlace a la gestión de Alumno.	
					     Se llama al servlet 'Alumno'-->	
					<a href="alumno.do">G. Alumnos</a>
				</li>
				<!-- Línea de la lista.	Gestión de Profesores.-->
				<li>
					<!-- Enlace a la gestión de Profesor.	-->	
					<a href="profesor.do">G. Profesores</a>
				</li>
				<!-- Línea de la lista.	Gestión de Curso.-->
				<li>
					<!-- Enlace a la gestión de Curso.	-->	
					<a href="#">G. Cursos</a>
				</li>			
			</ul>
		</nav>
	</header>
	
	<!-- Indica que su contenido no es estatico.	-->	
	<main>
	</main>
	
	<!-- Pie de la página.	-->	
	<footer>
		<p> Realizado por Raúl de Roba 2.017 </p>
	</footer>									
</body>

</html>