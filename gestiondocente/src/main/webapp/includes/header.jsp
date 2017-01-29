<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Add jslt fmt 
<c:set var="language" value="${'eu_ES'}" scope="page" />
-->

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmessages" />

<!DOCTYPE html>
<html lang="${language}">
<head>
<meta charset="ISO-8859-1">
<title>Gestor Docente - Página de inicio</title>
<link href="<c:url value="/css/styles.css" />" type="text/css" rel="stylesheet" />
<script src="<c:url value="/js/codigo.js" />"></script>
<!-- si href="../css/styles.css" directamente: NO funciona
por la manera en que aquí se referencian las rutas. En un jsp las rutas 
las gestiona Tomcat (esa sintaxis en html o php SÍ funcionaría). 
RUTAS RELATIVAS EN JAVA ¡# NO #! FUNCIONAN -->
</head>
<body>
<header>
<h1>Gestor Docente Ipartek</h1>
	<nav>
<!-- marca que lo que hay dentro es un elemento de navegaci�n-->
		<ul>
		<!-- 
		Al hacer un enlace: poner SIEMPRE # 
		defecto, aunque luego lo sustituyamos. -->
		<!-- NOTA! Cuidado no copiar el path del servlet con "/" includa, tal y 
		como aparecen en el web.xml, porque aqu hara referencia 
		no al parent folder, sino al directorio raíz. 
		-->
			<li><a href="<%=Constantes.SERVLET_ALUMNO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ%>">G. Alumnos</a></li>
			<li><a href="<%=Constantes.SERVLET_PROFESOR%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ%>">G. Profesores</a></li>
			<li><a href="<%=Constantes.SERVLET_CURSO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ%>">G. Cursos</a></li>
		</ul>
	</nav>
	<a href="<%=Constantes.SERVLET_IDIOMA%>?<%=Constantes.PAR_IDIOMA%>=<%=Constantes.IDIOMA_CASTELLANO%>">Castellano</a>
	<a href="<%=Constantes.SERVLET_IDIOMA%>?<%=Constantes.PAR_IDIOMA%>=<%=Constantes.IDIOMA_EUSKERA%>">Euskera</a>
	<a href="<%=Constantes.SERVLET_IDIOMA%>?<%=Constantes.PAR_IDIOMA%>=<%=Constantes.IDIOMA_INGLES%>">Inglés</a>
	<jsp:include page="mensajes.jsp"/>
</header>
</body>
</html>