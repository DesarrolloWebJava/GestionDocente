
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Gestior Docente - Pagina de Inicio</title>
</head>
<body>
	<h1>Gestior Docente</h1>
<header>
	<nav>
		<ul> 	<!-- PARA COMENTAR -->
			<li><a href="<%=Constantes.SERVLET_ALUMNO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ%>">G. Alumnos</a></li>
			<li><a href="<%=Constantes.SERVLET_PROFESOR%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ%>">G. Profesores</a></li>
			<li><a href="<%=Constantes.SERVLET_CURSO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ%>">G. Cursos</a></li>
		</ul>
	</nav>
	<jsp:include page="mensajes.jsp"/>
</header>