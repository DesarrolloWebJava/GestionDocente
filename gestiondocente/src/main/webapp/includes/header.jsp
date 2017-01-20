<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Página Inicial</title>
</head>
<body>
<header>
	<h1>Gestion Docente</h1>
	<nav>
		<ul>
			<li><a href=" <%= Constantes.SERVLET_ALUMNO  %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_READ %>">G. Alumnos</a></li>
			<li><a href=" <%= Constantes.SERVLET_PROFESOR  %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_READ %>">G. Profesores</a></li>
			<li><a href="#">G. Cursos</a></li>
		</ul>
	</nav>
	<jsp:include page="mensajes.jsp"></jsp:include>
</header>