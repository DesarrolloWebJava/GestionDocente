<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Creamos la variable language -->
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'es_ES'}" scope="page" />
<!-- formatea el texto en el lenguaje que indica la variable -->
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmesages" />     
<!DOCTYPE html>
<html lang="${language}">
<head>
<meta charset="UTF-8">
<title>Pagína Inicial</title>
<!-- Introduce una hoja de estilos externa en la pagina -->
<link href="<c:url value="/css/styles.css" />" type="text/css" rel="stylesheet" />
<script src="<c:url value="/js/codigo.js" />"></script>
</head>
<body>
	<header>
		<h1>Gestor Docente</h1>
		<nav>
			<ul>
				<li><a href="<%=Constantes.SERVLET_ALUMNO %>?<%= Constantes.PAR_OPERACION %>=<%=Constantes.OP_READ %>">G. Alumnos</a></li>
				<li><a href="<%=Constantes.SERVLET_PROFESOR %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_READ %>">G. Profesores</a></li>
				<li><a href="<%=Constantes.SERVLET_CURSO %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_READ %>">G. Cursos</a></li>
			</ul>
		</nav>
		<!-- 
		<a href="<%=Constantes.SERVLET_IDIOMA %>?<%=Constantes.PAR_IDIOMA %>=<%=Constantes.IDIOMA_CASTELLANO %>">Castellano</a> 
		<a href="<%=Constantes.SERVLET_IDIOMA %>?<%=Constantes.PAR_IDIOMA %>=<%=Constantes.IDIOMA_EUSKERA %>">Euskera</a> 
		<a href="<%=Constantes.SERVLET_IDIOMA %>?<%=Constantes.PAR_IDIOMA %>=<%=Constantes.IDIOMA_INGLES %>">Ingles</a> 
		-->
		<%
		//RECOGEMOS LA SESION
		//3 condiciones:
		//Que la sesion es distinto de nulo
		//Si la sesion no se acaba de instanciar (en este archivo: header.jsp)
		//Si la constante sesion idioma no es nula
		if(session!=null&&!session.isNew()&&session.getAttribute(Constantes.SESSION_IDIOMA)!= null){
		%>
		<!-- Al pulsar el enlace se enlaza al doGet() del LoginServlet -->
		<a href="<%=Constantes.SERVLET_LOGIN %>">Cerrar Sesión </a>
		<%
		}
		%>
		<jsp:include page="mensajes.jsp"></jsp:include>
	</header>