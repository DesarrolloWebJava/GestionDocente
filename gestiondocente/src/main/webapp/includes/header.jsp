<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="language" value="${'es_ES'}" scope="page" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmessages" />
<!DOCTYPE html>
<html lang="${language}">
<head>
<meta charset="UTF-8">
<title>Gestor Docente - Página de Inicio</title>
<link href="<c:url value="/css/styles.css"/>" type="text/css" rel="stylesheet"/>
<script src="<c:url value="/js/codigo.js"/>"></script>
<img src="images/ipartek.png"/>
</head>

<body>

<header>
	<h1><fmt:message key="index.nombreapp"/><!-- Gestor Docente --></h1>
	<nav>
		<ul>
			<li><a href="<%=Constantes.SERVLET_ALUMNO%>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_READ%>">G. Alumnos</a></li>
			<li><a href="<%=Constantes.SERVLET_PROFESOR%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ%>">G. Profesores</a></li>
			<li><a href="<%=Constantes.SERVLET_CURSO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ%>">G. Cursos</a></li> 
		</ul>
	</nav>
	<!--  
	<a href="<%=Constantes.SERVLET_IDIOMA%>?<%=Constantes.PAR_IDIOMA%>=<%=Constantes.IDIOMA_CASTELLANO%>">Castellano</a>
	<a href="<%=Constantes.SERVLET_IDIOMA%>?<%=Constantes.PAR_IDIOMA%>=<%=Constantes.IDIOMA_EUSKERA%>">Euskera</a> 
	<a href="<%=Constantes.SERVLET_IDIOMA%>?<%=Constantes.PAR_IDIOMA%>=<%=Constantes.IDIOMA_INGLES%>">Ingles</a> 
	-->
	<%
	
		if(session!=null&&session.isNew()&&session.getAttribute(Constantes.SESSION_IDIOMA)!=null){
	%>
	<a href="<%=Constantes.SERVLET_LOGIN%>">Cerrar Session</a>
	<%
		}
	%>
	<jsp:include page="mensajes.jsp"/>
</header>
	