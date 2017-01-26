<!-- Se importa la libreria de JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Se importa la libreria de las constantes para los Servlets. -->
<%@page import="com.ipartek.formacion.controller.Constantes"%>

<!-- Se declara la codificación Html 5. -->
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
		 otra en función de la salida.
		 Con JSTL se usa la instrucción 'url' para enrutar la dirección,ya que apache
		 genera sus propias rutas independientemente de las rutas de WebApp. -->		   
	<link rel="stylesheet" href="<c:url value="/css/styles.css" />" type="text/css"  media="screen">	
	<!-- Se declaran las rutas de los ficheros JavaScript. 
		 Con JSTL se usa la instrucción 'url' para enrutar la dirección,ya que apache
		 genera sus propias rutas independientemente de las rutas de WebApp. -->
	<script scr="<c:url value="/js/codigo.js" />"></script>
	
	      	
	<!-- Es la primera etiqueta que busca los buscadores,por lo que conviene que sea descriptivo. -->
	<title> Gestión Docente.Ipartek. (En Construcción)</title>
</head>

<!-- Cuerpo de la página para el usuario (contenido). -->	
<body>
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
					<!-- Enlace a la gestión de Alumno. Se llama al servlet 'Alumno' -->	
					<a href="<%=Constantes.SERVLET_ALUMNO%>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_READ%>">
						G. Alumnos
					</a>
				</li>
				<!-- Línea de la lista.	Gestión de Profesores.-->
				<li>
					<!-- Enlace a la gestión de Profesor. Se llama al servlet 'Profesor' -->	
					<a href="<%=Constantes.SERVLET_PROFESOR%>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_READ%>">
						G. Profesores</a>
				</li>
				<!-- Línea de la lista.	Gestión de Curso.-->
				<li>
					<!-- Enlace a la gestión de Cursos. Se llama al servlet 'Curso' -->	
					<a href="<%=Constantes.SERVLET_CURSO%>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_READ%>">
						G. Cursos</a>
				</li>			
			</ul>
		</nav>
		<!-- Include de mensanjes.	
		  Se llama al include dimanico 
		  (posee codigo java que cambie en función de alguna variable).-->	
		<jsp:include page="mensajes.jsp"/>
	</header>
