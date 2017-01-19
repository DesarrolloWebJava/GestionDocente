<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Gestión Docente - Página Inicial</title>
	</head>
	<body>
		<header>
			<h1>Gestión Docente</h1>
			<nav>
				<ul>
					<li><a href="<%=Constantes.SERVLET_ALUMNO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ%>">G. Alumnos</a></li>
					<li><a href="<%=Constantes.SERVLET_PROFESOR%>">G. Profesores</a></li>
					<li><a href="#">G. Curso</a></li>
				</ul>
			</nav>
		</header>
		<main>
			Bienvenidos a Gestión Alumnos
		</main>
		<footer>
			Realizada por Enrique J. Ruiz
		</footer>
	</body>
</html>