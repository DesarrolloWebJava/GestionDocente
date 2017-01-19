<%@page import="com.ipartek.formacion.controler.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen"> 
<title>Página Inicial</title>
</head>
<body>
	<h1>Bienvenidos a Gestión Alumnos</h1>
<nav>
	<ul>
		<li>
			<a href="Alumno.do">Listado de Alumnos</a>
		</li>
		<li>
			<a href="<%=Constantes.SERVLET_PROFESOR%>">Listado de Profesores</a>
		</li>
		<li>
			<a href="#">Listado de Alumnoss</a>
		</li>
	</ul>
</nav>
</body>
</html>