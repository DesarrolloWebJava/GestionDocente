<!-- Se importa la libreria de JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Se importa la libreria de FMT 
     (Gesti�n de las claves de los ficheros de propiedades) -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Se importa la libreria de las constantes para los Servlets. -->
<%@page import="com.ipartek.formacion.controller.Constantes"%>

<!-- Se asigna el idioma de la sesion,
     en caso de no existir se asigna castellano(es_ES). -->
<c:set var="language" 
       value="${not empty sessionScope.language ? sessionScope.language : 'es_ES'}" 
       scope="page" />
<!-- Se indica el formateo del fichero de propiedades al lenguaje de la p�gina,
     es decir se le indica de que fichero ha de recogerlo.-->
<fmt:setLocale value="${language}" />
<!-- Se indica la ruta de los ficheros de propiedades de la internacionalizacion. -->
<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmessages" />
	
<!-- Se declara la codificaci�n Html 5. -->
<!DOCTYPE html>
<!-- Se especifica el lenguaje de la p�gina(recogido del locate). -->
<html lang="${language}">
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
		 otra en funci�n de la salida.
		 Con JSTL se usa la instrucci�n 'url' para enrutar la direcci�n,ya que apache
		 genera sus propias rutas independientemente de las rutas de WebApp. -->		   
	<link rel="stylesheet" href="<c:url value="/css/styles.css" />" type="text/css"  media="screen">	
	<!-- Se declaran las rutas de los ficheros JavaScript. 
		 Con JSTL se usa la instrucci�n 'url' para enrutar la direcci�n,ya que apache
		 genera sus propias rutas independientemente de las rutas de WebApp. -->
	<script scr="<c:url value="/js/codigo.js" />"></script>
	
	      	
	<!-- Es la primera etiqueta que busca los buscadores,por lo que conviene que sea descriptivo. -->
	<title> Gesti�n Docente.Ipartek. (En Construcci�n)</title>
</head>

<!-- Cuerpo de la p�gina para el usuario (contenido). -->	
<body>
	<!-- Cabecera de la p�gina.	-->	
	<header>
		<!-- Titulo de la p�gina.	-->	
		<h1>
			<!-- Se recoge la traduci�n del fichero de propiedades de 
			     internacionalizazi�n para el nombre de la aplicaci�n. -->
			<fmt:message key="index.nombreApp"/> 
		</h1>
		<h3>
			<!-- Se recoge la traduci�n del fichero de propiedades de 
			     internacionalizazi�n para el mensaje de bienvenida.-->
			<fmt:message key="index.bienvenida"/> 
		</h3>
		<!-- Caja que indica que su contenido es de navegaci�n.	-->	
		<nav>
			<!-- Lista no numerada.	-->	
			<ul>
				<!-- L�nea de la lista.	Gesti�n de Alumno.-->	
				<li>
					<!-- Enlace a la gesti�n de Alumno. Se llama al servlet 'Alumno' -->	
					<a href="<%=Constantes.SERVLET_ALUMNO%>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_READ%>">
						<!-- Se recoge la traduci�n del fichero de propiedades de 
			     			 internacionalizazi�n para la opcion el menu. -->
						<fmt:message key="index.gestionAlumnos"/> 
					</a>
				</li>
				<!-- L�nea de la lista.	Gesti�n de Profesores.-->
				<li>
					<!-- Enlace a la gesti�n de Profesor. Se llama al servlet 'Profesor' -->	
					<a href="<%=Constantes.SERVLET_PROFESOR%>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_READ%>">
						<!-- Se recoge la traduci�n del fichero de propiedades de 
			     			 internacionalizazi�n para la opcion el menu. -->
						<fmt:message key="index.gestionProfesores"/> 
					</a>
				</li>
				<!-- L�nea de la lista.	Gesti�n de Curso.-->
				<li>
					<!-- Enlace a la gesti�n de Cursos. Se llama al servlet 'Curso' -->	
					<a href="<%=Constantes.SERVLET_CURSO%>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_READ%>">
						<!-- Se recoge la traduci�n del fichero de propiedades de 
			     			 internacionalizazi�n para la opcion el menu. -->
						<fmt:message key="index.gestionCursos"/> 
					</a>
				</li>			
			</ul>
		</nav>
		<!-- Idiomas. -->
		<nav>
			<!-- Enlace a la gesti�n de Idioma para la traducci�n al Castellano -->	
			<a href="<%=Constantes.SERVLET_IDIOMA%>?<%=Constantes.PAR_IDIOMA%>=<%=Constantes.IDIOMA_CASTELLANO%>">
   					<fmt:message key="idioma.castellano"/></a> 
			<!-- Enlace a la gesti�n de Idioma para la traducci�n al Euskera -->
			<a href="<%=Constantes.SERVLET_IDIOMA%>?<%=Constantes.PAR_IDIOMA%>=<%=Constantes.IDIOMA_EUSKERA%>">
			    <fmt:message key="idioma.euskera"/></a> 
			<!-- Enlace a la gesti�n de Idioma para la traducci�n al Ingles -->
			<a href="<%=Constantes.SERVLET_IDIOMA%>?<%=Constantes.PAR_IDIOMA%>=<%=Constantes.IDIOMA_INGLES%>">
			    <fmt:message key="idioma.ingles"/></a> 
			<!--  Se comprueba si exite la sesi�n de cara a mostrar el boto�n o no. -->
			<%				
			    /* Por defecto las JSP tienen envebida la session por lo tanto se puede utilizar.
			       omprobamos si tenemos una sesi�n creada.
			       Con isNew comprobamos si la sessi�n ha sido creada en la p�gina actual. */
				if(session != null && 
				   !session.isNew() && 
				   session.getAttribute(Constantes.SESION_IDIOMA) != null){
			%>
				<!-- Enlace al cierre de sesion. -->
				<a href="<%=Constantes.SERVLET_LOGIN%>">
				                              <fmt:message key="index.cerrarSesion"/></a>
			<%
				}
			%>			    
						
		</nav>				
		<!-- Include de mensanjes.	
		  Se llama al include dimanico 
		  (posee codigo java que cambie en funci�n de alguna variable).-->	
		<jsp:include page="mensajes.jsp"/>
	</header>
