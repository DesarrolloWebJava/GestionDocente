<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Página Inicial</title>
</head>
<body>
	<h1>Gestior Docente</h1>
<header>
	<nav>
		<ul> 	<!-- PARA COMENTAR -->
			<li><a href="<%=Constantes.SERVLET_ALUMNO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READE%>">G. Alumnos</a></li>
			<li><a href="<%=Constantes.SERVLET_PROFESOR%>">G. Profesores</a></li>
			<li><a href="#">G. Cursos</a></li>
		</ul>
	</nav>
</header>
<main>
	Bienvenidos a Gestión Alumnos
</main>
<footer>
	Realizado por Ipartek S. Coop.
</footer>
</body>
</html>