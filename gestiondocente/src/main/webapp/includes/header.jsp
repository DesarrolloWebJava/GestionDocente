<!-- Se importa la libreria de JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Se importa la libreria de FMT 
     (Gestión de las claves de los ficheros de propiedades) -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Se importa la libreria de las constantes para los Servlets. -->
<%@page import="com.ipartek.formacion.controller.Constantes"%>

<!-- Se asigna el idioma de la sesion,
     en caso de no existir se asigna castellano(es_ES). -->
<c:set var="language" 
       value="${not empty sessionScope.language ? sessionScope.language : 'es_ES'}" 
       scope="session" />
<!-- Se indica el formateo del fichero de propiedades al lenguaje de la página,
     es decir se le indica de que fichero ha de recogerlo.-->
<fmt:setLocale value="${language}" />
<!-- Se indica la ruta de los ficheros de propiedades de la internacionalizacion. -->
<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmessages" />
	
<!-- Se declara la codificación Html 5. -->
<!DOCTYPE html>
<!-- Se especifica el lenguaje de la página(recogido del locate). -->
<html lang="${language}">
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
		<h1>
			<!-- Se recoge la tradución del fichero de propiedades de 
			     internacionalizazión para el nombre de la aplicación. -->
			<fmt:message key="index.nombreApp"/> 
		</h1>
		<h3>
			<!-- Se recoge la tradución del fichero de propiedades de 
			     internacionalizazión para el mensaje de bienvenida.-->
			<fmt:message key="index.bienvenida"/> 
		</h3>
		<!-- Caja que indica que su contenido es de navegación.	-->	
		<nav>
			<!-- Lista no numerada.	-->	
			<ul>
				<!-- Línea de la lista.	Gestión de Alumno.-->	
				<li>
					<!-- Enlace a la gestión de Alumno. Se llama al servlet 'Alumno' -->	
					<a href="<%=Constantes.SERVLET_ALUMNO%>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_READ%>">
						<!-- Se recoge la tradución del fichero de propiedades de 
			     			 internacionalizazión para la opcion el menu. -->
						<fmt:message key="index.gestionAlumnos"/> 
					</a>
				</li>
				<!-- Línea de la lista.	Gestión de Profesores.-->
				<li>
					<!-- Enlace a la gestión de Profesor. Se llama al servlet 'Profesor' -->	
					<a href="<%=Constantes.SERVLET_PROFESOR%>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_READ%>">
						<!-- Se recoge la tradución del fichero de propiedades de 
			     			 internacionalizazión para la opcion el menu. -->
						<fmt:message key="index.gestionProfesores"/> 
					</a>
				</li>
				<!-- Línea de la lista.	Gestión de Curso.-->
				<li>
					<!-- Enlace a la gestión de Cursos. Se llama al servlet 'Curso' -->	
					<a href="<%=Constantes.SERVLET_CURSO%>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_READ%>">
						<!-- Se recoge la tradución del fichero de propiedades de 
			     			 internacionalizazión para la opcion el menu. -->
						<fmt:message key="index.gestionCursos"/> 
					</a>
				</li>			
			</ul>
		</nav>
		<!-- Idiomas. -->
		<nav>
			<!-- Enlace a la gestión de Idioma para la traducción al Castellano -->	
			<a href="<%=Constantes.SERVLET_IDIOMA%>?<%=Constantes.PAR_IDIOMA%>=<%=Constantes.IDIOMA_CASTELLANO%>">
   					Castellano</a> 
			<!-- Enlace a la gestión de Idioma para la traducción al Euskera -->
			<a href="<%=Constantes.SERVLET_IDIOMA%>?<%=Constantes.PAR_IDIOMA%>=<%=Constantes.IDIOMA_EUSKERA%>">
			    Euskera</a> 
			<!-- Enlace a la gestión de Idioma para la traducción al Ingles -->
			<a href="<%=Constantes.SERVLET_IDIOMA%>?<%=Constantes.PAR_IDIOMA%>=<%=Constantes.IDIOMA_INGLES%>">
			    Ingles</a> 
			</a>				
		</nav>				
		<!-- Include de mensanjes.	
		  Se llama al include dimanico 
		  (posee codigo java que cambie en función de alguna variable).-->	
		<jsp:include page="mensajes.jsp"/>
	</header>
