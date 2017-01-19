<%@page import="com.ipartek.formacion.controler.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen"> 
<script src="js/bootstrap.min.js"></script>
<title>Página Inicial</title>
</head>
<body>
	<h1>Bienvenidos a Gestión Alumnos</h1>
<nav>
	<ul>
		<li>
			<a href="<%=Constantes.SERVLET_ALUMNO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ %>">Listado de Alumnos</a>
		</li>
		<li>
			<a href="<%=Constantes.SERVLET_PROFESOR%>">Listado de Profesores</a>
		</li>
		<li>
			<a href="#">Listado de Alumnos</a>
		</li>
	</ul>
</nav>

</body>
</html>