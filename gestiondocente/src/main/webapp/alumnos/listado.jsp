<!-- Importación de clases. -->
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="java.util.List"%>

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
	<title>Gestión Alumno. Listado de Alumnos.</title>
</head>
<body>
	<h1>Página Listado de Alumno.</h1>
	<main>
		<!-- ScriptLet que contiene codigo java. -->
		<%
			/* Se recoge el atributo que contiene la lista de alumnos.
				(Previamente asignado en AlumnoServlet).*/
			List<Alumno> alumnos = 
			                (List<Alumno>) request.getAttribute("listado-alumnos");
		%>
			<!-- Enlace al Servlet para crear el alumno. -->
			<a href="<%= Constantes.SERVLET_ALUMNO %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_CREATE%>">
				Crear Alumno
			</a>
		
		<%
		/* Se recorre la lista de alumnos recogida del request.*/
		for(Alumno alumno:alumnos){
			/* Se imprime el alumno en la página.*/
			out.println("<p>" + alumno.toString() + "</p>");	
		}	
		%>
	</main>
</body>
</html>