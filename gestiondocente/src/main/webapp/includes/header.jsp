<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestor Docente - Página de Inicio</title>
<link href="<c:url value="/css/styles.css"/>" type="text/css" rel="stylesheet"/>
<script src="<c:url value="/js/codigo.js"/>"></script>
<img src="images/ipartek.png"/>
</head>

<body>

<header>
	<h1>Gestor Docente</h1>
	<nav>
		<ul>
			<li><a href="<%=Constantes.SERVLET_ALUMNO%>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_READ%>">G. Alumnos</a></li>
			<li><a href="<%=Constantes.SERVLET_PROFESOR%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ%>">G. Profesores</a></li>
			<li><a href="<%=Constantes.SERVLET_CURSO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ%>">G. Cursos</a></li> 
		</ul>
	</nav>
	<jsp:include page="mensajes.jsp"/>
</header>
	