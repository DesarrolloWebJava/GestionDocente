<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.ipartek.formacion.controler.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'es_ES'}" scope="page" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ipartek.formacion.controler.i18nmesages" /> 


<!DOCTYPE html>
<html lang="${language}">
<head>
<meta charset="UTF-8" >
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="<c:url value="css/bootstrap.min.css" />" rel="stylesheet" media="screen">

<link href="<c:url value="css/style.css" />" rel="stylesheet" media="screen">  
<link rel="icon" href="images/favicon.ico">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
 <!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<c:url value="js/bootstrap.min.js" />"></script>
<title>Inicio-Gestión</title>
</head>
<body>
<header>
<div id="header">
	<h1>Gestión Docente</h1>
</div>
<c:if test="${not empty sessionScope.usuario}">	
	
	<nav class="navbar navbar-default">
		<ul class="nav navbar-nav">
			<li>
				<a href="<%=Constantes.SERVLET_ALUMNO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ %>" class="btn btn-info">Listado de Alumnos</a>
			</li>
			<li>
				<a href="<%=Constantes.SERVLET_PROFESOR%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ %>" class="btn btn-info">Listado de Profesores</a>
			</li>
			<li>
				<a href="<%=Constantes.SERVLET_CURSO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ %>" class="btn btn-info">Listado de Cursos</a>
			</li>
			<li>
			 <a href="<%=Constantes.SERVLET_LOGIN%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_CERRAR_SESION %>" class="btn btn-warning btn-sm">Cerrar Sesión</a>
			</li>
			<li>
			 <a href="<%=Constantes.SERVLET_LOGIN%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_LISTAR_USUARIOS_SESION %>" class="btn  btn-info btn-sm">Usuarios conectados</a>
			</li>
		</ul>
	</nav>
	<jsp:include page="mensajes.jsp"/>
</c:if>	

</header>
