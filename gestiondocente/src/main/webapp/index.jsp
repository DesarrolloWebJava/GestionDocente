<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestor Docente - Página de Inicio</title>
<img src="images/ipartek.png"/>
</head>
<body>

<header>
	<h1>Gestor Docente</h1>
	<nav>
		<ul>
			<li><a href=<%=Constantes.SERVLET_ALUMNO %>>G. Alumnos</a></li>
			<li><a href=<%=Constantes.SERVLET_PROFESOR%>>G. Profesores</a></li>
			<li><a href="#">G. Cursos</a></li>
		</ul>
	</nav>
	
</header>
<main>
	Bienvenidos a Gestión Alumnos
</main>
<footer>
	Realizado por Mikel Bruce
</footer>
</body>
</html>