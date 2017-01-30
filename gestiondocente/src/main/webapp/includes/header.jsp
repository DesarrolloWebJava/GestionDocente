<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Para los enlaces de los lenguajes -->
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'es_ES'}" scope="page" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmesages" />    
<!DOCTYPE html>
<html lang="${language}">
<head>
	<meta charset="UTF-8">
	<title>Gestior Docente - Pagina de Inicio</title>
	<link href="<c:url value="../css/styles.css" />" type="text/css" rel="stylesheet" /> <!-- Para untroducir la ruta -->
	<script src="<c:url value="/js/codigo.js" />"></script>
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
	<!-- Enlaces a los idiomas. y PONER LAS URL-->
	<a href="<%=Constantes.SERVLET_IDIOMA %>?<%=Constantes.PAR_IDIOMA%>=<%=Constantes.IDIOMA_CASTELLANO%>">Castellano</a>
	<a href="<%=Constantes.SERVLET_IDIOMA %>?<%=Constantes.PAR_IDIOMA%>=<%=Constantes.IDIOMA_EUSKERA%>">Euskera</a>
	<a href="<%=Constantes.SERVLET_IDIOMA %>?<%=Constantes.PAR_IDIOMA%>=<%=Constantes.IDIOMA_INGLES%>">Ingles</a>
	
	<!-- Boton CERRAR SESION if(si la sesion existe) -->
	<% 
	if(session != null && !session.isNew() && session.getAttribute(Constantes.SESSION_IDIOMA)!=null){
	%>
	<a href="<%=Constantes.SERVLET_LOGIN%>">Cerrar sesion</a>
	<%
	}
	%>
	
	<jsp:include page="mensajes.jsp"/>
</header>