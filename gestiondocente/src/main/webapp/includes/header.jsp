<%@page import="com.ipartek.formacion.controler.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/style.css" rel="stylesheet" media="screen">  
<link rel="icon" href="images/favicon.ico">
<script src="js/bootstrap.min.js"></script>
<title>Inicio-Gestión</title>
</head>
<body>

<header>
<div id="header">
	<h1>Gestión Docente</h1>
</div>
	<nav class="navbar navbar-default">
		<ul class="nav navbar-nav">
			<li>
				<a href="<%=Constantes.SERVLET_ALUMNO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ %>" class="btn btn-info">Listado de Alumnos</a>
			</li>
			<li>
				<a href="<%=Constantes.SERVLET_PROFESOR%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ %>" class="btn btn-info">Listado de Profesores</a>
			</li>
			<li>
				<a href="#" class="btn btn-info">Listado de Cursos</a>
			</li>
		</ul>
	</nav>
	
	<jsp:include page="mensajes.jsp"/>
	
</header>
